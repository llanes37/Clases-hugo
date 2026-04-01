/******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  FECHA: 2026
 *  UNIDAD 12 (PARTE 2): MANEJO DE FICHEROS EN JAVA
 *  VERSION: FICHEROS2 MAS COMPLETO, DIDACTICO Y COMENTADO
 ******************************************************************************************/

import java.io.BufferedReader;       // ? Leer texto linea por linea
import java.io.BufferedWriter;       // ? Escribir texto de forma eficiente
import java.io.File;                 // ? Representar rutas, archivos y carpetas
import java.io.FileReader;           // ? Abrir archivo para lectura
import java.io.FileWriter;           // ? Abrir archivo para escritura
import java.io.IOException;          // ? Capturar errores de E/S
import java.util.Scanner;            // ? Leer datos del usuario por teclado

public class UT12_Ficheros2 {

    // * CONFIGURACION GLOBAL DE LA PRACTICA
    // --------------------------------------------------------------
    // ? Usamos una carpeta fija para que todos los ejercicios queden ordenados.
    // ? File.separator hace que funcione en Windows, Linux y Mac.
    private static final String CARPETA_TRABAJO = "datos_ficheros2";
    private static final String ARCHIVO_PRINCIPAL = CARPETA_TRABAJO + File.separator + "notas.txt";
    private static final String ARCHIVO_BACKUP = CARPETA_TRABAJO + File.separator + "backup.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // * Objeto para entradas del usuario
        int opcion;                           // * Control del menu

        // * MENU PRINCIPAL
        // --------------------------------------------------------------
        // ? El do-while garantiza que el menu se muestra al menos una vez.
        // ? Repetimos hasta que el usuario elija 0 (Salir).
        do {
            mostrarMenu();
            opcion = leerEnteroSeguro(sc);

            switch (opcion) {
                case 1 -> crearCarpetaTrabajo();
                case 2 -> crearArchivo(ARCHIVO_PRINCIPAL);
                case 3 -> escribirEnArchivoAppend(ARCHIVO_PRINCIPAL, sc);
                case 4 -> leerArchivoConLineas(ARCHIVO_PRINCIPAL);
                case 5 -> copiarArchivo(ARCHIVO_PRINCIPAL, ARCHIVO_BACKUP);
                case 6 -> listarTxt(CARPETA_TRABAJO);
                case 7 -> mostrarEstadisticas(ARCHIVO_PRINCIPAL);
                case 8 -> borrarArchivoConConfirmacion(ARCHIVO_PRINCIPAL, sc);
                case 0 -> System.out.println("Hasta la proxima.");
                default -> System.out.println("Opcion no valida. Prueba otra vez.");
            }
        } while (opcion != 0);

