/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 PRÁCTICA GUIADA: RED SOCIAL (Ampliación)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║                                                                         ║
// ║                     📋 ENUNCIADO DEL EJERCICIO                          ║
// ║                                                                         ║
// ╚═══════════════════════════════════════════════════════════════════════════╝
//
// ? En este ejercicio vamos a implementar una Red Social (Ampliación).
// ? Tendremos tres entidades principales según la documentación:
// ?   1. Usuario: representa a una persona en la red.
// ?   2. Foto: representa las publicaciones.
// ?   3. GestionFotos: es el sistema principal que administra la red social.
// ?
// ? 📌 OBJETIVO: Sigue la estructura del código, lee detenidamente cada parte
// ? y luego completa las pequeñas tareas dejadas al final para mejorar el sistema.
// ? Todo está preparado para que puedas ejecutarlo directamente y ver cómo funciona.

import java.util.*;

// 🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩
// 🟨🟨 CLASE USUARIO
// 🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩

// * 📖 TEORÍA: La clase Usuario define los datos de cada persona.
// * Su identificador principal (id único) será el "nick".
class Usuario {

    // * Atributos privados
    private String nick; // ? Identificador del usuario (ej: "joaquin_99")
    private String nombre; // ? Nombre real
    private String apellidos; // ? Apellidos reales

    // * Constructor — inicializa todos los campos
    public Usuario(String nick, String nombre, String apellidos) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // * Getters — Accedemos a la información
    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    // * equals() y hashCode() — sirven para comparar objetos
    // ? Dos usuarios son exactamente el mismo si tienen el mismo "nick"
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Usuario otro = (Usuario) obj;
        return nick.equals(otro.nick); // Compara por nick
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }

    // * toString() — Para imprimir el objeto por pantalla cómodamente
    @Override
    public String toString() {
        return "@" + nick + " (" + nombre + " " + apellidos + ")";
    }
}

// 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
// 🟨🟨 CLASE FOTO
// 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦

// * 📖 TEORÍA: La clase Foto representa una publicación en la red.
// * Usaremos un array de Strings para el nombre y un HashSet para los usuarios
// etiquetados.
class Foto {

    // * Atributos privados
    private String[] nombre; // ? Nombre de la foto en array, ej: ["IMG", "001"]
    private String formato; // ? Formato, ej: "jpg", "png", "gif"
    private double tamanio; // ? Tamaño en MB
    private HashSet<Usuario> etiquetados; // ? Colección de usuarios etiquetados
    private int likes; // ? Número de "me gusta"

    // * Constructor Completo
    // ? Fíjate en que etiquetados y likes NO se piden por parámetro,
    // ? siempre nacen vacíos y con 0 likes.
    public Foto(String[] nombre, String formato, double tamanio) {
        this.nombre = nombre;
        this.formato = formato;
        this.tamanio = tamanio;
        this.etiquetados = new HashSet<>(); // Se inicializa vacío de forma segura
        this.likes = 0; // Comienza en 0
    }

    // * Constructor Sobrecargado
    // ? Si no especificamos el formato de la foto por parámetro, asume "jpg".
    public Foto(String[] nombre, double tamanio) {
        this(nombre, "jpg", tamanio); // Reutiliza el constructor de arriba con "jpg"
    }

    // * Getters
    public String[] getNombre() {
        return nombre;
    }

    public String getFormato() {
        return formato;
    }

    public double getTamanio() {
        return tamanio;
    }

    public int getLikes() {
        return likes;
    }

    // * getUsuarios()
    // ? Devuelve un String uniendo la información de todos los etiquetados.
    // ? Lo retornamos como un String para proteger nuestro HashSet original
    // (encapsulación).
    public String getUsuarios() {
        if (etiquetados.isEmpty()) {
            return "Sin etiquetados";
        }
        StringBuilder sb = new StringBuilder();
        for (Usuario u : etiquetados) {
            sb.append(u.getNick()).append(", ");
        }
        return sb.substring(0, sb.length() - 2); // Quitamos la última coma
    }

    // * like() — aumenta el número de likes en 1
    public void like() {
        this.likes++;
    }

    // * addEtiquetado() — etiqueta a un amigo
    // ? hashCode del HashSet nos ayuda aquí: devuelve 'true' si lo añade bien.
    public boolean addEtiquetado(Usuario u) {
        return this.etiquetados.add(u);
    }

