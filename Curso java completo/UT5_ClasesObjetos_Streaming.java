/*
 * ******************************************************************************************
 * (Programación Orientada a Objetos - POO)
 *                  📚 **TEORÍA Y CONCEPTOS: CLASES Y OBJETOS EN JAVA**
 *                      🎵 SISTEMA DE STREAMING DE MÚSICA 🎵
 * ──────────────────────────────────────────────────
 * En esta práctica aprenderás a:
 *
 * ✅ **Comprender la programación orientada a objetos en Java.**
 * ✅ **Crear y utilizar clases en Java.**
 * ✅ **Definir atributos y métodos en una clase.**
 * ✅ **Crear objetos a partir de una clase.**
 * ✅ **Usar constructores para inicializar objetos.**
 * ✅ **Comprender la encapsulación y el uso de getters y setters.**
 * ✅ **Implementar métodos con lógica de negocio.**
 * ✅ **Sobrecarga de constructores.**
 * ✅ **Métodos de comparación entre objetos.**
 *
 * 🚀 **¡Explora, experimenta y mejora el código!**
 ******************************************************************************************/

/*
 * 🧠 **TEORÍA GLOBAL: CLASES Y OBJETOS EN JAVA**
 * ────────────────────────────────────────────
 *
 * 🟢 **¿Qué es una Clase?**
 *  - Una **clase** es una plantilla o modelo que define las características (**atributos**) y
 *    comportamientos (**métodos**) de un objeto.
 *  - Es como un "plano" para construir objetos.
 *
 * 🟠 **¿Qué es un Objeto?**
 *  - Un **objeto** es una instancia concreta de una clase, que tiene valores específicos.
 *  - En Java, un objeto se crea con la palabra clave `new`.
 *
 * 🔵 **Encapsulación:**
 *  - Consiste en ocultar los datos internos de un objeto y proporcionar acceso controlado
 *    mediante métodos públicos (getters/setters).
 *  - Los atributos se declaran como `private` para protegerlos.
 *
 * 🟣 **Constructor:**
 *  - Método especial que se ejecuta automáticamente al crear un objeto.
 *  - Tiene el mismo nombre que la clase y no devuelve ningún valor.
 *  - Se puede sobrecargar (tener varios constructores con diferentes parámetros).
 *
 * 🟡 **Métodos de Instancia:**
 *  - Son comportamientos que puede realizar cada objeto.
 *  - Pueden acceder y modificar los atributos del objeto.
 *  - Pueden recibir parámetros y devolver valores.
 *
 * 🔹 **Ejemplo básico de una Clase y su Objeto:**
 * ```java
 * class Cancion {
 *     private String titulo;
 *     private int duracion;
 *
 *     public Cancion(String titulo, int duracion) {
 *         this.titulo = titulo;
 *         this.duracion = duracion;
 *     }
 *
 *     public void reproducir() {
 *         System.out.println("Reproduciendo: " + titulo);
 *     }
 * }
 *
 * public class Main {
 *     public static void main(String[] args) {
 *         Cancion miCancion = new Cancion("Bohemian Rhapsody", 354);
 *         miCancion.reproducir();
 *     }
 * }
 * ```
 */

// (Programación Orientada a Objetos - POO)
// Definimos la clase principal
public class UT5_ClasesObjetos_Streaming {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   🎵 SISTEMA DE STREAMING DE MÚSICA 🎵      ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * CREACIÓN DE OBJETOS CANCION
        // * ─────────────────────────────────────────────────────

