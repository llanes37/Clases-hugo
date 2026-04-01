---
description: Reglas y patrones para generar o modificar archivos del Curso Java Completo (1º-2º DAM/DAW). Autor: Joaquín Rodríguez Llanes.
---

# 📚 Curso Java Completo — Instrucciones para IA

> Este archivo define las convenciones, patrones y estilo que DEBE seguir cualquier IA al crear o modificar archivos de este curso de Java.
> **Leer COMPLETO antes de generar cualquier archivo.**

---

## 🎯 Contexto del proyecto

- **Tipo:** Curso de programación Java para alumnos de 1º y 2º DAM/DAW (España).
- **Autor:** Joaquín Rodríguez Llanes.
- **Estructura:** Archivos `.java` sueltos (UT0–UT20) + proyectos Maven (UT19, UT20) + guías `.md`.
- **IDE principal:** Visual Studio Code con extensiones Java + Better Comments.
- **JDK:** OpenJDK 23+ (usa switch con `->`, `List.of()`, `String.repeat()`, records, etc.).
- **Extensiones instaladas:** Extension Pack for Java, Better Comments, Code Runner, SQLite Viewer.

---

## 👤 Perfil del alumno objetivo

Esto es CRÍTICO para calibrar el nivel de los archivos:

| Unidad | Nivel alumno | Qué sabe | Qué NO sabe todavía |
|--------|-------------|----------|---------------------|
| UT0–UT3 | **Principiante absoluto** | Nada | Variables, bucles, funciones |
| UT4 | **Principiante** | Variables, if, switch, bucles | Funciones, clases |
| UT5 | **Básico** | Funciones | POO, colecciones |
| UT5b–UT5c | **Básico-Medio** | Clases simples | Herencia, colecciones avanzadas |
| UT6–UT12 | **Medio** | POO, excepciones | Streams, BD, APIs |
| UT13–UT15 | **Medio-Avanzado** | Colecciones | Streams, patrones, lambdas |
| UT16 | **Avanzado** | Todo lo anterior | BD, APIs, Spring |
| UT17–UT18 | **Avanzado** | Java completo | Arquitectura, APIs REST |
| UT19–UT20 | **Profesional** | JDBC, Maven | Spring avanzado, microservicios |

**Regla de oro:** Si un alumno de UT5 no puede entender el archivo → está demasiado complejo.
**Regla de simplicidad:** Explica el "por qué" antes del "cómo". Analogías del mundo real siempre.

---

## 📝 Regla FUNDAMENTAL: nombre de archivo = nombre de clase

```java
// ✅ CORRECTO: archivo UT5_ClasesObjetos.java
public class UT5_ClasesObjetos { ... }

// ❌ INCORRECTO: archivo UT5_ClasesObjetos.java pero dentro dice:
public class Main { ... }  // NO COMPILA
```

**SIEMPRE** verificar que el nombre del archivo `.java` coincida EXACTAMENTE con el `public class` que contiene.

---

## 🎨 Better Comments (OBLIGATORIO en todo archivo .java)

Los comentarios usan la extensión "Better Comments" de VS Code. Cada prefijo tiene un color distinto:

| Prefijo | Color | Uso | Ejemplo |
|---------|-------|-----|---------|
| `// *` | Verde | Títulos, flujo principal, acciones | `// * Crear objetos de la clase Persona` |
| `// ?` | Azul | Teoría, explicaciones, contexto | `// ? Una clase es una plantilla para crear objetos` |
| `// !` | Rojo | Advertencias, errores, peligros | `// ! Si haces esto → NullPointerException` |
| `// TODO` | Naranja | Tareas pendientes del alumno | `// TODO Escribe aquí tu solución` |
| `// ! ✅ TAREA ALUMNO:` | Rojo | Tarea para el alumno con instrucciones | Seguido de `// *` con pasos numerados |

### Uso correcto en bloques teóricos

