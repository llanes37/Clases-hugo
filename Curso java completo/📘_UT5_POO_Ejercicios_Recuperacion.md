# 📘 UT5 — Guía para Ejercicios de Examen / Recuperación POO

> **Autor:** Joaquín Rodríguez Llanes
> **Nivel:** Java Intermedio — 1º DAM
> **Prerrequisitos:** UT5_ClasesObjetos, UT5_ClasesObjetosAvanzado, UT5_HerenciaPolimorfismoInterfaces

---

## 🎯 ¿Qué es este tipo de ejercicio?

Los ejercicios de examen de POO en 1º DAM siguen siempre el **mismo patrón**:

1. Se te dan **2 o 3 clases** con sus atributos, constructores y métodos especificados.
2. Las clases están **relacionadas** entre sí (composición o agregación).
3. Una de las clases es una **clase gestora** que usa un `HashMap` o `ArrayList`.
4. Tienes que **implementarlo todo desde cero** y probarlo en un `main()`.

---

## 🗺️ Archivos de este bloque

| Archivo | Tipo | Para qué |
|---------|------|----------|
| `UT5_POO_EjemploResuelto_Ordenadores.java` | ✅ Resuelto | Estudiar el ejemplo completo |
| `UT5_POO_EjemploResuelto_RedSocial.java` | ✅ Resuelto | Estudiar el ejemplo completo |
| `UT5_POO_Practica_Ordenadores.java` | 📝 Para ti | Implementa lo que falta |
| `UT5_POO_Practica_RedSocial.java` | 📝 Para ti | Implementa lo que falta |
| `UT5_POO_Ejercicios_Recuperacion.java` | 📝 Para ti | 5 ejercicios completos nuevos |
| `📘_UT5_POO_Ejercicios_Recuperacion.md` | 📖 Esta guía | Cómo afrontarlos |

> [!TIP]
> **Orden recomendado:** Estudia los resueltos → Practica los ejercicios vacíos → Resuelve los 5 ejercicios nuevos.

---

## 🧠 Cómo leer un enunciado de examen

Cuando te den el enunciado, sigue estos pasos **antes de escribir una sola línea de código**:

### Paso 1 — Identifica las clases
```
¿Cuántas clases hay?
¿Cuál es la más sencilla? (sin dependencias)
¿Cuál tiene colección de otras? (HashSet, ArrayList...)
¿Cuál es la gestora? (tiene HashMap)
```

### Paso 2 — Para cada clase, anota
```
✅ Atributos: nombre, tipo, visibilidad
✅ Constructor: ¿qué recibe? ¿qué valores por defecto?
✅ Getters: ¿todos? ¿alguno devuelve String en vez de la colección?
✅ Setters: ¿los hay? ¿con validación?
✅ equals/hashCode: ¿por qué campos?
✅ Métodos especiales: calcular algo, añadir/eliminar, buscar...
```

### Paso 3 — Dibuja las relaciones
```
Periferico ←── está dentro de ──→ Ordenador (HashSet)
Usuario    ←── está en fotos de ──→ Foto (HashSet)
Clave      ←── HashMap ──────────→ Valor
```

---

## 📋 Orden de implementación (MUY IMPORTANTE)

> [!IMPORTANT]
> **Siempre implementa en este orden.** Si haces la gestora primero, no podrás usarla sin las otras.

```
1️⃣  Clase más sencilla (sin dependencias)
     Ejemplo: Periferico, Usuario, Libro
     → Atributos + Constructor + Getters + equals/hashCode + toString

2️⃣  Clase intermedia (usa la anterior)
     Ejemplo: Ordenador (usa Periferico), Foto (usa Usuario)
     → Atributos + Constructor + Getters + Métodos de negocio + toString

3️⃣  Clase Gestora (usa ambas)
     Ejemplo: GestionFotos, GestionBiblioteca
     → HashMap/ArrayList + Métodos de gestión

4️⃣  main() — Probar cada cosa que implementas
     → No esperes al final para probar. Prueba cada método al hacerlo.
```

---

## ⚠️ Errores típicos en examen

> [!WARNING]
> Estos son los errores más frecuentes. Repásalos ANTES de entregar.

### ❌ Error 1: Olvidar `equals` y `hashCode`

```java
// ❌ SIN equals/hashCode — el HashSet no detecta duplicados
HashSet<Periferico> set = new HashSet<>();
set.add(new Periferico("Ratón", "Logitech"));
set.add(new Periferico("Ratón", "Logitech")); // ← entra como si fuera diferente
System.out.println(set.size()); // 2 ← MAL

// ✅ CON equals/hashCode correctos
System.out.println(set.size()); // 1 ← BIEN
```

**Regla:** Si usas `HashSet` o `HashMap` con objetos propios → **SIEMPRE** `equals` + `hashCode`.

---

### ❌ Error 2: Borrar dentro de un for-each

```java
// ❌ INCORRECTO — lanza ConcurrentModificationException
for (Foto f : listaFotos) {
    if (f.getTamanio() > 5) {
        listaFotos.remove(f); // ← CRASH
    }
}

// ✅ CORRECTO — usar Iterator
Iterator<Foto> it = listaFotos.iterator();
while (it.hasNext()) {
    Foto f = it.next();
    if (f.getTamanio() > 5) {
        it.remove(); // ← seguro
    }
}
```

---

### ❌ Error 3: Devolver la colección directamente

```java
// ❌ INCORRECTO — expone la colección interna
public HashSet<Periferico> getPerifericos() {
    return perifericos; // alguien puede hacer .clear() desde fuera
}

// ✅ CORRECTO — devolver String o copia
public String getPerifericos() {
    StringBuilder sb = new StringBuilder();
    for (Periferico p : perifericos) {
        sb.append(p).append("\n");
    }
    return sb.toString();
}
```