        // ! ✅ Crear canciones usando diferentes constructores
        Cancion cancion1 = new Cancion("Bohemian Rhapsody", "Queen", 354, "Rock");
        Cancion cancion2 = new Cancion("Blinding Lights", "The Weeknd", 200);
        Cancion cancion3 = new Cancion("Shape of You");

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * MODIFICACIÓN DE ATRIBUTOS CON SETTERS
        // * ─────────────────────────────────────────────────────
        cancion2.setGenero("Pop");
        cancion3.setArtista("Ed Sheeran");
        cancion3.setDuracion(233);
        cancion3.setGenero("Pop");

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * MOSTRAR INFORMACIÓN DE LAS CANCIONES
        // * ─────────────────────────────────────────────────────
        System.out.println("🎼 BIBLIOTECA MUSICAL:\n");
        cancion1.mostrarInformacion();
        System.out.println();
        cancion2.mostrarInformacion();
        System.out.println();
        cancion3.mostrarInformacion();
        System.out.println();

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * REPRODUCIR CANCIONES Y AGREGAR REPRODUCCIONES
        // * ─────────────────────────────────────────────────────
        System.out.println("▶️  REPRODUCIENDO CANCIONES:\n");
        cancion1.reproducir();
        cancion1.reproducir();
        cancion1.reproducir();

        cancion2.reproducir();
        cancion2.reproducir();
        cancion2.reproducir();
        cancion2.reproducir();
        cancion2.reproducir();

        cancion3.reproducir();
        cancion3.reproducir();

        System.out.println("\n📊 ESTADÍSTICAS ACTUALIZADAS:\n");
        cancion1.mostrarInformacion();
        System.out.println();
        cancion2.mostrarInformacion();
        System.out.println();
        cancion3.mostrarInformacion();
        System.out.println();

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * AGREGAR A FAVORITOS Y DAR ME GUSTA
        // * ─────────────────────────────────────────────────────
        System.out.println("❤️  INTERACCIONES CON LAS CANCIONES:\n");
        cancion1.agregarMeGusta(1250);
        cancion2.agregarMeGusta(5800);
        cancion3.agregarMeGusta(3200);

        cancion1.agregarAFavoritos();
        cancion2.agregarAFavoritos();

        System.out.println("\n🎵 CATÁLOGO COMPLETO:\n");
        cancion1.mostrarInformacion();
        System.out.println();
        cancion2.mostrarInformacion();
        System.out.println();
        cancion3.mostrarInformacion();
        System.out.println();

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * COMPARAR CANCIONES
        // * ─────────────────────────────────────────────────────
        System.out.println("⚖️  COMPARACIONES ENTRE CANCIONES:\n");
        cancion1.compararDuracion(cancion2);
        cancion2.compararPopularidad(cancion3);

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * CREAR Y GESTIONAR ARTISTAS
        // * ─────────────────────────────────────────────────────
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║           🎤 GESTIÓN DE ARTISTAS 🎤          ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        Artista artista1 = new Artista("The Weeknd", "Pop/R&B", 2010);
        Artista artista2 = new Artista("Queen", "Rock", 1970);
        Artista artista3 = new Artista("Ed Sheeran");

        // * Configurar artista3
        artista3.setGeneroMusical("Pop");
        artista3.setAñoDebut(2011);

        // * Agregar álbumes y oyentes mensuales
        artista1.agregarAlbum();
        artista1.agregarAlbum();
        artista1.agregarAlbum();
        artista1.agregarAlbum();
        artista1.agregarOyentesMensuales(95000000);

        artista2.agregarAlbum();
        artista2.agregarAlbum();
        artista2.agregarAlbum();
        artista2.agregarAlbum();
        artista2.agregarAlbum();
        artista2.agregarOyentesMensuales(45000000);

        artista3.agregarAlbum();
        artista3.agregarAlbum();
        artista3.agregarAlbum();
        artista3.agregarOyentesMensuales(78000000);

        // * Mostrar información de artistas
        System.out.println();
        artista1.mostrarPerfil();
        System.out.println();
        artista2.mostrarPerfil();
        System.out.println();
        artista3.mostrarPerfil();
        System.out.println();

        // (Programación Orientada a Objetos - POO)
        // * ─────────────────────────────────────────────────────
        // * CREAR Y GESTIONAR USUARIOS
        // * ─────────────────────────────────────────────────────
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║          👤 GESTIÓN DE USUARIOS 👤           ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        Usuario usuario1 = new Usuario("MusicLover99", "Ana", "García", "Premium");
        Usuario usuario2 = new Usuario("RockFan", "Carlos", "Martínez");