```java
// * ════════════════════════════════════════════════════════════════
// * 📖 TEORÍA: NOMBRE DEL CONCEPTO
// * ════════════════════════════════════════════════════════════════
//
// ? Explicación clara y sencilla del concepto.
// ? Usa analogías del mundo real cuando sea posible.
// ? Ejemplo: "una clase es como el plano de una casa".
// ?
// ! Cuidado: advertencia o error común aquí.
// ! Ejemplo: "sin encapsulación, cualquiera puede poner edad = -5".
```

### Uso correcto en código ejecutable

```java
// * Crear objetos de la clase Persona
Persona p1 = new Persona("Ana", 25);  // ? Constructor con 2 parámetros
p1.mostrarInformacion();               // ? Llama al método del objeto

// ! Intentar poner edad negativa → el setter lo rechaza
p1.setEdad(-5);  // ? Mostrará mensaje de error
```

### Patrón específico para ejercicios tipo examen (UT5 POO)

Cuando el archivo es un ejercicio de práctica o recuperación, el enunciado va DOS veces:
1. **Al inicio** del archivo (bloque completo, para que el alumno lea el ejercicio entero).
2. **Justo antes de cada clase** (resumido, para que el alumno lo vea donde programa).

```java
// ? 📋 ENUNCIADO:
// ? Implementa la clase X. Tiene los atributos:
// ?   - atributo1 (Tipo): descripción.
// ?   - atributo2 (Tipo): descripción.
// ?
// ? Constructor: recibe ...
// ? Métodos: ...
```

---

## 📂 Estructura de archivos .java

### Cabecera OBLIGATORIA (primeras líneas de todo archivo)

```java
/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2026
 *  🔹 UNIDAD X: TÍTULO DE LA UNIDAD
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  En esta práctica aprenderás a:
 *
 *  ✅ Punto 1 que aprenderá el alumno.
 *  ✅ Punto 2 que aprenderá el alumno.
 *  ✅ Punto 3 que aprenderá el alumno.
 *
 *  🚀 ¡Explora, experimenta y mejora el código!
 * ******************************************************************************************
 */
```

### Dos tipos de archivo .java

#### TIPO A: Sencillo (sin menú) — para conceptos introductorios

- Se ejecuta de arriba a abajo (sin menú interactivo).
- Teoría con `// *` y `// ?` ANTES del código.
- Código breve y directo en `main()`.
- Tarea del alumno al final del `main()`.
- Clases auxiliares DESPUÉS de la clase principal.
- **Ejemplos:** `UT5_ClasesObjetos.java`, `UT5_HerenciaPolimorfismoInterfaces.java`, `UT7_CadenasTexto.java`.

#### TIPO B: Con menú interactivo — para temas con varias secciones

- Menú `do-while` + `switch` con emojis y caja ASCII.
- Cada sección en un método `static` separado.
- Cada sección tiene su bloque teórico `// * 📖 TEORÍA:` + `// ?`.
- Cada sección termina con `// ! ✅ TAREA ALUMNO:`.
- **Ejemplos:** `UT13b_Genericos.java`, `UT15b_LambdasYStreams.java`, `UT15c_PatronesDiseno.java`.

```java
// * MENÚ PRINCIPAL — ELIGE QUÉ SECCIÓN EJECUTAR
do {
    System.out.println("\n╔═══════════════════════════════════════════════════════╗");
    System.out.println("║  📦 UTxx — TÍTULO                                    ║");
    System.out.println("╚═══════════════════════════════════════════════════════╝");
    System.out.println("1. 📖 Sección 1");
    System.out.println("2. 🧩 Sección 2");
    System.out.println("0. 👋 Salir");
    System.out.print("👉 Elige una opción: ");
    opcion = sc.nextInt();

    switch (opcion) {
        case 1 -> seccion1();
        case 2 -> seccion2();
        case 0 -> System.out.println("👋 ¡Hasta luego!");
        default -> System.out.println("❌ Opción no válida.");
    }
} while (opcion != 0);
```

#### TIPO C: Ejercicio tipo examen/recuperación (NUEVO)