    // * removeEtiquetado() — retira la etiqueta de un amigo
    public boolean removeEtiquetado(Usuario u) {
        return this.etiquetados.remove(u);
    }

    // * equals() y hashCode()
    // ? Dos fotos son idénticas si tienen el mismo nombre (array), formato y
    // tamaño.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Foto otra = (Foto) obj;
        return Double.compare(otra.tamanio, tamanio) == 0 &&
                Arrays.equals(nombre, otra.nombre) &&
                formato.equals(otra.formato);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(formato, tamanio);
        result = 31 * result + Arrays.hashCode(nombre);
        return result;
    }

    // * toString() — imprime bonita la foto para consola
    @Override
    public String toString() {
        String nombreCompleto = nombre[0] + nombre[1]; // ej: IMG001
        return "Foto [" + nombreCompleto + "." + formato + ", " + tamanio + "MB, Likes: " + likes
                + "] -> Etiquetados: [" + getUsuarios() + "]";
    }
}

// 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
// 🟨🟨 CLASE GESTION FOTOS (El motor principal de la Red)
// 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

// * 📖 TEORÍA: GestionFotos utilizará un HashMap.
// * Cada Usuario (clave) tiene su propia colección/lista de fotos (valor
// asociado).
class GestionFotos {

    // * HashMap principal de la Red Social
    private HashMap<Usuario, List<Foto>> redSocial;

    public GestionFotos() {
        this.redSocial = new HashMap<>(); // Inicializamos el mapa vacío
    }

    // * addUsuario() — da de alta un usuario nuevo en el sistema
    public boolean addUsuario(Usuario u) {
        if (redSocial.containsKey(u)) {
            return false; // El usuario ya existía, devuelve false
        }
        // Creamos su hueco en el mapa asociándolo con una lista vacía
        redSocial.put(u, new ArrayList<>());
        return true;
    }

    // * addFoto() — sube una foto vinculándola a un usuario concreto
    public boolean addFoto(Usuario u, Foto f) {
        if (!redSocial.containsKey(u)) {
            return false; // El usuario no está registrado en la red
        }
        redSocial.get(u).add(f); // Obtenemos su lista y ahí mismo metemos la foto
        return true;
    }

    // * getFotosUsuario() — devuelve la lista completa de fotos
    public List<Foto> getFotosUsuario(Usuario u) {
        return redSocial.get(u); // Cuidado: Puede devolver null si el usuario no existe
    }

    // * darLike() — da un me gusta a cierta foto del usuario elegido
    public int darLike(Usuario u, Foto f) {
        List<Foto> fotos = redSocial.get(u);
        if (fotos != null) {
            for (Foto fotoActual : fotos) {
                if (fotoActual.equals(f)) {
                    fotoActual.like();
                    return fotoActual.getLikes(); // Devuelve likes actualizados
                }
            }
        }
        return -1; // -1 indica que no se encontró
    }

    // * etiquetar() — busca la foto por TODA la red para etiquetar a alguien
    // ? pista del enunciado: usamos recorrer el conjunto de 'values()'
    public boolean etiquetar(Foto fotoBuscada, Usuario usuarioAEtiquetar) {
        for (List<Foto> listaFotos : redSocial.values()) {
            for (Foto f : listaFotos) {
                if (f.equals(fotoBuscada)) {
                    return f.addEtiquetado(usuarioAEtiquetar);
                }
            }
        }
        return false;
    }

    // * eliminar() — elimina todas fotos de UN usuario particular que excedan un
    // límite en MB
    public HashSet<Foto> eliminar(Usuario u, double tamMax) {
        HashSet<Foto> borradas = new HashSet<>();
        List<Foto> fotos = redSocial.get(u);

        if (fotos != null) {
            // ! USAMOS ITERATOR: Es super necesario porque vamos a borrar mientras estamos
            // dentro del bucle.
            Iterator<Foto> it = fotos.iterator();
            while (it.hasNext()) {
                Foto f = it.next();
                if (f.getTamanio() > tamMax) {
                    borradas.add(f); // La guardamos para nuestro histórico
                    it.remove(); // La borramos directamente de forma segura
                }
            }
        }
        return borradas;
    }

