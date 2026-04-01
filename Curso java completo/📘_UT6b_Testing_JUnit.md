# 📘 UT6b — Testing con JUnit 5

> **Autor:** Joaquín Rodríguez Llanes
> **Nivel:** Java Intermedio — 1º DAM / 2º DAM
> **Prerrequisito:** `UT6_ExcepcionesManejoErrores.java` — necesitas dominar excepciones para `assertThrows`.
> **Archivo Java:** `UT6b_Testing_JUnit.java`

---

## 🎯 ¿Por qué el testing es imprescindible?

> [!IMPORTANT]
> El módulo **"Entornos de Desarrollo"** del currículum oficial de DAM **exige** el testing unitario. Sin testing, no trabajarás en ninguna empresa profesional.

Sin testing:
- Ejecutas el `main()` y miras si "parece correcto" → **no escala, no es fiable**
- Un cambio en una parte del código puede romper otras partes **sin que te enteres**
- En equipos grandes es **imposible** trabajar sin tests automáticos

Con testing:
- Tienes una **red de seguridad**: cualquier error de regresión se detecta al instante
- Puedes refactorizar código con **confianza**
- La calidad del software es **medible**

---

## 🧩 Tipos de testing

| Tipo | Qué prueba | Herramienta |
|------|-----------|-------------|
| **Unitario** | Un método o clase aislada | JUnit 5 ← *este archivo* |
| **Integración** | Varios componentes juntos | Spring Test, Testcontainers |
| **End-to-End** | Todo el sistema | Selenium, Playwright |
| **De carga** | Rendimiento bajo estrés | JMeter, Gatling |

---

## 📖 Patrón AAA — La base de todo test

Todos los tests siguen el mismo patrón de 3 pasos:

```java
@Test
void testSumar_dosPositivos() {
    // 1️⃣ ARRANGE — preparar los datos y el objeto
    Calculadora calc = new Calculadora();

    // 2️⃣ ACT — ejecutar el método que queremos probar
    double resultado = calc.sumar(2, 3);

    // 3️⃣ ASSERT — comprobar que el resultado es el esperado
    assertEquals(5.0, resultado);
}
```

> [!TIP]
> Un buen test prueba **una sola cosa**. Si necesitas comprobar más de un comportamiento, escribe más tests.

---

## ⚙️ Configuración de JUnit 5

### OPCIÓN A — Maven (recomendado para proyectos)

Añade al `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>
```

Ejecutar tests:
```powershell
mvn test
mvn -q test   # sin output verboso
```

---

### OPCIÓN B — VS Code con Extension Pack for Java

1. Instala la extensión **Extension Pack for Java** (ya la tienes si sigues el curso).
2. VS Code detecta las clases con `@Test` automáticamente.
3. Aparece el botón `▶️ Run Test` al lado de cada método `@Test`.
4. En el panel lateral **Testing** (icono del matraz) ves todos los tests.

> [!NOTE]
> VS Code descarga **automáticamente** JUnit 5 cuando detecta los `@Test`. No necesitas configurar nada más.

---

### OPCIÓN C — Gradle

```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}

test {
    useJUnitPlatform()
}
```

---

## 📋 Anotaciones de JUnit 5

| Anotación | Descripción |
|-----------|-------------|
| `@Test` | Marca un método como prueba unitaria |
| `@BeforeEach` | Se ejecuta **antes de cada** `@Test` |
| `@AfterEach` | Se ejecuta **después de cada** `@Test` |
| `@BeforeAll` | Se ejecuta **una vez** al inicio (método `static`) |
| `@AfterAll` | Se ejecuta **una vez** al final (método `static`) |
| `@DisplayName("texto")` | Nombre legible en el informe |
| `@ParameterizedTest` | Mismo test con varios valores de entrada |
| `@ValueSource` | Fuente de valores para `@ParameterizedTest` |
| `@Disabled` | Desactiva temporalmente un test |
| `@Nested` | Agrupa tests relacionados en clases internas |

---

## ✅ Assertions más usadas