- Enunciado completo al inicio (bloques `// ? 📋 ENUNCIADO:`).
- Enunciado resumido justo antes de cada clase.
- Clases con código completo Y explicado con `// ?` en cada método.
- `main()` breve con solo las pruebas esenciales.
- Tarea del alumno al final.
- **Ejemplos:** `UT5_POO_EjemploResuelto_Ordenadores.java`, `UT5_POO_EjemploResuelto_RedSocial.java`.

#### TIPO D: Práctica guiada con TODOs (NUEVO)

- Enunciado completo al inicio.
- Enunciado justo antes de cada clase.
- Código vacío con TODOs numerados (`// TODO 1: ...`, `// TODO 2: ...`).
- Cada TODO describe EXACTAMENTE qué implementar + una pista.
- `main()` con pasos numerados de prueba (también TODOs).
- **Ejemplos:** `UT5_POO_Practica_Ordenadores.java`, `UT5_POO_Practica_RedSocial.java`.

### Separadores de secciones

```java
// ═══════════════════════════════════════════════════════════════
// * 📖 SECCIÓN X: TÍTULO DE LA SECCIÓN
// ═══════════════════════════════════════════════════════════════
```

### Separadores de clases auxiliares

```java
// ═══════════════════════════════════════════════════════════════
// * 🚗 CLASE COCHE
// ═══════════════════════════════════════════════════════════════
// ? Breve descripción de qué representa esta clase.
```

---

## 📂 Estructura de archivos .md (guías)

Cada unidad DEBE tener una guía `📘_UTxx_NombreUnidad.md`. Estructura obligatoria:

```markdown
# 📘 UTxx — Título completo

> **Autor:** Joaquín Rodríguez Llanes
> **Nivel:** Java Básico/Intermedio/Avanzado — 1º DAM / 2º DAM
> **Prerrequisito:** `UTxx_Nombre.java` — qué debe saber antes.
> **Archivo Java:** `UTxx_Nombre.java`

---

## 🎯 ¿Qué aprenderás en esta unidad?

| Concepto | Descripción |
|---------|-------------|
| ... | ... |

---

## 📖 Conceptos clave con código

(Bloques de código comentados explicando cada concepto)

---

## ✅ Tarea para el alumno

> [!TIP]
> Empieza por el más sencillo e implémentalos en orden.

1. Tarea 1...
2. Tarea 2...

---

## 🔗 Archivos relacionados

| Archivo | Tipo | Descripción |
|---------|------|-------------|
| ... | 📄 / 📝 | ... |
```

### Alertas GitHub permitidas en .md

```markdown
> [!NOTE]      ← información de contexto (azul)
> [!TIP]       ← consejo o buena práctica (verde)
> [!IMPORTANT] ← requisito o paso crítico (morado)
> [!WARNING]   ← cuidado, puede fallar (amarillo)
> [!CAUTION]   ← peligro, puede romper cosas (rojo)
```

---

## 🔢 Numeración de unidades

| Rango | Contenido | Nivel |
|-------|-----------|-------|
| UT0–UT4 | Fundamentos (variables, control, bucles, funciones) | 🟢 Básico |
| UT4b | Funciones avanzadas | 🟢 Básico |
| UT5 | POO: Clases, Objetos básicos | 🟡 Medio |
| UT5b | POO: Composición, ArrayList, `static` | 🟡 Medio |
| UT5c | POO: Herencia, polimorfismo, interfaces | 🟡 Medio |
| UT6 | Excepciones y manejo de errores | 🟡 Medio |
| UT6b | Testing con JUnit 5 | 🟡 Medio |
| UT7–UT11 | Strings, Arrays, Regex, Math, Fechas | 🟡 Medio |
| UT12 | Ficheros | 🟡 Medio |
| UT12b | Consumo de APIs REST (HttpClient) | 🟠 Medio-Alto |
| UT13 | Colecciones: List, Map, Set | 🟠 Medio-Alto |
| UT13b | Genéricos | 🟠 Medio-Alto |
| UT14–UT15 | Enum, Modularidad, Paquetes | 🟠 Medio-Alto |
| UT15b | Lambdas y Streams | 🔴 Avanzado |
| UT15c | Patrones de diseño | 🔴 Avanzado |
| UT16 | Proyecto final (Java puro) | 🔴 Avanzado |
| UT17–UT18 | SQL con JDBC/SQLite | 🔴 Avanzado |
| UT19 | Arquitectura por capas (Maven) | 🔴 Avanzado |
| UT20 | Spring Boot API REST | 🔴 Avanzado |

