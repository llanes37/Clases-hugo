/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 PRÁCTICA GUIADA 2: RED SOCIAL — IMPLEMENTA TÚ EL CÓDIGO
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  📋 INSTRUCCIONES:
 *  Este archivo tiene la ESTRUCTURA dada. Tú tienes que:
 *    1. Implementar cada método donde ves // TODO
 *    2. Probar cada clase en el main() antes de pasar a la siguiente
 *    3. Cuando acabes, compara con UT5_POO_EjemploResuelto_RedSocial.java
 *
 *  🎯 ORDEN RECOMENDADO:
 *    1️⃣  Clase Usuario (la más sencilla, sin dependencias)
 *    2️⃣  Clase Foto (usa Usuario en su HashSet)
 *    3️⃣  Clase GestionFotos (usa HashMap<Usuario, List<Foto>>)
 *    4️⃣  Probar todo en main()
 *
 *  💡 Si algo no funciona → lee 📘_UT5_POO_Ejercicios_Recuperacion.md
 * ******************************************************************************************
 */

import java.util.*;

// ═══════════════════════════════════════════════════════════════
// * 👤 CLASE USUARIO (empieza por aquí — la más sencilla)
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
// ?   - equals y hashCode: dos usuarios son iguales si tienen el mismo nick.
// ?   - toString: muestra los datos del usuario.

class Usuario {

    // TODO 1: Declara los 3 atributos privados (nick, nombre, apellidos)

    // TODO 2: Implementa el constructor
    public Usuario(String nick, String nombre, String apellidos) {
        // TODO
    }

    // TODO 3: Implementa los getters (getNick, getNombre, getApellidos)

