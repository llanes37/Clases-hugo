/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 5 (AVANZADO): CLASES Y OBJETOS — Múltiples clases interactuando
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  Prerrequisito: haber completado UT5_ClasesObjetos.java
 *
 *  En esta práctica aprenderás a:
 *
 *  ✅ Crear VARIAS clases que se relacionan entre sí.
 *  ✅ Usar un objeto DENTRO de otro objeto (composición).
 *  ✅ Constructores sobrecargados (varios constructores por clase).
 *  ✅ Métodos con lógica de negocio real (validaciones, cálculos).
 *  ✅ Métodos que reciben y devuelven OBJETOS.
 *  ✅ Arrays y listas de objetos.
 *  ✅ toString() para representar un objeto como texto.
 *
 *  🚀 TODO esto SIN herencia — solo clases, objetos y composición.
 * ******************************************************************************************
 */

import java.util.ArrayList;

// * ════════════════════════════════════════════════════════════════
// * 📖 CONCEPTO NUEVO: COMPOSICIÓN ("tiene un")
// * ════════════════════════════════════════════════════════════════
//
// ? Composición = un objeto CONTIENE otro objeto como atributo.
// ? Ejemplo: un Pedido TIENE un Cliente y una lista de Productos.
// ?
// ? Es diferente a herencia ("es un"):
// ?   - Composición: Coche TIENE un Motor      → Motor es atributo
// ?   - Herencia:    Coche ES UN Vehículo      → Coche extends Vehículo (UT5b)
// ?
// ? En este archivo usamos SOLO composición (sin herencia).

// ═══════════════════════════════════════════════════════════════
// * 🏪 PROGRAMA PRINCIPAL: TIENDA DE PRODUCTOS
// ═══════════════════════════════════════════════════════════════

public class UT5_ClasesObjetosAvanzado {

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   🏪 SISTEMA DE TIENDA — CLASES Y OBJETOS   ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        // * ──────────────────────────────────────────────
        // * 1. CREAR PRODUCTOS (clase Producto)
        // * ──────────────────────────────────────────────

        // ? Usamos el constructor completo
        Producto p1 = new Producto("Laptop Gaming", 999.99, 5);
        Producto p2 = new Producto("Ratón Inalámbrico", 29.99, 50);
        Producto p3 = new Producto("Teclado Mecánico", 89.99, 20);
        Producto p4 = new Producto("Monitor 27\"", 349.99, 8);

        // ? Usamos el constructor solo con nombre y precio (stock = 0)
        Producto p5 = new Producto("Auriculares Bluetooth", 59.99);

