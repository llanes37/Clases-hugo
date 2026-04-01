/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 13b: GENÉRICOS EN JAVA (<T>, Wildcards, Clases y Métodos Genéricos)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

import java.util.*;

public class UT13b_Genericos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // * MENÚ PRINCIPAL — ELIGE QUÉ SECCIÓN EJECUTAR
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║  📦 UT13b — GENÉRICOS EN JAVA                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.println("1. 📖 ¿Qué son los Genéricos? (Sin genéricos vs con)");
            System.out.println("2. 🧩 Clase genérica propia (Caja<T>)");
            System.out.println("3. 🛠️  Método genérico (imprimir cualquier tipo)");
            System.out.println("4. 🔒 Bounded Type (<T extends Comparable>)");
            System.out.println("5. ❓ Wildcards (?, extends, super)");
            System.out.println("6. 📦 Par genérico (Par<K,V> con dos tipos)");
            System.out.println("7. 🎯 Interfaz genérica (Repositorio<T>)");
            System.out.println("0. 👋 Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> sinGenericosVsCon();
                case 2 -> claseGenericaCaja();
                case 3 -> metodoGenerico();
                case 4 -> boundedType();
                case 5 -> wildcards();
                case 6 -> parGenerico();
                case 7 -> interfazGenerica();
                case 0 -> System.out.println("👋 ¡Hasta luego!");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📖 SECCIÓN 1: ¿QUÉ SON LOS GENÉRICOS?
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Genéricos
    // ──────────────────────────────────────────────────────────────
    // ? Los genéricos permiten crear clases, interfaces y métodos
    // ? que trabajan con CUALQUIER tipo de dato, sin perder seguridad de tipos.
    // ?
    // ? Sin genéricos: usamos Object → hay que hacer casting → posibles errores en
    // runtime
    // ? Con genéricos: usamos <T> → el compilador verifica los tipos → errores en
    // compile time
    // !
    // ! Ventajas:
    // ! 1. Seguridad de tipos en tiempo de compilación
    // ! 2. No necesitas hacer casting manual
    // ! 3. Código reutilizable para cualquier tipo

    public static void sinGenericosVsCon() {
        System.out.println("\n🔹 SECCIÓN 1: ¿Qué son los genéricos?\n");

        // ! ❌ SIN genéricos — peligro de ClassCastException
        System.out.println("❌ SIN genéricos (Object):");
        List sinGenericos = new ArrayList(); // ? Raw type — acepta cualquier cosa
        sinGenericos.add("Hola");
        sinGenericos.add(42); // ! Mezcla String e Integer — ¡peligro!
        sinGenericos.add(3.14);

        // ? Para usar los elementos hay que hacer casting manual
        for (Object obj : sinGenericos) {
            System.out.println("  Elemento: " + obj + " (tipo: " + obj.getClass().getSimpleName() + ")");
        }
        // ! Si hiciéramos String s = (String) sinGenericos.get(1) → ¡CRASH en runtime!

        System.out.println();

        // * ✅ CON genéricos — seguridad en compilación
        System.out.println("✅ CON genéricos (List<String>):");
        List<String> conGenericos = new ArrayList<>(); // * Solo acepta String
        conGenericos.add("Java");
        conGenericos.add("Python");
        conGenericos.add("JavaScript");
        // conGenericos.add(42); // ! ❌ Error de compilación — no es String

        // * No necesitamos casting — el compilador ya sabe que son String
        for (String lenguaje : conGenericos) {
            System.out.println("  🔹 " + lenguaje.toUpperCase()); // * Podemos usar métodos de String directamente
        }

        // ! ✅ TAREA ALUMNO:
        // * Crea una List<Integer> con 5 números, suma todos y muestra el total.
        // * Intenta añadir un String → observa el error del compilador.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🧩 SECCIÓN 2: CLASE GENÉRICA PROPIA
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Clases genéricas
    // ──────────────────────────────────────────────────────────────
    // ? Una clase genérica usa un parámetro de tipo <T> que se sustituye
    // ? por el tipo real cuando se crea un objeto.
    // ?
    // ? <T> → Type (tipo genérico, el más común)
    // ? <E> → Element (usado en colecciones)
    // ? <K> → Key (usado en mapas)
    // ? <V> → Value (usado en mapas)

    public static void claseGenericaCaja() {
        System.out.println("\n🔹 SECCIÓN 2: Clase genérica Caja<T>\n");

        // * Crear una Caja de String
        Caja<String> cajaTexto = new Caja<>("¡Hola Mundo!");
        System.out.println("📦 Caja de String: " + cajaTexto.getContenido());
        System.out.println("   Tipo: " + cajaTexto.obtenerTipo());

        // * Crear una Caja de Integer
        Caja<Integer> cajaNumero = new Caja<>(42);
        System.out.println("📦 Caja de Integer: " + cajaNumero.getContenido());
        System.out.println("   Tipo: " + cajaNumero.obtenerTipo());

        // * Crear una Caja de Double
        Caja<Double> cajaPrecio = new Caja<>(29.99);
        System.out.println("📦 Caja de Double: " + cajaPrecio.getContenido());
        System.out.println("   Tipo: " + cajaPrecio.obtenerTipo());

        // * Crear una Caja de Boolean
        Caja<Boolean> cajaActivo = new Caja<>(true);
        System.out.println("📦 Caja de Boolean: " + cajaActivo.getContenido());

        // ! ✅ TAREA ALUMNO:
        // * Crea una Caja<List<String>> que contenga una lista de nombres.
        // * Saca la lista, recórrela y muestra cada nombre.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🛠️ SECCIÓN 3: MÉTODO GENÉRICO
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Métodos genéricos
    // ──────────────────────────────────────────────────────────────
    // ? Un método genérico declara su propio parámetro <T> antes del tipo de
    // retorno.
    // ? Puede usarse en cualquier clase (no hace falta que la clase sea genérica).
    // ? Sintaxis: public static <T> void metodo(T param)

    // * Método genérico que imprime cualquier tipo con formato
    public static <T> void imprimirConFormato(String etiqueta, T valor) {
        // ? <T> antes de void declara que este método acepta cualquier tipo T
        System.out.println("  📌 " + etiqueta + ": " + valor + " (tipo: " + valor.getClass().getSimpleName() + ")");
    }

    // * Método genérico que imprime un array de cualquier tipo
    public static <T> void imprimirArray(T[] array) {
        System.out.print("  [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void metodoGenerico() {
        System.out.println("\n🔹 SECCIÓN 3: Métodos genéricos\n");

        // * Llamamos al mismo método con diferentes tipos
        imprimirConFormato("Nombre", "Joaquín");
        imprimirConFormato("Edad", 30);
        imprimirConFormato("Precio", 19.99);
        imprimirConFormato("Activo", true);

        System.out.println("\n  Imprimiendo arrays genéricos:");
        Integer[] numeros = { 1, 2, 3, 4, 5 };
        String[] palabras = { "Java", "Python", "C++" };
        Double[] precios = { 9.99, 19.99, 29.99 };

        imprimirArray(numeros);
        imprimirArray(palabras);
        imprimirArray(precios);

        // ! ✅ TAREA ALUMNO:
        // * Crea un método genérico <T> T encontrarMaximo(T[] array) que devuelva
        // * el mayor elemento de un array. Pista: T debe extender Comparable<T>.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🔒 SECCIÓN 4: BOUNDED TYPE (TIPO ACOTADO)
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Bounded Types
    // ──────────────────────────────────────────────────────────────
    // ? <T extends Number> → T solo puede ser Number o sus hijos (Integer, Double,
    // etc.)
    // ? <T extends Comparable<T>> → T debe implementar Comparable (se puede
    // comparar)
    // !
    // ! Con "extends" limitamos qué tipos se aceptan, ganando acceso a sus métodos.
    // ! Ejemplo: si T extends Number, podemos usar .doubleValue() con seguridad.

    // * Método que solo acepta subtipos de Number
    public static <T extends Number> double sumar(T a, T b) {
        // ? .doubleValue() funciona porque T SEGURO es un Number
        return a.doubleValue() + b.doubleValue();
    }

    // * Método que encuentra el máximo entre dos Comparable
    public static <T extends Comparable<T>> T maximo(T a, T b) {
        // ? .compareTo() funciona porque T SEGURO implementa Comparable
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static void boundedType() {
        System.out.println("\n🔹 SECCIÓN 4: Bounded Types (<T extends ...>)\n");

        // * Sumar con Integer
        System.out.println("  📌 Sumar Integer: sumar(10, 20) = " + sumar(10, 20));
        // * Sumar con Double
        System.out.println("  📌 Sumar Double: sumar(3.5, 2.5) = " + sumar(3.5, 2.5));
        // * Sumar mezclando tipos (ambos son Number)
        System.out.println("  📌 Sumar mixto: sumar(10, 3.14) = " + sumar(10, 3.14));

        System.out.println();

        // * Máximo entre Integer
        System.out.println("  📌 Máximo Integer: maximo(15, 42) = " + maximo(15, 42));
        // * Máximo entre String (comparación alfabética)
        System.out.println("  📌 Máximo String: maximo(\"Ana\", \"Zoe\") = " + maximo("Ana", "Zoe"));
        // * Máximo entre Double
        System.out.println("  📌 Máximo Double: maximo(3.14, 2.71) = " + maximo(3.14, 2.71));

        // ! ✅ TAREA ALUMNO:
        // * Crea un método genérico <T extends Number> double promedio(List<T> lista)
        // * que calcule el promedio de una lista de números de cualquier tipo.
    }

    // ═══════════════════════════════════════════════════════════════
    // * ❓ SECCIÓN 5: WILDCARDS
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Wildcards
    // ──────────────────────────────────────────────────────────────
    // ? <?> → Cualquier tipo (comodín sin restricción)
    // ? <? extends T> → Cualquier subtipo de T (leer, no escribir)
    // ? <? super T> → Cualquier supertipo de T (escribir, no leer)
    // !
    // ! Regla PECS: Producer Extends, Consumer Super
    // ! - Si LEES de la colección → usa extends (produce datos)
    // ! - Si ESCRIBES en la colección → usa super (consume datos)

    // * Método con <?> — acepta una lista de CUALQUIER tipo
    public static void imprimirLista(List<?> lista) {
        // ? No sabemos qué tipo tiene, pero podemos leerlo como Object
        System.out.print("  Lista: [");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i));
            if (i < lista.size() - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    // * Método con <? extends Number> — solo listas de números
    public static double sumarLista(List<? extends Number> numeros) {
        // ? Podemos leer como Number, pero NO añadir (extends = solo lectura)
        double total = 0;
        for (Number n : numeros) {
            total += n.doubleValue();
        }
        return total;
    }

    // * Método con <? super Integer> — podemos añadir Integer
    public static void agregarNumeros(List<? super Integer> lista) {
        // ? Podemos escribir Integer, pero leer solo como Object (super = escritura)
        lista.add(100);
        lista.add(200);
        lista.add(300);
    }

    public static void wildcards() {
        System.out.println("\n🔹 SECCIÓN 5: Wildcards (?, extends, super)\n");

        // * <?> — Cualquier tipo
        System.out.println("  ❓ Wildcard <?> — cualquier tipo:");
        imprimirLista(List.of("Java", "Python", "C++"));
        imprimirLista(List.of(1, 2, 3, 4, 5));
        imprimirLista(List.of(true, false, true));

        System.out.println();

        // * <? extends Number> — solo números (leer)
        System.out.println("  📊 Wildcard <? extends Number> — solo números:");
        List<Integer> enteros = List.of(10, 20, 30);
        List<Double> decimales = List.of(1.5, 2.5, 3.5);
        System.out.println("  Suma enteros: " + sumarLista(enteros));
        System.out.println("  Suma decimales: " + sumarLista(decimales));

        System.out.println();

        // * <? super Integer> — escribir Integer
        System.out.println("  ✏️  Wildcard <? super Integer> — escribir:");
        List<Number> listaNumeros = new ArrayList<>();
        agregarNumeros(listaNumeros); // ? Funciona: Number es supertipo de Integer
        imprimirLista(listaNumeros);

        // ! ✅ TAREA ALUMNO:
        // * Crea un método imprimirSoloStrings(List<? extends CharSequence> lista)
        // * que acepte List<String> y List<StringBuilder>.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📦 SECCIÓN 6: PAR GENÉRICO (DOS TIPOS)
    // ═══════════════════════════════════════════════════════════════

    public static void parGenerico() {
        System.out.println("\n🔹 SECCIÓN 6: Par genérico Par<K, V>\n");

        // * Par de String, Integer (nombre → edad)
        Par<String, Integer> persona = new Par<>("Ana", 28);
        System.out.println("  👤 " + persona.getClave() + " tiene " + persona.getValor() + " años");

        // * Par de String, Double (producto → precio)
        Par<String, Double> producto = new Par<>("Laptop", 999.99);
        System.out.println("  💻 " + producto.getClave() + " cuesta " + producto.getValor() + "€");

        // * Par de Integer, Boolean (ID → activo)
        Par<Integer, Boolean> estado = new Par<>(1001, true);
        System.out.println("  🔑 ID " + estado.getClave() + " → Activo: " + estado.getValor());

        // * Lista de pares (simula un mini-mapa)
        List<Par<String, Integer>> alumnos = new ArrayList<>();
        alumnos.add(new Par<>("Carlos", 19));
        alumnos.add(new Par<>("María", 21));
        alumnos.add(new Par<>("Pedro", 20));

        System.out.println("\n  📋 Lista de alumnos:");
        for (Par<String, Integer> alumno : alumnos) {
            System.out.println("    🎓 " + alumno.getClave() + " — " + alumno.getValor() + " años");
        }

        // ! ✅ TAREA ALUMNO:
        // * Crea un Par<String, List<String>> que almacene un curso y la lista de
        // alumnos.
        // * Muestra el nombre del curso y cuántos alumnos hay.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🎯 SECCIÓN 7: INTERFAZ GENÉRICA (REPOSITORIO<T>)
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Interfaces genéricas
    // ──────────────────────────────────────────────────────────────
    // ? Una interfaz genérica define un contrato para CUALQUIER tipo de entidad.
    // ? Ejemplo real: los Repository<T> de los proyectos usan exactamente esto.
    // ? Así el mismo código sirve para Alumno, Curso, Producto, etc.

    public static void interfazGenerica() {
        System.out.println("\n🔹 SECCIÓN 7: Interfaz genérica Repositorio<T>\n");

        // * Crear repositorios para diferentes tipos
        RepositorioEnMemoria<String> repoNombres = new RepositorioEnMemoria<>();
        repoNombres.guardar("Ana");
        repoNombres.guardar("Carlos");
        repoNombres.guardar("María");

        System.out.println("  📦 Repositorio de nombres (" + repoNombres.contar() + "):");
        for (String nombre : repoNombres.listarTodos()) {
            System.out.println("    👤 " + nombre);
        }

        RepositorioEnMemoria<Integer> repoNumeros = new RepositorioEnMemoria<>();
        repoNumeros.guardar(100);
        repoNumeros.guardar(200);
        repoNumeros.guardar(300);

        System.out.println("\n  📦 Repositorio de números (" + repoNumeros.contar() + "):");
        for (Integer num : repoNumeros.listarTodos()) {
            System.out.println("    🔢 " + num);
        }

        // * Usar buscar
        System.out.println("\n  🔍 ¿Existe 'Ana'? " + repoNombres.buscar("Ana"));
        System.out.println("  🔍 ¿Existe 'Pedro'? " + repoNombres.buscar("Pedro"));

        // * Borrar
        repoNombres.borrar("Carlos");
        System.out.println("  🗑️  Carlos borrado. Quedan: " + repoNombres.contar());

        // ! ✅ TAREA ALUMNO:
        // * Crea una clase Producto(nombre, precio) y un
        // RepositorioEnMemoria<Producto>.
        // * Guarda 3 productos, lista todos y borra uno.
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🧩 CLASES AUXILIARES (fuera de la clase principal)
// ═══════════════════════════════════════════════════════════════════

// * Clase genérica Caja<T>
// ? T es un parámetro de tipo — se sustituye al crear el objeto
// ? Caja<String> → T = String | Caja<Integer> → T = Integer
class Caja<T> {
    private T contenido; // ? El tipo del atributo lo decide quien crea el objeto

    // * Constructor — recibe un objeto del tipo T
    public Caja(T contenido) {
        this.contenido = contenido;
    }

    // * Getter — devuelve un objeto del tipo T (sin casting)
    public T getContenido() {
        return contenido;
    }

    // * Setter — acepta un objeto del tipo T
    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    // * Obtener el nombre del tipo real en runtime
    public String obtenerTipo() {
        return contenido.getClass().getSimpleName();
    }
}

// * Clase genérica Par<K, V> — con DOS parámetros de tipo
// ? K = tipo de la clave (Key), V = tipo del valor (Value)
// ? Simula la estructura de Map.Entry<K, V>
class Par<K, V> {
    private K clave;
    private V valor;

    public Par(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }
}

// * Interfaz genérica Repositorio<T>
// ? Define el contrato de un almacén para CUALQUIER tipo T
// ! Esto es exactamente lo que usan los proyectos del curso (AlumnoRepository,
// etc.)
interface Repositorio<T> {
    void guardar(T elemento);

    boolean buscar(T elemento);

    boolean borrar(T elemento);

    List<T> listarTodos();

    int contar();
}

// * Implementación en memoria del Repositorio<T>
// ? Usa un ArrayList<T> internamente — sin BD, sin ficheros
class RepositorioEnMemoria<T> implements Repositorio<T> {
    private final List<T> datos = new ArrayList<>(); // ? Lista interna genérica

    @Override
    public void guardar(T elemento) {
        datos.add(elemento);
    }

    @Override
    public boolean buscar(T elemento) {
        return datos.contains(elemento);
    }

    @Override
    public boolean borrar(T elemento) {
        return datos.remove(elemento);
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(datos); // ? Devuelve copia para proteger la lista interna
    }

    @Override
    public int contar() {
        return datos.size();
    }
}