        sc.close(); // * Buena practica: cerrar recursos al finalizar
    }

    // * UTILIDAD: Mostrar menu en pantalla
    public static void mostrarMenu() {
        System.out.println("\n==== MENU - UT12 FICHEROS 2 ====");
        System.out.println("1. Crear carpeta de trabajo");
        System.out.println("2. Crear archivo principal (notas.txt)");
        System.out.println("3. Anadir texto al archivo (modo append)");
        System.out.println("4. Leer archivo con numero de linea");
        System.out.println("5. Copiar archivo a backup.txt");
        System.out.println("6. Listar archivos .txt de la carpeta");
        System.out.println("7. Mostrar estadisticas del archivo");
        System.out.println("8. Borrar archivo principal");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");
    }

    // * UTILIDAD: Leer un entero sin romper el flujo del programa
    // --------------------------------------------------------------
    // ? Si el usuario escribe texto en lugar de numero, limpiamos el buffer
    // ? y volvemos a pedir el dato hasta que sea valido.
    public static int leerEnteroSeguro(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Introduce un numero valido: ");
            sc.next(); // ! Descarta el token invalido
        }
        int valor = sc.nextInt();
        sc.nextLine(); // ? Limpia salto de linea pendiente
        return valor;
    }

    // * TEORIA: Crear carpeta de trabajo
    // --------------------------------------------------------------
    // ? File.mkdir() crea una unica carpeta si no existe.
    // ? Si la carpeta ya existe, no se vuelve a crear.
    public static void crearCarpetaTrabajo() {
        File carpeta = new File(CARPETA_TRABAJO);

        if (carpeta.exists()) {
            System.out.println("La carpeta ya existe: " + carpeta.getAbsolutePath());
            return;
        }

        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada correctamente: " + carpeta.getAbsolutePath());
        } else {
            System.out.println("No se pudo crear la carpeta.");
        }

        // ! TAREA ALUMNO:
        // * Prueba ahora con mkdirs() creando una subcarpeta: datos_ficheros2/historico/2026
    }

    // * TEORIA: Crear archivo de texto
    // --------------------------------------------------------------
    // ? createNewFile() devuelve true solo cuando el archivo se crea por primera vez.
    // ? Si existe, devuelve false y no lo sobreescribe.
    public static void crearArchivo(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            File carpetaPadre = archivo.getParentFile();

            // ? Verificamos que la carpeta exista antes de crear el archivo
            if (carpetaPadre != null && !carpetaPadre.exists()) {
                carpetaPadre.mkdir();
            }

            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        // ! TAREA ALUMNO:
        // * Crea tambien alumnos.txt y profesores.txt en la misma carpeta.
    }

    // * TEORIA: Escribir en modo append (anadir)
    // --------------------------------------------------------------
    // ? FileWriter(ruta, true) conserva el contenido y agrega al final.
    // ? Si usamos false (o constructor simple), se sobreescribe todo.
    public static void escribirEnArchivoAppend(String rutaArchivo, Scanner sc) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Primero debes crear el archivo.");
            return;
        }

        System.out.print("Cuantas lineas quieres anadir? ");
        int cantidad = leerEnteroSeguro(sc);

        if (cantidad <= 0) {
            System.out.println("Debes introducir un numero mayor que 0.");
            return;
        }

        // ? try-with-resources cierra el BufferedWriter automaticamente.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            for (int i = 1; i <= cantidad; i++) {
                System.out.print("Linea " + i + ": ");
                String texto = sc.nextLine();
                bw.write(texto);
                bw.newLine(); // ? Salto de linea para separar registros
            }
            System.out.println("Texto anadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }

        // ! TAREA ALUMNO:
        // * Pide tambien un titulo y guardalo como primera linea del bloque.
    }

    // * TEORIA: Leer archivo con numero de linea
    // --------------------------------------------------------------
    // ? BufferedReader + readLine() es ideal para texto plano.
    // ? readLine() retorna null al llegar al final del archivo.
    public static void leerArchivoConLineas(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 1;

            System.out.println("\nContenido de " + archivo.getName() + ":");
            while ((linea = br.readLine()) != null) {
                System.out.println(numeroLinea + ") " + linea);
                numeroLinea++;
            }

            if (numeroLinea == 1) {
                System.out.println("(Archivo vacio)");
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        // ! TAREA ALUMNO:
        // * Muestra solo las lineas que incluyan una palabra clave indicada por el usuario.
    }

    // * TEORIA: Copiar archivo linea a linea
    // --------------------------------------------------------------
    // ? Practicamos el proceso completo: leer origen y escribir destino.
    // ? Este enfoque es didactico para entender los flujos de E/S.
    public static void copiarArchivo(String rutaOrigen, String rutaDestino) {
        File origen = new File(rutaOrigen);
        File destino = new File(rutaDestino);

        if (!origen.exists()) {
            System.out.println("No existe el archivo origen.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(origen));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destino))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Copia completada en: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al copiar: " + e.getMessage());
        }

        // ! TAREA ALUMNO:
        // * Crea un metodo que compare si origen y backup tienen el mismo numero de lineas.
    }

    // * TEORIA: Listar archivos .txt de una carpeta
    // --------------------------------------------------------------
    // ? listFiles() devuelve todos los elementos del directorio (archivos + carpetas).
    // ? Filtramos solo archivos y extension .txt.
    public static void listarTxt(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La carpeta no existe o no es valida.");
            return;
        }

        File[] archivos = carpeta.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay archivos en la carpeta.");
            return;
        }

        int contador = 0;
        System.out.println("\nArchivos .txt encontrados:");
        for (File f : archivos) {
            if (f.isFile() && f.getName().toLowerCase().endsWith(".txt")) {
                System.out.println("- " + f.getName() + " (" + f.length() + " bytes)");
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay archivos .txt en la carpeta.");
        }

        // ! TAREA ALUMNO:
        // * Muestra tambien la fecha de ultima modificacion con f.lastModified().
    }

    // * TEORIA: Estadisticas de contenido
    // --------------------------------------------------------------
    // ? Recorremos cada linea para calcular: lineas, palabras y caracteres.
    // ? Sirve para practicar contadores y tratamiento de cadenas.
    public static void mostrarEstadisticas(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        int lineas = 0;
        int palabras = 0;
        int caracteres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();

                String limpia = linea.trim();
                if (!limpia.isEmpty()) {
                    palabras += limpia.split("\\s+").length; // ? Cuenta bloques separados por espacios
                }
            }

            System.out.println("\nEstadisticas de " + archivo.getName() + ":");
            System.out.println("Lineas: " + lineas);
            System.out.println("Palabras: " + palabras);
            System.out.println("Caracteres (sin saltos de linea): " + caracteres);
            System.out.println("Tamano real en disco: " + archivo.length() + " bytes");
        } catch (IOException e) {
            System.out.println("Error al calcular estadisticas: " + e.getMessage());
        }

        // ! TAREA ALUMNO:
        // * Calcula cuantas veces aparece una vocal concreta (a, e, i, o, u).
    }

    // * TEORIA: Borrado con confirmacion
    // --------------------------------------------------------------
    // ? delete() elimina el archivo del sistema.
    // ! Siempre pedimos confirmacion para evitar borrados accidentales.
    public static void borrarArchivoConConfirmacion(String rutaArchivo, Scanner sc) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        System.out.print("Seguro que quieres borrar " + archivo.getName() + "? (s/n): ");
        String respuesta = sc.nextLine().trim().toLowerCase();

        if (!respuesta.equals("s")) {
            System.out.println("Borrado cancelado.");
            return;
        }

        if (archivo.delete()) {
            System.out.println("Archivo borrado correctamente.");
        } else {
            System.out.println("No se pudo borrar el archivo.");
        }

        // ! TAREA ALUMNO:
        // * Reutiliza este metodo para borrar tambien backup.txt desde otra opcion del menu.
    }
}