**Para insertar nuevas unidades sin renumerar:** usar sufijos con letra: `UT6b`, `UT12b`, `UT15c`.

---

## 📁 Archivos de ejercicios POO (patrón clave del curso)

Cada bloque temático de POO tiene 3 tipos de archivo. SIEMPRE crearlos en este orden:

```
1. UTx_POO_EjemploResuelto_[Tema].java  ← código completo explicado (TIPO C)
2. UTx_POO_Practica_[Tema].java          ← código vacío con TODOs (TIPO D)
3. UTx_POO_Ejercicios_[Nombre].java      ← enunciados solo, el alumno lo resuelve
```

El enunciado de cada clase va **dos veces**:
- Una al inicio del archivo (completo)
- Una justo antes de la declaración de la clase (resumido)

---

## 🌐 APIs gratuitas aprobadas para ejemplos (sin API key)

Cuando se necesiten ejemplos de APIs REST, usar SIEMPRE estas (sin registro):

| API | URL base | Uso recomendado |
|-----|----------|----------------|
| **PokéAPI** | `https://pokeapi.co/api/v2/` | GET Pokémon por nombre o ID |
| **JSONPlaceholder** | `https://jsonplaceholder.typicode.com` | GET/POST/PUT/DELETE con datos ficticios |
| **Open-Meteo** | `https://api.open-meteo.com/v1/` | El tiempo (sin clave) |
| **RestCountries** | `https://restcountries.com/v3.1/` | Datos de países |
| **TheMealDB** | `https://www.themealdb.com/api/json/v1/1/` | Recetas |

**NUNCA** usar APIs que requieren registro o tarjeta de crédito en los ejemplos del curso.

---

## 🎨 Estilo de código Java

- **Switch moderno:** Usar `case X ->` (no `case X:` con `break`).
- **var:** NO usar `var` — siempre tipos explícitos (más didáctico para alumnos).
- **Imports:** Explícitos o con `*` para `java.util.*`. Nunca dejar imports sin usar.
- **Emojis en output:** Usar emojis para hacer la consola más visual: 📌 📦 ✅ ❌ ⚠️ 👤 🔹 📊 🏦 🎮 etc.
- **Cajas ASCII en output:** Para resúmenes o fichas de objetos:

```java
System.out.println("  ┌──────────────────────────────────────────┐");
System.out.println("  │ 🏦 RESUMEN DE CUENTA                   │");
System.out.println("  ├──────────────────────────────────────────┤");
System.out.println("  │ Titular: " + titular);
System.out.println("  └──────────────────────────────────────────┘");
```

- **Formato de precios:** `String.format("%.2f€", precio)`
- **Validaciones:** Siempre en setters y métodos de negocio, con mensajes claros (`⚠️`, `❌`).
- **toString():** SIEMPRE implementar en clases de dominio para poder hacer `System.out.println(objeto)`.
- **equals() + hashCode():** SIEMPRE van JUNTOS. Mismos campos en ambos métodos. Usar `Objects.hash()`.
- **Iterator para borrado seguro:** Cuando se borra de colecciones durante iteración, SIEMPRE usar `Iterator`, NUNCA `for-each` con `.remove()`.
- **Comparar arrays en equals:** Usar `Arrays.equals()` y `Arrays.hashCode()`, NUNCA `.equals()` directamente.

---

## 📋 Patrón de clase completa (estándar del curso)

Estructura interna de cada clase de dominio:

```java
class NombreClase {

    // * Atributos privados
    private Tipo atributo1;  // ? descripción breve
    private Tipo atributo2;  // ? descripción breve

    // * Constructor
    // ? qué recibe, qué valores por defecto, qué inicializa
    public NombreClase(Tipo param1, Tipo param2) {
        this.atributo1 = param1;
        this.atributo2 = param2;
    }

    // * Getters (y setters solo si el enunciado lo pide)
    public Tipo getAtributo1() { return atributo1; }

    // * Métodos de negocio (con // ? explicando la lógica)
    public TipoRetorno nombreMetodo(Tipo param) {
        // ? Explicación de por qué se hace así
        ...
    }

    // * equals — campos que definen igualdad
    @Override
    public boolean equals(Object obj) { ... }

    // * hashCode — MISMOS campos que equals
    @Override
    public int hashCode() { return Objects.hash(campo1, campo2); }

    // * toString — muestra todos los datos relevantes
    @Override
    public String toString() { ... }
}
```

---

## 📋 Patrón de clase gestora HashMap (estándar del curso)

```java
class NombreGestora {

    // * HashMap — clave → lista de valores
    private HashMap<ClaseClave, List<ClaseValor>> mapa;

    public NombreGestora() {
        this.mapa = new HashMap<>();
    }

    // * addClave — Devuelve boolean
    // ? false si ya existía. containsKey() usa equals/hashCode de la clave.
    // ! Si ya existía, NO borrar sus datos asociados.
    public boolean addClave(ClaseClave c) {
        if (mapa.containsKey(c)) return false;
        mapa.put(c, new ArrayList<>());
        return true;
    }

    // * eliminar con Iterator — NUNCA for-each con .remove()
    public HashSet<ClaseValor> eliminar(ClaseClave c, /* condición */) {
        HashSet<ClaseValor> eliminados = new HashSet<>();
        List<ClaseValor> lista = mapa.get(c);
        if (lista == null) return eliminados;
        Iterator<ClaseValor> it = lista.iterator();
        while (it.hasNext()) {
            ClaseValor v = it.next();
            if (/* condición */) {
                eliminados.add(v);
                it.remove();  // ? seguro, no lanza ConcurrentModificationException
            }
        }
        return eliminados;
    }
}
```

---

## 🧪 Patrón de testing (UT6b)

Cuando se creen archivos de testing, seguir este patrón:

1. **Primero la clase bajo test** (no en el archivo de test, sino como referencia).
2. **Sección de testing manual** (funciona sin JUnit — primera sección siempre ejecutable).
3. **Sección de JUnit 5 comentada** — lista para descomentar con las dependencias.
4. Cada test sigue el patrón AAA: `// ? ARRANGE`, `// ? ACT`, `// ? ASSERT`.
5. Probar siempre: caso feliz + caso límite + caso de error con `assertThrows`.

---

## 🚫 Lo que NO hacer

1. **NO** crear clases con nombre `Main` → siempre el nombre de la unidad.
2. **NO** usar paréntesis `()` ni espacios en nombres de archivo `.java`.
3. **NO** dejar código sin comentarios Better Comments.
4. **NO** crear archivos `.java` sin la cabecera estándar del curso.
5. **NO** mezclar contenido de herencia en archivos de POO básica (y viceversa).
6. **NO** duplicar tareas (si la tarea está en el `main()`, no repetirla al final).
7. **NO** usar `System.out.println` genéricos — siempre con emojis y formato claro.
8. **NO** usar `var` — tipos siempre explícitos.
9. **NO** devolver colecciones internas directamente desde getters — devolver String o copia.
10. **NO** usar `for-each` para borrar de colecciones — usar `Iterator`.
11. **NO** olvidar `hashCode()` cuando se implementa `equals()` — siempre van juntos.
12. **NO** usar APIs con registro obligatorio en ejemplos de código.
13. **NO** crear un ejercicio de práctica (TIPO D) sin su correspondiente resuelto (TIPO C).
14. **NO** poner el enunciado solo arriba — también antes de cada clase.
15. **NO** usar la fecha 2025 — la fecha correcta es **2026**.