```java
// Igualdad
assertEquals(5.0, resultado);
assertNotEquals(0, resultado);

// Booleanos
assertTrue(calc.esPositivo(3));
assertFalse(calc.esPar(5));

// Null
assertNull(objeto);
assertNotNull(objeto);

// Colecciones
assertIterableEquals(listaEsperada, listaObtenida);

// Excepciones — MUY IMPORTANTE
assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));

// Con mensaje de error personalizado
assertEquals(5.0, resultado, "La suma de 2+3 debería ser 5");

// Múltiples assertions (no para si falla una)
assertAll(
    () -> assertEquals("Ana", usuario.getNombre()),
    () -> assertEquals("ana@mail.com", usuario.getEmail())
);
```

---

## 🔁 Ciclo de vida completo

```
@BeforeAll       ← se ejecuta UNA vez al inicio
    │
    ├── @BeforeEach  ← antes de test 1
    │   @Test        ← test 1 ejecuta
    │   @AfterEach   ← después de test 1
    │
    ├── @BeforeEach  ← antes de test 2
    │   @Test        ← test 2 ejecuta
    │   @AfterEach   ← después de test 2
    │
    └── (... mismo para cada @Test)

@AfterAll        ← se ejecuta UNA vez al final
```

---

## 💡 Buenas prácticas en testing

> [!TIP]
> Sigue estas reglas y tus tests serán mantenibles y fiables.

1. **Nombres descriptivos**: `testRetirar_saldoInsuficiente_lanzaExcepcion` — se entiende sin leerlo
2. **Un assert por test** (o pocos relacionados): si falla, sabes exactamente qué
3. **Tests independientes**: cada `@Test` debe funcionar solo, sin depender de otros
4. **`@BeforeEach` para estado inicial**: crea objetos frescos antes de cada test
5. **Prueba también los errores**: testing de excepciones con `assertThrows` es tan importante como el caso feliz

---

## ⚠️ Errores típicos al hacer testing

> [!WARNING]
> Estos son los errores más frecuentes de los estudiantes.

### ❌ Error 1: Tests que dependen del orden
```java
// ❌ Test 2 depende de que Test 1 haya modificado el objeto
// Si el orden cambia → falla
private Cuenta cuenta; // compartida entre tests

@Test void test1_depositar() { cuenta.depositar(100); }
@Test void test2_retirar_loQueDepositamos() { cuenta.retirar(100); } // depende de test1!

// ✅ Crear objeto nuevo en @BeforeEach → independientes
@BeforeEach void setUp() { cuenta = new Cuenta(1000); }
```

### ❌ Error 2: Probar el mismo caso de éxito siempre
```java
// ❌ Solo pruebas el caso feliz
@Test void testSumar() { assertEquals(5, calc.sumar(2, 3)); }

// ✅ Prueba también casos límite y errores
@Test void testSumar_negativos() { assertEquals(-5, calc.sumar(-2, -3)); }
@Test void testDividir_porCero_lanzaExcepcion() {
    assertThrows(ArithmeticException.class, () -> calc.dividir(1, 0));
}
```

### ❌ Error 3: Nombre de test sin información
```java
// ❌ ¿Qué prueba esto?
@Test void test1() { ... }

// ✅ El nombre describe qué, con qué y qué debe pasar
@Test void testRetirar_cantidadMayorQueSaldo_lanzaIllegalStateException() { ... }
```

---

## 🗺️ ¿Qué testear de cada clase?

Para cada método público, escribe tests para:

| Caso | Descripción |
|------|-------------|
| **Caso feliz** | Input válido → resultado correcto |
| **Caso límite** | Input en el borde (0, máximo, mínimo...) |
| **Caso de error** | Input inválido → excepción correcta |
| **Estado tras error** | El objeto no se corrompe si falla una operación |

---

## 🔗 Archivos relacionados

| Archivo | Descripción |
|---------|-------------|
| `UT6b_Testing_JUnit.java` | Código con testing manual + JUnit 5 comentado |
| `UT6_ExcepcionesManejoErrores.java` | Prerrequisito: excepciones con try-catch |
| `UT19_ArquitecturaCapas_JDBC/` | Proyecto Maven con JUnit ya configurado |
| `📘_UT19_ArquitecturaCapas.md` | Guía de Maven y JUnit en proyectos reales |
