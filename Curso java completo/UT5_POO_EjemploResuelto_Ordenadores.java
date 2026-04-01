/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 EJEMPLO RESUELTO 1: SISTEMA DE ORDENADORES
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 */

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║                                                                         ║
// ║                     📋 ENUNCIADO DEL EJERCICIO                          ║
// ║                                                                         ║
// ╚═══════════════════════════════════════════════════════════════════════════╝
//
// ? Implementa la clase Periferico. Tiene los atributos:
// ?   - tipo (String): Ratón, Teclado, Webcam, etc.
// ?   - marca (String): Marca del periférico.
// ?   - averiado (boolean): Indica si está o no averiado.
// ?
// ? Constructor: recibe tipo y marca.
// ?   - Cuando se crea un periférico, su estado averiado se genera
// ?     aleatoriamente con Random: new Random().nextBoolean()
// ?
// ? Métodos:
// ?   - Getters para todos los atributos.
// ?   - No hay setters.
// ?   - equals y hashCode: dos periféricos son iguales si tienen
// ?     el mismo tipo y marca.
// ?   - toString: muestra tipo, marca y estado.
// ?
// ?
// ? Implementa la clase Ordenador. Tiene los atributos:
// ?   - nSerie (String): Número de serie del ordenador.
// ?   - marca (String): Marca del ordenador.
// ?   - procesador (double[]): Array de dos posiciones. En la primera
// ?     se almacena el número de núcleos y en la segunda la velocidad.
// ?   - ram (int): Cantidad de RAM en GB. No puede tener decimales.
// ?   - perifericos (HashSet<Periferico>): Los periféricos conectados
// ?     al ordenador. Se implementa como agregación en un HashSet.
// ?   - maxPerifericos (final int): Constante con el máximo número
// ?     de periféricos que puede tener el ordenador.
// ?   - encendido (boolean): Indica si está o no encendido.
// ?
// ? Constructor: recibe nSerie, marca, núcleos, velocidad, ram y
// ?   maxPerifericos. Un ordenador siempre se crea sin periféricos
// ?   y apagado.
// ?
// ? Métodos:
// ?   - Getters para todos los atributos. NO se permite que
// ?     getPerifericos devuelva el HashSet directamente.
// ?   - No hay setters.
// ?   - calcularConsumo(): Calcula y devuelve el consumo según:
// ?     (núcleos × velocidad) / 10
// ?   - addPeriferico(Periferico p): Añade un periférico. Devuelve
// ?     boolean indicando si se pudo añadir o no. No se pueden
// ?     añadir más periféricos que el máximo. Un HashSet no permite
// ?     elementos duplicados.
// ?   - removePeriferico(Periferico p): Elimina un periférico.
// ?     Devuelve boolean indicando si se pudo eliminar o no.
// ?   - encender() y apagar(): Cambian el estado del ordenador.
// ?   - equals y hashCode: dos ordenadores son iguales si tienen
// ?     el mismo número de serie y marca.
// ?   - toString: muestra todos los datos incluyendo consumo y
// ?     datos de todos sus periféricos.
//
// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║                     📝 SOLUCIÓN A CONTINUACIÓN                          ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

// ═══════════════════════════════════════════════════════════════
// * 🖱️ CLASE PERIFERICO
// ═══════════════════════════════════════════════════════════════
//
// ? 📋 ENUNCIADO:
// ? Implementa la clase Periferico. Tiene los atributos:
// ?   - tipo (String): Ratón, Teclado, Webcam, etc.
// ?   - marca (String): Marca del periférico.
// ?   - averiado (boolean): Indica si está o no averiado.
// ?
// ? Constructor: recibe tipo y marca.
// ?   - El estado averiado se genera aleatoriamente:
// ?     new Random().nextBoolean()
// ?
// ? Métodos:
// ?   - Getters para todos los atributos. No hay setters.
// ?   - equals y hashCode: por tipo y marca.
// ?   - toString: muestra tipo, marca y estado.

class Periferico {

    // * Atributos privados (encapsulación)
    private String tipo; // ? "Ratón", "Teclado", "Webcam", etc.
    private String marca; // ? "Logitech", "Corsair", etc.
    private boolean averiado; // ? Se genera aleatoriamente al crear

    // * Constructor
    // ? Recibe tipo y marca. El estado averiado se genera con Random.
    // ? Random().nextBoolean() devuelve true o false al azar.
    public Periferico(String tipo, String marca) {
        this.tipo = tipo;
        this.marca = marca;
        this.averiado = new Random().nextBoolean();
    }

    // * Getters — NO hay setters (datos inmutables tras creación)
    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isAveriado() {
        return averiado;
    }