    // TODO 4: Implementa equals
    // Dos usuarios son iguales si tienen el MISMO nick
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 5: Implementa hashCode
    // Pista: return Objects.hash(nick);
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    // TODO 6: Implementa toString
    // Ejemplo: "@ana_garcia (Ana García)"
    @Override
    public String toString() {
        // TODO
        return "";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📸 CLASE FOTO (segundo paso — usa Usuario)
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
// ? Pista: usa this(nombre, "jpg", tamanio) para llamar al otro constructor.
// ?
// ? Métodos:
// ? - Getters para todos. getUsuarios devuelve String, NO el HashSet.
// ? - No hay setters.
// ? - like(): Añade un like.
// ? - addEtiquetado(Usuario u): Devuelve boolean.
// ? (HashSet.add() devuelve false si ya existía)
// ? - removeEtiquetado(Usuario u): Devuelve boolean.
// ? - equals y hashCode: por nombre (array), formato y tamaño.
// ? Para arrays: Arrays.equals(nombre, otra.nombre)
// ? Arrays.hashCode(nombre)
// ? - toString: muestra todos los datos.

class Foto {

    // TODO 7: Declara los 5 atributos privados
    // nombre es String[], etiquetados es HashSet<Usuario>

    // TODO 8: Implementa el constructor COMPLETO (nombre, formato, tamanio)
    // Se crea con etiquetados = new HashSet<>() y likes = 0
    public Foto(String[] nombre, String formato, double tamanio) {
        // TODO
    }

    // TODO 9: Implementa el constructor SOBRECARGADO (solo nombre y tamanio)
    // Formato por defecto = "jpg"
    // Pista: usa this(nombre, "jpg", tamanio)
    public Foto(String[] nombre, double tamanio) {
        // TODO
    }

    // TODO 10: Implementa los getters
    // getNombreCompleto() → nombre[0] + "-" + nombre[1]
    // getUsuarios() → devuelve String con los etiquetados, NO el HashSet

    // TODO 11: Implementa like()
    // Simplemente suma 1 a likes
    public void like() {
        // TODO
    }

    // TODO 12: Implementa addEtiquetado(Usuario u)
    // Pista: return etiquetados.add(u);
    // (HashSet detecta duplicados gracias a equals/hashCode de Usuario)
    public boolean addEtiquetado(Usuario u) {
        // TODO
        return false;
    }

    // TODO 13: Implementa removeEtiquetado(Usuario u)
    // Pista: return etiquetados.remove(u);
    public boolean removeEtiquetado(Usuario u) {
        // TODO
        return false;
    }

    // TODO 14: Implementa equals
    // Por nombre (array), formato y tamaño
    // IMPORTANTE: Arrays.equals(nombre, otra.nombre) para el array
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 15: Implementa hashCode
    // Pista:
    // int result = Objects.hash(formato, tamanio);
    // result = 31 * result + Arrays.hashCode(nombre);
    // return result;
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    // TODO 16: Implementa toString
    @Override
    public String toString() {
        // TODO
        return "";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📋 CLASE GESTORA: GestionFotos (tercer paso — la más compleja)
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
// ? Devuelve boolean. False si el usuario no existe.
// ? - getFotosUsuario(Usuario u): Devuelve todas sus fotos.
// ? - darLike(Usuario u, Foto f): Da like a foto del usuario.
// ? Devuelve nº de likes o -1 si no se encontró.
// ? - etiquetar(Foto f, Usuario u): Busca la foto en toda la
// ? red (recorre values()) y etiqueta al usuario. Devuelve boolean.
// ? - eliminar(Usuario u, double tamMax): Elimina fotos de un
// ? usuario que superen tamMax. Usa Iterator, NO for-each.
// ? Devuelve HashSet<Foto> con las eliminadas.
// ? - eliminar(double tamMax): SOBRECARGA. Elimina de TODOS
// ? los usuarios. Reutiliza el método anterior. Devuelve HashSet<Foto>.

class GestionFotos {

    // TODO 17: Declara el atributo HashMap<Usuario, List<Foto>>
    // (la clave es Usuario, el valor es la lista de sus fotos)

    // TODO 18: Implementa el constructor
    // Inicializa el HashMap con new HashMap<>()
    public GestionFotos() {
        // TODO
    }

    // TODO 19: Implementa addUsuario(Usuario u)
    // - Si ya existe (containsKey) → return false
    // - Si no existe → put(u, new ArrayList<>()) → return true
    // IMPORTANTE: si ya existía, NO borrar sus fotos
    public boolean addUsuario(Usuario u) {
        // TODO
        return false;
    }

    // TODO 20: Implementa addFoto(Usuario u, Foto foto)
    // - Obtén la lista con red.get(u)
    // - Si es null (usuario no existe) → return false
    // - Si existe → añade la foto y return true
    public boolean addFoto(Usuario u, Foto foto) {
        // TODO
        return false;
    }

    // TODO 21: Implementa getFotosUsuario(Usuario u)
    // Pista: return red.get(u);
    public List<Foto> getFotosUsuario(Usuario u) {
        // TODO
        return null;
    }

    // TODO 22: Implementa darLike(Usuario u, Foto foto)
    // - Obtén la lista del usuario
    // - Si null → return -1
    // - Recorre la lista buscando f.equals(foto)
    // - Si la encuentras → f.like(); return f.getLikes();
    // - Si no la encuentras → return -1
    public int darLike(Usuario u, Foto foto) {
        // TODO
        return -1;
    }

    // TODO 23: Implementa etiquetar(Foto foto, Usuario u)
    // - Recorre red.values() (todas las listas)
    // - En cada lista, busca f.equals(foto)
    // - Si la encuentras → return f.addEtiquetado(u)
    // - Si no la encuentras en nadie → return false
    public boolean etiquetar(Foto foto, Usuario u) {
        // TODO
        return false;
    }

    // TODO 24: Implementa eliminar(Usuario u, double tamMax)
    // - Obtén la lista del usuario
    // - Si null → return HashSet vacío
    // - USA Iterator para borrar (NO for-each con .remove())
    // - Si f.getTamanio() > tamMax → añade al HashSet de eliminados + it.remove()
    // - Devuelve el HashSet con las fotos eliminadas
    public HashSet<Foto> eliminar(Usuario u, double tamMax) {
        // TODO
        return new HashSet<>();
    }

    // TODO 25: Implementa eliminar(double tamMax) — SOBRECARGA
    // - Recorre red.keySet() para obtener todos los usuarios
    // - Para cada usuario llama a eliminar(u, tamMax)
    // - Acumula todos los eliminados en un HashSet
    // - Devuelve el HashSet total
    public HashSet<Foto> eliminar(double tamMax) {
        // TODO
        return new HashSet<>();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 📸 PROGRAMA PRINCIPAL — PRUEBAS
// ═══════════════════════════════════════════════════════════════

public class UT5_POO_Practica_RedSocial {

    public static void main(String[] args) {

        // * PASO 1: Crear usuarios y probar equals
        // TODO: Crea 3 usuarios y muéstralos
        // Comprueba que dos usuarios con mismo nick son iguales

        // * PASO 2: Crear fotos (con y sin formato)
        // TODO: Crea 4 fotos. Al menos una sin formato (usa el constructor
        // sobrecargado)
        // Muéstralas por pantalla

        // * PASO 3: Crear la red social y añadir usuarios
        // TODO: Crea un GestionFotos
        // Añade 2 usuarios
        // Intenta añadir el mismo usuario otra vez → debe devolver false

        // * PASO 4: Añadir fotos a usuarios
        // TODO: Añade 2 fotos a un usuario, 2 fotos a otro
        // Intenta añadir una foto a un usuario no registrado → false

        // * PASO 5: Probar darLike y etiquetar
        // TODO: Da 3 likes a una foto y muestra el resultado
        // Etiqueta a un usuario en una foto de otro usuario

        // * PASO 6: Mostrar fotos de un usuario
        // TODO: Obtén y muestra las fotos de cada usuario

        // * PASO 7: Probar eliminar (versión 1 y versión 2 sobrecargada)
        // TODO: Muestra las fotos de un usuario ANTES de eliminar
        // Elimina las fotos de ese usuario con tamaño > 3 MB
        // Muestra cuántas se eliminaron y las que quedan
        // Luego prueba la sobrecarga eliminando de TODOS con tamaño > 4 MB

        // ! ✅ CUANDO ACABES:
        // ! Compara tu solución con UT5_POO_EjemploResuelto_RedSocial.java
        // ! Repasa cada TODO que hayas completado
        // ! Usa el checklist de 📘_UT5_POO_Ejercicios_Recuperacion.md
    }
}
