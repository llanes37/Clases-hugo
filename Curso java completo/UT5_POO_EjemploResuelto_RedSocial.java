/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 EJEMPLO RESUELTO 2: RED SOCIAL (Usuario + Foto + GestionFotos)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║                                                                         ║
// ║                     📋 ENUNCIADO DEL EJERCICIO                          ║
// ║                                                                         ║
// ╚═══════════════════════════════════════════════════════════════════════════╝
//
// ? Implementa la clase Usuario. Tiene los atributos:
// ?   - nick (String): Identificador del usuario.
// ?   - nombre (String): Nombre real del usuario.
// ?   - apellidos (String): Apellidos reales del usuario.
// ?
// ? Constructor: recibe nick, nombre y apellidos.
// ?
// ? Métodos:
// ?   - Getters para todos los atributos.
// ?   - equals y hashCode: dos usuarios son iguales si tienen el mismo nick.
// ?   - toString: muestra los datos del usuario.
// ?
// ?
// ? Implementa la clase Foto. Tiene los atributos:
// ?   - nombre (String[]): Array de dos posiciones. En la primera se
// ?     almacenan letras y en la segunda un número identificativo.
// ?     Ejemplo: ["IMG", "001"]
// ?   - formato (String): Formato de la foto (jpg, png, gif, etc.).
// ?   - tamanio (double): Tamaño en MB de la foto.
// ?   - etiquetados (HashSet<Usuario>): Usuarios etiquetados en la foto.
// ?     Se implementa como agregación en un HashSet.
// ?   - likes (int): Número de "me gusta" de la foto.
// ?
// ? Constructores:
// ?   - Constructor con parámetros: nombre, formato y tamaño.
// ?     Una foto siempre se crea sin usuarios etiquetados y sin likes.
// ?   - Constructor sobrecargado: nombre y tamaño solamente.
// ?     En caso de que no se especifique un formato, este es "jpg".
// ?
// ? Métodos:
// ?   - Getters para todos los atributos.
// ?   - getUsuarios: devuelve un String con los datos de los usuarios
// ?     etiquetados, NO devuelve el HashSet directamente.
// ?   - No hay setters.
// ?   - equals y hashCode: dos fotos son iguales si tienen el mismo
// ?     nombre, formato y tamaño.
// ?   - toString: muestra todos los datos de la foto.
// ?   - like(): Añade un like a la foto.
// ?   - addEtiquetado(Usuario u): Añade un usuario como etiquetado.
// ?     Devuelve boolean indicando si se pudo etiquetar o no.
// ?   - removeEtiquetado(Usuario u): Quita un usuario como etiquetado.
// ?     Devuelve boolean indicando si se pudo realizar la operación.
// ?
// ?
// ? Crea la clase GestionFotos que gestiona la red social a través de
// ? un HashMap cuya clave es un Usuario y el valor asociado es una
// ? Lista de sus fotos.
// ?
// ? Métodos de GestionFotos:
// ?   - addUsuario(Usuario u): Añade un nuevo usuario a la red social.
// ?     Devuelve boolean indicando si se añadió o si ya existía.
// ?     Si ya existía el usuario, NO se deben eliminar sus fotos.
// ?   - addFoto(Usuario u, Foto f): Añade una foto a un usuario.
// ?     Devuelve boolean indicando si se pudo añadir.
// ?   - getFotosUsuario(Usuario u): Dado un usuario, devuelve todas
// ?     sus fotos.
// ?   - darLike(Usuario u, Foto f): Recibe un usuario y su foto para
// ?     darle like. Devuelve el número de likes de la foto, o -1
// ?     si no se encontró la foto.
// ?   - etiquetar(Foto f, Usuario u): Recibe una foto y el usuario
// ?     que se quiere etiquetar. Busca la foto en toda la red social
// ?     y devuelve boolean indicando si se pudo etiquetar.
// ?   - eliminar(Usuario u, double tamMax): Elimina las fotos de un
// ?     usuario cuyo tamaño supere tamMax. Devuelve un HashSet con
// ?     las fotos eliminadas.
// ?   - eliminar(double tamMax): Sobrecarga del método anterior.
// ?     Elimina fotos de TODOS los usuarios cuyo tamaño supere tamMax.
// ?     Devuelve un HashSet con las fotos eliminadas.
//
// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║                     📝 SOLUCIÓN A CONTINUACIÓN                          ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

import java.util.*;