        System.out.println("\n📦 PRODUCTOS CREADOS:");
        System.out.println(p1); // ? Llama automáticamente a toString()
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);

        // * ──────────────────────────────────────────────
        // * 2. MÉTODOS DE PRODUCTO (lógica de negocio)
        // * ──────────────────────────────────────────────

        System.out.println("\n💰 OPERACIONES CON PRODUCTOS:");

        // ? Aplicar descuento
        p1.aplicarDescuento(10); // ? 10% de descuento
        System.out.println("  Laptop tras descuento: " + p1.getPrecioFormateado());

        // ? Añadir stock
        p5.agregarStock(15);
        System.out.println("  Auriculares ahora tienen " + p5.getStock() + " unidades");

        // ? Vender unidades
        p2.vender(3);
        System.out.println("  Ratón tras vender 3: " + p2.getStock() + " unidades");

        // ! Intentar vender más de lo que hay
        p4.vender(100); // ? Mostrará error — no hay tantos

        // * ──────────────────────────────────────────────
        // * 3. CREAR CLIENTES (clase Cliente)
        // * ──────────────────────────────────────────────

        System.out.println("\n👥 CLIENTES:");

        Cliente cliente1 = new Cliente("Ana García", "ana@email.com");
        Cliente cliente2 = new Cliente("Carlos López", "carlos@email.com", true); // ? VIP

        System.out.println(cliente1);
        System.out.println(cliente2);

        // * ──────────────────────────────────────────────
        // * 4. CREAR PEDIDOS (clase Pedido — usa composición)
        // * ──────────────────────────────────────────────

        // ? Un Pedido TIENE un Cliente y una lista de Productos
        // ? Esto es COMPOSICIÓN — un objeto dentro de otro

        System.out.println("\n🛒 CREANDO PEDIDOS:");

        Pedido pedido1 = new Pedido(cliente1);
        pedido1.agregarProducto(p1); // ? Laptop
        pedido1.agregarProducto(p2); // ? Ratón
        pedido1.agregarProducto(p3); // ? Teclado

        Pedido pedido2 = new Pedido(cliente2);
        pedido2.agregarProducto(p4); // ? Monitor
        pedido2.agregarProducto(p5); // ? Auriculares

        // * ──────────────────────────────────────────────
        // * 5. MOSTRAR RESUMEN DEL PEDIDO (método completo)
        // * ──────────────────────────────────────────────

        pedido1.mostrarResumen();
        pedido2.mostrarResumen(); // ? Carlos es VIP → tiene descuento

        // * ──────────────────────────────────────────────
        // * 6. COMPARAR PEDIDOS (método que recibe objeto)
        // * ──────────────────────────────────────────────

        System.out.println("\n⚖️ COMPARACIÓN:");
        pedido1.compararCon(pedido2);

        // * ──────────────────────────────────────────────
        // * 7. USAR TIENDA (clase que gestiona todo)
        // * ──────────────────────────────────────────────

        System.out.println("\n🏪 ESTADÍSTICAS DE LA TIENDA:");

        Tienda tienda = new Tienda("TechShop");
        tienda.registrarProducto(p1);
        tienda.registrarProducto(p2);
        tienda.registrarProducto(p3);
        tienda.registrarProducto(p4);
        tienda.registrarProducto(p5);

        tienda.mostrarInventario();
        tienda.mostrarProductoMasCaro();
        tienda.mostrarProductoMasBarato();
        tienda.mostrarValorTotal();

        // ! ✅ TAREA PARA EL ALUMNO:
        // * 1. Añade un atributo 'categoria' a Producto (Electrónica, Periféricos,
        // etc.).
        // * 2. Crea un método en Tienda que filtre productos por categoría.
        // * 3. Añade un método a Cliente que cuente cuántos pedidos ha hecho.
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📦 CLASE PRODUCTO
// ═══════════════════════════════════════════════════════════════
// ? Representa un producto de la tienda.
// ? Demuestra: atributos, constructores sobrecargados, validaciones,
// ? métodos de negocio y toString().

class Producto {
    // * Atributos privados (encapsulación)
    private String nombre;
    private double precio;
    private int stock;

    // * Constructor COMPLETO (3 parámetros)
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // * Constructor SOBRECARGADO (solo nombre y precio, stock = 0)
    // ? La sobrecarga permite crear objetos de formas diferentes
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0; // ? Valor por defecto
    }

    // * Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // * Getter especial: devuelve el precio formateado como String
    public String getPrecioFormateado() {
        return String.format("%.2f€", precio);
    }

    // * Setter con validación
    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("  ⚠️ El precio no puede ser negativo.");
        }
    }

    // * Método: aplicar descuento (porcentaje)
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = precio * (porcentaje / 100);
            precio -= descuento;
            System.out.println("  🏷️ " + nombre + ": -" + porcentaje + "% → ahora " + getPrecioFormateado());
        } else {
            System.out.println("  ⚠️ Porcentaje debe estar entre 1 y 100.");
        }
    }

    // * Método: agregar stock
    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
        }
    }

    // * Método: vender unidades (con validación de stock)
    public boolean vender(int cantidad) {
        if (cantidad > stock) {
            System.out.println("  ❌ " + nombre + ": no hay suficiente stock (" + stock + " disponibles)");
            return false; // ? Devuelve false si no se pudo vender
        }
        stock -= cantidad;
        return true; // ? Devuelve true si se vendió correctamente
    }

    // * Método: verificar si hay stock
    public boolean hayStock() {
        return stock > 0;
    }

    // * toString(): representación del objeto como texto
    // ? Se llama automáticamente cuando haces System.out.println(producto)
    @Override
    public String toString() {
        String estado = hayStock() ? "✅ En stock (" + stock + ")" : "❌ Agotado";
        return "  📦 " + nombre + " — " + getPrecioFormateado() + " — " + estado;
    }
}