        // * Agregar tiempo de escucha y canciones
        usuario1.agregarTiempoEscucha(1200);
        usuario1.agregarCancionEscuchada();
        usuario1.agregarCancionEscuchada();
        usuario1.agregarCancionEscuchada();
        usuario1.agregarCancionEscuchada();

        usuario2.agregarTiempoEscucha(850);
        usuario2.agregarCancionEscuchada();
        usuario2.agregarCancionEscuchada();
        usuario2.agregarCancionEscuchada();

        // * Mostrar perfiles de usuarios
        System.out.println();
        usuario1.mostrarPerfil();
        System.out.println();
        usuario2.mostrarPerfil();
        System.out.println();

        // * Comparar usuarios
        System.out.println("⚖️  COMPARACIÓN DE USUARIOS:");
        usuario1.compararActividad(usuario2);

        // (Programación Orientada a Objetos - POO)
        // ! ✅ TAREA PARA EL ALUMNO:
        // * 1. Crea una clase `Playlist` con atributos: nombre, creador,
        // numeroCancionesList, duracionTotal
        // * 2. Implementa constructores sobrecargados (al menos 2 versiones)
        // * 3. Crea métodos: agregarCancion(), eliminarCancion(),
        // calcularDuracionTotal()
        // * 4. Añade getters y setters con validaciones apropiadas
        // * 5. En main(), crea 3 objetos Playlist y prueba todos los métodos
        // * 6. BONUS: Crea un método que compare dos playlists y determine cuál es más
        // larga
    }
}

