/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2026
 *  🔹 UT6b: TESTING CON JUNIT 5 — Pruebas automáticas de código Java
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  📋 ¿QUÉ ES TESTING?
 *  Testing = comprobar automáticamente que tu código hace lo que se supone que debe hacer.
 *
 *  Sin testing:   Ejecutas el main() y miras si el resultado parece correcto. ❌
 *  Con testing:   Escribes pruebas que se ejecutan solas y te dicen "PASS" o "FAIL". ✅
 *
 *  🎯 CONCEPTOS QUE SE APRENDEN:
 *  ✅ ¿Por qué es necesario el testing?
 *  ✅ Sección 1: Testing MANUAL (sin librerías — para entender la base)
 *  ✅ Sección 2: Testing con JUnit 5 (el estándar de la industria)
 *  ✅ Anotaciones: @Test, @BeforeEach, @AfterEach, @DisplayName, @ParameterizedTest
 *  ✅ Assertions: assertEquals, assertTrue, assertFalse, assertThrows, assertNull
 *  ✅ Ciclo de vida de un test (setup → test → teardown)
 *
 *  ⚙️ REQUISITO PARA EJECUTAR LA SECCIÓN JUNIT:
 *  JUnit 5 no está incluido en el JDK estándar. Tienes dos opciones:
 *    - OPCIÓN A: Añade al build.gradle: testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
 *    - OPCIÓN B: Con Maven (pom.xml), ver la guía 📘_UT6b_Testing_JUnit.md
 *    - OPCIÓN C: En VS Code con "Extension Pack for Java" → detecta los @Test automáticamente
 *
 *  💡 La Sección 1 (testing manual) funciona SIN ninguna dependencia extra.
 * ******************************************************************************************
 */

// * ════════════════════════════════════════════════════════════════
// * 📦 CLASES QUE VAMOS A TESTEAR
// * ════════════════════════════════════════════════════════════════
//
// ? Primero definimos las clases de "producción" (el código real).
// ? Luego escribimos las pruebas sobre ellas.

// ═══════════════════════════════════════════════════════════════
// * 🧮 CLASE: Calculadora
// ═══════════════════════════════════════════════════════════════
// ? Clase sencilla con operaciones matemáticas.
// ? Es el ejemplo perfecto para aprender testing porque:
// ?   - Los resultados son fáciles de comprobar.
// ?   - Tiene un caso de error: división por cero.

class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    // ? Este método puede lanzar una excepción — interesante para testing
    public double dividir(double a, double b) {
        if (b == 0) {
            // ! Si el divisor es 0, lanzamos excepción
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return a / b;
    }

    public boolean esPositivo(double n) {
        return n > 0;
    }

    public boolean esPar(int n) {
        return n % 2 == 0;
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🏦 CLASE: CuentaBancaria
// ═══════════════════════════════════════════════════════════════
// ? Clase con estado (saldo) y reglas de negocio.
// ? Testear esta clase es crítico porque un error puede costar dinero real.

class CuentaBancaria {

    private String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a depositar debe ser positiva");
        }
        saldo += cantidad;
    }

    public void retirar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
        }
        if (cantidad > saldo) {
            throw new IllegalStateException("Saldo insuficiente. Saldo: " + saldo);
        }
        saldo -= cantidad;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}

// ══════════════════════════════════════════════════════════════════════════
// *
// * ════════════════════════════════════════════════════════════════════
// * 🧪 SECCIÓN 1: TESTING MANUAL (sin JUnit — funciona sin dependencias)
// * ════════════════════════════════════════════════════════════════════
// *
// ? Antes de usar JUnit, vamos a entender QUÉ hace un test:
// ? 1. Preparar el objeto que vamos a probar (ARRANGE)
// ? 2. Ejecutar el método que queremos probar (ACT)
// ? 3. Comprobar que el resultado es el esperado (ASSERT)
// ?
// ? A esto se le llama el PATRÓN AAA: Arrange → Act → Assert

class TestManual {

    // ? Contador de resultados
    private static int testsTotal = 0;
    private static int testsPasados = 0;
    private static int testsFallados = 0;

