/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 15c: PATRONES DE DISEÑO BÁSICOS EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

import java.util.*;

public class UT15c_PatronesDiseno {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // * MENÚ PRINCIPAL — ELIGE QUÉ PATRÓN EJECUTAR
        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║  🏗️  UT15c — PATRONES DE DISEÑO EN JAVA              ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.println("1. 📖 ¿Qué es un patrón de diseño?");
            System.out.println("2. 🔒 Singleton (una sola instancia)");
            System.out.println("3. 🏭 Factory Method (crear sin new)");
            System.out.println("4. 🎯 Strategy (cambiar algoritmo en runtime)");
            System.out.println("5. 📡 Observer (notificar cambios)");
            System.out.println("6. 🎨 Builder (construir objetos complejos)");
            System.out.println("7. 🗺️  Resumen: dónde se usan en los proyectos");
            System.out.println("0. 👋 Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> introduccion();
                case 2 -> singletonDemo();
                case 3 -> factoryDemo();
                case 4 -> strategyDemo();
                case 5 -> observerDemo();
                case 6 -> builderDemo();
                case 7 -> resumenProyectos();
                case 0 -> System.out.println("👋 ¡Hasta luego!");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📖 SECCIÓN 1: ¿QUÉ ES UN PATRÓN DE DISEÑO?
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Patrones de diseño
    // ──────────────────────────────────────────────────────────────
    // ? Un patrón de diseño es una SOLUCIÓN probada a un problema RECURRENTE
    // ? en el diseño de software. NO es código concreto, sino una plantilla.
    // ?
    // ? Se clasifican en 3 categorías:
    // ? 🔧 Creacionales → Cómo se CREAN los objetos (Singleton, Factory, Builder)
    // ? 🏗️ Estructurales → Cómo se ORGANIZAN las clases (Adapter, Decorator)
    // ? 🎭 De comportamiento → Cómo INTERACTÚAN los objetos (Strategy, Observer)
    // !
    // ! Los patrones NO se inventan, se DESCUBREN. Son experiencia condensada
    // ! de miles de programadores en un lenguaje común.

    public static void introduccion() {
        System.out.println("\n🔹 SECCIÓN 1: ¿Qué es un patrón de diseño?\n");

        System.out.println("  🏗️  Un patrón de diseño es una RECETA para resolver");
        System.out.println("     un problema que aparece una y otra vez.\n");

        System.out.println("  📋 LOS 5 PATRONES QUE VEREMOS:");
        System.out.println("  ┌────────────────────────────────────────────────────┐");
        System.out.println("  │ 🔒 Singleton    → Solo UNA instancia en toda la app│");
        System.out.println("  │ 🏭 Factory      → Crear objetos sin usar new       │");
        System.out.println("  │ 🎯 Strategy     → Cambiar algoritmo en runtime     │");
        System.out.println("  │ 📡 Observer     → Notificar cambios a suscriptores │");
        System.out.println("  │ 🎨 Builder      → Construir objetos paso a paso    │");
        System.out.println("  └────────────────────────────────────────────────────┘\n");

        System.out.println("  💡 DATO: Ya has usado patrones sin saberlo:");
        System.out.println("     - MVC (Model-View-Controller) en los proyectos");
        System.out.println("     - Repository Pattern en UT19 y los proyecto-final");
        System.out.println("     - Singleton en Db.java de UT19");
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🔒 SECCIÓN 2: SINGLETON
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Singleton
    // ──────────────────────────────────────────────────────────────
    // ? El patrón Singleton garantiza que una clase tenga UNA SOLA instancia
    // ? y proporciona un punto de acceso global a ella.
    // ?
    // ? Casos de uso reales:
    // ? - Conexión a BD (Db.java en UT19)
    // ? - Configuración de la aplicación
    // ? - Pool de conexiones
    // ? - Logger
    // !
    // ! Regla: constructor PRIVATE + método estático getInstance()

    public static void singletonDemo() {
        System.out.println("\n🔹 SECCIÓN 2: Singleton\n");

        // * Obtener la instancia (se crea la primera vez)
        Configuracion config1 = Configuracion.getInstance();
        config1.setIdioma("Español");
        config1.setModoOscuro(true);
        config1.mostrar();

        System.out.println();

        // * Obtener la instancia de nuevo (es la MISMA)
        Configuracion config2 = Configuracion.getInstance();
        System.out.println("  🔍 ¿config1 == config2? " + (config1 == config2));
        // ? true → son exactamente el MISMO objeto en memoria

        // * Los cambios de config1 se ven en config2
        System.out.println("  📌 config2 ve el idioma: " + config2.getIdioma());
        System.out.println("  📌 config2 ve modo oscuro: " + config2.isModoOscuro());

        // ! Intentar new Configuracion() → ERROR de compilación
        // ! Configuracion c = new Configuracion(); // ❌ Constructor es private

        // ! ✅ TAREA ALUMNO:
        // * Crea una clase Logger (Singleton) con un método log(String mensaje)
        // * que imprima la hora + el mensaje. Comprueba que solo hay 1 instancia.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🏭 SECCIÓN 3: FACTORY METHOD
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Factory Method
    // ──────────────────────────────────────────────────────────────
    // ? El patrón Factory delega la creación de objetos a un método
    // ? en vez de usar new directamente. El código cliente NO conoce
    // ? la clase concreta que se instancia.
    // ?
    // ? Ventaja: si el tipo cambia, solo se modifica la fábrica, no
    // ? todos los sitios donde se usa new.
    // ?
    // ? Caso real: crear Notificaciones (Email, SMS, Push) según un parámetro.

    public static void factoryDemo() {
        System.out.println("\n🔹 SECCIÓN 3: Factory Method\n");

        // * Crear notificaciones sin saber la clase concreta
        Notificacion email = NotificacionFactory.crear("email");
        Notificacion sms = NotificacionFactory.crear("sms");
        Notificacion push = NotificacionFactory.crear("push");

        // * Todas implementan la misma interfaz
        email.enviar("¡Tu pedido ha sido enviado!");
        sms.enviar("Código de verificación: 1234");
        push.enviar("Tienes 3 mensajes nuevos");

        System.out.println();

        // * El código cliente NO sabe si es Email, SMS o Push
        // * Solo trabaja con la interfaz Notificacion
        System.out.println("  📌 Tipos creados:");
        System.out.println("    email → " + email.getClass().getSimpleName());
        System.out.println("    sms → " + sms.getClass().getSimpleName());
        System.out.println("    push → " + push.getClass().getSimpleName());

        // ! ✅ TAREA ALUMNO:
        // * Crea una FiguraFactory que cree objetos Circulo, Rectangulo o Triangulo
        // * según un String. Cada Figura tiene un método calcularArea().
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🎯 SECCIÓN 4: STRATEGY
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Strategy
    // ──────────────────────────────────────────────────────────────
    // ? El patrón Strategy permite cambiar el ALGORITMO de un objeto
    // ? en tiempo de ejecución sin modificar su código.
    // ?
    // ? Estructura:
    // ? 1. Interfaz Strategy → define el contrato (ej: calcularPrecio)
    // ? 2. Implementaciones concretas → cada una con su lógica
    // ? 3. Contexto → usa la estrategia actual (se puede cambiar)
    // ?
    // ? Caso real: calcular descuentos según el tipo de cliente.

    public static void strategyDemo() {
        System.out.println("\n🔹 SECCIÓN 4: Strategy\n");

        double precioBase = 100.0;
        System.out.println("  💰 Precio base: " + precioBase + "€\n");

        // * Crear calculadora de precios
        CalculadoraPrecio calculadora = new CalculadoraPrecio();

        // * Estrategia 1: Sin descuento
        calculadora.setEstrategia(new SinDescuento());
        System.out.println("  📌 Sin descuento: " + String.format("%.2f", calculadora.calcular(precioBase)) + "€");

        // * Estrategia 2: Descuento socio (10%)
        calculadora.setEstrategia(new DescuentoSocio());
        System.out.println("  📌 Socio (10%): " + String.format("%.2f", calculadora.calcular(precioBase)) + "€");

        // * Estrategia 3: Descuento VIP (25%)
        calculadora.setEstrategia(new DescuentoVIP());
        System.out.println("  📌 VIP (25%): " + String.format("%.2f", calculadora.calcular(precioBase)) + "€");

        // * Estrategia 4: Black Friday (50%)
        calculadora.setEstrategia(new DescuentoBlackFriday());
        System.out.println("  📌 Black Friday (50%): " + String.format("%.2f", calculadora.calcular(precioBase)) + "€");

        System.out.println();

        // * Con lambdas (Java 8+) — la interfaz funcional permite esto
        System.out.println("  ⚡ Strategy con lambdas:");
        calculadora.setEstrategia(precio -> precio * 0.70); // ? 30% descuento como lambda
        System.out.println("  📌 Lambda 30%: " + String.format("%.2f", calculadora.calcular(precioBase)) + "€");

        // ! ✅ TAREA ALUMNO:
        // * Crea un Strategy para ordenar una lista de alumnos:
        // * OrdenarPorNombre, OrdenarPorEdad, OrdenarPorNota.
        // * Usa la interfaz Comparator<Alumno> como Strategy.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 📡 SECCIÓN 5: OBSERVER
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Observer
    // ──────────────────────────────────────────────────────────────
    // ? El patrón Observer permite que UN objeto (Subject/Emisor)
    // ? NOTIFIQUE automáticamente a MUCHOS objetos (Observers/Suscriptores)
    // ? cuando su estado cambia.
    // ?
    // ? Analogía: suscribirse a un canal de YouTube → recibes notificación
    // ? cada vez que sube un vídeo, sin tener que estar comprobando.
    // ?
    // ? Caso real: evento de nuevo pedido → notificar a Inventario, Email, Factura.

    public static void observerDemo() {
        System.out.println("\n🔹 SECCIÓN 5: Observer\n");

        // * Crear la tienda (Subject/Emisor)
        TiendaOnline tienda = new TiendaOnline();

        // * Crear observadores (se suscriben a la tienda)
        ObservadorEmail obsEmail = new ObservadorEmail();
        ObservadorInventario obsInventario = new ObservadorInventario();
        ObservadorEstadistica obsStats = new ObservadorEstadistica();

        // * Suscribir observadores
        tienda.suscribir(obsEmail);
        tienda.suscribir(obsInventario);
        tienda.suscribir(obsStats);

        // * Nuevo pedido → todos los observadores reciben la notificación
        System.out.println("  📦 Nuevo pedido:\n");
        tienda.nuevoPedido("Laptop Gaming — 999.99€");

        System.out.println();

        // * Desuscribir el observador de email
        tienda.desuscribir(obsEmail);
        System.out.println("  📧 Email desuscrito.\n");

        // * Nuevo pedido → solo Inventario y Estadística reciben la notificación
        System.out.println("  📦 Segundo pedido:\n");
        tienda.nuevoPedido("Teclado Mecánico — 89.99€");

        // ! ✅ TAREA ALUMNO:
        // * Crea un sistema de alertas meteorológicas:
        // * - Subject: EstacionMeteo (temperatura, humedad)
        // * - Observers: PantallaMovil, PantallaWeb, AlertaSMS
        // * Cuando la temperatura cambie, todos los observers se actualizan.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🎨 SECCIÓN 6: BUILDER
    // ═══════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Builder
    // ──────────────────────────────────────────────────────────────
    // ? El patrón Builder construye un objeto COMPLEJO paso a paso.
    // ? Evita constructores con muchos parámetros ("telescoping constructor").
    // ?
    // ? Ventaja: claridad — sabes QUÉ parámetro estás asignando.
    // ? Caso real: Java StringBuilder, Stream.Builder, configuraciones.

    public static void builderDemo() {
        System.out.println("\n🔹 SECCIÓN 6: Builder\n");

        // ! ❌ SIN Builder — constructor con 7 parámetros (confuso)
        System.out.println("  ❌ Sin Builder — ¿qué es cada parámetro?");
        System.out.println("     new Pedido(\"Ana\", \"Laptop\", 1, 999.99, true, false, \"Madrid\")");
        System.out.println("     ¿Qué es true? ¿Y false? ¿Y \"Madrid\"? Imposible saber.\n");

        // * ✅ CON Builder — claro y legible
        System.out.println("  ✅ Con Builder — cada campo tiene nombre:");
        Pedido pedido1 = new Pedido.Builder("Ana García")
                .producto("Laptop Gaming")
                .cantidad(1)
                .precio(999.99)
                .envioUrgente(true)
                .regalo(false)
                .direccion("C/ Mayor 10, Madrid")
                .build();

        pedido1.mostrar();

        System.out.println();

        // * Builder con solo los campos obligatorios
        Pedido pedido2 = new Pedido.Builder("Carlos López")
                .producto("Ratón Inalámbrico")
                .precio(29.99)
                .build();

        pedido2.mostrar();

        System.out.println();

        // * Builder con regalo
        Pedido pedido3 = new Pedido.Builder("María Ruiz")
                .producto("Auriculares Bluetooth")
                .cantidad(2)
                .precio(49.99)
                .regalo(true)
                .direccion("Av. Libertad 5, Barcelona")
                .build();

        pedido3.mostrar();

        // ! ✅ TAREA ALUMNO:
        // * Crea un Builder para una clase Vehiculo con campos:
        // * marca, modelo, color, potencia, esElectrico, esAutomatico.
        // * Solo marca y modelo son obligatorios.
    }

    // ═══════════════════════════════════════════════════════════════
    // * 🗺️ SECCIÓN 7: RESUMEN — DÓNDE SE USAN EN LOS PROYECTOS
    // ═══════════════════════════════════════════════════════════════

    public static void resumenProyectos() {
        System.out.println("\n🔹 SECCIÓN 7: ¿Dónde ves estos patrones en el curso?\n");

        System.out.println("  ┌───────────────────────────────────────────────────────────────┐");
        System.out.println("  │ PATRÓN      │ DÓNDE LO USAS EN EL CURSO                      │");
        System.out.println("  ├───────────────────────────────────────────────────────────────┤");
        System.out.println("  │ 🔒 Singleton │ Db.java (UT19) — una sola conexión a SQLite     │");
        System.out.println("  │ 🏭 Factory   │ Spring Boot crea @Service, @Controller con IoC  │");
        System.out.println("  │ 🎯 Strategy  │ Comparator en listas, @Repository intercambiable│");
        System.out.println("  │ 📡 Observer  │ Eventos Spring (@EventListener), notificaciones │");
        System.out.println("  │ 🎨 Builder   │ StringBuilder, Stream.Builder, ResponseEntity   │");
        System.out.println("  │ 🏗️ MVC       │ TODOS los proyectos (Model-View-Controller)     │");
        System.out.println("  │ 📦 Repository│ TODOS los proyectos (interfaz + implementación) │");
        System.out.println("  └───────────────────────────────────────────────────────────────┘");

        System.out.println("\n  💡 Ahora cuando veas estos patrones en los proyectos,");
        System.out.println("     ya sabes POR QUÉ están diseñados así.");
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🔒 CLASES PARA SINGLETON
// ═══════════════════════════════════════════════════════════════════

// * Clase Singleton — Configuración de la aplicación
class Configuracion {
    // * 1. Instancia estática única (se crea al llamar a getInstance por primera
    // vez)
    private static Configuracion instancia;

    // * 2. Constructor PRIVATE — nadie puede hacer new Configuracion()
    private String idioma;
    private boolean modoOscuro;
    private int tamañoFuente;

    private Configuracion() {
        // ? Valores por defecto
        this.idioma = "English";
        this.modoOscuro = false;
        this.tamañoFuente = 14;
    }

    // * 3. Método estático para obtener la ÚNICA instancia
    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion(); // ? Se crea solo la primera vez
            System.out.println("  🔒 Singleton: instancia CREADA (primera vez)");
        } else {
            System.out.println("  🔒 Singleton: instancia REUTILIZADA (ya existía)");
        }
        return instancia;
    }

    // * Getters y Setters
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isModoOscuro() {
        return modoOscuro;
    }

    public void setModoOscuro(boolean modoOscuro) {
        this.modoOscuro = modoOscuro;
    }

    public void mostrar() {
        System.out.println("  ⚙️  Configuración actual:");
        System.out.println("    Idioma: " + idioma);
        System.out.println("    Modo oscuro: " + (modoOscuro ? "🌙 Sí" : "☀️ No"));
        System.out.println("    Tamaño fuente: " + tamañoFuente + "px");
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🏭 CLASES PARA FACTORY
// ═══════════════════════════════════════════════════════════════════

// * Interfaz que define el contrato de toda Notificación
interface Notificacion {
    void enviar(String mensaje);
}

// * Implementaciones concretas (el cliente NO las conoce)
class NotificacionEmail implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("  📧 [EMAIL] " + mensaje);
    }
}

class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("  📱 [SMS] " + mensaje);
    }
}

class NotificacionPush implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("  🔔 [PUSH] " + mensaje);
    }
}

// * La fábrica — decide qué clase concreta instanciar
class NotificacionFactory {
    public static Notificacion crear(String tipo) {
        // ? El switch decide qué clase crear según el parámetro
        return switch (tipo.toLowerCase()) {
            case "email" -> new NotificacionEmail();
            case "sms" -> new NotificacionSMS();
            case "push" -> new NotificacionPush();
            default -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        };
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🎯 CLASES PARA STRATEGY
// ═══════════════════════════════════════════════════════════════════

// * Interfaz Strategy — define el contrato (interfaz funcional)
@FunctionalInterface
interface EstrategiaDescuento {
    double aplicar(double precio);
}

// * Implementaciones concretas
class SinDescuento implements EstrategiaDescuento {
    @Override
    public double aplicar(double precio) {
        return precio;
    }
}

class DescuentoSocio implements EstrategiaDescuento {
    @Override
    public double aplicar(double precio) {
        return precio * 0.90;
    }
}

class DescuentoVIP implements EstrategiaDescuento {
    @Override
    public double aplicar(double precio) {
        return precio * 0.75;
    }
}

class DescuentoBlackFriday implements EstrategiaDescuento {
    @Override
    public double aplicar(double precio) {
        return precio * 0.50;
    }
}

// * Contexto — usa la estrategia actual (se puede cambiar en runtime)
class CalculadoraPrecio {
    private EstrategiaDescuento estrategia;

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double precio) {
        return estrategia.aplicar(precio);
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 📡 CLASES PARA OBSERVER
// ═══════════════════════════════════════════════════════════════════

// * Interfaz Observer — quienes reciben notificaciones
interface Observador {
    void notificar(String evento);
}

// * Subject — el emisor que mantiene la lista de suscriptores
class TiendaOnline {
    private final List<Observador> observadores = new ArrayList<>();

    public void suscribir(Observador obs) {
        observadores.add(obs);
    }

    public void desuscribir(Observador obs) {
        observadores.remove(obs);
    }

    public void nuevoPedido(String detalle) {
        // ? Notifica a TODOS los observadores suscritos
        for (Observador obs : observadores) {
            obs.notificar(detalle);
        }
    }
}

// * Observadores concretos
class ObservadorEmail implements Observador {
    @Override
    public void notificar(String evento) {
        System.out.println("    📧 [Email] Enviando confirmación: " + evento);
    }
}

class ObservadorInventario implements Observador {
    @Override
    public void notificar(String evento) {
        System.out.println("    📦 [Inventario] Actualizando stock: " + evento);
    }
}

class ObservadorEstadistica implements Observador {
    @Override
    public void notificar(String evento) {
        System.out.println("    📊 [Estadísticas] Registrando venta: " + evento);
    }
}

// ═══════════════════════════════════════════════════════════════════
// * 🎨 CLASES PARA BUILDER
// ═══════════════════════════════════════════════════════════════════

// * Clase con Builder interno estático
class Pedido {
    // ? Campos del pedido (todos privados y final — inmutables)
    private final String cliente;
    private final String producto;
    private final int cantidad;
    private final double precio;
    private final boolean envioUrgente;
    private final boolean regalo;
    private final String direccion;

    // * Constructor PRIVADO — solo el Builder puede crear Pedidos
    private Pedido(Builder builder) {
        this.cliente = builder.cliente;
        this.producto = builder.producto;
        this.cantidad = builder.cantidad;
        this.precio = builder.precio;
        this.envioUrgente = builder.envioUrgente;
        this.regalo = builder.regalo;
        this.direccion = builder.direccion;
    }

    public void mostrar() {
        System.out.println("  ┌──────────────────────────────────────────────┐");
        System.out.println("  │ 📦 PEDIDO                                   │");
        System.out.println("  ├──────────────────────────────────────────────┤");
        System.out.println("  │ Cliente:    " + cliente);
        System.out.println("  │ Producto:   " + producto);
        System.out.println("  │ Cantidad:   " + cantidad);
        System.out.println("  │ Precio:     " + String.format("%.2f", precio) + "€");
        System.out.println("  │ Urgente:    " + (envioUrgente ? "🚀 Sí" : "📬 No"));
        System.out.println("  │ Regalo:     " + (regalo ? "🎁 Sí" : "❌ No"));
        System.out.println("  │ Dirección:  " + (direccion != null ? direccion : "Sin especificar"));
        System.out.println("  └──────────────────────────────────────────────┘");
    }

    // * Clase Builder interna estática
    public static class Builder {
        // ? Campo obligatorio
        private final String cliente;

        // ? Campos opcionales con valores por defecto
        private String producto = "Sin especificar";
        private int cantidad = 1;
        private double precio = 0.0;
        private boolean envioUrgente = false;
        private boolean regalo = false;
        private String direccion = null;

        // * Constructor del Builder — solo el campo obligatorio
        public Builder(String cliente) {
            this.cliente = cliente;
        }

        // * Métodos fluidos — cada uno asigna un campo y retorna this
        public Builder producto(String producto) {
            this.producto = producto;
            return this; // ? Retorna this para poder encadenar
        }

        public Builder cantidad(int cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder precio(double precio) {
            this.precio = precio;
            return this;
        }

        public Builder envioUrgente(boolean envioUrgente) {
            this.envioUrgente = envioUrgente;
            return this;
        }

        public Builder regalo(boolean regalo) {
            this.regalo = regalo;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        // * Método terminal — construye el objeto final
        public Pedido build() {
            return new Pedido(this);
        }
    }
}