---

### ❌ Error 4: Comparar arrays con `.equals()`

```java
// ❌ Compara referencias, no contenido
return nombre.equals(otra.nombre); // MAL para String[]

// ✅ Compara contenido posición a posición
return Arrays.equals(nombre, otra.nombre); // BIEN
```

---

### ❌ Error 5: `hashCode` incoherente con `equals`

```java
// ✅ REGLA: si dos objetos son equals → DEBEN tener el mismo hashCode
// Usa siempre los MISMOS campos en equals y hashCode

@Override
public boolean equals(Object obj) {
    // usa: tipo, marca
    return tipo.equals(otro.tipo) && marca.equals(otro.marca);
}

@Override
public int hashCode() {
    return Objects.hash(tipo, marca); // ← mismos campos: tipo, marca
}
```

---

### ❌ Error 6: Olvidar inicializar colecciones en el constructor

```java
// ❌ MAL — NullPointerException al primer .add()
private HashSet<Periferico> perifericos;

// ✅ BIEN — inicializar en el constructor
this.perifericos = new HashSet<>();
```

---

### ❌ Error 7: Nombre del archivo ≠ nombre de la clase public

```java
// Archivo: MiClase.java
public class OtraClase { } // ← NO COMPILA

// Archivo: MiClase.java
public class MiClase { }   // ← BIEN
```

---

## ✅ Checklist antes de entregar

Antes de dar por terminado el ejercicio, comprueba **cada punto**:

### Por cada clase:
- [ ] Todos los atributos son `private`
- [ ] Constructor inicializa todos los atributos
- [ ] Las colecciones se inicializan en el constructor (`new HashSet<>()`, `new ArrayList<>()`)
- [ ] `equals()` implementado con los campos correctos
- [ ] `hashCode()` usa los **mismos campos** que `equals()`
- [ ] `toString()` muestra todos los datos relevantes
- [ ] Los métodos de colección devuelven `boolean` cuando el enunciado lo dice
- [ ] `getPerifericos()` / `getUsuarios()` devuelve `String`, NO la colección

### Clase gestora:
- [ ] El `HashMap` / colección se crea en el constructor
- [ ] `addXxx()` comprueba si ya existe antes de añadir
- [ ] Métodos de eliminación usan `Iterator`, no for-each
- [ ] Los métodos de búsqueda usan `.equals()`, no `==`

### Main:
- [ ] Se prueban los casos de éxito
- [ ] Se prueban los casos de fallo (añadir duplicado, eliminar algo que no existe...)
- [ ] Se llama a `toString()` para mostrar los objetos

---

## 💡 Plantilla mental: Clase gestora con HashMap

```java
class GestionXxx {
    private HashMap<Clave, List<Valor>> mapa;

    public GestionXxx() {
        this.mapa = new HashMap<>();
    }

    // Añadir clave
    public boolean addClave(Clave c) {
        if (mapa.containsKey(c)) return false;     // ya existe
        mapa.put(c, new ArrayList<>());
        return true;
    }

    // Añadir valor a una clave
    public boolean addValor(Clave c, Valor v) {
        List<Valor> lista = mapa.get(c);
        if (lista == null) return false;            // clave no existe
        lista.add(v);
        return true;
    }

    // Eliminar valores por condición
    public Set<Valor> eliminar(Clave c, condicion) {
        Set<Valor> eliminados = new HashSet<>();
        List<Valor> lista = mapa.get(c);
        if (lista == null) return eliminados;
        Iterator<Valor> it = lista.iterator();
        while (it.hasNext()) {
            Valor v = it.next();
            if (condicion) {
                eliminados.add(v);
                it.remove();
            }
        }
        return eliminados;
    }

    // Buscar en TODOS los valores
    public boolean buscarEnTodo(Valor buscado) {
        for (List<Valor> lista : mapa.values()) {
            for (Valor v : lista) {
                if (v.equals(buscado)) return true;
            }
        }
        return false;
    }
}
```

---

## 🚀 Estrategia de tiempo en examen

| Tiempo | Qué hacer |
|--------|-----------|
| **5 min** | Leer TODO el enunciado. Identificar clases y relaciones. |
| **15 min** | Implementar clase 1 (sencilla) + probar en main |
| **20 min** | Implementar clase 2 (intermedia) + probar en main |
| **25 min** | Implementar clase gestora + probar en main |
| **10 min** | Repasar checklist. Añadir casos de prueba. |
| **5 min** | Leer el código por encima. Verificar que compila. |

> [!CAUTION]
> **No te saltes las pruebas en el main.** Un código que no compila o que falla en el main vale 0, aunque la lógica sea correcta.

---

## 📂 Ejercicios disponibles para practicar

### ✅ Ejemplos resueltos (estudia estos primero)
- `UT5_POO_EjemploResuelto_Ordenadores.java` — Periferico + Ordenador
- `UT5_POO_EjemploResuelto_RedSocial.java` — Usuario + Foto + GestionFotos

### 📝 Práctica guiada (implementa tú con estructura dada)
- `UT5_POO_Practica_Ordenadores.java` — mismas clases, código vacío
- `UT5_POO_Practica_RedSocial.java` — mismas clases, código vacío

### 🏋️ Ejercicios nuevos completos (nivel examen real)
En `UT5_POO_Ejercicios_Recuperacion.java`:
1. 🏥 Hospital — Paciente + Médico + GestionHospital
2. 📚 Biblioteca — Libro + Socio + GestionBiblioteca
3. 🛒 Tienda Online — Producto + Cliente + Pedido + GestionTienda
4. 🚗 Flota Vehículos — Vehículo + Conductor + GestionFlota
5. 📺 Streaming — Contenido + Suscriptor + GestionPlataforma