// (Programación Orientada a Objetos - POO)
// Definimos la clase Cancion
class Cancion {
    // (Programación Orientada a Objetos - POO)
    // * ⚠️ Atributos (variables de instancia) - PRIVATE para encapsulación
    private String titulo;
    private String artista;
    private int duracion; // * En segundos
    private String genero;
    private int reproducciones;
    private int meGusta;
    private boolean esFavorita;

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor completo (4 parámetros)
    // * Inicializa todos los atributos principales al crear el objeto
    public Cancion(String titulo, String artista, int duracion, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.genero = genero;
        this.reproducciones = 0;
        this.meGusta = 0;
        this.esFavorita = false;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor sobrecargado (3 parámetros)
    // * Permite crear una canción sin especificar el género
    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.genero = "No especificado";
        this.reproducciones = 0;
        this.meGusta = 0;
        this.esFavorita = false;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor sobrecargado (1 parámetro)
    // * Permite crear una canción solo con el título
    public Cancion(String titulo) {
        this.titulo = titulo;
        this.artista = "Artista desconocido";
        this.duracion = 0;
        this.genero = "No especificado";
        this.reproducciones = 0;
        this.meGusta = 0;
        this.esFavorita = false;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Getters - Permiten acceder a los atributos privados
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public int getMeGusta() {
        return meGusta;
    }

    public boolean isEsFavorita() {
        return esFavorita;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Setters - Permiten modificar los atributos privados con validación
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        } else {
            System.out.println("⚠️ El título no puede estar vacío.");
        }
    }

    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artista = artista;
        } else {
            System.out.println("⚠️ El artista no puede estar vacío.");
        }
    }

    public void setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
        } else {
            System.out.println("⚠️ La duración debe ser mayor a 0.");
        }
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.trim().isEmpty()) {
            this.genero = genero;
        } else {
            System.out.println("⚠️ El género no puede estar vacío.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: reproducir
    // * Incrementa el contador de reproducciones
    public void reproducir() {
        reproducciones++;
        System.out.println("▶️  Reproduciendo: " + titulo + " - " + artista + " (" + formatearDuracion() + ")");
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: formatearDuracion
    // * Convierte los segundos a formato mm:ss
    public String formatearDuracion() {
        int minutos = duracion / 60;
        int segundos = duracion % 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarMeGusta
    // * Incrementa el contador de me gusta
    public void agregarMeGusta(int cantidad) {
        if (cantidad > 0) {
            meGusta += cantidad;
            System.out.println("❤️  " + titulo + " recibió " + cantidad + " me gusta");
        } else {
            System.out.println("⚠️ La cantidad debe ser mayor a 0.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarAFavoritos
    // * Marca la canción como favorita
    public void agregarAFavoritos() {
        if (!esFavorita) {
            esFavorita = true;
            System.out.println("⭐ " + titulo + " agregada a favoritos");
        } else {
            System.out.println("ℹ️  " + titulo + " ya está en favoritos");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: esPopular
    // * Determina si la canción es popular basándose en las reproducciones
    public boolean esPopular() {
        return reproducciones >= 1000000;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: calcularPorcentajeAprobacion
    // * Calcula el porcentaje de aprobación basado en reproducciones y me gusta
    public double calcularPorcentajeAprobacion() {
        if (reproducciones == 0) {
            return 0.0;
        }
        return (meGusta * 100.0) / reproducciones;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: compararDuracion
    // * Compara la duración de esta canción con otra
    public void compararDuracion(Cancion otra) {
        System.out.println("Comparando duración:");
        System.out.println("  " + this.titulo + ": " + this.formatearDuracion());
        System.out.println("  " + otra.titulo + ": " + otra.formatearDuracion());

        if (this.duracion > otra.duracion) {
            System.out.println(
                    "  ➡️  " + this.titulo + " es más larga por " + (this.duracion - otra.duracion) + " segundos");
        } else if (this.duracion < otra.duracion) {
            System.out.println(
                    "  ➡️  " + otra.titulo + " es más larga por " + (otra.duracion - this.duracion) + " segundos");
        } else {
            System.out.println("  ➡️  Ambas canciones tienen la misma duración");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: compararPopularidad
    // * Compara la popularidad (reproducciones) con otra canción
    public void compararPopularidad(Cancion otra) {
        System.out.println("Comparando popularidad:");
        System.out.println("  " + this.titulo + ": " + this.reproducciones + " reproducciones");
        System.out.println("  " + otra.titulo + ": " + otra.reproducciones + " reproducciones");

        if (this.reproducciones > otra.reproducciones) {
            System.out.println("  ➡️  " + this.titulo + " es más popular");
        } else if (this.reproducciones < otra.reproducciones) {
            System.out.println("  ➡️  " + otra.titulo + " es más popular");
        } else {
            System.out.println("  ➡️  Ambas canciones tienen la misma popularidad");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: mostrarInformacion
    // * Muestra toda la información de la canción de forma formateada
    public void mostrarInformacion() {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│ 🎵 INFORMACIÓN DE LA CANCIÓN               │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Título:          " + titulo);
        System.out.println("│ Artista:         " + artista);
        System.out.println("│ Género:          " + genero);
        System.out.println("│ Duración:        " + formatearDuracion());
        System.out.println("│ Reproducciones:  " + reproducciones);
        System.out.println("│ Me gusta:        " + meGusta);
        if (reproducciones > 0) {
            System.out.println("│ % Aprobación:    " + String.format("%.1f", calcularPorcentajeAprobacion()) + "%");
        }
        System.out.println("│ Favorita:        " + (esFavorita ? "⭐ Sí" : "❌ No"));
        System.out.println("│ Estado:          " + (esPopular() ? "🔥 POPULAR" : "📊 En crecimiento"));
        System.out.println("└─────────────────────────────────────────────┘");
    }
}

// (Programación Orientada a Objetos - POO)
// Definimos la clase Artista
class Artista {
    // (Programación Orientada a Objetos - POO)
    // * ⚠️ Atributos (variables de instancia)
    private String nombre;
    private String generoMusical;
    private int añoDebut;
    private int numeroAlbumes;
    private long oyentesMensuales;
    private boolean esVerificado;

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor completo (3 parámetros)
    public Artista(String nombre, String generoMusical, int añoDebut) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.añoDebut = añoDebut;
        this.numeroAlbumes = 0;
        this.oyentesMensuales = 0;
        this.esVerificado = false;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor sobrecargado (solo nombre)
    public Artista(String nombre) {
        this.nombre = nombre;
        this.generoMusical = "No especificado";
        this.añoDebut = 0;
        this.numeroAlbumes = 0;
        this.oyentesMensuales = 0;
        this.esVerificado = false;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Getters
    public String getNombre() {
        return nombre;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public int getAñoDebut() {
        return añoDebut;
    }

    public int getNumeroAlbumes() {
        return numeroAlbumes;
    }

    public long getOyentesMensuales() {
        return oyentesMensuales;
    }

    public boolean isEsVerificado() {
        return esVerificado;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Setters con validación
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("⚠️ El nombre no puede estar vacío.");
        }
    }

    public void setGeneroMusical(String generoMusical) {
        if (generoMusical != null && !generoMusical.trim().isEmpty()) {
            this.generoMusical = generoMusical;
        } else {
            System.out.println("⚠️ El género musical no puede estar vacío.");
        }
    }

    public void setAñoDebut(int añoDebut) {
        if (añoDebut > 1900 && añoDebut <= 2025) {
            this.añoDebut = añoDebut;
        } else {
            System.out.println("⚠️ El año de debut debe estar entre 1900 y 2025.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarAlbum
    // * Incrementa el contador de álbumes
    public void agregarAlbum() {
        numeroAlbumes++;
        System.out.println("💿 " + nombre + " lanzó un nuevo álbum (Total: " + numeroAlbumes + ")");

        // * Auto-verificación si tiene 3 o más álbumes
        if (numeroAlbumes >= 3 && !esVerificado) {
            esVerificado = true;
            System.out.println("   ✅ " + nombre + " ahora está verificado!");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarOyentesMensuales
    // * Establece el número de oyentes mensuales
    public void agregarOyentesMensuales(long cantidad) {
        if (cantidad > 0) {
            oyentesMensuales = cantidad;
            System.out.println("👥 " + nombre + " tiene " + formatearNumero(cantidad) + " oyentes mensuales");
        } else {
            System.out.println("⚠️ La cantidad debe ser mayor a 0.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: formatearNumero
    // * Formatea números grandes con separadores
    private String formatearNumero(long numero) {
        if (numero >= 1000000) {
            return String.format("%.1fM", numero / 1000000.0);
        } else if (numero >= 1000) {
            return String.format("%.1fK", numero / 1000.0);
        }
        return String.valueOf(numero);
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: calcularAñosCarrera
    // * Calcula los años de carrera del artista
    public int calcularAñosCarrera() {
        if (añoDebut > 0) {
            return 2025 - añoDebut;
        }
        return 0;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: esLegendario
    // * Determina si el artista es legendario (más de 30 años de carrera)
    public boolean esLegendario() {
        return calcularAñosCarrera() >= 30;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: obtenerCategoria
    // * Devuelve la categoría del artista según oyentes mensuales
    public String obtenerCategoria() {
        if (oyentesMensuales >= 50000000) {
            return "🌟 SUPERESTRELLA";
        } else if (oyentesMensuales >= 10000000) {
            return "⭐ ESTRELLA";
        } else if (oyentesMensuales >= 1000000) {
            return "🎸 POPULAR";
        } else if (oyentesMensuales >= 100000) {
            return "🎤 EMERGENTE";
        } else {
            return "🎵 NUEVO";
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: mostrarPerfil
    // * Muestra el perfil completo del artista
    public void mostrarPerfil() {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│ 🎤 PERFIL DE ARTISTA                       │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Nombre:          " + nombre + (esVerificado ? " ✓" : ""));
        System.out.println("│ Género:          " + generoMusical);
        System.out.println("│ Año debut:       " + (añoDebut > 0 ? añoDebut : "Desconocido"));
        if (añoDebut > 0) {
            System.out.println("│ Años carrera:    " + calcularAñosCarrera() + " años");
        }
        System.out.println("│ Álbumes:         " + numeroAlbumes);
        System.out.println("│ Oyentes/mes:     " + formatearNumero(oyentesMensuales));
        System.out.println("│ Categoría:       " + obtenerCategoria());
        System.out.println("│ Estado:          " + (esLegendario() ? "👑 LEGENDARIO" : "🎸 Activo"));
        System.out.println("└─────────────────────────────────────────────┘");
    }
}

// (Programación Orientada a Objetos - POO)
// Definimos la clase Usuario
class Usuario {
    // (Programación Orientada a Objetos - POO)
    // * ⚠️ Atributos (variables de instancia)
    private String username;
    private String nombre;
    private String apellido;
    private String tipoCuenta; // * "Free" o "Premium"
    private int tiempoEscucha; // * En minutos
    private int cancionesEscuchadas;
    private int playlistsCreadas;

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor completo (4 parámetros)
    public Usuario(String username, String nombre, String apellido, String tipoCuenta) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCuenta = tipoCuenta;
        this.tiempoEscucha = 0;
        this.cancionesEscuchadas = 0;
        this.playlistsCreadas = 0;
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Constructor sobrecargado (3 parámetros - sin tipo de cuenta)
    public Usuario(String username, String nombre, String apellido) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCuenta = "Free";
        this.tiempoEscucha = 0;
        this.cancionesEscuchadas = 0;
        this.playlistsCreadas = 0;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Getters
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public int getTiempoEscucha() {
        return tiempoEscucha;
    }

    public int getCancionesEscuchadas() {
        return cancionesEscuchadas;
    }

    public int getPlaylistsCreadas() {
        return playlistsCreadas;
    }

    // (Programación Orientada a Objetos - POO)
    // * 🛠️ Setters con validación
    public void setUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.username = username;
        } else {
            System.out.println("⚠️ El username no puede estar vacío.");
        }
    }

    public void setTipoCuenta(String tipoCuenta) {
        if (tipoCuenta.equals("Free") || tipoCuenta.equals("Premium")) {
            this.tipoCuenta = tipoCuenta;
        } else {
            System.out.println("⚠️ El tipo de cuenta debe ser 'Free' o 'Premium'.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarTiempoEscucha
    // * Incrementa el tiempo de escucha en minutos
    public void agregarTiempoEscucha(int minutos) {
        if (minutos > 0) {
            tiempoEscucha += minutos;
            System.out.println("⏱️  " + username + " escuchó " + minutos + " minutos de música");
        } else {
            System.out.println("⚠️ Los minutos deben ser positivos.");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: agregarCancionEscuchada
    // * Incrementa el contador de canciones escuchadas
    public void agregarCancionEscuchada() {
        cancionesEscuchadas++;
        System.out.println("🎵 " + username + " escuchó una canción (Total: " + cancionesEscuchadas + ")");
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: crearPlaylist
    // * Incrementa el contador de playlists creadas
    public void crearPlaylist() {
        playlistsCreadas++;
        System.out.println("📝 " + username + " creó una nueva playlist (Total: " + playlistsCreadas + ")");
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: formatearTiempoEscucha
    // * Convierte minutos a formato horas y minutos
    public String formatearTiempoEscucha() {
        int horas = tiempoEscucha / 60;
        int minutos = tiempoEscucha % 60;
        return horas + "h " + minutos + "m";
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: esPremium
    // * Verifica si el usuario tiene cuenta premium
    public boolean esPremium() {
        return tipoCuenta.equals("Premium");
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: obtenerNivelActividad
    // * Devuelve el nivel de actividad según el tiempo de escucha
    public String obtenerNivelActividad() {
        if (tiempoEscucha >= 1000) {
            return "🔥 MUY ACTIVO";
        } else if (tiempoEscucha >= 500) {
            return "⭐ ACTIVO";
        } else if (tiempoEscucha >= 100) {
            return "📊 MODERADO";
        } else {
            return "🌱 NUEVO";
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: compararActividad
    // * Compara la actividad con otro usuario
    public void compararActividad(Usuario otro) {
        System.out.println("\nComparando actividad:");
        System.out.println("  " + this.username + ": " + this.tiempoEscucha + " minutos");
        System.out.println("  " + otro.username + ": " + otro.tiempoEscucha + " minutos");

        if (this.tiempoEscucha > otro.tiempoEscucha) {
            System.out.println("  ➡️  " + this.username + " es más activo");
        } else if (this.tiempoEscucha < otro.tiempoEscucha) {
            System.out.println("  ➡️  " + otro.username + " es más activo");
        } else {
            System.out.println("  ➡️  Ambos usuarios tienen la misma actividad");
        }
    }

    // (Programación Orientada a Objetos - POO)
    // * ✅ Método: mostrarPerfil
    // * Muestra el perfil completo del usuario
    public void mostrarPerfil() {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│ 👤 PERFIL DE USUARIO                       │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Username:        " + username);
        System.out.println("│ Nombre real:     " + nombre + " " + apellido);
        System.out.println("│ Tipo cuenta:     " + (esPremium() ? "⭐ Premium" : "🆓 Free"));
        System.out.println("│ Tiempo escucha:  " + formatearTiempoEscucha());
        System.out.println("│ Canciones:       " + cancionesEscuchadas);
        System.out.println("│ Playlists:       " + playlistsCreadas);
        System.out.println("│ Actividad:       " + obtenerNivelActividad());
        System.out.println("└─────────────────────────────────────────────┘");
    }
}

/*
 * ⚡ **TAREAS PARA EL ALUMNO:**
 * ════════════════════════════════════════════════════════════════
 *
 * 🎯 TAREA 1: Clase Playlist
 * ──────────────────────────────────────────────────────────────
 * 1. Crea una clase `Playlist` con los siguientes atributos:
 * - nombre (String)
 * - creador (String)
 * - numeroCanciones (int)
 * - duracionTotal (int en segundos)
 * - esPublica (boolean)
 *
 * 2. Implementa al menos 3 constructores sobrecargados.
 *
 * 3. Crea los siguientes métodos:
 * - agregarCancion(int duracion): añade una canción y actualiza duración
 * - eliminarCancion(int duracion): elimina una canción
 * - calcularDuracionFormateada(): devuelve duración en formato "Xh Ym"
 * - hacerPublica() / hacerPrivada(): cambia visibilidad
 * - mostrarDetalles(): muestra toda la información
 *
 * 4. Añade getters y setters con validaciones apropiadas.
 *
 * 5. En main(), crea 3 objetos Playlist y prueba todos los métodos.
 *
 * 🎯 TAREA 2: Mejoras a las clases existentes
 * ──────────────────────────────────────────────────────────────
 * 1. En la clase Cancion, añade un método `compararMeGusta(Cancion otra)`
 * que indique cuál canción tiene más me gusta.
 *
 * 2. En la clase Artista, añade un método `compararCarrera(Artista otro)`
 * que compare dos artistas e indique cuál tiene más años de carrera.
 *
 * 3. En la clase Usuario, añade un método `actualizarAPremium()`
 * que cambie el tipo de cuenta a Premium con un mensaje.
 *
 * 🎯 TAREA 3: Sistema de Podcast (AVANZADO)
 * ──────────────────────────────────────────────────────────────
 * 1. Crea una clase `Podcast` con atributos:
 * - titulo (String)
 * - presentador (String)
 * - numeroEpisodios (int)
 * - categoria (String)
 * - suscriptores (int)
 *
 * 2. Implementa métodos para agregar episodios y suscriptores.
 *
 * 3. Crea un método que calcule el promedio de duración por episodio.
 *
 * 4. BONUS: Crea un método que compare dos podcasts por popularidad.
 *
 * 🚀 **¡Explora, experimenta y mejora el código!**
 *
 * 💡 CONCEPTOS CLAVE APRENDIDOS:
 * ══════════════════════════════════════════════════════════════
 * ✅ Clases y Objetos
 * ✅ Atributos (variables de instancia)
 * ✅ Métodos (comportamientos)
 * ✅ Constructores y sobrecarga de constructores
 * ✅ Encapsulación (private + getters/setters)
 * ✅ Validación de datos
 * ✅ Métodos con lógica de negocio
 * ✅ Métodos de comparación entre objetos
 * ✅ Formateo de datos (tiempo, números)
 * ✅ Formateo de salida profesional
 *
 */
