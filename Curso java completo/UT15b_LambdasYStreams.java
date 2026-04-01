/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 15b: LAMBDAS, STREAMS Y OPTIONAL EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class UT15b_LambdasYStreams {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // * MENÚ PRINCIPAL — ELIGE QUÉ SECCIÓN EJECUTAR
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║  ⚡ UT15b — LAMBDAS, STREAMS Y OPTIONAL              ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.println("1. 📖 De clase anónima a Lambda (evolución)");
            System.out.println("2. ⚡ Interfaces funcionales (Predicate, Function, Consumer)");
            System.out.println("3. 🌊 Streams básicos (filter, map, forEach)");
            System.out.println("4. 📊 Streams intermedios (sorted, distinct, limit)");
            System.out.println("5. 🎯 Streams terminales (collect, reduce, count)");
            System.out.println("6. 🔄 Optional<T> — adiós al null");
            System.out.println("7. 🏆 Caso práctico completo (lista de alumnos)");
            System.out.println("0. 👋 Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> deAnonimaaLambda();
                case 2 -> interfacesFuncionales();
                case 3 -> streamsBasicos();
                case 4 -> streamsIntermedios();
                case 5 -> streamsTerminales();
                case 6 -> optionalDemo();
                case 7 -> casoPracticoAlumnos();
                case 0 -> System.out.println("👋 ¡Hasta luego!");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📖 SECCIÓN 1: DE CLASE ANÓNIMA A LAMBDA
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Lambdas
    // ──────────────────────────────────────────────────────────────
    // ? Una lambda es una función anónima — código que se pasa como dato.
    // ? Sintaxis: (parámetros) -> { cuerpo }
    // ? Si solo hay 1 línea: (parámetros) -> expresión
    // ? Si solo hay 1 parámetro: parámetro -> expresión
    // !
    // ! Las lambdas SOLO funcionan con interfaces funcionales
    // ! (interfaces con exactamente 1 método abstracto).

    public static void deAnonimaaLambda() {
        System.out.println("\n🔹 SECCIÓN 1: De clase anónima a Lambda\n");

        List<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Beatriz", "David"));

        // ! ❌ ANTES (Java 7) — Clase anónima para ordenar
        System.out.println("  ❌ ANTES — Clase anónima (verbose):");
        Collections.sort(nombres, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println("  Ordenado: " + nombres);

        // * ✅ AHORA (Java 8+) — Lambda
        System.out.println("\n  ✅ AHORA — Lambda (1 línea):");
        nombres.sort((a, b) -> a.compareTo(b));
        System.out.println("  Ordenado: " + nombres);

        // * ✅ MEJOR AÚN — Method reference
        System.out.println("\n  ✅ MEJOR — Method reference:");
        nombres.sort(String::compareTo);
        System.out.println("  Ordenado: " + nombres);

        // * Más ejemplos de lambdas
        System.out.println("\n  📌 Más ejemplos de lambdas:");

        // ? Sin parámetros
        Runnable saludo = () -> System.out.println("    ¡Hola desde una lambda!");
        saludo.run();

        // ? Con 1 parámetro (se omiten paréntesis)
        Consumer<String> grito = nombre -> System.out.println("    🔊 " + nombre.toUpperCase());
        grito.accept("joaquín");

        // ? Con 2 parámetros y retorno
        Comparator<Integer> comparador = (a, b) -> b - a; // ? Orden descendente
        List<Integer> nums = new ArrayList<>(List.of(3, 1, 4, 1, 5));
        nums.sort(comparador);
        System.out.println("    📉 Descendente: " + nums);

        // ! ✅ TAREA ALUMNO:
        // * Crea una List<String> de ciudades y ordénalas por longitud del nombre
        // * usando una lambda: (a, b) -> a.length() - b.length()
    }

    // ═══════════════════════════════════════════════════════════════
    // * ⚡ SECCIÓN 2: INTERFACES FUNCIONALES
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Interfaces funcionales
    // ──────────────────────────────────────────────────────────────
    // ? Java 8 añadió interfaces funcionales predefinidas en java.util.function:
    // ?
    // ? Predicate<T> → entrada T → sale boolean → para FILTRAR
    // ? Function<T,R> → entrada T → sale R → para TRANSFORMAR
    // ? Consumer<T> → entrada T → no sale nada → para EJECUTAR
    // ? Supplier<T> → no entra nada → sale T → para CREAR/PRODUCIR
    // ? UnaryOperator<T> → entrada T → sale T → para MODIFICAR

    public static void interfacesFuncionales() {
        System.out.println("\n🔹 SECCIÓN 2: Interfaces funcionales\n");

        // * PREDICATE — ¿cumple una condición? (devuelve boolean)
        Predicate<Integer> esPar = n -> n % 2 == 0;
        Predicate<String> esLargo = s -> s.length() > 5;

        System.out.println("  🔍 Predicate (filtrar):");
        System.out.println("    ¿4 es par? " + esPar.test(4));
        System.out.println("    ¿7 es par? " + esPar.test(7));
        System.out.println("    ¿\"Joaquín\" es largo? " + esLargo.test("Joaquín"));

        // ? Combinar Predicates con and(), or(), negate()
        Predicate<Integer> esPositivo = n -> n > 0;
        Predicate<Integer> esParPositivo = esPar.and(esPositivo);
        System.out.println("    ¿6 es par Y positivo? " + esParPositivo.test(6));
        System.out.println("    ¿-4 es par Y positivo? " + esParPositivo.test(-4));

        System.out.println();

        // * FUNCTION — transforma un valor (entrada → salida)
        Function<String, Integer> longitud = s -> s.length();
        Function<Integer, String> aEstrellas = n -> "⭐".repeat(n);

        System.out.println("  🔄 Function (transformar):");
        System.out.println("    Longitud de \"Java\": " + longitud.apply("Java"));
        System.out.println("    3 estrellas: " + aEstrellas.apply(3));

        // ? Encadenar con andThen()
        Function<String, String> longitudEnEstrellas = longitud.andThen(aEstrellas);
        System.out.println("    \"Hola\" → estrellas: " + longitudEnEstrellas.apply("Hola"));

        System.out.println();

        // * CONSUMER — ejecuta una acción (no devuelve nada)
        Consumer<String> imprimir = s -> System.out.println("    📢 " + s);
        System.out.println("  📢 Consumer (ejecutar):");
        imprimir.accept("¡Esto lo imprime un Consumer!");

        System.out.println();

        // * SUPPLIER — produce un valor (no recibe nada)
        Supplier<Double> randomPrice = () -> Math.round(Math.random() * 10000) / 100.0;
        System.out.println("  🎲 Supplier (producir):");
        System.out.println("    Precio aleatorio: " + randomPrice.get() + "€");
        System.out.println("    Otro precio: " + randomPrice.get() + "€");

        // ! ✅ TAREA ALUMNO:
        // * Crea un Predicate<String> que devuelva true si contiene "@" (email válido).
        // * Crea un Function<String, String> que ponga la primera letra en mayúscula.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🌊 SECCIÓN 3: STREAMS BÁSICOS
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Streams
    // ──────────────────────────────────────────────────────────────
    // ? Un Stream es un flujo de datos sobre el que se aplican operaciones
    // ? de forma encadenada (pipeline), sin modificar la colección original.
    // ?
    // ? Colección.stream() → operaciones intermedias → operación terminal
    // ?
    // ? Operaciones intermedias: filter, map, sorted, distinct, limit
    // ? Operaciones terminales: forEach, collect, count, reduce, findFirst
    // !
    // ! Un Stream NO modifica la colección original (es inmutable).
    // ! Un Stream solo se puede consumir UNA vez.

    public static void streamsBasicos() {
        System.out.println("\n🔹 SECCIÓN 3: Streams básicos\n");

        List<String> nombres = List.of("Carlos", "Ana", "Beatriz", "David", "Elena", "Ana");

        // * FILTER — filtra elementos que cumplen una condición
        System.out.println("  🔍 filter() — nombres con más de 4 letras:");
        nombres.stream()
                .filter(n -> n.length() > 4)
                .forEach(n -> System.out.println("    ✅ " + n));

        System.out.println();

        // * MAP — transforma cada elemento
        System.out.println("  🔄 map() — nombres en mayúsculas:");
        nombres.stream()
                .map(String::toUpperCase)
                .forEach(n -> System.out.println("    📌 " + n));

        System.out.println();

        // * FOREACH — ejecuta una acción por cada elemento
        System.out.println("  📢 forEach() — saludar a cada uno:");
        nombres.stream()
                .forEach(n -> System.out.println("    👋 ¡Hola " + n + "!"));

        System.out.println();

        // * Encadenar filter + map + forEach
        System.out.println("  ⛓️  Pipeline: filter + map + forEach:");
        nombres.stream()
                .filter(n -> n.startsWith("A")) // ? Solo los que empiezan por A
                .map(n -> n.toUpperCase()) // ? Poner en mayúsculas
                .forEach(n -> System.out.println("    🎯 " + n));

        // ! ✅ TAREA ALUMNO:
        // * Crea una List<Integer> con 10 números. Filtra los pares y muestra su
        // cuadrado.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📊 SECCIÓN 4: STREAMS INTERMEDIOS
    // ═══════════════════════════════════════════════════════════════

    public static void streamsIntermedios() {
        System.out.println("\n🔹 SECCIÓN 4: Operaciones intermedias\n");

        List<Integer> numeros = List.of(5, 3, 8, 1, 9, 2, 7, 3, 5, 1, 8);

        // * SORTED — ordena los elementos
        System.out.println("  📊 sorted() — números ordenados:");
        numeros.stream()
                .sorted()
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // * DISTINCT — elimina duplicados
        System.out.println("  🔹 distinct() — sin duplicados:");
        numeros.stream()
                .distinct()
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // * LIMIT — toma solo los primeros N
        System.out.println("  ✂️  limit(3) — primeros 3:");
        numeros.stream()
                .limit(3)
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // * SKIP — salta los primeros N
        System.out.println("  ⏭️  skip(7) — saltar primeros 7:");
        numeros.stream()
                .skip(7)
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // * Pipeline completo: distinct + sorted + limit
        System.out.println("\n  ⛓️  Pipeline: distinct + sorted + limit(5) (top 5 únicos):");
        numeros.stream()
                .distinct()
                .sorted()
                .limit(5)
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // * PEEK — para debug (ver los datos sin modificar el flujo)
        System.out.println("\n  🔬 peek() — debug del pipeline:");
        long resultado = numeros.stream()
                .peek(n -> System.out.print("  [" + n + "]"))
                .filter(n -> n > 5)
                .peek(n -> System.out.print("→" + n + " "))
                .count();
        System.out.println("\n  Total > 5: " + resultado);

        // ! ✅ TAREA ALUMNO:
        // * Crea una List<String> de 10 nombres. Muestra los 3 primeros en orden
        // * alfabético sin repetidos, todo en mayúsculas.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🎯 SECCIÓN 5: STREAMS TERMINALES
    // ═══════════════════════════════════════════════════════════════

    public static void streamsTerminales() {
        System.out.println("\n🔹 SECCIÓN 5: Operaciones terminales\n");

        List<Integer> numeros = List.of(10, 20, 30, 40, 50, 15, 25);

        // * COLLECT — recoger en una nueva colección
        System.out.println("  📦 collect() — filtrar pares a nueva lista:");
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("    Pares: " + pares);

        // * COLLECT a String
        String concatenado = numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("    Concatenado: " + concatenado);

        System.out.println();

        // * COUNT — contar elementos
        long cuantos = numeros.stream().filter(n -> n > 25).count();
        System.out.println("  🔢 count() — números > 25: " + cuantos);

        // * REDUCE — acumular todos en un solo valor
        int suma = numeros.stream().reduce(0, Integer::sum);
        System.out.println("  ➕ reduce() — suma total: " + suma);

        Optional<Integer> max = numeros.stream().max(Integer::compareTo);
        System.out.println("  📈 max() — máximo: " + max.orElse(0));

        Optional<Integer> min = numeros.stream().min(Integer::compareTo);
        System.out.println("  📉 min() — mínimo: " + min.orElse(0));

        System.out.println();

        // * toMap — convertir a Map
        List<String> frutas = List.of("Manzana", "Pera", "Kiwi", "Sandía");
        Map<String, Integer> frutaLongitud = frutas.stream()
                .collect(Collectors.toMap(f -> f, String::length));
        System.out.println("  🗺️  toMap() — fruta → longitud: " + frutaLongitud);

        // * Estadísticas con IntStream
        IntSummaryStatistics stats = numeros.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("\n  📊 Estadísticas de los números:");
        System.out.println("    Total: " + stats.getCount());
        System.out.println("    Suma: " + stats.getSum());
        System.out.println("    Media: " + String.format("%.2f", stats.getAverage()));
        System.out.println("    Mín: " + stats.getMin());
        System.out.println("    Máx: " + stats.getMax());

        // ! ✅ TAREA ALUMNO:
        // * Crea una List<Double> de precios. Calcula el total, la media y
        // * genera una nueva lista con los precios con IVA (precio * 1.21).
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🔄 SECCIÓN 6: OPTIONAL
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Optional<T>
    // ──────────────────────────────────────────────────────────────
    // ? Optional<T> es un contenedor que PUEDE tener un valor o estar vacío.
    // ? Reemplaza el uso de null para evitar NullPointerException.
    // ?
    // ? Optional.of(valor) → crea con valor (no puede ser null)
    // ? Optional.ofNullable(x) → crea con valor o vacío si x es null
    // ? Optional.empty() → crea vacío
    // ?
    // ? .isPresent() → ¿tiene valor?
    // ? .get() → obtener valor (peligroso si está vacío)
    // ? .orElse(default) → obtener valor o un valor por defecto
    // ? .ifPresent(consumer) → ejecutar acción si hay valor
    // ? .map(function) → transformar el valor si existe

    // * Simula una búsqueda que puede no encontrar nada
    public static Optional<String> buscarUsuario(int id) {
        // ? Simula una base de datos
        Map<Integer, String> usuarios = Map.of(1, "Ana", 2, "Carlos", 3, "María");
        return Optional.ofNullable(usuarios.get(id));
    }

    public static void optionalDemo() {
        System.out.println("\n🔹 SECCIÓN 6: Optional<T>\n");

        // * Crear Optional
        Optional<String> conValor = Optional.of("Hola");
        Optional<String> vacio = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("  📌 conValor.isPresent(): " + conValor.isPresent());
        System.out.println("  📌 vacio.isPresent(): " + vacio.isPresent());
        System.out.println("  📌 nullable.isPresent(): " + nullable.isPresent());

        System.out.println();

        // * orElse — valor por defecto si está vacío
        System.out.println("  🔄 orElse():");
        System.out.println("    conValor: " + conValor.orElse("default"));
        System.out.println("    vacio: " + vacio.orElse("(vacío — se usó default)"));

        System.out.println();

        // * ifPresent — ejecutar acción solo si hay valor
        System.out.println("  ✅ ifPresent():");
        conValor.ifPresent(v -> System.out.println("    Valor encontrado: " + v));
        vacio.ifPresent(v -> System.out.println("    Esto NO se imprime"));

        System.out.println();

        // * map — transformar si hay valor
        System.out.println("  🔄 map():");
        Optional<Integer> longitud = conValor.map(String::length);
        System.out.println("    Longitud de 'Hola': " + longitud.orElse(0));

        System.out.println();

        // * Caso real: buscar usuario
        System.out.println("  🔍 Caso real — buscar usuario:");
        Optional<String> encontrado = buscarUsuario(1);
        Optional<String> noEncontrado = buscarUsuario(999);

        encontrado.ifPresentOrElse(
                u -> System.out.println("    ✅ Encontrado: " + u),
                () -> System.out.println("    ❌ No encontrado"));
        noEncontrado.ifPresentOrElse(
                u -> System.out.println("    ✅ Encontrado: " + u),
                () -> System.out.println("    ❌ No encontrado (ID 999)"));

        // ! ✅ TAREA ALUMNO:
        // * Crea un método Optional<Double> buscarPrecio(String producto)
        // * que devuelva el precio si existe o Optional.empty() si no.
        // * Usa orElse() para mostrar "Precio no disponible" cuando no exista.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🏆 SECCIÓN 7: CASO PRÁCTICO COMPLETO
    // ═══════════════════════════════════════════════════════════════

    public static void casoPracticoAlumnos() {
        System.out.println("\n🔹 SECCIÓN 7: Caso práctico — Gestión de alumnos\n");

        // * Crear lista de alumnos
        List<Alumno> alumnos = List.of(
                new Alumno("Ana García", 19, 8.5),
                new Alumno("Carlos López", 21, 6.2),
                new Alumno("María Ruiz", 20, 9.1),
                new Alumno("Pedro Martín", 22, 4.8),
                new Alumno("Laura Sánchez", 19, 7.3),
                new Alumno("David Fernández", 23, 3.5),
                new Alumno("Elena Torres", 20, 8.8),
                new Alumno("Juan Díaz", 21, 5.0));

        // * 1. Listar todos con forEach
        System.out.println("  📋 Todos los alumnos:");
        alumnos.forEach(a -> System.out.println("    " + a));

        System.out.println();

        // * 2. Filtrar aprobados (nota >= 5)
        System.out.println("  ✅ Aprobados (nota ≥ 5):");
        alumnos.stream()
                .filter(a -> a.getNota() >= 5)
                .forEach(a -> System.out.println("    ✅ " + a.getNombre() + " — " + a.getNota()));

        System.out.println();

        // * 3. Top 3 mejores notas
        System.out.println("  🏆 Top 3 mejores notas:");
        alumnos.stream()
                .sorted((a, b) -> Double.compare(b.getNota(), a.getNota()))
                .limit(3)
                .forEach(a -> System.out.println("    🥇 " + a.getNombre() + " — " + a.getNota()));

        System.out.println();

        // * 4. Estadísticas con mapToDouble
        DoubleSummaryStatistics stats = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .summaryStatistics();
        System.out.println("  📊 Estadísticas de notas:");
        System.out.println("    Media: " + String.format("%.2f", stats.getAverage()));
        System.out.println("    Máxima: " + stats.getMax());
        System.out.println("    Mínima: " + stats.getMin());

        System.out.println();

        // * 5. Extraer solo nombres a nueva lista
        List<String> nombres = alumnos.stream()
                .map(Alumno::getNombre)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("  📛 Nombres ordenados: " + nombres);

        System.out.println();

        // * 6. Agrupar por resultado (aprobado/suspenso) con Collectors.partitioningBy
        Map<Boolean, List<Alumno>> porResultado = alumnos.stream()
                .collect(Collectors.partitioningBy(a -> a.getNota() >= 5));
        System.out.println("  📊 Aprobados: " + porResultado.get(true).size());
        System.out.println("  📊 Suspensos: " + porResultado.get(false).size());

        System.out.println();

        // * 7. Buscar alumno con Optional
        Optional<Alumno> mejor = alumnos.stream()
                .max(Comparator.comparingDouble(Alumno::getNota));
        mejor.ifPresent(a -> System.out.println("  🏅 Mejor alumno: " + a.getNombre() + " — " + a.getNota()));

        Optional<Alumno> buscado = alumnos.stream()
                .filter(a -> a.getNombre().contains("Pedro"))
                .findFirst();
        buscado.ifPresentOrElse(
                a -> System.out.println("  🔍 Pedro encontrado: nota " + a.getNota()),
                () -> System.out.println("  🔍 Pedro no encontrado"));

        // ! ✅ TAREA ALUMNO:
        // * 1. Filtra alumnos menores de 21 años con nota >= 7 y muéstralos.
        // * 2. Calcula la nota media SOLO de los aprobados.
        // * 3. Genera un String con los nombres separados por ", " usando
        // Collectors.joining().
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🎓 CLASE AUXILIAR: Alumno
// ═══════════════════════════════════════════════════════════════════

// * Clase simple para el caso práctico
// ? No es genérica — es una entidad de dominio normal
class Alumno {
    private String nombre;
    private int edad;
    private double nota;

    public Alumno(String nombre, int edad, double nota) {
        this.nombre = nombre;
        this.edad = edad;
        this.nota = nota;
    }

    // * Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años) — Nota: " + nota;
    }
}
