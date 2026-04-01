/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  � UNIDAD 5b: HERENCIA, POLIMORFISMO E INTERFACES
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  Prerrequisito: haber completado UT5_ClasesObjetos.java
 *
 *  En esta práctica aprenderás a:
 *
 *  ✅ Comprender la HERENCIA: reutilizar código entre clase padre e hija.
 *  ✅ Usar extends para crear clases hijas.
 *  ✅ Entender el POLIMORFISMO: un mismo método, distintos comportamientos.
 *  ✅ Crear INTERFACES: contratos que las clases deben cumplir.
 *  ✅ Ver la diferencia entre extends (herencia) e implements (interfaz).
 *
 *  🚀 ¡Explora, experimenta y mejora el código!
 * ******************************************************************************************
 */

// * ════════════════════════════════════════════════════════════════
// * 📖 TEORÍA: ¿QUÉ ES LA HERENCIA?
// * ════════════════════════════════════════════════════════════════
//
// ? La herencia permite que una clase HIJA reciba los atributos y
// ? métodos de una clase PADRE, sin tener que escribirlos de nuevo.
// ?
// ? Palabra clave: extends
// ?   class Perro extends Animal → "Perro ES UN Animal"
// ?
// ? La clase hija puede:
// ?   1. HEREDAR métodos del padre (usarlos tal cual)
// ?   2. SOBRESCRIBIR métodos (@Override) para cambiar comportamiento
// ?   3. AÑADIR métodos y atributos propios
// ?
// ? super(...)  → llama al constructor del padre desde el hijo
// ?
// ! ¿Para qué sirve? → Evitar repetir código.
// !   Si Perro y Gato comparten atributo "nombre", lo definimos UNA vez en Animal.

// * ════════════════════════════════════════════════════════════════
// * � TEORÍA: ¿QUÉ ES EL POLIMORFISMO?
// * ════════════════════════════════════════════════════════════════
//
// ? Polimorfismo = "muchas formas". Un MISMO método se comporta
// ? de forma DIFERENTE según la clase que lo ejecute.
// ?
// ? Ejemplo: el método hacerSonido()
// ?   - En Animal → "El animal hace un sonido"
// ?   - En Perro  → "Rex ladra: ¡Guau guau!"
// ?   - En Gato   → "Whiskers maulla: ¡Miau miau!"
// ?
// ? Lo potente: puedes tener una lista de Animal y meter Perros y Gatos.
// ?   ArrayList<Animal> animales = new ArrayList<>();
// ?   animales.add(new Perro("Rex"));
// ?   animales.add(new Gato("Whiskers"));
// ?
// ? Al llamar a.hacerSonido(), Java sabe cuál ejecutar → ¡eso es polimorfismo!

// * ════════════════════════════════════════════════════════════════
// * � TEORÍA: ¿QUÉ ES UNA INTERFAZ?
// * ════════════════════════════════════════════════════════════════
//
// ? Una interfaz es un CONTRATO: define QUÉ métodos debe tener una clase,
// ? pero NO los implementa (no dice CÓMO hacerlo).
// ?
// ? Palabra clave: implements
// ?   class Coche implements Vehiculo → "Coche cumple el contrato Vehiculo"
// ?
// ? Diferencia con herencia:
// ?   - extends     → solo puedes heredar de UNA clase
// ?   - implements  → puedes implementar VARIAS interfaces
// ?
// ! ¿Cuándo usar interfaz vs herencia?
// !   Herencia → cuando las clases tienen relación "ES UN" (Perro ES UN Animal)
// !   Interfaz → cuando las clases comparten CAPACIDAD (Coche PUEDE acelerar)

import java.util.ArrayList;

// ═══════════════════════════════════════════════════════════════
// * � EJEMPLO DE HERENCIA: Animal → Perro, Gato
// ═══════════════════════════════════════════════════════════════

// ? Clase PADRE (superclase): define lo que TODOS los animales tienen
class Animal {
    String nombre; // ? Atributo heredado por todas las clases hijas

    // * Constructor del padre
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // * Método que las clases hijas pueden SOBRESCRIBIR
    void hacerSonido() {
        System.out.println("El animal hace un sonido.");
    }
}

// ? Clase HIJA: hereda de Animal y SOBRESCRIBE hacerSonido()
class Perro extends Animal {
    public Perro(String nombre) {
        super(nombre); // ? super() llama al constructor del padre
    }

    @Override // ? Indica que estamos SOBRESCRIBIENDO el método del padre
    void hacerSonido() {
        System.out.println(nombre + " ladra: ¡Guau guau!");
    }
}

// ? Otra clase HIJA: mismo padre, comportamiento diferente
class Gato extends Animal {
    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    void hacerSonido() {
        System.out.println(nombre + " maulla: ¡Miau miau!");
    }
}

// ═══════════════════════════════════════════════════════════════
// * � EJEMPLO DE INTERFAZ: Vehiculo → Coche
// ═══════════════════════════════════════════════════════════════

// ? La interfaz define el CONTRATO (qué métodos obligatorios)
// ? No tiene código dentro — solo las firmas de los métodos
interface Vehiculo {
    void acelerar(); // ? Obligatorio implementar

    void frenar(); // ? Obligatorio implementar
}

// ? La clase Coche IMPLEMENTA la interfaz → debe dar código a cada método
class Coche implements Vehiculo {
    @Override
    public void acelerar() {
        System.out.println("El coche acelera.");
    }

    @Override
    public void frenar() {
        System.out.println("El coche frena.");
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🎭 EJEMPLO DE POLIMORFISMO EN ACCIÓN
// ═══════════════════════════════════════════════════════════════

public class UT5_HerenciaPolimorfismoInterfaces {
    public static void main(String[] args) {

        // * Creamos una lista de tipo Animal (padre)
        // ? Pero metemos objetos Perro y Gato (hijos) → ¡polimorfismo!
        ArrayList<Animal> animales = new ArrayList<>();
        animales.add(new Perro("Rex"));
        animales.add(new Gato("Whiskers"));

        System.out.println("🔹 Sonidos de los animales (polimorfismo):");
        // ? Java llama al hacerSonido() correcto según el tipo REAL del objeto
        for (Animal a : animales) {
            a.hacerSonido(); // ? Perro → ladra, Gato → maúlla
        }

        System.out.println();

        // * Probamos la interfaz Vehiculo
        System.out.println("🔹 Probando la interfaz Vehiculo:");
        Coche miCoche = new Coche();
        miCoche.acelerar(); // ? Método obligatorio de la interfaz
        miCoche.frenar(); // ? Método obligatorio de la interfaz

        // ! ✅ TAREA PARA EL ALUMNO:
        // * 1️⃣ Crea una clase `Ave` que herede de `Animal` y sobrescriba
        // `hacerSonido()`.
        // * 2️⃣ Crea una interfaz `Nadador` con el método `nadar()`, e impleméntala en
        // una clase `Pez`.
        // * 3️⃣ Agrega un nuevo método en `Vehiculo` llamado `repostar()` y modifícalo
        // en `Coche`.
    }
}
