# 📘 UT5 (Avanzado) — Clases y Objetos: Múltiples Clases Interactuando

> **Autor:** Joaquín Rodríguez Llanes
> **Nivel:** Java Intermedio — 1º DAM
> **Prerrequisito:** `UT5_ClasesObjetos.java` — debes dominar clases básicas antes de continuar.
> **Siguiente paso:** `UT5_HerenciaPolimorfismoInterfaces.java`

---

## 🎯 ¿Qué aprenderás en esta unidad?

| Concepto | Descripción |
|---------|-------------|
| **Composición** | Un objeto contiene a otro como atributo |
| **Constructores sobrecargados** | Varias formas de crear el mismo objeto |
| **Métodos con objetos** | Métodos que reciben y devuelven objetos |
| **ArrayList de objetos** | Listas de instancias propias |
| **`static`** | Contadores compartidos entre instancias |
| **`toString()`** | Representación del objeto como texto |
| **Métodos de negocio** | Lógica real con validaciones y cálculos |

> [!NOTE]
> Todo esto **sin herencia**. Herencia, polimorfismo e interfaces se verán en la siguiente unidad.

---

## 🧩 Clases del ejercicio

Este archivo resuelve un sistema de tienda con 4 clases que interactúan:

```
┌─────────────────────────────────────────────────────────────────┐
│                        TIENDA                                   │
│   - nombre                                                      │
│   - inventario: ArrayList<Producto>                             │
│   + registrarProducto()  + mostrarInventario()                  │
│   + mostrarProductoMasCaro()  + mostrarValorTotal()             │
└─────────────────────────────────────────────────────────────────┘
          │ gestiona
          ▼
┌────────────────────────────┐     ┌────────────────────────────┐
│         PEDIDO             │     │         PRODUCTO            │
│  - cliente: Cliente        │────►│  - nombre                  │
│  - productos: ArrayList<>  │     │  - precio: double          │
│  - numeroPedido (static)   │     │  - stock: int              │
│  + agregarProducto()       │     │  + vender(int)             │
│  + calcularTotal()         │     │  + aplicarDescuento(double) │
│  + mostrarResumen()        │     │  + hayStock(): boolean     │
│  + compararCon(Pedido)     │     └────────────────────────────┘
└────────────────────────────┘
          │ pertenece a
          ▼
┌────────────────────────────┐
│         CLIENTE            │
│  - nombre                  │
│  - email                   │
│  - esVIP: boolean          │
│  + getDescuentoVIP()       │
└────────────────────────────┘
```

---

## 📖 Conceptos clave explicados

### 1️⃣ Composición — "tiene un"

```java
// Un Pedido TIENE un Cliente → eso es COMPOSICIÓN
class Pedido {
    private Cliente cliente;     // objeto dentro de otro objeto
    private ArrayList<Producto> productos;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;          // recibe el objeto como parámetro
        this.productos = new ArrayList<>();
    }
}
```

> **¿Cuándo usarlo?** Cuando una clase "posee" o "usa" otra, pero no "es" esa clase.
> - Pedido **tiene** un Cliente → composición ✅
> - Pedido **es** un Cliente → no tiene sentido ❌

---

### 2️⃣ Constructores sobrecargados

```java
class Producto {
    // Constructor COMPLETO — con stock
    public Producto(String nombre, double precio, int stock) { ... }

    // Constructor SOBRECARGADO — sin stock (por defecto = 0)
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0;   // valor por defecto
    }
}

// Uso:
Producto p1 = new Producto("Laptop", 999.99, 5);  // con stock
Producto p2 = new Producto("Auriculares", 59.99);  // sin stock → 0
```

---

### 3️⃣ Atributo `static` — compartido por todos

```java
class Pedido {
    private static int contadorPedidos = 0;  // uno para TODOS los pedidos
    private int numeroPedido;                // uno para CADA pedido

    public Pedido(Cliente cliente) {
        contadorPedidos++;                   // se incrementa globalmente
        this.numeroPedido = contadorPedidos; // cada pedido guarda el suyo
    }
}
```

> `static` → pertenece a la **clase**, no a cada objeto.
> Todos los pedidos comparten `contadorPedidos`, pero cada uno tiene su propio `numeroPedido`.

---

### 4️⃣ Método que recibe un objeto

```java
// Un pedido puede compararse CON otro pedido
public void compararCon(Pedido otro) {
    double miTotal = this.calcularTotalConDescuento();
    double otroTotal = otro.calcularTotalConDescuento();

    if (miTotal > otroTotal) {
        System.out.println("Este pedido es más caro.");
    }
}

// Uso:
pedido1.compararCon(pedido2);
```