// ═══════════════════════════════════════════════════════════════
// * 👤 CLASE USUARIO
// ═══════════════════════════════════════════════════════════════
//
// ? 📋 ENUNCIADO:
// ? Implementa la clase Usuario. Tiene los atributos:
// ?   - nick (String): Identificador del usuario.
// ?   - nombre (String): Nombre real del usuario.
// ?   - apellidos (String): Apellidos reales del usuario.
// ?
// ? Constructor: recibe nick, nombre y apellidos.
// ?
// ? Métodos:
// ?   - Getters para todos los atributos.
// ?   - equals y hashCode: por nick.
// ?   - toString: muestra los datos del usuario.

class Usuario {

    // * Atributos privados
    private String nick; // ? Identificador único del usuario
    private String nombre; // ? Nombre real
    private String apellidos; // ? Apellidos reales

    // * Constructor — recibe nick, nombre y apellidos
    public Usuario(String nick, String nombre, String apellidos) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // * Getters
    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    // * equals — dos usuarios iguales si tienen el mismo nick
    // ? El nick es el identificador único (como un DNI del usuario)
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Usuario otro = (Usuario) obj;
        return nick.equals(otro.nick);
    }

    // * hashCode — coherente con equals (mismo campo: nick)
    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }

    // * toString
    @Override
    public String toString() {
        return "@" + nick + " (" + nombre + " " + apellidos + ")";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📸 CLASE FOTO
// ═══════════════════════════════════════════════════════════════
//
// ? 📋 ENUNCIADO:
// ? Implementa la clase Foto. Tiene los atributos:
// ? - nombre (String[]): Array de 2 posiciones ["IMG", "001"].
// ? - formato (String): jpg, png, gif, etc.
// ? - tamanio (double): Tamaño en MB.
// ? - etiquetados (HashSet<Usuario>): Agregación en HashSet.
// ? - likes (int): Número de "me gusta".
// ?
// ? Constructores:
// ? - Con parámetros: nombre, formato, tamaño.
// ? Se crea SIN etiquetados y SIN likes.
// ? - Sobrecargado: nombre, tamaño. Formato por defecto = "jpg".
// ?
// ? Métodos:
// ? - Getters para todos. getUsuarios devuelve String, NO HashSet.
// ? - No hay setters.
// ? - like(): Añade un like.
// ? - addEtiquetado(Usuario u): Devuelve boolean.
// ? - removeEtiquetado(Usuario u): Devuelve boolean.
// ? - equals y hashCode: por nombre, formato y tamaño.
// ? - toString: muestra todos los datos.

class Foto {

    // * Atributos privados
    private String[] nombre; // ? Array de 2 posiciones: ["IMG", "001"]
    private String formato; // ? "jpg", "png", "gif", etc.
    private double tamanio; // ? Tamaño en MB
    private HashSet<Usuario> etiquetados; // ? Usuarios etiquetados (agregación)
    private int likes; // ? Contador de "me gusta"

    // * Constructor COMPLETO — recibe nombre, formato y tamaño
    // ? Se crea SIN etiquetados (HashSet vacío) y SIN likes (0)
    public Foto(String[] nombre, String formato, double tamanio) {
        this.nombre = nombre;
        this.formato = formato;
        this.tamanio = tamanio;
        this.etiquetados = new HashSet<>();
        this.likes = 0;
    }

    // * Constructor SOBRECARGADO — sin formato → por defecto "jpg"
    // ? this(...) llama al constructor de ARRIBA pasándole "jpg" como formato
    // ? Así no repetimos código (el otro constructor hace todo el trabajo)
    public Foto(String[] nombre, double tamanio) {
        this(nombre, "jpg", tamanio);
    }

    // * Getters
    public String getNombreCompleto() {
        return nombre[0] + "-" + nombre[1];
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

    // * getUsuarios — devuelve String, NO el HashSet
    // ? Protegemos la colección interna: nadie puede hacer
    // ? foto.getUsuarios().clear() desde fuera
    public String getUsuarios() {
        if (etiquetados.isEmpty())
            return "(ninguno)";
        StringBuilder sb = new StringBuilder();
        for (Usuario u : etiquetados) {
            sb.append(u).append(", ");
        }
        // ? Quitamos la última coma y espacio
        return sb.substring(0, sb.length() - 2);
    }

    // * like — suma 1 al contador
    public void like() {
        likes++;
    }

    // * addEtiquetado — añade un usuario
    // ? Devuelve false si ya estaba etiquetado
    // ? (HashSet.add() devuelve false si ya existía, gracias a equals/hashCode)
    public boolean addEtiquetado(Usuario u) {
        return etiquetados.add(u);
    }

    // * removeEtiquetado — quita un usuario
    // ? Devuelve false si no estaba etiquetado
    public boolean removeEtiquetado(Usuario u) {
        return etiquetados.remove(u);
    }

    // * equals — dos fotos iguales si mismo nombre, formato y tamaño
    // ? El nombre es un array → usamos Arrays.equals() para compararlo
    // ? (si usáramos nombre.equals() compararía la referencia, no el contenido)
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Foto otra = (Foto) obj;
        return Arrays.equals(nombre, otra.nombre)
                && formato.equals(otra.formato)
                && Double.compare(tamanio, otra.tamanio) == 0;
    }

    // * hashCode — coherente con equals
    // ? Combinamos hash del array (Arrays.hashCode) + formato + tamaño
    @Override
    public int hashCode() {
        int result = Objects.hash(formato, tamanio);
        result = 31 * result + Arrays.hashCode(nombre);
        return result;
    }

    // * toString
    @Override
    public String toString() {
        return getNombreCompleto() + "." + formato
                + " (" + tamanio + " MB) | Likes: " + likes
                + " | Etiquetados: " + getUsuarios();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📋 CLASE GESTORA: GestionFotos
// ═══════════════════════════════════════════════════════════════
//
// ? 📋 ENUNCIADO:
// ? Crea la clase GestionFotos que gestiona la red social a través
// ? de un HashMap cuya clave es un Usuario y el valor es una
// ? Lista de sus fotos.
// ?
// ? Métodos:
// ? - addUsuario(Usuario u): Añade usuario. Devuelve boolean.
// ? Si ya existía, NO eliminar sus fotos.
// ? - addFoto(Usuario u, Foto f): Añade foto a usuario.
// ? Devuelve boolean.
// ? - getFotosUsuario(Usuario u): Devuelve todas sus fotos.
// ? - darLike(Usuario u, Foto f): Da like a foto del usuario.
// ? Devuelve nº de likes o -1 si no se encontró.
// ? - etiquetar(Foto f, Usuario u): Busca la foto en toda la
// ? red y etiqueta al usuario. Devuelve boolean.
// ? - eliminar(Usuario u, double tamMax): Elimina fotos de un
// ? usuario que superen tamMax. Devuelve HashSet<Foto>.
// ? - eliminar(double tamMax): Sobrecarga. Elimina de TODOS
// ? los usuarios. Devuelve HashSet<Foto>.

class GestionFotos {

    // * El HashMap es el corazón de la gestión
    // ? Cada usuario tiene asociada una lista de fotos
    private HashMap<Usuario, List<Foto>> red;

    // * Constructor — crea el HashMap vacío
    public GestionFotos() {
        this.red = new HashMap<>();
    }

    // * addUsuario — añade un usuario nuevo con lista vacía de fotos
    // ? Devuelve false si ya existía.
    // ? containsKey() busca la clave usando equals/hashCode de Usuario
    // ! ¡IMPORTANTE! Si ya existía, NO borrar sus fotos
    public boolean addUsuario(Usuario u) {
        if (red.containsKey(u))
            return false;
        red.put(u, new ArrayList<>());
        return true;
    }

    // * addFoto — añade una foto a un usuario
    // ? Devuelve false si el usuario no existe en la red.
    // ? get() devuelve null si la clave no existe en el HashMap.
    public boolean addFoto(Usuario u, Foto foto) {
        List<Foto> fotos = red.get(u);
        if (fotos == null)
            return false;
        fotos.add(foto);
        return true;
    }

    // * getFotosUsuario — devuelve la lista de fotos de un usuario
    // ? Devuelve null si el usuario no existe
    public List<Foto> getFotosUsuario(Usuario u) {
        return red.get(u);
    }

    // * darLike — da like a una foto de un usuario
    // ? Devuelve el nº de likes, o -1 si no se encontró.
    // ? Recorre la lista de fotos del usuario buscando con equals()
    public int darLike(Usuario u, Foto foto) {
        List<Foto> fotos = red.get(u);
        if (fotos == null)
            return -1;

        for (Foto f : fotos) {
            if (f.equals(foto)) {
                f.like();
                return f.getLikes();
            }
        }
        return -1; // ? No se encontró esa foto en este usuario
    }

    // * etiquetar — busca una foto en TODA la red y etiqueta a un usuario
    // ? Recorre values() del HashMap (todas las listas de fotos)
    // ? porque no sabemos a qué usuario pertenece la foto
    public boolean etiquetar(Foto foto, Usuario u) {
        for (List<Foto> listaFotos : red.values()) {
            for (Foto f : listaFotos) {
                if (f.equals(foto)) {
                    return f.addEtiquetado(u);
                }
            }
        }
        return false;
    }

    // * eliminar — VERSIÓN 1: elimina fotos de UN usuario por tamaño
    // ? Devuelve un HashSet con las fotos eliminadas.
    // ! CUIDADO: No se puede usar for-each para borrar mientras recorres.
    // ! Hay que usar Iterator.remove() para borrado seguro.
    // ? Si haces fotos.remove(f) dentro de un for-each →
    // ? lanza ConcurrentModificationException
    public HashSet<Foto> eliminar(Usuario u, double tamMax) {
        HashSet<Foto> eliminadas = new HashSet<>();
        List<Foto> fotos = red.get(u);
        if (fotos == null)
            return eliminadas;

        // ? Iterator nos permite recorrer Y borrar a la vez
        Iterator<Foto> it = fotos.iterator();
        while (it.hasNext()) {
            Foto f = it.next();
            if (f.getTamanio() > tamMax) {
                eliminadas.add(f);
                it.remove(); // ? Borra de la lista de forma segura
            }
        }
        return eliminadas;
    }

    // * eliminar — VERSIÓN 2 (SOBRECARGA): elimina de TODOS los usuarios
    // ? Misma firma pero sin el parámetro Usuario.
    // ? Reutiliza la versión 1 para cada usuario (principio DRY).
    // ? keySet() devuelve todas las claves (usuarios) del HashMap.
    public HashSet<Foto> eliminar(double tamMax) {
        HashSet<Foto> todas = new HashSet<>();
        for (Usuario u : red.keySet()) {
            todas.addAll(eliminar(u, tamMax));
        }
        return todas;
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📸 PROGRAMA PRINCIPAL — PRUEBAS
// ═══════════════════════════════════════════════════════════════

public class UT5_POO_EjemploResuelto_RedSocial {

    public static void main(String[] args) {

        // * Crear usuarios
        Usuario ana = new Usuario("ana_garcia", "Ana", "García");
        Usuario carlos = new Usuario("carlos_lop", "Carlos", "López");
        Usuario maria = new Usuario("mari_99", "María", "Fernández");

        // * Crear fotos (con y sin formato)
        Foto foto1 = new Foto(new String[] { "IMG", "001" }, "jpg", 2.5);
        Foto foto2 = new Foto(new String[] { "IMG", "002" }, "png", 5.0);
        Foto foto3 = new Foto(new String[] { "IMG", "003" }, 1.2); // ? formato = "jpg"
        Foto foto4 = new Foto(new String[] { "VID", "001" }, "mp4", 8.0);

        // * Crear la red social
        GestionFotos red = new GestionFotos();
        red.addUsuario(ana);
        red.addUsuario(carlos);
        System.out.println("¿Añadir Ana otra vez? " + red.addUsuario(ana)); // ? false

        // * Añadir fotos
        red.addFoto(ana, foto1);
        red.addFoto(ana, foto2);
        red.addFoto(carlos, foto3);
        red.addFoto(carlos, foto4);

        // * Dar likes y etiquetar
        red.darLike(ana, foto1);
        red.darLike(ana, foto1);
        red.darLike(ana, foto1);
        System.out.println("Likes de foto1: " + foto1.getLikes());

        red.etiquetar(foto3, maria);
        System.out.println("Etiquetados foto3: " + foto3.getUsuarios());

        // * Mostrar fotos de Ana
        System.out.println("\nFotos de Ana:");
        for (Foto f : red.getFotosUsuario(ana)) {
            System.out.println("  " + f);
        }

        // * Eliminar fotos de Carlos > 3 MB
        System.out.println("\nFotos de Carlos ANTES:");
        for (Foto f : red.getFotosUsuario(carlos)) {
            System.out.println("  " + f);
        }

        HashSet<Foto> eliminadas = red.eliminar(carlos, 3.0);
        System.out.println("Eliminadas: " + eliminadas.size());

        System.out.println("Fotos de Carlos DESPUÉS:");
        for (Foto f : red.getFotosUsuario(carlos)) {
            System.out.println("  " + f);
        }

        // * Eliminar de TODOS > 4 MB (sobrecarga)
        HashSet<Foto> global = red.eliminar(4.0);
        System.out.println("\nEliminadas globalmente: " + global.size());

        // ! ✅ TAREA ALUMNO:
        // * 1. Añade un método contarLikesTotales(Usuario u) en GestionFotos
        // * que sume los likes de TODAS las fotos de un usuario.
        // * 2. Añade un método fotoConMasLikes() que recorra TODO el HashMap
        // * y devuelva la Foto con más likes de toda la red social.
        // * 3. Añade un método buscarPorFormato(String formato) que devuelva
        // * un ArrayList<Foto> con todas las fotos de ese formato.
        // * 4. Añade un atributo "privada" (boolean) a Foto y modifica
        // * addEtiquetado para que no se pueda etiquetar en fotos privadas.
    }
}