    // ? Método de aserción manual (simula lo que hace JUnit internamente)
    private static void assertEquals(String nombreTest, double esperado, double obtenido) {
        testsTotal++;
        if (Math.abs(esperado - obtenido) < 0.0001) {
            System.out.println("  ✅ PASS — " + nombreTest);
            testsPasados++;
        } else {
            System.out.println("  ❌ FAIL — " + nombreTest);
            System.out.println("       Esperado: " + esperado + " | Obtenido: " + obtenido);
            testsFallados++;
        }
    }

    private static void assertTrue(String nombreTest, boolean condicion) {
        testsTotal++;
        if (condicion) {
            System.out.println("  ✅ PASS — " + nombreTest);
            testsPasados++;
        } else {
            System.out.println("  ❌ FAIL — " + nombreTest + " (la condición era false)");
            testsFallados++;
        }
    }

    // ? Este método verifica que SE LANZA una excepción
    private static void assertThrows(String nombreTest, Runnable accion) {
        testsTotal++;
        try {
            accion.run();
            System.out.println("  ❌ FAIL — " + nombreTest + " (debía lanzar excepción pero no lo hizo)");
            testsFallados++;
        } catch (Exception e) {
            System.out.println("  ✅ PASS — " + nombreTest + " (" + e.getClass().getSimpleName() + ")");
            testsPasados++;
        }
    }

    public static void ejecutarTodos() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║   🧪 TESTING MANUAL — Sin JUnit                         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");

        Calculadora calc = new Calculadora();

        System.out.println("\n📌 Tests de Calculadora:");
        // ? ARRANGE: ya tenemos calc
        // ? ACT + ASSERT: ejecutamos y comprobamos en una sola línea

        assertEquals("sumar 2+3 = 5", 5.0, calc.sumar(2, 3));
        assertEquals("sumar negativos -1+(-2)", -3.0, calc.sumar(-1, -2));
        assertEquals("restar 10-4 = 6", 6.0, calc.restar(10, 4));
        assertEquals("multiplicar 3*4 = 12", 12.0, calc.multiplicar(3, 4));
        assertEquals("dividir 10/2 = 5", 5.0, calc.dividir(10, 2));

        // ? Test de excepción: esperamos que dividir por 0 lance ArithmeticException
        assertThrows("dividir por 0 lanza excepción", () -> calc.dividir(5, 0));

        assertTrue("3 es positivo", calc.esPositivo(3));
        assertTrue("4 es par", calc.esPar(4));
        assertTrue("5 NO es par", !calc.esPar(5));

        System.out.println("\n📌 Tests de CuentaBancaria:");

        CuentaBancaria cuenta = new CuentaBancaria("Ana García", 1000.0);
        assertEquals("saldo inicial = 1000", 1000.0, cuenta.getSaldo());

        cuenta.depositar(500);
        assertEquals("tras depositar 500 = 1500", 1500.0, cuenta.getSaldo());

        cuenta.retirar(200);
        assertEquals("tras retirar 200 = 1300", 1300.0, cuenta.getSaldo());

        // ? Excepción esperada: no hay suficiente saldo
        assertThrows("retirar más de lo disponible lanza excepción",
                () -> cuenta.retirar(99999));

        // ? Excepción esperada: saldo inicial negativo
        assertThrows("crear cuenta con saldo negativo lanza excepción",
                () -> new CuentaBancaria("Test", -100));

        // * ── Resumen ──
        System.out.println("\n┌───────────────────────────────────┐");
        System.out.println("│ 📊 RESUMEN DE TESTS               │");
        System.out.println("│   Total:   " + testsTotal + "                        │");
        System.out.println("│   ✅ PASS: " + testsPasados + "                        │");
        System.out.println("│   ❌ FAIL: " + testsFallados + "                        │");
        System.out.println("└───────────────────────────────────┘");
    }
}