---

### 5️⃣ Recorrer un ArrayList de objetos

```java
// Calcular total sumando el precio de cada producto
public double calcularTotal() {
    double total = 0;
    for (Producto p : productos) {  // recorremos la lista
        total += p.getPrecio();     // usamos métodos del objeto
    }
    return total;
}
```

---

### 6️⃣ Buscar el máximo en una lista

```java
// Patrón clásico: empezar con el primero y comparar con el resto
public Producto getMasCaro() {
    Producto masCaro = inventario.get(0);   // empezamos con el primero
    for (Producto p : inventario) {
        if (p.getPrecio() > masCaro.getPrecio()) {
            masCaro = p;   // actualizamos si encontramos uno mayor
        }
    }
    return masCaro;
}
```

---

## 📋 Resumen de métodos por clase

### `Producto`
| Método | Descripción |
|--------|-------------|
| `getPrecioFormateado()` | Devuelve `"999.99€"` formateado |
| `aplicarDescuento(double %)` | Reduce el precio en ese porcentaje |
| `agregarStock(int)` | Suma unidades al stock |
| `vender(int)` | Resta stock. Devuelve `false` si no hay suficiente |
| `hayStock()` | Devuelve `true` si `stock > 0` |

### `Cliente`
| Método | Descripción |
|--------|-------------|
| `esVIP()` | `true` si es cliente VIP |
| `getDescuentoVIP()` | `15.0` si VIP, `0.0` si no |

### `Pedido`
| Método | Descripción |
|--------|-------------|
| `agregarProducto(Producto)` | Añade a la lista |
| `calcularTotal()` | Suma precios de todos los productos |
| `calcularTotalConDescuento()` | Total con descuento VIP aplicado |
| `mostrarResumen()` | Muestra ticket completo con ASCII |
| `compararCon(Pedido)` | Compara el total con otro pedido |

### `Tienda`
| Método | Descripción |
|--------|-------------|
| `registrarProducto(Producto)` | Añade al inventario |
| `mostrarInventario()` | Lista todos los productos |
| `mostrarProductoMasCaro()` | Busca y muestra el más caro |
| `mostrarProductoMasBarato()` | Busca y muestra el más barato |
| `mostrarValorTotal()` | Suma `precio × stock` de todos |

---

## ✅ Tarea para el alumno

> [!TIP]
> Empieza por el más sencillo e implémentalos en orden.

1. **Añade `categoria` a `Producto`** — `"Electrónica"`, `"Periféricos"`, `"Audio"`, etc.
   - Añade un getter `getCategoria()`.
   - Modifica `toString()` para que lo muestre.

2. **Crea `filtrarPorCategoria(String categoria)`** en `Tienda`:
   - Recorre el inventario y devuelve un `ArrayList<Producto>` con los de esa categoría.

3. **Crea `totalProductosEnStock()`** en `Tienda`:
   - Devuelve el número total de unidades disponibles sumando el stock de todos los productos.

4. **Añade `historialPedidos`** a `Cliente`:
   - Un `ArrayList<Integer>` con los números de pedido del cliente.
   - Método `registrarPedido(int numeroPedido)` que lo añade.
   - Método `getTotalPedidos()` que devuelve cuántos ha hecho.

---

## 🔗 Archivos relacionados

| Archivo | Tipo | Descripción |
|---------|------|-------------|
| `UT5_ClasesObjetos.java` | 📄 Prereq. | POO básica: una sola clase |
| `UT5_ClasesObjetosAvanzado.java` | 📄 Este | Múltiples clases + composición |
| `UT5_HerenciaPolimorfismoInterfaces.java` | 📄 Siguiente | `extends`, `@Override`, interfaces |
| `UT5_POO_EjemploResuelto_Ordenadores.java` | 📄 Práctica | Ejercicio resuelto tipo examen |
| `UT5_POO_EjemploResuelto_RedSocial.java` | 📄 Práctica | Ejercicio resuelto tipo examen |
| `UT5_POO_Practica_Ordenadores.java` | 📝 Para ti | Rellena los TODOs |
| `UT5_POO_Practica_RedSocial.java` | 📝 Para ti | Rellena los TODOs |
| `UT5_POO_Ejercicios_Recuperacion.java` | 📝 Para ti | 5 ejercicios nivel examen |
