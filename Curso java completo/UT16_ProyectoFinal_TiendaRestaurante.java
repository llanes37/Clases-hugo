/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 21: PROYECTO FINAL - SISTEMA TIENDA/RESTAURANTE (CONSOLA)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

/*
 * ******************************************************************************************
 * 🧠 OBJETIVO DEL PROYECTO (ESTRUCTURA "BETTER COMMENTS" + TEORÍA EN AZUL)
 * ──────────────────────────────────────────────────────────────
 * ✅ Construir una app de consola para gestionar una tienda/restaurante realista.
 * ✅ Aplicar: clases, herencia básica (enums), composición, colecciones, excepciones, E/S de ficheros.
 * ✅ Módulos: Productos, Pedidos y Persistencia simple (CSV/TXT).
 * ✅ Menú interactivo con validaciones y feedback claro.
 *
 * � TEORÍA CLAVE (POO, COLECCIONES, ENCAPSULACIÓN, PERSISTENCIA):
 * - Encapsulación: atributos privados + getters/setters con validación.
 * - Composición: Pedido contiene múltiples OrderItem; el Inventario contiene Productos.
 * - Enums: categoría de producto y estado del pedido (evita magic strings).
 * - Persistencia: CSV para productos y TXT (tipo ticket) para pedidos.
 * - Manejo de errores: try/catch, InputMismatch, IOException.
 *
 * 🔎 Resumen de conceptos clave:
 * - Buenas prácticas de diseño y separación de responsabilidades.
 * - Ejemplo realista y ampliable para el mundo laboral.
 *
 * 🧪 Recomendación didáctica:
 * - Compila y ejecuta este archivo por separado para evitar colisiones de nombres con otras UTs.
 * - Trabaja iterativamente: prueba cada opción del menú tras modificarla.
 * - Lee los bloques de teoría (🔷) y resuelve los retos (🚩) para dominar cada parte.
 ******************************************************************************************
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

enum EstadoPedido { NUEVO, PAGADO, CANCELADO, ENTREGADO }
// * =========================================================================================
// * 🔷 TEORÍA: ENUMS EN JAVA
// * -----------------------------------------------------------------------------------------
// ? Los enums evitan "magic strings" y mejoran la legibilidad y seguridad.
// ? Ejemplo: Categoria.BEBIDA, EstadoPedido.PAGADO
// ! 🚩 RETO: Añade más categorías o estados según tu contexto real.
// * =========================================================================================
enum Categoria { BEBIDA, COMIDA, POSTRE, OTROS }
enum EstadoPedido { NUEVO, PAGADO, CANCELADO, ENTREGADO }

// * =========================================================================================
// * 🔷 TEORÍA: UTILIDADES DE FICHEROS EN JAVA
// * -----------------------------------------------------------------------------------------
// * Leer y escribir archivos es esencial para la persistencia de datos.
// * - writeAll: sobrescribe el archivo con todas las líneas.
// * - appendLine: añade una línea al final (útil para tickets o logs).
// * - readAll: lee todas las líneas si existe el archivo.
// ! 🚩 RETO: Añade métodos para importar/exportar en otros formatos (JSON, XML).
// * =========================================================================================
class FileUtil {
    // * Escribe todas las líneas en un archivo (sobrescribe)
    public static void writeAll(String nombre, List<String> lineas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            for (String l : lineas) { bw.write(l); bw.newLine(); }
        }
    }

    // * Añade una línea al final de un archivo (append)
    public static void appendLine(String nombre, String linea) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre, true))) {
            bw.write(linea); bw.newLine();
        }
    }

    // * Lee todas las líneas de un archivo si existe; en caso contrario, lista vacía
    public static List<String> readAll(String nombre) throws IOException {
        List<String> res = new ArrayList<>();
        File f = new File(nombre);
        if (!f.exists()) return res;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea; while ((linea = br.readLine()) != null) res.add(linea);
        }
        return res;
    }
}

// * =========================================================================================
// * 🔷 TEORÍA: CLASE PRODUCTO (MODELO DE DATOS)
// * -----------------------------------------------------------------------------------------
// ? Un Producto tiene:
// * - id único (clave primaria)
// * - nombre, categoría, precio, stock
// ? Buenas prácticas:
// * - Encapsula atributos (private)
// * - Valida en setters y constructores
// * - Usa métodos para conversión a CSV y desde CSV
// ! 🚩 RETO: Añade atributos como descripción, fecha de alta, etc.
// * =========================================================================================
class Producto {
    private int id;                 // Identificador interno
    private String nombre;          // Nombre del producto
    private Categoria categoria;    // Enum de categoría
    private double precio;          // Precio unitario
    private int stock;              // Unidades disponibles

    public Producto(int id, String nombre, Categoria categoria, double precio, int stock) {
        setId(id);
        setNombre(nombre);
        setCategoria(categoria);
        setPrecio(precio);
        setStock(stock);
    }

    // * Getters/Setters con validación básica
    public int getId() { return id; }
    public void setId(int id) { if (id < 0) throw new IllegalArgumentException("ID inválido"); this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        this.nombre = nombre.trim();
    }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = (categoria != null) ? categoria : Categoria.OTROS; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { if (precio < 0) throw new IllegalArgumentException("Precio inválido"); this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { if (stock < 0) throw new IllegalArgumentException("Stock inválido"); this.stock = stock; }

    // * Conversión a CSV y desde CSV (para persistir inventario)
    public String toCsv() {
        return String.format(Locale.US, "%d;%s;%s;%.2f;%d", id, nombre.replace(';', ','), categoria.name(), precio, stock);
    }

    public static Producto fromCsv(String csv) {
        String[] p = csv.split(";");
        if (p.length != 5) throw new IllegalArgumentException("Línea CSV inválida: " + csv);
        int id = Integer.parseInt(p[0]);
        String nombre = p[1];
        Categoria cat = Categoria.valueOf(p[2]);
        double precio = Double.parseDouble(p[3]);
        int stock = Integer.parseInt(p[4]);
        return new Producto(id, nombre, cat, precio, stock);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "#%d | %-12s | %-7s | %.2f€ | stock: %d", id, nombre, categoria, precio, stock);
    }
}

// * =========================================================================================
// * 🔷 TEORÍA: CLASE PEDIDO Y OrderItem (COMPOSICIÓN)
// * -----------------------------------------------------------------------------------------
// ? Un Pedido tiene:
// * - id único, cliente, fecha, estado
// * - lista de OrderItem (cada uno asocia producto y cantidad)
// ? Buenas prácticas:
// * - Composición: Pedido contiene OrderItem
// * - Métodos para calcular total, mostrar resumen, serializar ticket
// ! 🚩 RETO: Añade métodos para aplicar descuentos, IVA, o exportar a CSV
// * =========================================================================================
class OrderItem {
    private final int productoId;
    private final String nombre;
    private final double precioUnit;
    private final int cantidad;

    public OrderItem(Producto p, int cantidad) {
        this.productoId = p.getId();
        this.nombre = p.getNombre();
        this.precioUnit = p.getPrecio();
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
        this.cantidad = cantidad;
    }

    public int getProductoId() { return productoId; }
    public String getNombre() { return nombre; }
    public double getPrecioUnit() { return precioUnit; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return precioUnit * cantidad; }

    @Override
    public String toString() {
        return String.format(Locale.US, "%dx %-12s @ %.2f€ = %.2f€", cantidad, nombre, precioUnit, getSubtotal());
    }
}

class Pedido {
    private final int id;
    private final String cliente;
    private final LocalDateTime fecha;
    private EstadoPedido estado;
    private final List<OrderItem> items = new ArrayList<>();

    public Pedido(int id, String cliente) {
        this.id = id;
        this.cliente = (cliente == null || cliente.isBlank()) ? "SIN NOMBRE" : cliente.trim();
        this.fecha = LocalDateTime.now();
        this.estado = EstadoPedido.NUEVO;
    }

    public void agregarItem(OrderItem item) { items.add(item); }
    public int getId() { return id; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido e) { this.estado = e; }

    public double total() {
        double t = 0.0; for (OrderItem it : items) t += it.getSubtotal(); return t;
    }

    public String resumen() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        sb.append("\n🧾 PEDIDO #").append(id).append("  ").append(fecha.format(fmt)).append("  Cliente: ").append(cliente).append("\n");
        for (OrderItem it : items) sb.append("   • ").append(it).append("\n");
        sb.append(String.format(Locale.US, "   Total: %.2f€\n", total()));
        sb.append("   Estado: ").append(estado).append("\n");
        return sb.toString();
    }

    // * Serialización simple para guardar en TXT (tipo ticket)
    public String toTicket() { return resumen(); }
}

// * =========================================================================================
// * 🔷 TEORÍA: GESTOR DE INVENTARIO (SERVICIO)
// * -----------------------------------------------------------------------------------------
// ? Centraliza la lógica de negocio:
// * - CRUD de productos
// * - Persistencia en CSV
// * - Búsqueda, validación, informes
// ? Buenas prácticas:
// * - Métodos claros y atómicos
// * - Manejo de errores robusto
// ! 🚩 RETO: Añade métodos para buscar por nombre, categoría, etc.
// * =========================================================================================
class InventoryManager {
    private final List<Producto> productos = new ArrayList<>();
    private final String archivoProductos = "productos.csv";

    public InventoryManager() { cargarDesdeCsv(); }

    public List<Producto> listar() { return productos; }

    public void agregar(Producto p) { productos.add(p); }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) if (p.getId() == id) return p; return null;
    }

    public boolean eliminar(int id) {
        Producto p = buscarPorId(id); return (p != null) && productos.remove(p);
    }

    public void actualizarPrecio(int id, double nuevoPrecio) {
        Producto p = buscarPorId(id); if (p != null) p.setPrecio(nuevoPrecio);
    }

    public void actualizarStock(int id, int nuevoStock) {
        Producto p = buscarPorId(id); if (p != null) p.setStock(nuevoStock);
    }

    // * Persistencia: CSV
    public void guardarEnCsv() {
        List<String> lineas = new ArrayList<>();
        for (Producto p : productos) lineas.add(p.toCsv());
        try { FileUtil.writeAll(archivoProductos, lineas); System.out.println("💾 Inventario guardado en " + archivoProductos); }
        catch (IOException e) { System.out.println("❌ Error guardando CSV: " + e.getMessage()); }
    }

    public void cargarDesdeCsv() {
        try {
            List<String> lineas = FileUtil.readAll(archivoProductos);
            productos.clear();
            for (String l : lineas) {
                try { productos.add(Producto.fromCsv(l)); }
                catch (Exception ex) { System.out.println("⚠️ Línea inválida en CSV: " + l); }
            }
            if (!lineas.isEmpty()) System.out.println("📥 Inventario cargado desde " + archivoProductos + " (" + productos.size() + " productos)");
        } catch (IOException e) {
            // ? Archivo puede no existir en primera ejecución: no es un error crítico
        }

        // ! 🚩 RETO: Añade un método buscarPorNombre(String) que devuelva una lista con coincidencias parciales.
        // ! 🚩 RETO: Mejora la validación para impedir nombres duplicados.
    }
}

// * =========================================================================================
// * 🔷 TEORÍA: GESTOR DE PEDIDOS (SERVICIO)
// * -----------------------------------------------------------------------------------------
// ? Centraliza la lógica de negocio:
// * - Crear pedidos, guardar tickets
// * - Persistencia en TXT (tipo ticket)
// * - Listar pedidos
// ? Buenas prácticas:
// * - Métodos claros y atómicos
// * - Manejo de errores robusto
// ! 🚩 RETO: Implementa carga de pedidos desde archivo, cálculo de IVA, etc.
// * =========================================================================================
class OrderManager {
    private final List<Pedido> pedidos = new ArrayList<>();
    private final String archivoPedidos = "pedidos.txt";
    private int secuenciaId = 1;

    public Pedido crearPedido(String cliente) {
        Pedido p = new Pedido(secuenciaId++, cliente);
        pedidos.add(p);
        return p;
    }

    public void guardarTicket(Pedido p) {
        try { FileUtil.appendLine(archivoPedidos, p.toTicket()); System.out.println("🧾 Ticket guardado en " + archivoPedidos); }
        catch (IOException e) { System.out.println("❌ Error guardando ticket: " + e.getMessage()); }
    }

    public List<Pedido> listar() { return pedidos; }

    // ! 🚩 RETO: Implementa la carga de pedidos desde archivo (parseando el formato de ticket) o cámbialo a CSV.
    // ! 🚩 RETO: Añade cálculo de IVA (por ejemplo 10%) y total con impuestos.
}

// * =========================================================================================
// * 🔷 TEORÍA: FLUJO PRINCIPAL DE LA APP (IDEAL PARA EXPLICAR EN CLASE)
// * -----------------------------------------------------------------------------------------
// ? 1. Carga datos de ejemplo si es la primera vez
// ? 2. Muestra menú principal
// ? 3. Permite gestionar productos y pedidos
// ? 4. Guarda datos antes de salir
// ? Buenas prácticas:
// * - Separar lógica de presentación y negocio
// * - Validar entradas y manejar errores
// ! 🚩 RETO: Añade autenticación, backup automático, o informes avanzados
// * =========================================================================================
public class UT21_ProyectoFinal_TiendaRestaurante {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryManager inventario = new InventoryManager();
        OrderManager orderMgr = new OrderManager();

        precargarSiVacio(inventario); // Carga datos de ejemplo si inventario vacío

        int opcion = -1;
        while (opcion != 0) {
            try {
                mostrarMenuPrincipal();
                opcion = sc.nextInt(); sc.nextLine();
                switch (opcion) {
                    case 1 -> menuProductos(sc, inventario);
                    case 2 -> crearPedido(sc, inventario, orderMgr);
                    case 3 -> listarProductos(inventario);
                    case 4 -> inventario.guardarEnCsv();
                    case 0 -> System.out.println("👋 ¡Gracias por usar el sistema!");
                    default -> System.out.println("⚠️ Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Debes ingresar un número.");
                sc.nextLine(); // limpiar buffer
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
        sc.close();

        // ! 🚩 RETO: Añade persistencia de clientes en clientes.csv y relación con los pedidos.
        // ! 🚩 RETO: Implementa actualización de estado del pedido (PAGADO/ENTREGADO) desde el menú.
        // ! 🚩 RETO: Exporta un reporte diario con totales y número de pedidos.
    }

    // * =========================================================================================
    // * 🔷 MENÚ PRINCIPAL (EXPLICACIÓN EN CLASE: FLUJO DE OPCIONES)
    // * -----------------------------------------------------------------------------------------
    // ? 1. Gestionar productos (añadir, eliminar, listar, actualizar)
    // * 2. Crear pedido (carrito)
    // ? 3. Listar productos
    // ? 4. Guardar inventario
    // ? 0. Salir
    // * =========================================================================================
    private static void mostrarMenuPrincipal() {
        System.out.println("\n🛒 MENÚ PRINCIPAL - TIENDA/RESTAURANTE");
        System.out.println("1. Gestionar Productos (CRUD)");
        System.out.println("2. Crear Pedido (carrito)");
        System.out.println("3. Listar Productos");
        System.out.println("4. Guardar Inventario en CSV");
        System.out.println("0. Salir");
        System.out.print("👉 Opción: ");
    }

    // * =========================================================================================
    // ? 🔷 SUBMENÚ DE PRODUCTOS (CRUD)
    // * -----------------------------------------------------------------------------------------
    // ? 1. Agregar producto
    // ? 2. Actualizar precio
    // ? 3. Actualizar stock
    // ? 4. Eliminar producto
    // ? 5. Listar productos
    // ? 0. Volver
    // ! 🚩 RETO: Añade opción para buscar por nombre o categoría
    // * =========================================================================================
    private static void menuProductos(Scanner sc, InventoryManager inventario) {
        int op;
        do {
            System.out.println("\n📦 GESTIÓN DE PRODUCTOS");
            System.out.println("1. Agregar producto");
            System.out.println("2. Actualizar precio");
            System.out.println("3. Actualizar stock");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Listar productos");
            System.out.println("0. Volver");
            System.out.print("👉 Opción: ");
            op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> agregarProducto(sc, inventario);
                case 2 -> actualizarPrecio(sc, inventario);
                case 3 -> actualizarStock(sc, inventario);
                case 4 -> eliminarProducto(sc, inventario);
                case 5 -> listarProductos(inventario);
                case 0 -> System.out.println("↩️ Volviendo al menú principal...");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (op != 0);

        // ! ✅ TAREA (Alumno):
        // * Añade opción para importar inventario desde CSV (sobrescribir o fusionar).
        // * Agrega opción para buscar por categoría o por nombre.
    }

    // * =========================================================================================
    // * 🔷 OPERACIONES CRUD DE PRODUCTOS (EXPLICACIÓN EN CLASE)
    // * -----------------------------------------------------------------------------------------
    // ? Métodos para agregar, actualizar, eliminar y listar productos
    // ? Buenas prácticas: validar datos, feedback claro
    // * =========================================================================================
    private static void agregarProducto(Scanner sc, InventoryManager inv) {
        try {
            System.out.print("🆔 ID: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("🏷️ Nombre: "); String nombre = sc.nextLine();
            System.out.print("📂 Categoría (BEBIDA, COMIDA, POSTRE, OTROS): ");
            Categoria cat = Categoria.valueOf(sc.nextLine().trim().toUpperCase());
            System.out.print("💶 Precio: "); double precio = sc.nextDouble();
            System.out.print("📦 Stock: "); int stock = sc.nextInt(); sc.nextLine();
            inv.agregar(new Producto(id, nombre, cat, precio, stock));
            System.out.println("✅ Producto agregado.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Datos inválidos: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("❌ Formato incorrecto."); sc.nextLine();
        }
    }

    private static void actualizarPrecio(Scanner sc, InventoryManager inv) {
        System.out.print("🆔 ID del producto: "); int id = sc.nextInt();
        System.out.print("💶 Nuevo precio: "); double precio = sc.nextDouble(); sc.nextLine();
        inv.actualizarPrecio(id, precio); System.out.println("✅ Precio actualizado.");
    }

    private static void actualizarStock(Scanner sc, InventoryManager inv) {
        System.out.print("🆔 ID del producto: "); int id = sc.nextInt();
        System.out.print("📦 Nuevo stock: "); int stock = sc.nextInt(); sc.nextLine();
        inv.actualizarStock(id, stock); System.out.println("✅ Stock actualizado.");
    }

    private static void eliminarProducto(Scanner sc, InventoryManager inv) {
        System.out.print("🆔 ID del producto a eliminar: "); int id = sc.nextInt(); sc.nextLine();
        if (inv.eliminar(id)) System.out.println("🗑️ Producto eliminado."); else System.out.println("❌ No encontrado.");
    }

    private static void listarProductos(InventoryManager inv) {
        System.out.println("\n📃 LISTA DE PRODUCTOS:");
        if (inv.listar().isEmpty()) System.out.println("(vacío)");
        for (Producto p : inv.listar()) System.out.println(" - " + p);
    }

    // * =========================================================================================
    // * 🔷 CREAR PEDIDO (EXPLICACIÓN EN CLASE)
    // * -----------------------------------------------------------------------------------------
    // ? 1. Pide nombre del cliente
    // ? 2. Permite añadir productos al carrito
    // ? 3. Valida stock y cantidades
    // ? 4. Guarda ticket y actualiza inventario
    // ! 🚩 RETO: Aplica descuentos, códigos promocionales, o calcula IVA
    // * =========================================================================================
    private static void crearPedido(Scanner sc, InventoryManager inv, OrderManager orderMgr) {
        if (inv.listar().isEmpty()) { System.out.println("⚠️ No hay productos en inventario."); return; }
        System.out.print("👤 Nombre del cliente: "); String cliente = sc.nextLine();
        Pedido pedido = orderMgr.crearPedido(cliente);

        boolean agregando = true;
        while (agregando) {
            listarProductos(inv);
            System.out.print("🆔 ID producto (0 para terminar): "); int id = sc.nextInt(); sc.nextLine();
            if (id == 0) break;
            Producto p = inv.buscarPorId(id);
            if (p == null) { System.out.println("❌ Producto no encontrado."); continue; }
            System.out.print("🔢 Cantidad: "); int cant = sc.nextInt(); sc.nextLine();
            if (cant <= 0) { System.out.println("❌ Cantidad inválida."); continue; }
            if (cant > p.getStock()) { System.out.println("❌ Stock insuficiente."); continue; }
            pedido.agregarItem(new OrderItem(p, cant));
            p.setStock(p.getStock() - cant); // Reducimos stock
            System.out.println("➕ Añadido: " + cant + " x " + p.getNombre());
        }

        System.out.println(pedido.resumen());
        System.out.print("💳 Marcar como PAGADO? (s/n): "); String resp = sc.nextLine().trim().toLowerCase();
        if (resp.equals("s")) pedido.setEstado(EstadoPedido.PAGADO);
        orderMgr.guardarTicket(pedido);
        inv.guardarEnCsv(); // Persistimos inventario tras el pedido

        // ! ✅ TAREA (Alumno):
        // * Aplica un descuento del 10% si el total supera cierta cantidad.
        // * Implementa códigos promocionales leídos desde un archivo descuentos.csv.
    }

    // * =========================================================================================
    // * 🔷 PRE-CARGA DE DATOS PARA DEMOS
    // * -----------------------------------------------------------------------------------------
    // ? Añade productos de ejemplo si el inventario está vacío
    // * =========================================================================================
    private static void precargarSiVacio(InventoryManager inv) {
        if (!inv.listar().isEmpty()) return;
        inv.agregar(new Producto(1, "Café", Categoria.BEBIDA, 1.50, 50));
        inv.agregar(new Producto(2, "Sandwich", Categoria.COMIDA, 3.80, 30));
        inv.agregar(new Producto(3, "Tarta", Categoria.POSTRE, 2.90, 20));
        inv.agregar(new Producto(4, "Agua", Categoria.BEBIDA, 1.00, 100));
        System.out.println("📦 Inventario de ejemplo cargado.");
    }
}

/*
 * ******************************************************************************************
 * ✅ TAREAS PARA EL ALUMNO (AVANZADAS)
 * ──────────────────────────────────────────────────────────────
 * 1️⃣ Añade persistencia de pedidos en CSV (id;fecha;cliente;estado;items JSON) y función para cargar.
 * 2️⃣ Implementa búsqueda de productos por texto (contiene) y por categoría en el menú.
 * 3️⃣ Añade cálculo de IVA (10%) en el ticket y muestra Total + IVA.
 * 4️⃣ Evita IDs duplicados al crear productos (auto-incremento o validación).
 * 5️⃣ Crea un reporte diario (reporte_YYYYMMDD.txt) con total de ventas y unidades vendidas.
 * 6️⃣ Refactoriza el código separando en paquetes: modelo, servicio, util, app.
 * 7️⃣ Añade pruebas unitarias simples (si usas Maven/Gradle) para fromCsv() y total del pedido.
 * 8️⃣ Implementa un backup automático del inventario al cerrar el programa.
 * 9️⃣ Soporta múltiples monedas usando Locale o un formato configurable.
 * 🔟 Agrega autenticación sencilla (PIN) para entrar en el menú de gestión de productos.
 *
 * 🧩 EXTRA (OPCIONAL): Añade un sistema de mesas (restaurante) o carritos múltiples (tienda),
 * asignando pedidos a una mesa o a un carrito por cliente.
 ******************************************************************************************
 */
