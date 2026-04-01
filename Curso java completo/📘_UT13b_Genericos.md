# 📘 UT13b — Genéricos en Java (`<T>`, Wildcards, Clases y Métodos Genéricos)

> **Autor:** Joaquín Rodríguez Llanes  
> **Nivel:** Java Intermedio (después de Colecciones UT13)  
> **Prerrequisitos:** UT5 (POO), UT13 (Colecciones), UT14 (Enum).

---

## 🎯 ¿Qué aprenderás en esta unidad?

| Concepto | Para qué sirve | Dónde lo usarás |
|----------|----------------|-----------------|
| **`<T>` parámetro de tipo** | Crear clases/métodos que funcionan con cualquier tipo | `List<String>`, `Repository<T>` |
| **Clase genérica** | `Caja<T>`, `Par<K,V>` — contenedores reutilizables | Entidades, DTOs |
| **Método genérico** | Un método que acepta cualquier tipo | Utilidades, logging |
| **Bounded Type** | `<T extends Number>` — limitar qué tipos se aceptan | Cálculos numéricos |
| **Wildcards** | `<?>`, `<? extends>`, `<? super>` — flexibilidad en parámetros | Métodos de librería |
| **Interfaz genérica** | `Repositorio<T>` — contrato para cualquier entidad | Proyectos UT19, UT20 |

---

## 📋 Secciones del archivo `.java`

| # | Sección | Concepto clave |
|---|---------|----------------|
| 1 | Sin genéricos vs con | Por qué los genéricos son necesarios |
| 2 | Clase genérica `Caja<T>` | Crear tu propio contenedor genérico |
| 3 | Método genérico `<T>` | Métodos que aceptan cualquier tipo |
| 4 | Bounded Type `<T extends>` | Limitar el tipo a Number o Comparable |
| 5 | Wildcards `<?>` | Comodines para máxima flexibilidad |
| 6 | Par genérico `Par<K,V>` | Dos parámetros de tipo |
| 7 | Interfaz genérica `Repositorio<T>` | El patrón Repository del curso |

---

## 🧠 Conceptos clave

### ¿Qué es un genérico?
```java
// Sin genéricos → peligro
List lista = new ArrayList();
lista.add("Hola");
lista.add(42);  // Mezcla tipos → error en runtime

// Con genéricos → seguridad
List<String> lista = new ArrayList<>();
lista.add("Hola");
// lista.add(42);  // Error de COMPILACIÓN → te avisa antes
```

### Convenciones de nombres
| Letra | Significado | Ejemplo |
|-------|-------------|---------|
| `T` | Type (tipo genérico) | `Caja<T>` |
| `E` | Element (en colecciones) | `List<E>` |
| `K` | Key (clave en mapas) | `Map<K,V>` |
| `V` | Value (valor en mapas) | `Map<K,V>` |
| `R` | Return type | `Function<T,R>` |

### Regla PECS (Producer Extends, Consumer Super)
```java
// Si LEES de la colección → extends (produce datos)
double sumar(List<? extends Number> numeros) { ... }

// Si ESCRIBES en la colección → super (consume datos)
void agregar(List<? super Integer> lista) { ... }
```

---

## 🔗 Conexión con los proyectos del curso

| Proyecto | Dónde ves genéricos |
|----------|---------------------|
| **UT19** | `UsuarioRepository` interfaz, `List<Usuario>`, `Optional<Usuario>` |
| **UT20** | `JpaRepository<Usuario, Long>`, `ResponseEntity<T>` |
| **proyecto-final** | `List<Alumno>`, `List<Curso>`, `List<Matricula>` |

---

## 📋 Tareas incluidas en el código

1. Crear una `List<Integer>` y sumar todos sus elementos.
2. Crear una `Caja<List<String>>` y recorrer su contenido.
3. Método genérico `encontrarMaximo(T[] array)`.
4. Método `promedio(List<T extends Number>)`.
5. `imprimirSoloStrings(List<? extends CharSequence>)`.
6. `Par<String, List<String>>` para curso + alumnos.
7. `RepositorioEnMemoria<Producto>` con CRUD.

---

*Para compilar y ejecutar:*
```bash
javac UT13b_Genericos.java
java UT13b_Genericos
```
