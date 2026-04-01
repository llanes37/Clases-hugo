# 📘 UT15b — Lambdas, Streams y Optional en Java

> **Autor:** Joaquín Rodríguez Llanes  
> **Nivel:** Java Intermedio-Avanzado (después de Modularidad UT15)  
> **Prerrequisitos:** UT5 (POO), UT13 (Colecciones), UT13b (Genéricos).

---

## 🎯 ¿Qué aprenderás en esta unidad?

| Concepto | Para qué sirve | Dónde lo usarás |
|----------|----------------|-----------------|
| **Lambdas** | Funciones anónimas — código como dato | Filtrar, ordenar, transformar |
| **Method References** | `String::toUpperCase` — atajo de lambda | Streams, Comparator |
| **Interfaces funcionales** | `Predicate`, `Function`, `Consumer`, `Supplier` | Condiciones, transformaciones |
| **Streams API** | Pipeline de datos: filtrar → transformar → recoger | Procesamiento de listas |
| **Operaciones intermedias** | `filter`, `map`, `sorted`, `distinct`, `limit` | Consultas sobre datos |
| **Operaciones terminales** | `collect`, `reduce`, `count`, `forEach` | Resultados finales |
| **Optional\<T\>** | Contenedor que puede estar vacío — adiós a null | `findById()`, búsquedas |

---

## 📋 Secciones del archivo `.java`

| # | Sección | Concepto clave |
|---|---------|----------------|
| 1 | De clase anónima a Lambda | Evolución Java 5 → 8: menos código, más expresivo |
| 2 | Interfaces funcionales | Predicate, Function, Consumer, Supplier |
| 3 | Streams básicos | filter, map, forEach — pipeline encadenado |
| 4 | Streams intermedios | sorted, distinct, limit, skip, peek |
| 5 | Streams terminales | collect, reduce, count, max/min, toMap |
| 6 | Optional\<T\> | Evitar null — of, ofNullable, orElse, map, ifPresent |
| 7 | Caso práctico completo | Lista de alumnos: filtrar, ordenar, estadísticas |

---

## 🧠 Cheatsheet rápido

### Lambdas — Sintaxis
```java
// Sin parámetros
Runnable r = () -> System.out.println("Hola");

// Con 1 parámetro (se omiten paréntesis)
Consumer<String> c = s -> System.out.println(s);

// Con 2 parámetros
Comparator<String> comp = (a, b) -> a.compareTo(b);

// Con bloque de código
Function<Integer, String> f = n -> {
    if (n > 0) return "Positivo";
    else return "No positivo";
};
```

### Interfaces funcionales

| Interfaz | Entrada | Salida | Uso |
|----------|---------|--------|-----|
| `Predicate<T>` | T | boolean | Filtrar |
| `Function<T,R>` | T | R | Transformar |
| `Consumer<T>` | T | void | Ejecutar |
| `Supplier<T>` | — | T | Producir |

### Streams — Pipeline típico
```java
List<String> resultado = nombres.stream()
    .filter(n -> n.length() > 3)        // filtrar
    .map(String::toUpperCase)           // transformar
    .sorted()                           // ordenar
    .distinct()                         // sin duplicados
    .collect(Collectors.toList());      // recoger
```

### Optional — Patrones comunes
```java
// Buscar con seguridad
Optional<Usuario> u = repo.findById(1);
u.ifPresentOrElse(
    user -> System.out.println("Hola " + user),
    () -> System.out.println("No encontrado")
);

// Valor por defecto
String nombre = optional.orElse("Anónimo");

// Transformar si existe
Optional<Integer> longitud = optional.map(String::length);
```

---

## 🔗 Conexión con los proyectos del curso

| Proyecto | Dónde ves estos conceptos |
|----------|---------------------------|
| **proyecto-final** | `alumnos.stream().filter(...).forEach(...)` en controllers |
| **UT19** | `Optional<Usuario>` en `findById()`, lambdas en validaciones |
| **UT20** | `ResponseEntity`, `Optional<>` en servicios, Streams en queries |
| **pf-sqlite** | `@Service` con Streams para filtrar matrículas |

---

## 📋 Tareas incluidas en el código

1. Ordenar ciudades por longitud de nombre con lambda.
2. Crear un `Predicate<String>` para validar emails.
3. Filtrar pares y mostrar su cuadrado con Stream.
4. Top 3 nombres únicos en orden alfabético.
5. Calcular total y media de precios con IVA.
6. Método `buscarPrecio()` con Optional.
7. Caso práctico: alumnos — filtrar menores de 21, media aprobados, joining.

---

*Para compilar y ejecutar:*
```bash
javac UT15b_LambdasYStreams.java
java UT15b_LambdasYStreams
```
