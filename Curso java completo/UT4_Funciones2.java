/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 REPOSITORIO PRIVADO EN GITHUB (ACCESO SOLO PARA ALUMNOS AUTORIZADOS)
 *  ❌ PROHIBIDA SU DISTRIBUCIÓN SIN PERMISO DEL AUTOR
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  UNIDAD 4 | FUNCIONES EN JAVA
 * ==========================================================================================
 *  Las funciones, llamadas tambien metodos en Java, permiten dividir
 *  un programa en bloques mas pequenos, claros y reutilizables.
 *
 *  En este archivo trabajaras:
 *  - Funciones sin parametros.
 *  - Funciones con parametros.
 *  - Funciones con retorno.
 *  - Sobrecarga de metodos y un ejemplo de recursion.
 *
 *  Idea clave:
 *  - Una funcion agrupa una tarea concreta y ayuda a evitar codigo repetido,
 *    haciendo que el programa sea mas ordenado y facil de entender.
 *
 *  Objetivo de la practica:
 *  - Leer, ejecutar y modificar los ejemplos para comprender como se
 *    declaran, se llaman y devuelven resultados las funciones.
 *
 *  Recomendacion:
 *  - Prueba las llamadas del `main` una a una para ver con claridad
 *    que hace cada metodo.
 * ==========================================================================================
 */

import java.util.Scanner;

