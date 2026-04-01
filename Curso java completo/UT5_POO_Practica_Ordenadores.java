/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 PRÁCTICA GUIADA 1: SISTEMA DE ORDENADORES — IMPLEMENTA TÚ EL CÓDIGO
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  📋 INSTRUCCIONES:
 *  Este archivo tiene la ESTRUCTURA dada. Tú tienes que:
 *    1. Implementar cada método donde ves // TODO
 *    2. Probar cada clase en el main() antes de pasar a la siguiente
 *    3. Cuando acabes, compara con UT5_POO_EjemploResuelto_Ordenadores.java
 *
 *  🎯 ORDEN RECOMENDADO:
 *    1️⃣  Clase Periferico (la más sencilla)
 *    2️⃣  Clase Ordenador (usa Periferico)
 *    3️⃣  Probar todo en main()
 *
 *  💡 Si algo no funciona → lee 📘_UT5_POO_Ejercicios_Recuperacion.md
 * ******************************************************************************************
 */

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
// ?   - toString: muestra tipo, marca y estado averiado.

class Periferico {

    // TODO 1: Declara los 3 atributos privados (tipo, marca, averiado)

    // TODO 2: Implementa el constructor (recibe tipo y marca)
    // El atributo averiado se genera con new Random().nextBoolean()
    public Periferico(String tipo, String marca) {
        // TODO
    }

    // TODO 3: Implementa los getters (getTipo, getMarca, isAveriado)
    // NO hay setters

    // TODO 4: Implementa equals
    // Dos periféricos son iguales si tienen el mismo TIPO y MARCA
    // (aunque el atributo averiado sea diferente)
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false; // quita esto cuando lo implentes
    }

    // TODO 5: Implementa hashCode
    // IMPORTANTE: usa exactamente los MISMOS campos que en equals
    // Pista: return Objects.hash(campo1, campo2);
    @Override
    public int hashCode() {
        // TODO
        return 0; // quita esto cuando lo implementes
    }

    // TODO 6: Implementa toString
    // Debe mostrar: tipo + marca + estado (Averiado / OK)
    @Override
    public String toString() {
        // TODO
        return ""; // quita esto cuando lo implementes
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
// ? - Se crea SIN periféricos (HashSet vacío) y APAGADO.
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

    // TODO 7: Declara los 7 atributos privados
    // Recuerda: procesador es double[], maxPerifericos es final

    // TODO 8: Implementa el constructor
    // Inicializa el array procesador con {nucleos, velocidad}
    // Inicializa perifericos con new HashSet<>()
    // encendido empieza en false
    public Ordenador(String nSerie, String marca, int nucleos,
            double velocidad, int ram, int maxPerifericos) {
        // TODO
    }

    // TODO 9: Implementa los getters
    // getNucleos() → (int) procesador[0]
    // getVelocidad() → procesador[1]
    // getPerifericos() → devuelve String (NO el HashSet)

    // TODO 10: Implementa calcularConsumo()
    // Fórmula: (procesador[0] * procesador[1]) / 10
    public double calcularConsumo() {
        // TODO
        return 0; // quita esto cuando lo implementes
    }

    // TODO 11: Implementa addPeriferico(Periferico p)
    // - Si perifericos.size() >= maxPerifericos → return false
    // - Si el periférico ya existía → HashSet.add() devuelve false
    // Pista: return perifericos.add(p);
    public boolean addPeriferico(Periferico p) {
        // TODO
        return false; // quita esto cuando lo implementes
    }

    // TODO 12: Implementa removePeriferico(Periferico p)
    // Pista: return perifericos.remove(p);
    public boolean removePeriferico(Periferico p) {
        // TODO
        return false; // quita esto cuando lo implementes
    }

    // TODO 13: Implementa encender() y apagar()
    public void encender() {
        /* TODO */ }

    public void apagar() {
        /* TODO */ }

    // TODO 14: Implementa equals (por nSerie y marca)
    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    // TODO 15: Implementa hashCode (mismos campos que equals)
    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    // TODO 16: Implementa toString
    // Debe mostrar: nSerie, marca, núcleos, velocidad, ram,
    // estado, consumo y la lista de periféricos
    @Override
    public String toString() {
        // TODO
        return "";
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🖥️ PROGRAMA PRINCIPAL — PRUEBAS
// ═══════════════════════════════════════════════════════════════

public class UT5_POO_Practica_Ordenadores {

    public static void main(String[] args) {

        // * PASO 1: Crear periféricos y probar Periferico
        // TODO: Crea 3 o 4 periféricos distintos y muéstralos por pantalla

        // * PASO 2: Probar equals de Periferico
        // TODO: Crea dos periféricos con mismo tipo y marca (averiado puede diferir)
        // Comprueba que equals devuelve true
        // Comprueba que dos distintos devuelven false

        // * PASO 3: Crear ordenadores
        // TODO: Crea 2 ordenadores con distintas specs y muéstralos

        // * PASO 4: Añadir periféricos y probar límites
        // TODO: Añade periféricos hasta el máximo
        // Intenta añadir uno más → debe devolver false
        // Intenta añadir un duplicado → debe devolver false

        // * PASO 5: Probar calcularConsumo
        // TODO: Muestra el consumo de cada ordenador

        // * PASO 6: Probar equals de Ordenador
        // TODO: Crea una copia con mismo nSerie y marca pero distintas specs
        // Comprueba que equals devuelve true

        // ! ✅ CUANDO ACABES:
        // ! Compara tu solución con UT5_POO_EjemploResuelto_Ordenadores.java
        // ! Repasa cada TODO que hayas completado
        // ! Usa el checklist de 📘_UT5_POO_Ejercicios_Recuperacion.md
    }
}
