# 📘 UT15c — Patrones de Diseño Básicos en Java

> **Autor:** Joaquín Rodríguez Llanes  
> **Nivel:** Java Avanzado (antes de los proyectos finales)  
> **Prerrequisitos:** UT5 (POO completa), UT13b (Genéricos), UT15b (Lambdas).

---

## 🎯 ¿Qué aprenderás en esta unidad?

| Patrón | Problema que resuelve | Dónde lo verás |
|--------|------------------------|---------------|
| **🔒 Singleton** | Solo UNA instancia en toda la app | `Db.java` en UT19 |
| **🏭 Factory** | Crear objetos sin usar `new` directamente | Spring IoC (`@Service`, `@Controller`) |
| **🎯 Strategy** | Cambiar algoritmo en tiempo de ejecución | Comparator, Repository intercambiable |
| **📡 Observer** | Notificar cambios a múltiples suscriptores | Eventos Spring, listeners |
| **🎨 Builder** | Construir objetos complejos paso a paso | `StringBuilder`, `ResponseEntity` |

---

## 📋 Secciones del archivo `.java`

| # | Sección | Patrón | Dificultad |
|---|---------|--------|------------|
| 1 | ¿Qué es un patrón de diseño? | Introducción | ⭐ |
| 2 | Singleton — Configuración | Creacional | ⭐⭐ |
| 3 | Factory Method — Notificaciones | Creacional | ⭐⭐ |
| 4 | Strategy — Descuentos | Comportamiento | ⭐⭐⭐ |
| 5 | Observer — Tienda Online | Comportamiento | ⭐⭐⭐ |
| 6 | Builder — Pedidos | Creacional | ⭐⭐⭐ |
| 7 | Resumen — Dónde se usan en el curso | Referencia | ⭐ |

---

## 🧠 Resumen de cada patrón

### 🔒 Singleton
```java
class Configuracion {
    private static Configuracion instancia;
    private Configuracion() { }  // Constructor PRIVADO
    
    public static Configuracion getInstance() {
        if (instancia == null) instancia = new Configuracion();
        return instancia;
    }
}
```
**Clave:** constructor `private` + método `static` `getInstance()`.

### 🏭 Factory
```java
Notificacion n = NotificacionFactory.crear("email");
n.enviar("Hola");  // No sabemos si es Email, SMS o Push
```
**Clave:** el código cliente trabaja con la **interfaz**, no con la clase concreta.

### 🎯 Strategy
```java
calculadora.setEstrategia(new DescuentoVIP());
double precio = calculadora.calcular(100.0);  // 75.0€
// Cambiar en runtime:
calculadora.setEstrategia(precio -> precio * 0.60);  // Lambda
```
**Clave:** la estrategia se puede cambiar **sin tocar** la clase que la usa.

### 📡 Observer
```java
tienda.suscribir(new ObservadorEmail());
tienda.suscribir(new ObservadorInventario());
tienda.nuevoPedido("Laptop");  // Todos los suscritos reciben la notificación
```
**Clave:** el emisor no conoce a los receptores — solo itera la lista.

### 🎨 Builder
```java
Pedido p = new Pedido.Builder("Ana")
    .producto("Laptop")
    .precio(999.99)
    .envioUrgente(true)
    .build();
```
**Clave:** métodos fluidos (retornan `this`) + método `build()` final.

---

## 🔗 Dónde ves estos patrones en el curso

| Patrón | Proyecto | Ejemplo concreto |
|--------|----------|------------------|
| Singleton | UT19 | `Db.getConnection()` — una sola conexión |
| Factory | UT20 | Spring crea `@Service`, `@Controller` automáticamente |
| Strategy | UT19 | `UsuarioRepositoryJdbc` vs `UsuarioRepositoryInMemory` |
| Observer | UT20 | Eventos Spring `@EventListener` |
| Builder | UT20 | `ResponseEntity.ok().body(...)` |
| MVC | Todos | Model → Controller → View/API |
| Repository | Todos | Interfaz + implementación intercambiable |

---

## 📋 Tareas incluidas en el código

1. Crear un `Logger` Singleton con método `log(mensaje)`.
2. Crear una `FiguraFactory` (Circulo, Rectangulo, Triangulo).
3. Strategy con `Comparator<Alumno>` (por nombre, edad, nota).
4. Observer meteorológico (EstacionMeteo + 3 pantallas).
5. Builder para `Vehiculo` (marca, modelo, color, potencia).

---

*Para compilar y ejecutar:*
```bash
javac UT15c_PatronesDiseno.java
java UT15c_PatronesDiseno
```