    // * equals — dos periféricos son iguales si tienen mismo TIPO y MARCA
    // ? Sin este método, el HashSet del Ordenador no detectaría
    // ? que dos ratones Logitech son "el mismo" periférico.
    // ?
    // ? Pasos del equals:
    // ? 1. ¿Es el mismo objeto en memoria? → true
    // ? 2. ¿Es null o de otra clase? → false
    // ? 3. Casting + comparar los campos que definen la igualdad
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Periferico otro = (Periferico) obj;
        return tipo.equals(otro.tipo) && marca.equals(otro.marca);
    }

    // * hashCode — DEBE ser coherente con equals
    // ? Si dos objetos son equals → DEBEN tener el mismo hashCode.
    // ? Objects.hash() combina los mismos campos usados en equals.
    @Override
    public int hashCode() {
        return Objects.hash(tipo, marca);
    }

    // * toString — representación legible del objeto
    @Override
    public String toString() {
        String estado = averiado ? "Averiado" : "OK";
        return tipo + " (" + marca + ") [" + estado + "]";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🖥️ CLASE ORDENADOR
// ═══════════════════════════════════════════════════════════════
//
// ? 📋 ENUNCIADO:
// ? Implementa la clase Ordenador. Tiene los atributos:
// ? - nSerie (String): Número de serie.
// ? - marca (String): Marca del ordenador.
// ? - procesador (double[]): Array de 2 posiciones [núcleos, velocidad].
// ? - ram (int): RAM en GB (sin decimales).
// ? - perifericos (HashSet<Periferico>): Agregación en HashSet.
// ? - maxPerifericos (final int): Constante con el máximo.
// ? - encendido (boolean): Estado del ordenador.
// ?
// ? Constructor: recibe nSerie, marca, núcleos, velocidad, ram, maxPerif.
// ? - Se crea SIN periféricos y APAGADO.
// ?
// ? Métodos:
// ? - Getters para todos. getPerifericos NO devuelve el HashSet.
// ? - No hay setters.
// ? - calcularConsumo(): (núcleos × velocidad) / 10
// ? - addPeriferico(Periferico p): Devuelve boolean.
// ? No superar máximo. HashSet no permite duplicados.
// ? - removePeriferico(Periferico p): Devuelve boolean.
// ? - encender() y apagar(): Cambian el estado.
// ? - equals y hashCode: por nSerie y marca.
// ? - toString: muestra todo incluyendo consumo y periféricos.

class Ordenador {

    // * Atributos privados
    private String nSerie; // ? Número de serie (identificador)
    private String marca; // ? Marca del ordenador
    private double[] procesador; // ? [0] = nº núcleos, [1] = velocidad GHz
    private int ram; // ? RAM en GB (sin decimales)
    private HashSet<Periferico> perifericos; // ? Agregación con HashSet
    private final int maxPerifericos; // ? final = constante, no cambia
    private boolean encendido; // ? Estado del ordenador

    // * Constructor
    // ? Recibe: nSerie, marca, núcleos, velocidad, ram, maxPerifericos.
    // ? Se crea SIN periféricos (HashSet vacío) y APAGADO.
    // ? El procesador se guarda en un array de 2 posiciones.
    public Ordenador(String nSerie, String marca, int nucleos,
            double velocidad, int ram, int maxPerifericos) {
        this.nSerie = nSerie;
        this.marca = marca;
        this.procesador = new double[] { nucleos, velocidad };
        this.ram = ram;
        this.maxPerifericos = maxPerifericos;
        this.perifericos = new HashSet<>(); // ? Vacío al crear
        this.encendido = false; // ? Apagado al crear
    }

    // * Getters
    public String getNSerie() {
        return nSerie;
    }

    public String getMarca() {
        return marca;
    }

    public int getNucleos() {
        return (int) procesador[0];
    }

    public double getVelocidad() {
        return procesador[1];
    }

    public int getRam() {
        return ram;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public int getMaxPerifericos() {
        return maxPerifericos;
    }

    // * getPerifericos — devuelve String, NO el HashSet
    // ? ¿Por qué? Para PROTEGER la colección interna.
    // ? Si devolviéramos el HashSet, alguien podría hacer:
    // ? ordenador.getPerifericos().clear(); → ¡borraría todo!
    // ? Devolviendo un String, eso no puede pasar.
    public String getPerifericos() {
        if (perifericos.isEmpty())
            return "(Sin periféricos)";
        StringBuilder sb = new StringBuilder();
        for (Periferico p : perifericos) {
            sb.append("    - ").append(p).append("\n");
        }
        return sb.toString();
    }

    // * NO hay setters

    // * calcularConsumo — fórmula: (núcleos × velocidad) / 10
    // ? Accedemos al array: procesador[0] = núcleos, procesador[1] = velocidad
    public double calcularConsumo() {
        return (procesador[0] * procesador[1]) / 10;
    }

    // * addPeriferico — añade un periférico al ordenador
    // ? Devuelve false si:
    // ? - Ya se alcanzó el máximo → perifericos.size() >= maxPerifericos
    // ? - El periférico ya existía → HashSet.add() devuelve false
    // ? (por eso es TAN IMPORTANTE tener equals/hashCode en Periferico)
    public boolean addPeriferico(Periferico p) {
        if (perifericos.size() >= maxPerifericos)
            return false;
        return perifericos.add(p);
    }

    // * removePeriferico — elimina un periférico
    // ? Devuelve false si el periférico no estaba conectado.
    // ? HashSet.remove() usa equals() para buscarlo.
    public boolean removePeriferico(Periferico p) {
        return perifericos.remove(p);
    }

    // * encender / apagar — cambian el estado
    public void encender() {
        encendido = true;
    }

    public void apagar() {
        encendido = false;
    }

    // * equals — dos ordenadores iguales si mismo nSerie Y marca
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Ordenador otro = (Ordenador) obj;
        return nSerie.equals(otro.nSerie) && marca.equals(otro.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nSerie, marca);
    }

    // * toString — muestra TODOS los datos incluidos periféricos y consumo
    @Override
    public String toString() {
        String estado = encendido ? "Encendido" : "Apagado";
        return "Ordenador [" + nSerie + "] " + marca
                + " | CPU: " + getNucleos() + " núcleos, " + getVelocidad() + " GHz"
                + " | RAM: " + ram + " GB"
                + " | " + estado
                + " | Consumo: " + String.format("%.2f", calcularConsumo())
                + "\n  Periféricos (" + perifericos.size() + "/" + maxPerifericos + "):\n"
                + getPerifericos();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🖥️ PROGRAMA PRINCIPAL — PRUEBAS
// ═══════════════════════════════════════════════════════════════

public class UT5_POO_EjemploResuelto_Ordenadores {

    public static void main(String[] args) {

        // * Crear periféricos
        Periferico raton = new Periferico("Ratón", "Logitech");
        Periferico teclado = new Periferico("Teclado", "Corsair");
        Periferico webcam = new Periferico("Webcam", "Logitech");
        Periferico monitor = new Periferico("Monitor", "Samsung");

        // * Crear ordenadores
        Ordenador pc1 = new Ordenador("SN-001", "ASUS", 8, 3.5, 16, 3);
        Ordenador pc2 = new Ordenador("SN-002", "HP", 4, 2.8, 8, 2);

        // * Añadir periféricos al PC1 (máximo 3)
        pc1.addPeriferico(raton);
        pc1.addPeriferico(teclado);
        pc1.addPeriferico(webcam);
        System.out.println("¿Cabe monitor en PC1? " + pc1.addPeriferico(monitor)); // ? false, máx=3

        // * Probar duplicado
        Periferico ratonCopia = new Periferico("Ratón", "Logitech");
        pc2.addPeriferico(raton);
        System.out.println("¿Cabe ratón copia en PC2? " + pc2.addPeriferico(ratonCopia)); // ? false

        // * Mostrar ordenadores completos
        System.out.println("\n" + pc1);
        System.out.println(pc2);

        // * Consumo
        System.out.println("Consumo PC1: " + String.format("%.2f", pc1.calcularConsumo()));

        // * equals
        Ordenador pc1Copia = new Ordenador("SN-001", "ASUS", 2, 1.0, 4, 1);
        System.out.println("¿PC1 = PC1Copia? " + pc1.equals(pc1Copia)); // ? true
        System.out.println("¿PC1 = PC2? " + pc1.equals(pc2)); // ? false

        // ! ✅ TAREA ALUMNO:
        // * 1. Añade un método contarAveriados() en Ordenador que devuelva
        // * cuántos periféricos conectados están averiados.
        // * 2. Crea una clase GestionOrdenadores con un ArrayList<Ordenador>
        // * y estos métodos:
        // * - addOrdenador(Ordenador o): Añade. Devuelve boolean.
        // * - buscarPorMarca(String marca): Devuelve ArrayList con
        // * los ordenadores de esa marca.
        // * - ordenadorConMasConsumo(): Devuelve el Ordenador con
        // * mayor consumo.
        // * - totalRAM(): Devuelve la suma de RAM de todos los
        // * ordenadores.
    }
}