public class UT4_Funciones2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarTitulo("UNIDAD 4 - FUNCIONES EN JAVA");
            mostrarMenu();
            opcion = leerEntero(sc, "Elige una opcion: ");
            ejecutarOpcion(opcion, sc);

            if (opcion != 0) {
                System.out.println("\nPulsa ENTER para volver al menu...");
                sc.nextLine();
            }
        } while (opcion != 0);

        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("1. Funcion sin parametros");
        System.out.println("2. Funcion con parametros");
        System.out.println("3. Funcion con retorno");
        System.out.println("4. Funcion recursiva");
        System.out.println("5. Funciones booleanas");
        System.out.println("6. Multiples resultados");
        System.out.println("7. Funciones practicas");
        System.out.println("8. Conversiones");
        System.out.println("9. Funcion con decision");
        System.out.println("10. Ejecutar todo");
        System.out.println("0. Salir");
        System.out.println("-".repeat(90));
    }

    public static void ejecutarOpcion(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                ejecutarFuncionSinParametros();
                break;
            case 2:
                ejecutarFuncionConParametros();
                break;
            case 3:
                ejecutarFuncionConRetorno(sc);
                break;
            case 4:
                ejecutarFuncionRecursiva(sc);
                break;
            case 5:
                ejecutarFuncionesBooleanas(sc);
                break;
            case 6:
                ejecutarMultiplesResultados(sc);
                break;
            case 7:
                ejecutarFuncionesPracticas(sc);
                break;
            case 8:
                ejecutarConversiones(sc);
                break;
            case 9:
                ejecutarFuncionConDecision(sc);
                break;
            case 10:
                ejecutarTodo();
                break;
            case 0:
                System.out.println("Programa finalizado.");
                break;
            default:
                System.out.println("Opcion no valida. Intenta de nuevo.");
        }
    }

    public static void ejecutarTodo() {
        ejecutarFuncionSinParametros();
        ejecutarFuncionConParametros();
        mostrarSeccion("3. FUNCION CON RETORNO");
        System.out.println("La suma es: " + sumar(10, 5));
        System.out.println("La suma de decimales es: " + sumar(10.5, 4.3));
        System.out.println("La media de las notas es: " + calcularMedia(7.5, 8.0, 9.0));

        mostrarSeccion("4. FUNCION RECURSIVA");
        System.out.println("El factorial de 5 es: " + factorial(5));

        mostrarSeccion("5. FUNCIONES BOOLEANAS");
        System.out.println("¿18 es par? " + esPar(18));
        System.out.println("¿29 es primo? " + esPrimo(29));

        mostrarSeccion("6. MULTIPLES RESULTADOS");
        int[] resultadosPotencias = calcularCuadradoYCubo(4);
        System.out.println("Cuadrado de 4: " + resultadosPotencias[0]);
        System.out.println("Cubo de 4: " + resultadosPotencias[1]);

        mostrarSeccion("7. FUNCIONES PRACTICAS");
        System.out.println("Area del rectangulo: " + calcularAreaRectangulo(8, 3.5));
        System.out.println("Area del circulo: " + redondear(calcularAreaCirculo(4)));

        mostrarSeccion("8. CONVERSIONES");
        System.out.println("25 grados Celsius son " + redondear(celsiusAFahrenheit(25)) + " Fahrenheit");
        System.out.println("86 grados Fahrenheit son " + redondear(fahrenheitACelsius(86)) + " Celsius");

        mostrarSeccion("9. FUNCION CON DECISION");
        System.out.println(generarMensajeResultado(9.2));
        System.out.println(generarMensajeResultado(4.5));
    }

    public static void ejecutarFuncionSinParametros() {
        mostrarSeccion("1. FUNCION SIN PARAMETROS");
        imprimirMensaje();
    }

    public static void ejecutarFuncionConParametros() {
        mostrarSeccion("2. FUNCION CON PARAMETROS");
        saludar("Joaquin");
        presentarAlumno("Lucia", "DAM 1");
    }

    public static void ejecutarFuncionConRetorno(Scanner sc) {
        mostrarSeccion("3. FUNCION CON RETORNO");
        int numero1 = leerEntero(sc, "Introduce el primer numero entero: ");
        int numero2 = leerEntero(sc, "Introduce el segundo numero entero: ");
        double nota1 = leerDouble(sc, "Introduce la nota 1: ");
        double nota2 = leerDouble(sc, "Introduce la nota 2: ");
        double nota3 = leerDouble(sc, "Introduce la nota 3: ");

        System.out.println("La suma es: " + sumar(numero1, numero2));
        System.out.println("La media de las notas es: " + redondear(calcularMedia(nota1, nota2, nota3)));
    }

    public static void ejecutarFuncionRecursiva(Scanner sc) {
        mostrarSeccion("4. FUNCION RECURSIVA");
        int numero = leerEntero(sc, "Introduce un numero para calcular su factorial: ");

        if (numero < 0) {
            System.out.println("El factorial no esta definido para numeros negativos.");
            return;
        }

        System.out.println("El factorial de " + numero + " es: " + factorial(numero));
    }

    public static void ejecutarFuncionesBooleanas(Scanner sc) {
        mostrarSeccion("5. FUNCIONES BOOLEANAS");
        int numero = leerEntero(sc, "Introduce un numero: ");
        System.out.println("¿" + numero + " es par? " + esPar(numero));
        System.out.println("¿" + numero + " es primo? " + esPrimo(numero));
    }

    public static void ejecutarMultiplesResultados(Scanner sc) {
        mostrarSeccion("6. MULTIPLES RESULTADOS");
        int numero = leerEntero(sc, "Introduce un numero: ");
        int[] resultados = calcularCuadradoYCubo(numero);
        System.out.println("Cuadrado de " + numero + ": " + resultados[0]);
        System.out.println("Cubo de " + numero + ": " + resultados[1]);
    }

    public static void ejecutarFuncionesPracticas(Scanner sc) {
        mostrarSeccion("7. FUNCIONES PRACTICAS");
        double base = leerDouble(sc, "Introduce la base del rectangulo: ");
        double altura = leerDouble(sc, "Introduce la altura del rectangulo: ");
        double radio = leerDouble(sc, "Introduce el radio del circulo: ");

        System.out.println("Area del rectangulo: " + redondear(calcularAreaRectangulo(base, altura)));
        System.out.println("Area del circulo: " + redondear(calcularAreaCirculo(radio)));
    }

    public static void ejecutarConversiones(Scanner sc) {
        mostrarSeccion("8. CONVERSIONES");
        double celsius = leerDouble(sc, "Introduce grados Celsius: ");
        double fahrenheit = leerDouble(sc, "Introduce grados Fahrenheit: ");

        System.out.println(celsius + " grados Celsius son " + redondear(celsiusAFahrenheit(celsius)) + " Fahrenheit");
        System.out.println(fahrenheit + " grados Fahrenheit son " + redondear(fahrenheitACelsius(fahrenheit)) + " Celsius");
    }

    public static void ejecutarFuncionConDecision(Scanner sc) {
        mostrarSeccion("9. FUNCION CON DECISION");
        double nota = leerDouble(sc, "Introduce una nota: ");
        System.out.println(generarMensajeResultado(redondear(nota)));
    }

    public static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    public static void mostrarTitulo(String titulo) {
        System.out.println("\n" + "=".repeat(90));
        System.out.println(" " + titulo);
        System.out.println("=".repeat(90));
    }

    public static void mostrarSeccion(String titulo) {
        System.out.println("\n" + "-".repeat(90));
        System.out.println(titulo);
        System.out.println("-".repeat(90));
    }

    // * 📖 TEORÍA: Función sin parámetros y sin retorno
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Una función sin parámetros es aquella que no recibe valores al ser llamada.
    // ? Una función sin retorno no devuelve ningún valor al finalizar su ejecución.
    // ? Se usa cuando solo queremos ejecutar código sin recibir ni devolver datos.
    public static void imprimirMensaje() {
        System.out.println("Hola. Este es un mensaje desde una funcion sin parametros ni retorno.");
    }

    // * 📖 TEORÍA: Función con parámetros y sin retorno
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Una función con parámetros recibe valores al ser llamada.
    // ? Se usa para reutilizar código y hacerlo más flexible.
    public static void saludar(String nombre) {
        System.out.println("Hola, " + nombre + ". Bienvenido al mundo de Java.");
    }

    public static void presentarAlumno(String nombre, String curso) {
        System.out.println("Alumno: " + nombre + " | Curso: " + curso);
    }

    // * 📖 TEORÍA: Función con parámetros y con retorno
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Una función con retorno devuelve un valor al finalizar su ejecución.
    // ? Se usa cuando necesitamos un resultado calculado dentro de la función.
    public static int sumar(int a, int b) {
        return a + b; // Devuelve la suma de los dos números
    }

    public static double calcularMedia(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3;
    }

    // * 📖 TEORÍA: Sobrecarga de funciones (métodos con el mismo nombre pero distintos parámetros)
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Java permite definir múltiples funciones con el mismo nombre, siempre que tengan diferentes parámetros.
    public static double sumar(double a, double b) {
        return a + b; // Devuelve la suma de los dos números con decimales
    }

    // * 📖 TEORÍA: Función recursiva
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Una función recursiva es aquella que se llama a sí misma.
    // ? Se usa para problemas que pueden dividirse en subproblemas más pequeños.
    public static int factorial(int n) {
        if (n == 0) {
            return 1; // Caso base
        }
        return n * factorial(n - 1); // Llamada recursiva
    }

    // * 📖 TEORÍA: Función que retorna un valor booleano
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Se usa cuando necesitamos una respuesta de tipo verdadero/falso.
    public static boolean esPar(int numero) {
        return numero % 2 == 0; // Devuelve true si el número es par
    }

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    // * 📖 TEORÍA: Función con múltiples valores de retorno usando un array
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Si queremos devolver más de un valor, podemos usar un array.
    public static int[] calcularCuadradoYCubo(int numero) {
        return new int[] { numero * numero, numero * numero * numero };
    }

    // * 📖 TEORÍA: Funciones prácticas para reutilizar cálculos
    // ────────────────────────────────────────────────────────────────────────────────────────
    // ? Una buena función también puede encapsular fórmulas comunes para reutilizarlas fácilmente.
    public static double calcularAreaRectangulo(double base, double altura) {
        return base * altura;
    }

    public static double calcularAreaCirculo(double radio) {
        return Math.PI * radio * radio;
    }

    public static double celsiusAFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double fahrenheitACelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static String generarMensajeResultado(double nota) {
        if (nota >= 5) {
            return "Resultado: APROBADO con nota " + nota;
        }
        return "Resultado: SUSPENSO con nota " + nota;
    }

    public static double redondear(double numero) {
        return Math.round(numero * 100.0) / 100.0;
    }

    // ! ✅ TAREA PARA EL ALUMNO:
    // * 1️⃣ Implementa una función llamada `calcularAreaRectangulo` que reciba base y altura y devuelva el área.
    // * 2️⃣ Implementa una función llamada `esPrimo` que determine si un número es primo o no.
    // * 3️⃣ Crea una función que convierta grados Celsius a Fahrenheit y viceversa.
    // * 4️⃣ Llama a estas funciones en `main()` para probar su funcionamiento.
}