// ══════════════════════════════════════════════════════════════════════════
// *
// * ════════════════════════════════════════════════════════════════════
// * 🧪 SECCIÓN 2: TESTING CON JUNIT 5 (el estándar de la industria)
// * ════════════════════════════════════════════════════════════════════
// *
// ? JUnit 5 hace todo lo que hicimos arriba, pero de forma estándar,
// ? integrado con Maven/Gradle/VS Code y con informes bonitos.
// ?
// ? Para activarlo: ver las instrucciones en 📘_UT6b_Testing_JUnit.md
// ?
// ? ANOTACIONES PRINCIPALES:
// ? @Test → marca un método como prueba
// ? @BeforeEach → se ejecuta ANTES de cada @Test (setup)
// ? @AfterEach → se ejecuta DESPUÉS de cada @Test (cleanup)
// ? @BeforeAll → se ejecuta UNA VEZ al inicio de todos los tests
// ? @DisplayName → nombre legible del test en el informe
// ? @ParameterizedTest → ejecuta el mismo test con varios valores
// ?
// ? ASSERTIONS MÁS USADAS:
// ? assertEquals(esperado, obtenido) → valor igual
// ? assertNotEquals(a, b) → valores distintos
// ? assertTrue(condicion) → condición verdadera
// ? assertFalse(condicion) → condición falsa
// ? assertNull(objeto) → el objeto es null
// ? assertNotNull(objeto) → el objeto NO es null
// ? assertThrows(TipoExcepcion, lambda) → se lanza la excepción

/*
 * DESCOMENTAR CUANDO TENGAS JUNIT 5 EN EL CLASSPATH:
 *
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.AfterEach;
 * import org.junit.jupiter.api.DisplayName;
 * import org.junit.jupiter.params.ParameterizedTest;
 * import org.junit.jupiter.params.provider.ValueSource;
 * import static org.junit.jupiter.api.Assertions.*;
 *
 * @DisplayName("🧮 Tests de Calculadora")
 * class CalculadoraTest {
 *
 * private Calculadora calc; // ? objeto que se prueba (SUT = System Under Test)
 *
 * @BeforeEach // ? se ejecuta ANTES de CADA test
 * void setUp() {
 * calc = new Calculadora(); // ? creamos una instancia fresca para cada test
 * System.out.println("  ▶️ Preparando test...");
 * }
 *
 * @AfterEach // ? se ejecuta DESPUÉS de CADA test
 * void tearDown() {
 * System.out.println("  ⏹️ Test finalizado.");
 * }
 *
 * @Test
 * 
 * @DisplayName("Sumar dos positivos da resultado correcto")
 * void testSumar_dosPositivos() {
 * // ? ARRANGE — ya hecho en @BeforeEach
 * // ? ACT
 * double resultado = calc.sumar(2, 3);
 * // ? ASSERT
 * assertEquals(5.0, resultado);
 * }
 *
 * @Test
 * 
 * @DisplayName("Sumar número negativo y positivo")
 * void testSumar_negativoYPositivo() {
 * assertEquals(1.0, calc.sumar(-2, 3));
 * }
 *
 * @Test
 * 
 * @DisplayName("Dividir por cero lanza ArithmeticException")
 * void testDividir_porcero_lanzaExcepcion() {
 * // ? assertThrows verifica que SE lanza la excepción
 * // ? Si NO se lanza → el test FALLA
 * assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));
 * }
 *
 * @Test
 * 
 * @DisplayName("Número positivo es positivo")
 * void testEsPositivo_conPositivo() {
 * assertTrue(calc.esPositivo(5));
 * }
 *
 * @Test
 * 
 * @DisplayName("Cero NO es positivo")
 * void testEsPositivo_conCero() {
 * assertFalse(calc.esPositivo(0));
 * }
 *
 * // ? @ParameterizedTest: ejecuta el mismo test con varios valores
 * // ? Evita repetir el mismo test cambiando solo el valor de entrada
 * 
 * @ParameterizedTest
 * 
 * @ValueSource(ints = {2, 4, 6, 8, 100})
 * 
 * @DisplayName("Números pares → esPar() devuelve true")
 * void testEsPar_variosNumeroPares(int numero) {
 * assertTrue(calc.esPar(numero));
 * }
 *
 * @ParameterizedTest
 * 
 * @ValueSource(ints = {1, 3, 5, 7, 99})
 * 
 * @DisplayName("Números impares → esPar() devuelve false")
 * void testEsPar_variosNumerosImpares(int numero) {
 * assertFalse(calc.esPar(numero));
 * }
 * }
 *
 *
 * @DisplayName("🏦 Tests de CuentaBancaria")
 * class CuentaBancariaTest {
 *
 * private CuentaBancaria cuenta;
 *
 * @BeforeEach
 * void setUp() {
 * // ? Cada test empieza con una cuenta NUEVA con 1000€
 * // ? → los tests son INDEPENDIENTES entre sí
 * cuenta = new CuentaBancaria("Ana García", 1000.0);
 * }
 *
 * @Test
 * 
 * @DisplayName("Saldo inicial correcto al crear la cuenta")
 * void testCrear_saldoInicialCorrecto() {
 * assertEquals(1000.0, cuenta.getSaldo());
 * assertEquals("Ana García", cuenta.getTitular());
 * }
 *
 * @Test
 * 
 * @DisplayName("Crear cuenta con saldo negativo lanza excepción")
 * void testCrear_saldoNegativo_lanzaExcepcion() {
 * assertThrows(IllegalArgumentException.class,
 * () -> new CuentaBancaria("Test", -100));
 * }
 *
 * @Test
 * 
 * @DisplayName("Depositar 500€ → saldo queda en 1500€")
 * void testDepositar_aumentaSaldo() {
 * cuenta.depositar(500.0);
 * assertEquals(1500.0, cuenta.getSaldo());
 * }
 *
 * @Test
 * 
 * @DisplayName("Depositar cantidad negativa lanza excepción")
 * void testDepositar_cantidadNegativa_lanzaExcepcion() {
 * assertThrows(IllegalArgumentException.class, () -> cuenta.depositar(-50));
 * }
 *
 * @Test
 * 
 * @DisplayName("Retirar 200€ → saldo queda en 800€")
 * void testRetirar_reduceSaldo() {
 * cuenta.retirar(200.0);
 * assertEquals(800.0, cuenta.getSaldo());
 * }
 *
 * @Test
 * 
 * @DisplayName("Retirar más del saldo disponible lanza excepción")
 * void testRetirar_saldoInsuficiente_lanzaExcepcion() {
 * assertThrows(IllegalStateException.class, () -> cuenta.retirar(9999.0));
 * }
 *
 * @Test
 * 
 * @DisplayName("Saldo NO cambia si la operación falla")
 * void testRetirar_saldoNoCambiaEnFallo() {
 * try {
 * cuenta.retirar(9999.0);
 * } catch (IllegalStateException e) {
 * // ? Ignoramos la excepción intencionalmente
 * }
 * // ? El saldo debe seguir siendo 1000 (la operación falló)
 * assertEquals(1000.0, cuenta.getSaldo());
 * }
 *
 * @Test
 * 
 * @DisplayName("Varios depósitos y retiros mantienen saldo correcto")
 * void testOperacionesMultiples_saldoCorrecto() {
 * cuenta.depositar(500); // 1500
 * cuenta.retirar(200); // 1300
 * cuenta.depositar(100); // 1400
 * cuenta.retirar(400); // 1000
 * assertEquals(1000.0, cuenta.getSaldo());
 * }
 * }
 */