    // * eliminar (SOBRECARGADO) — limpia las fotos que pesen mucho en TODA LA RED
    public HashSet<Foto> eliminar(double tamMax) {
        HashSet<Foto> todasBorradas = new HashSet<>();
        // ? .keySet() nos entregará todos los usuarios en la red
        for (Usuario u : redSocial.keySet()) {
            // Reutilizamos toda la lógica del método de arriba (DRY: Don't Repeat Yourself)
            todasBorradas.addAll(eliminar(u, tamMax));
        }
        return todasBorradas; // Se entregan al exterior
    }
}

// 🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪
// 🟨🟨 CLASE PRINCIPAL — MAIN
// 🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪🟪

public class UT0_RedSocial_Ampliacion {
    public static void main(String[] args) {

        // * 1️⃣ CREAMOS Y PREPARAMOS EL ENTORNO
        // ────────────────────────────────────────────────────────────
        System.out.println("🤖 BIENVENIDO A LA PRÁCTICA: RED SOCIAL");
        System.out.println("------------------------------------------");

        GestionFotos instaManager = new GestionFotos();

        Usuario u1 = new Usuario("alex_99", "Alex", "Martinez");
        Usuario u2 = new Usuario("laura_tech", "Laura", "Gomez");
        Usuario u3 = new Usuario("pepe_dev", "Pepe", "Ruiz");

        // * Entrando al sistema...
        instaManager.addUsuario(u1);
        instaManager.addUsuario(u2);
        instaManager.addUsuario(u3);

        // * Creando algunas fotografías...
        Foto f1 = new Foto(new String[] { "IMG", "001" }, "jpg", 2.5);
        Foto f2 = new Foto(new String[] { "VID", "005" }, "mp4", 15.0);
        Foto f3 = new Foto(new String[] { "IMG", "002" }, 1.2); // Utiliza formato 'jpg' por defecto

        // * Las subimos al gestor (a su cuenta)
        instaManager.addFoto(u1, f1);
        instaManager.addFoto(u1, f2);
        instaManager.addFoto(u2, f3);

        // * 2️⃣ SEGUIMIENTO DEL CÓDIGO Y ACCIONES
        // ────────────────────────────────────────────────────────────
        System.out.println("👉 Dar like a f1 de u1: " + instaManager.darLike(u1, f1) + " like(s).");
        System.out.println("👉 Dar like a f1 de u1: " + instaManager.darLike(u1, f1) + " like(s).");

        System.out.println("👉 Etiquetando a Laura en la foto 1: " + instaManager.etiquetar(f1, u2));

        System.out.println("\n📸 FOTOS DE ALEX (@alex_99):");
        for (Foto foto : instaManager.getFotosUsuario(u1)) {
            System.out.println("   " + foto);
        }

        // Probando la eliminación global de los pesados...
        System.out.println("\n🔥 Eliminando archivos muy pesados de TODA LA RED (Más de 10 MB)...");
        HashSet<Foto> borradasGlobal = instaManager.eliminar(10.0);
        System.out.println("   Archivos eliminados y reciclados: " + borradasGlobal);

        // ──────────────────────────────────────────────────────────────────
        // ! ✅ TAREAS PARA EL ALUMNO (ACTIVIDADES PARA IR HACIENDO)
        // ──────────────────────────────────────────────────────────────────
        // * TAREA 1:
        // Añade aquí abajo una nueva foto (f4) con un tamaño pequeñito (0.5), súbela
        // a la cuenta de Pepe (@pepe_dev) e imprime sus fotos para verificar que subió
        // bien.

        // TODO: (Escribe tu código para la Tarea 1 a continuación:)

        // * TAREA 2:
        // Ve a la clase 'Usuario' y añade un método "public boolean esCuentaLarga()"
        // que devuelva true si el 'nick' tiene más de 8 caracteres. Luego, pruébalo
        // aquí con u2.

        // TODO: (Escribe tu código de prueba aquí:)

        // * TAREA 3:
        // Ve a la clase 'GestionFotos' y crea un nuevo método:
        // "public int borrarTodo(Usuario u)"
        // Que coja la lista de fotos de ese usuario, use el método .clear() sobre ella
        // para dejársela vacía (sin fotos), y devuelva la cantidad de fotos que le ha
        // borrado.
        // ¡Prúebalo aquí abajo! (puedes contar antes un .size() sobre su lista de
        // fotos)

        // TODO: (Escribe tu prueba a continuación:)

    }
}