// ═══════════════════════════════════════════════════════════════
// * 👤 CLASE CLIENTE
// ═══════════════════════════════════════════════════════════════
// ? Representa un cliente de la tienda.
// ? Demuestra: constructores sobrecargados y atributo booleano.

class Cliente {
    private String nombre;
    private String email;
    private boolean esVIP;

    // * Constructor normal (no VIP por defecto)
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.esVIP = false;
    }

    // * Constructor sobrecargado (permite indicar si es VIP)
    public Cliente(String nombre, String email, boolean esVIP) {
        this.nombre = nombre;
        this.email = email;
        this.esVIP = esVIP;
    }

    // * Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean esVIP() {
        return esVIP;
    }

    // * Método: obtener el porcentaje de descuento por ser VIP
    public double getDescuentoVIP() {
        return esVIP ? 15.0 : 0.0; // ? VIP = 15% descuento
    }

    @Override
    public String toString() {
        String tipo = esVIP ? "⭐ VIP" : "👤 Normal";
        return "  " + tipo + " — " + nombre + " (" + email + ")";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🛒 CLASE PEDIDO (usa COMPOSICIÓN: tiene Cliente + lista de Productos)
// ═══════════════════════════════════════════════════════════════
// ? Un Pedido CONTIENE un Cliente y una lista de Productos.
// ? Esto es composición: "un Pedido TIENE un Cliente".
// ? Demuestra: objetos como atributos, ArrayList de objetos,
// ? métodos que calculan sobre la lista.

class Pedido {
    // * Atributos: un Cliente y una lista de Productos
    private Cliente cliente; // ? Composición: objeto dentro de objeto
    private ArrayList<Producto> productos; // ? Lista de objetos
    private static int contadorPedidos = 0; // ? Atributo STATIC: compartido por todos los pedidos
    private int numeroPedido;

    // * Constructor: recibe el cliente
    public Pedido(Cliente cliente) {
        contadorPedidos++; // ? Se incrementa cada vez que se crea un pedido
        this.numeroPedido = contadorPedidos;
        this.cliente = cliente;
        this.productos = new ArrayList<>(); // ? Inicializar lista vacía
    }

    // * Método: agregar producto al pedido
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("  ✅ Añadido '" + producto.getNombre() + "' al pedido #" + numeroPedido);
    }

    // * Método: calcular total del pedido
    public double calcularTotal() {
        double total = 0;
        // ? Recorremos la lista de productos sumando precios
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    // * Método: calcular total CON descuento VIP
    public double calcularTotalConDescuento() {
        double total = calcularTotal();
        double descuento = cliente.getDescuentoVIP(); // ? Usa método del objeto Cliente
        if (descuento > 0) {
            total -= total * (descuento / 100);
        }
        return total;
    }

    // * Método: mostrar resumen completo del pedido
    public void mostrarResumen() {
        System.out.println("\n  ┌──────────────────────────────────────────────┐");
        System.out.println("  │ 🛒 PEDIDO #" + numeroPedido);
        System.out.println("  ├──────────────────────────────────────────────┤");
        System.out.println("  │ 👤 Cliente: " + cliente.getNombre());
        System.out.println("  │ 📧 Email:   " + cliente.getEmail());
        System.out.println("  │ ⭐ VIP:     " + (cliente.esVIP() ? "Sí (-15%)" : "No"));
        System.out.println("  ├──────────────────────────────────────────────┤");

        // ? Recorrer productos del pedido
        for (Producto p : productos) {
            System.out.println("  │   📦 " + p.getNombre() + " — " + p.getPrecioFormateado());
        }

        System.out.println("  ├──────────────────────────────────────────────┤");
        System.out.println("  │ 💰 Subtotal:  " + String.format("%.2f€", calcularTotal()));

        if (cliente.esVIP()) {
            System.out.println("  │ 🏷️ Descuento: -" + cliente.getDescuentoVIP() + "%");
        }

        System.out.println("  │ 💳 TOTAL:     " + String.format("%.2f€", calcularTotalConDescuento()));
        System.out.println("  └──────────────────────────────────────────────┘");
    }

    // * Método: comparar CON otro pedido (recibe un objeto Pedido)
    public void compararCon(Pedido otro) {
        double miTotal = this.calcularTotalConDescuento();
        double otroTotal = otro.calcularTotalConDescuento();

        System.out.println("  Pedido #" + this.numeroPedido + " (" + this.cliente.getNombre() + "): "
                + String.format("%.2f€", miTotal));
        System.out.println("  Pedido #" + otro.numeroPedido + " (" + otro.cliente.getNombre() + "): "
                + String.format("%.2f€", otroTotal));

        if (miTotal > otroTotal) {
            System.out.println("  ➡️ El pedido #" + this.numeroPedido + " es más caro.");
        } else if (miTotal < otroTotal) {
            System.out.println("  ➡️ El pedido #" + otro.numeroPedido + " es más caro.");
        } else {
            System.out.println("  ➡️ Ambos pedidos cuestan lo mismo.");
        }
    }

    // * Getters
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public int getCantidadProductos() {
        return productos.size();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🏪 CLASE TIENDA (gestiona una lista de productos)
// ═══════════════════════════════════════════════════════════════
// ? Demuestra: una clase que gestiona un ArrayList de objetos,
// ? con métodos de búsqueda y cálculo sobre la lista.

class Tienda {
    private String nombre;
    private ArrayList<Producto> inventario;

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArrayList<>();
    }

    // * Registrar un producto en el inventario
    public void registrarProducto(Producto producto) {
        inventario.add(producto);
    }

    // * Mostrar todo el inventario
    public void mostrarInventario() {
        System.out.println("\n  📋 Inventario de " + nombre + " (" + inventario.size() + " productos):");
        for (Producto p : inventario) {
            System.out.println("    " + p);
        }
    }

    // * Buscar el producto más caro (recorre y compara)
    public void mostrarProductoMasCaro() {
        if (inventario.isEmpty())
            return;

        Producto masCaro = inventario.get(0); // ? Empezamos con el primero
        for (Producto p : inventario) {
            if (p.getPrecio() > masCaro.getPrecio()) {
                masCaro = p; // ? Si encontramos uno más caro, lo guardamos
            }
        }
        System.out.println("  📈 Más caro: " + masCaro.getNombre() + " — " + masCaro.getPrecioFormateado());
    }

    // * Buscar el producto más barato
    public void mostrarProductoMasBarato() {
        if (inventario.isEmpty())
            return;

        Producto masBarato = inventario.get(0);
        for (Producto p : inventario) {
            if (p.getPrecio() < masBarato.getPrecio()) {
                masBarato = p;
            }
        }
        System.out.println("  📉 Más barato: " + masBarato.getNombre() + " — " + masBarato.getPrecioFormateado());
    }

    // * Calcular valor total del inventario (precio × stock de cada uno)
    public void mostrarValorTotal() {
        double total = 0;
        for (Producto p : inventario) {
            total += p.getPrecio() * p.getStock();
        }
        System.out.println("  💰 Valor total del inventario: " + String.format("%.2f€", total));
    }
}