// ═══════════════════════════════════════════════════════════════
// * 🖥️ PROGRAMA PRINCIPAL
// ═══════════════════════════════════════════════════════════════

public class UT6b_Testing_JUnit {

    public static void main(String[] args) {

        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║  🧪 UT6b: TESTING EN JAVA                                  ║");
        System.out.println("║  Aprende a probar tu código de forma automática            ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

        // * ── SECCIÓN 1: Testing manual (sin dependencias) ──
        TestManual.ejecutarTodos();

        System.out.println("\n──────────────────────────────────────────────────────────────");
        System.out.println("📌 Para activar la Sección 2 (JUnit 5):");
        System.out.println("   1. Descomenta las importaciones y la clase de prueba.");
        System.out.println("   2. Añade JUnit 5 a tu proyecto (ver 📘_UT6b_Testing_JUnit.md).");
        System.out.println("   3. En VS Code: el ▶️ aparece al lado de cada @Test.");
        System.out.println("──────────────────────────────────────────────────────────────");

        // ! ✅ TAREA ALUMNO:
        // * 1. Añade un método factorial(int n) a Calculadora.
        // * Debe lanzar IllegalArgumentException si n < 0.
        // * Escribe al menos 3 tests en TestManual para comprobarlo.
        // * 2. Crea una clase Validador con métodos:
        // * - esEmail(String email): true si contiene "@" y "."
        // * - esDNI(String dni): true si tiene 8 dígitos + 1 letra
        // * Escribe tests para cada método (casos válidos e inválidos).
        // * 3. (Con JUnit): Descomenta las clases de JUnit y ejecútalas
        // * desde VS Code usando el botón ▶️ que aparece junto a @Test.
    }
}
