
/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2026
 *  🔹 UNIDAD 12 (AVANZADO): FICHEROS Y PERSISTENCIA DE DATOS EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 *
 *  OBJETIVO: Esta versión "Avanzado" amplía el archivo UT12_Ficheros con más
 *  ejemplos, mejores prácticas, documentación Javadoc y tareas guiadas.
 *
 *  CONTENIDOS CLAVE:
 *  - Repaso rápido de métodos (void vs retorno, parámetros, ámbito, javadoc)
 *  - Ficheros: crear, escribir (overwrite y append), leer (BufferedReader, Scanner)
 *  - Metadatos y comprobaciones de existencia
 *  - Listado de directorios (plano y recursivo)
 *  - Copiar, mover/renombrar y borrar ficheros (java.nio.file)
 *  - Estadísticas de un archivo (líneas, palabras, caracteres, bytes)
 *  - CSV simple: escribir y leer filas de texto
 *  - CSV con objetos: guardar y cargar objetos Java desde/hacia CSV ✅ NUEVO
 *  - JSON manual sin librerías: leer y escribir JSON desde cero ✅ NUEVO
 *  - Registro (log) con marca temporal
 *  - Recursividad aplicada: contar archivos en árbol de directorios
 ******************************************************************************************/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UT12_Ficheros_Avanzado {

    // Scanner compartido (demostración de "variable de clase")
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroEnRango("👉 Elige una opción: ", 0, 99);
            sc.nextLine(); // Limpia retorno de carro si quedó algo

            switch (opcion) {
                // Básicos
                case 1 -> crearArchivo(pedirRuta("Nombre o ruta del archivo a crear", "archivo_avanzado.txt"));
                case 2 ->
                    escribirSobrescribiendo(pedirRuta("Archivo para escribir (overwrite)", "archivo_avanzado.txt"));
                case 3 -> escribirAppend(pedirRuta("Archivo para añadir texto (append)", "archivo_avanzado.txt"));
                case 4 -> leerConBufferedReader(pedirRuta("Archivo a leer (BufferedReader)", "archivo_avanzado.txt"));
                case 5 -> leerConScanner(pedirRuta("Archivo a leer (Scanner)", "archivo_avanzado.txt"));

                // Comprobaciones y utilidades
                case 6 -> comprobarArchivo(pedirRuta("Archivo para comprobar", "archivo_avanzado.txt"));
                case 7 -> listarDirectorio(pedirRuta("Directorio a listar", "."), false);
                case 8 -> listarDirectorio(pedirRuta("Directorio a listar (recursivo)", "."), true);
                case 9 -> copiarArchivo(
                        pedirRuta("Origen a copiar", "archivo_avanzado.txt"),
                        pedirRuta("Destino de la copia", "archivo_copiado.txt"),
                        true);
                case 10 -> moverORenombrar(
                        pedirRuta("Origen a mover/renombrar", "archivo_copiado.txt"),
                        pedirRuta("Nuevo nombre o ruta destino", "archivo_movido.txt"),
                        true);
                case 11 -> borrarArchivo(pedirRuta("Archivo a borrar", "archivo_movido.txt"));
                case 12 -> estadisticasArchivo(pedirRuta("Archivo para calcular estadísticas", "archivo_avanzado.txt"));

                // CSV básico
                case 13 -> csvEscribir(pedirRuta("Archivo CSV a crear", "alumnos.csv"));
                case 14 -> csvLeer(pedirRuta("Archivo CSV a leer", "alumnos.csv"));

                // CSV con objetos ✅ NUEVO
                case 18 -> csvGuardarAlumnos(pedirRuta("CSV para guardar alumnos", "alumnos_objetos.csv"));
                case 19 -> csvCargarAlumnos(pedirRuta("CSV para cargar alumnos", "alumnos_objetos.csv"));

                // JSON manual ✅ NUEVO
                case 20 -> jsonDemo(pedirRuta("Fichero JSON", "alumnos.json"));

                // LOG y recursividad
                case 15 -> logLinea(pedirRuta("Archivo de log", "app.log"), pedirTexto("Mensaje del log"));
                case 16 ->
                    demoRecursividadContar(pedirRuta("Directorio base para contar archivos recursivamente", "."));
                case 17 -> demoMetodos();

                case 0 -> System.out.println("👋 ¡Saliendo del programa Avanzado!");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // * 📖 TEORÍA: Menú principal
    // ──────────────────────────────────────────────
    // ? Igual que en UT12_Ficheros: un menú textual para navegar por ejemplos.
    // ? Permite al docente moverse de forma ágil entre teoría, demo y práctica.
    private static void mostrarMenu() {
        System.out.println("\n📂 MENÚ AVANZADO - FICHEROS Y PERSISTENCIA:");
        System.out.println("─── BÁSICO ───────────────────────────────────");
        System.out.println(" 1. Crear un archivo de texto");
        System.out.println(" 2. Escribir (sobrescribir) en un archivo");
        System.out.println(" 3. Escribir (añadir al final - append)");
        System.out.println(" 4. Leer archivo con BufferedReader");
        System.out.println(" 5. Leer archivo con Scanner (hasNext)");
        System.out.println("─── UTILIDADES ───────────────────────────────");
        System.out.println(" 6. Comprobar archivo y metadatos");
        System.out.println(" 7. Listar contenido de un directorio (plano)");
        System.out.println(" 8. Listar contenido de un directorio (recursivo)");
        System.out.println(" 9. Copiar archivo");
        System.out.println("10. Mover / Renombrar archivo");
        System.out.println("11. Borrar archivo");
        System.out.println("12. Estadísticas de un archivo (líneas, palabras, chars, bytes)");
        System.out.println("─── CSV ──────────────────────────────────────");
        System.out.println("13. CSV simple: crear archivo de ejemplo");
        System.out.println("14. CSV simple: leer y mostrar");
        System.out.println("18. ✅ CSV con OBJETOS: guardar lista de Alumnos");
        System.out.println("19. ✅ CSV con OBJETOS: cargar Alumnos desde fichero");
        System.out.println("─── JSON MANUAL (sin librerías) ──────────────");
        System.out.println("20. ✅ JSON: guardar y leer datos sin Gson");
        System.out.println("─── OTROS ────────────────────────────────────");
        System.out.println("15. Escribir línea en log (timestamp)");
        System.out.println("16. Recursividad: contar archivos en árbol de directorios");
        System.out.println("17. Demo de métodos: void vs retorno, parámetros, ámbito, javadoc");
        System.out.println(" 0. Salir");
    }

    // =====================
    // Utilidades de entrada
    // =====================

    // * 📖 TEORÍA: Utilidades de entrada
    // ──────────────────────────────────────────────
    // ? Encapsulamos lectura y validación para no repetir código.
    // ? Patrón didáctico: funciones pequeñas y reutilizables.
    private static String pedirRuta(String mensaje, String porDefecto) {
        System.out.print(mensaje + " [def: " + porDefecto + "]: ");
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty())
            return porDefecto;
        return entrada;
    }

    private static String pedirTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return sc.nextLine();
    }

    /**
     * Lee un entero limitado a un rango y valida entradas erróneas.
     * Inspirado en el patrón educativo de UT12 (limpieza de buffer y mensajes
     * claros).
     */
    private static int leerEnteroEnRango(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                if (v >= min && v <= max)
                    return v;
                System.out.printf(Locale.ROOT, "⚠️ Debe estar entre %d y %d.%n", min, max);
            } else {
                System.out.println("⚠️ Introduce un número entero válido.");
                sc.next(); // descarta token inválido
            }
        }
    }

    // ==============
    // Operaciones IO
    // ==============

    // * 📖 TEORÍA: Crear archivo (niveles File vs NIO)
    // ──────────────────────────────────────────────
    // ? Igual que en UT12_Ficheros, pero usando NIO (Paths/Files), más moderno.
    // ? Files.createFile crea el archivo si no existe. Si existe, podemos informar
    // o lanzar excepción.
    // ? Ventajas de NIO: mejor soporte de rutas, opciones atómicas y utilidades
    // adicionales.
    // ! ✅ TAREA ALUMNO:
    // * Crea el archivo "data/notas.txt" en una subcarpeta. ¿Qué ocurre si la
    // carpeta no existe?
    /**
     * Crea un archivo vacío si no existe.
     * 
     * @param ruta Ruta del archivo a crear.
     */
    public static void crearArchivo(String ruta) {
        try {
            Path p = Paths.get(ruta);
            if (Files.exists(p)) {
                System.out.println("ℹ️ El archivo ya existe: " + p.toAbsolutePath());
            } else {
                Files.createFile(p);
                System.out.println("✅ Archivo creado: " + p.toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("❌ Error al crear el archivo: " + e.getMessage());
        }
        // Tarea: prueba con otro nombre y una subcarpeta (por ejemplo data/notas.txt)
    }

    // * 📖 TEORÍA: Escribir (sobrescribir) en archivo
    // ──────────────────────────────────────────────
    // ? Similar a FileWriter del UT12 básico, pero con BufferedWriter + UTF-8
    // explícito (buenas prácticas).
    // ? try-with-resources cierra automáticamente los recursos (equivalente a
    // cerrar en finally).
    // ? Sobrescribir: borra el contenido previo y escribe el nuevo.
    // ! ✅ TAREA ALUMNO:
    // * Escribe 3-4 líneas y comprueba con la opción de lectura que se han
    // guardado.
    /**
     * Escribe contenido sobrescribiendo todo el archivo usando BufferedWriter
     * (UTF-8).
     * 
     * @param ruta Archivo destino.
     */
    public static void escribirSobrescribiendo(String ruta) {
        System.out.println("✍️ Escribe varias líneas. Línea vacía para terminar:");
        List<String> lineas = new ArrayList<>();
        while (true) {
            String linea = sc.nextLine();
            if (linea.isBlank())
                break;
            lineas.add(linea);
        }
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ruta), StandardCharsets.UTF_8)) {
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("📝 Escritura completada (overwrite).");
        } catch (IOException e) {
            System.out.println("❌ Error al escribir: " + e.getMessage());
        }
        // ! ✅ TAREA ALUMNO:
        // * Añade al final tu nombre y la fecha actual con la opción 3 (append).
    }

    // * 📖 TEORÍA: Añadir al final (append)
    // ──────────────────────────────────────────────
    // ? Equivalente al Apéndice II del temario (FileWriter con true como segundo
    // parámetro).
    // ? Alternativa NIO: StandardOpenOption.APPEND.
    // ! ✅ TAREA ALUMNO:
    // * Prueba a ejecutar varias veces y observa cómo crece el archivo.
    /**
     * Añade texto al final del archivo (append) usando PrintWriter.
     * 
     * @param ruta Archivo destino.
     */
    public static void escribirAppend(String ruta) {
        String texto = pedirTexto("Escribe la línea a añadir");
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta, true))) {
            pw.println(texto);
            System.out.println("➕ Línea añadida al final (append).");
        } catch (IOException e) {
            System.out.println("❌ Error en append: " + e.getMessage());
        }
        // ℹ️ Apunte: también puedes usar Files.write(path, bytes,
        // StandardOpenOption.APPEND)
    }

    // * 📖 TEORÍA: Leer con BufferedReader
    // ──────────────────────────────────────────────
    // ? Misma idea que FileReader + BufferedReader del UT12 básico.
    // ? Ventajas: lectura línea a línea eficiente y control de codificación.
    // ! ✅ TAREA ALUMNO:
    // * Compara el rendimiento con archivos grandes frente a Scanner.
    /**
     * Lee un archivo línea a línea con BufferedReader.
     * 
     * @param ruta Archivo a leer.
     */
    public static void leerConBufferedReader(String ruta) {
        Path p = Paths.get(ruta);
        if (!Files.exists(p)) {
            System.out.println("❌ No existe el archivo: " + p.toAbsolutePath());
            return;
        }
        System.out.println("\n📖 Contenido (BufferedReader):");
        try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("👉 " + linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer: " + e.getMessage());
        }
    }

    // * 📖 TEORÍA: Leer con Scanner (hasNext)
    // ──────────────────────────────────────────────
    // ? Como en consola, pero la entrada es un archivo (new File). Muy versátil
    // para parsing simple.
    // ? Recuerda: si mezclas nextInt/nextDouble con nextLine, consume el fin de
    // línea.
    // ! ✅ TAREA ALUMNO:
    // * Crea un archivo con números y léelos con nextInt, acumulando la suma.
    /**
     * Lee un archivo con Scanner usando hasNext y nextLine/nextXXX.
     * 
     * @param ruta Archivo a leer.
     */
    public static void leerConScanner(String ruta) {
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("❌ No existe el archivo: " + f.getAbsolutePath());
            return;
        }
        System.out.println("\n📖 Contenido (Scanner + hasNext):");
        try (Scanner fs = new Scanner(f, StandardCharsets.UTF_8)) {
            while (fs.hasNext()) {
                String linea = fs.nextLine();
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer con Scanner: " + e.getMessage());
        }
        // ! ✅ TAREA ALUMNO:
        // * Si el archivo contiene números, prueba nextInt/nextDouble y recuerda
        // consumir el fin de línea con nextLine().
    }

    // * 📖 TEORÍA: Comprobar archivo y ver metadatos
    // ──────────────────────────────────────────────
    // ? Como en UT12, pero ampliando con propiedades de NIO: legible, escribible,
    // modificado...
    // ? Útil para diagnósticos y para preparar operaciones posteriores (copiar,
    // borrar...).
    // ! ✅ TAREA ALUMNO:
    // * Comprueba un archivo inexistente y anota qué campos pueden consultarse sin
    // error.
    /**
     * Muestra metadatos de un archivo y si existe.
     * 
     * @param ruta Ruta del archivo.
     */
    public static void comprobarArchivo(String ruta) {
        Path p = Paths.get(ruta);
        if (Files.exists(p)) {
            System.out.println("📦 Existe: " + p.toAbsolutePath());
            try {
                System.out.println("📏 Tamaño: " + Files.size(p) + " bytes");
                System.out.println("📍 Directorio padre: " + p.toAbsolutePath().getParent());
                System.out.println("📄 Es directorio: " + Files.isDirectory(p));
                System.out.println("🔒 Es legible: " + Files.isReadable(p));
                System.out.println("✍️ Es escribible: " + Files.isWritable(p));
                System.out.println("🕒 Última modificación: " + Files.getLastModifiedTime(p));
            } catch (IOException e) {
                System.out.println("❌ Error obteniendo metadatos: " + e.getMessage());
            }
        } else {
            System.out.println("❌ El archivo no existe: " + p.toAbsolutePath());
        }
        // ! ✅ TAREA ALUMNO:
        // * Verifica también un archivo inexistente y observa la salida.
    }

    // * 📖 TEORÍA: Listar directorios (plano y recursivo)
    // ──────────────────────────────────────────────
    // ? DirectoryStream permite recorrer contenidos con bajo consumo de memoria.
    // ? Recursividad: aplicamos un helper que baja nivel a nivel.
    // ! ✅ TAREA ALUMNO:
    // * Cuenta cuántos directorios y archivos hay. ¿Qué cambia si listamos ocultos?
    /**
     * Lista el contenido de un directorio, de forma plana o recursiva.
     * 
     * @param rutaDir   Directorio a listar.
     * @param recursivo true para recorrido recursivo.
     */
    public static void listarDirectorio(String rutaDir, boolean recursivo) {
        Path dir = Paths.get(rutaDir);
        if (!Files.isDirectory(dir)) {
            System.out.println("❌ No es un directorio válido: " + dir.toAbsolutePath());
            return;
        }
        if (!recursivo) {
            System.out.println("🗂️ Contenido de " + dir.toAbsolutePath() + ":");
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
                for (Path p : ds) {
                    System.out.printf("%s %s%n", Files.isDirectory(p) ? "[DIR] " : "      ", p.getFileName());
                }
            } catch (IOException e) {
                System.out.println("❌ Error al listar: " + e.getMessage());
            }
        } else {
            System.out.println("🧭 Recorrido recursivo de " + dir.toAbsolutePath() + ":");
            listarRecursivoHelper(dir, 0);
        }
    }

    // 🧩 Helper recursivo: imprime con indentación según nivel
    private static void listarRecursivoHelper(Path dir, int nivel) {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            for (Path p : ds) {
                System.out.printf("%s%s %s%n", " ".repeat(nivel * 2), Files.isDirectory(p) ? "[DIR]" : "[ARQ]",
                        p.getFileName());
                if (Files.isDirectory(p))
                    listarRecursivoHelper(p, nivel + 1);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al recorrer: " + e.getMessage());
        }
    }

    // * 📖 TEORÍA: Copiar archivos
    // ──────────────────────────────────────────────
    // ? Files.copy simplifica la operación; con REPLACE_EXISTING decide si
    // sobreescribe.
    // ? Didáctico: comprobar existencia de origen y mostrar rutas absolutas.
    // ! ✅ TAREA ALUMNO:
    // * Copia un archivo grande y cronometra el tiempo (nanoTime) como reto.
    /**
     * Copia un archivo.
     * 
     * @param origen        Ruta de origen
     * @param destino       Ruta de destino
     * @param sobreescribir true para reemplazar si existe
     */
    public static void copiarArchivo(String origen, String destino, boolean sobreescribir) {
        Path src = Paths.get(origen);
        Path dst = Paths.get(destino);
        try {
            if (!Files.exists(src)) {
                System.out.println("❌ Origen no existe: " + src.toAbsolutePath());
                return;
            }
            if (sobreescribir) {
                Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.copy(src, dst);
            }
            System.out.println("✅ Copia realizada: " + dst.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error al copiar: " + e.getMessage());
        }
    }

    // * 📖 TEORÍA: Mover o renombrar
    // ──────────────────────────────────────────────
    // ? Files.move permite renombrar dentro del mismo directorio o mover a otra
    // ruta.
    // ? Precaución con reemplazar si existe.
    /**
     * Mueve o renombra un archivo.
     * 
     * @param origen     Ruta de origen
     * @param destino    Ruta destino/nuevo nombre
     * @param reemplazar Si true, reemplaza si existe
     */
    public static void moverORenombrar(String origen, String destino, boolean reemplazar) {
        Path src = Paths.get(origen);
        Path dst = Paths.get(destino);
        try {
            if (reemplazar) {
                Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.move(src, dst);
            }
            System.out.println("✅ Movido/renombrado a: " + dst.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error al mover/renombrar: " + e.getMessage());
        }
    }

    // * 📖 TEORÍA: Borrar archivos
    // ──────────────────────────────────────────────
    // ? Files.deleteIfExists evita excepciones si no está el archivo.
    // ? Siempre informar al usuario de la acción realizada (didáctico).
    /**
     * Borra un archivo si existe.
     * 
     * @param ruta Ruta del archivo a borrar
     */
    public static void borrarArchivo(String ruta) {
        Path p = Paths.get(ruta);
        try {
            boolean borrado = Files.deleteIfExists(p);
            if (borrado)
                System.out.println("🗑️ Archivo borrado: " + p.toAbsolutePath());
            else
                System.out.println("ℹ️ No existía: " + p.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error al borrar: " + e.getMessage());
        }
    }

    // * 📖 TEORÍA: Estadísticas de un archivo
    // ──────────────────────────────────────────────
    // ? Recorremos línea a línea y contamos líneas, palabras (split por espacios) y
    // caracteres.
    // ? Además, comparamos con bytes reales del archivo (Files.size).
    // ! ✅ TAREA ALUMNO:
    // * Prueba con distintos idiomas/caracteres especiales para observar
    // diferencias.
    /**
     * Calcula estadísticas básicas de un archivo de texto.
     * 
     * @param ruta Archivo de texto
     */
    public static void estadisticasArchivo(String ruta) {
        Path p = Paths.get(ruta);
        if (!Files.exists(p)) {
            System.out.println("❌ Archivo inexistente: " + p.toAbsolutePath());
            return;
        }
        long lineas = 0, palabras = 0, caracteres = 0, bytes = 0;
        try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                String[] trozos = linea.trim().split("\\s+");
                if (!(trozos.length == 1 && trozos[0].isBlank())) {
                    palabras += trozos.length;
                }
            }
            bytes = Files.size(p);
        } catch (IOException e) {
            System.out.println("❌ Error calculando estadísticas: " + e.getMessage());
            return;
        }
        System.out.printf(Locale.ROOT,
                "📊 Estadísticas de %s%n  Líneas: %d%n  Palabras: %d%n  Caracteres: %d%n  Bytes: %d%n",
                p.toAbsolutePath(), lineas, palabras, caracteres, bytes);
        // ! ✅ TAREA ALUMNO:
        // * ¿Qué diferencia observas entre caracteres y bytes si cambias la
        // codificación?
    }

    // =====
    // CSV
    // =====

    // * 📖 TEORÍA: CSV (texto plano separado por comas)
    // ──────────────────────────────────────────────
    // ? Caso simple sin comillas ni escapes. Útil para empezar con persistencia
    // tabular.
    // ? Más adelante, bibliotecas específicas (OpenCSV) para casos complejos.
    // ! ✅ TAREA ALUMNO:
    // * Añade más filas y una columna extra (edad) y ajústalo al lector.
    /**
     * Crea un CSV de ejemplo con cabecera y varias filas.
     * 
     * @param rutaCSV Ruta del archivo CSV
     */
    public static void csvEscribir(String rutaCSV) {
        Path p = Paths.get(rutaCSV);
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            bw.write("id,nombre,nota");
            bw.newLine();
            bw.write("1,Ana,8.5");
            bw.newLine();
            bw.write("2,Borja,7.25");
            bw.newLine();
            bw.write("3,Carla,9.1");
            bw.newLine();
            System.out.println("✅ CSV de ejemplo creado: " + p.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error creando CSV: " + e.getMessage());
        }
        // ℹ️ Nota: CSV simple (sin comillas, sin escapes). Para CSV avanzado usa
        // bibliotecas externas.
    }

    // * 📖 TEORÍA: Leer CSV
    // ──────────────────────────────────────────────
    // ? Leemos la cabecera para asociar nombre de columna a cada dato.
    // ? Dividimos por coma y mostramos pares clave-valor.
    // ! ✅ TAREA ALUMNO:
    // * Captura posibles líneas vacías o con menos columnas de lo esperado.
    /**
     * Lee un CSV simple separado por comas e imprime cada fila como columnas.
     * 
     * @param rutaCSV Ruta del CSV
     */
    public static void csvLeer(String rutaCSV) {
        Path p = Paths.get(rutaCSV);
        if (!Files.exists(p)) {
            System.out.println("❌ No existe: " + p.toAbsolutePath());
            return;
        }
        try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
            String cabecera = br.readLine();
            if (cabecera == null) {
                System.out.println("⚠️ CSV vacío.");
                return;
            }
            String[] cols = cabecera.split(",");
            System.out.printf("🧾 Cabecera (%d): %s%n", cols.length, String.join(" | ", cols));
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                System.out.printf("Fila %d -> ", ++fila);
                for (int i = 0; i < datos.length; i++) {
                    System.out.print((i < cols.length ? cols[i] : ("col" + i)) + ": " + datos[i]);
                    if (i < datos.length - 1)
                        System.out.print(" | ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("❌ Error leyendo CSV: " + e.getMessage());
        }
    }

    // =====
    // LOG
    // =====

    // * 📖 TEORÍA: Logging (registro de eventos)
    // ──────────────────────────────────────────────
    // ? Escribir un log de texto es una forma sencilla de trazar eventos.
    // ? Añadimos timestamp con DateTimeFormatter.
    // ! ✅ TAREA ALUMNO:
    // * Genera varios registros y observa cómo se ordenan por fecha.
    /**
     * Escribe una línea con timestamp al final de un archivo de log (append).
     * 
     * @param rutaLog Ruta del archivo de log
     * @param mensaje Mensaje a registrar
     */
    public static void logLinea(String rutaLog, String mensaje) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String linea = String.format("[%s] %s", ts, mensaje);
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaLog, true))) {
            pw.println(linea);
            System.out.println("🗒️ Log registrado en " + Paths.get(rutaLog).toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error escribiendo log: " + e.getMessage());
        }
        // ! ✅ TAREA ALUMNO:
        // * Crea un pequeño diario de actividad con varias entradas.
    }

    // =============================
    // Recursividad aplicada (archivos)
    // =============================

    // * 📖 TEORÍA: Recursividad (aplicada a ficheros)
    // ──────────────────────────────────────────────
    // ? Caso base + caso recursivo: recorrer árbol de directorios y contar
    // archivos.
    // ? Relación con el apéndice de recursividad (Fibonacci) del temario.
    /**
     * Demostración: contar archivos de forma recursiva en un directorio.
     * 
     * @param rutaDir Directorio raíz
     */
    public static void demoRecursividadContar(String rutaDir) {
        Path dir = Paths.get(rutaDir);
        if (!Files.isDirectory(dir)) {
            System.out.println("❌ No es un directorio: " + dir.toAbsolutePath());
            return;
        }
        long total = contarArchivosRecursivo(dir);
        System.out.printf(Locale.ROOT, "📦 Total de archivos bajo %s: %d%n", dir.toAbsolutePath(), total);
    }

    /**
     * Cuenta archivos (no directorios) en un árbol de directorios mediante
     * recursividad.
     * Caso base: si no hay entradas, devuelve 0. Caso recursivo: suma los de
     * subdirectorios.
     * 
     * @param dir Directorio
     * @return Número de archivos
     */
    public static long contarArchivosRecursivo(Path dir) {
        long cuenta = 0;
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            for (Path p : ds) {
                if (Files.isDirectory(p)) {
                    cuenta += contarArchivosRecursivo(p); // llamada recursiva
                } else {
                    cuenta++;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error recorriendo: " + e.getMessage());
        }
        return cuenta;
    }

    // =============================
    // Métodos: void, retorno, parámetros, ámbito y Javadoc
    // =============================

    // * 📖 TEORÍA: Métodos sin retorno (void)
    // ──────────────────────────────────────────────
    // ? Como "print" del temario: realizan acciones pero no devuelven valor.
    /**
     * Ejemplo de función que no devuelve (void): imprime una línea de asteriscos.
     * No requiere parámetros. Solo tiene efectos colaterales (salida por consola).
     */
    public static void estrellas1() {
        System.out.println("************************");
    }

    // * 📖 TEORÍA: Parámetros
    // ──────────────────────────────────────────────
    // ? Pasamos datos a la función para que actúe con valores externos.
    /**
     * Pinta 'cantidad' asteriscos en la misma línea y luego salto de línea.
     * 
     * @param cantidad Número de asteriscos a imprimir (si es <=0 no imprime nada).
     */
    public static void estrellas2(int cantidad) {
        for (int i = 0; i < cantidad; i++)
            System.out.print("*");
        System.out.println();
    }

    // * 📖 TEORÍA: Métodos con valor de retorno
    // ──────────────────────────────────────────────
    // ? Usamos return para devolver resultados y poder encadenar expresiones.
    /**
     * Suma dos enteros y devuelve el resultado (ejemplo de método con retorno).
     * 
     * @param a sumando 1
     * @param b sumando 2
     * @return a + b
     */
    public static int suma(int a, int b) {
        return a + b;
    }

    /**
     * Devuelve el signo de un entero (ejemplo de if anidados y retorno temprano).
     * 
     * @param n Número a evaluar
     * @return -1 si n<0, 1 si n>0, 0 si n==0
     */
    public static int signo(int n) {
        if (n < 0)
            return -1;
        if (n > 0)
            return 1;
        return 0; // Se alcanza si n==0
    }

    /**
     * Función recursiva: Fibonacci simple (exponencial, solo fines didácticos).
     * Caso base: n<=2 -> 1. Caso recursivo: F(n)=F(n-1)+F(n-2).
     * 
     * @param n Posición (1-indexada). Fib(1)=1, Fib(2)=1
     * @return Fibonacci(n)
     */
    public static int fibonacci(int n) {
        if (n <= 2)
            return 1; // caso base
        return fibonacci(n - 1) + fibonacci(n - 2); // caso recursivo
    }

    // * 📖 TEORÍA: Demo guiada de métodos y ámbitos
    // ──────────────────────────────────────────────
    // ? Reúne ejemplos de llamadas, retorno, parámetros y ámbito de variables.
    /**
     * Demostración guiada de métodos: llamada a varias funciones y comentarios.
     */
    public static void demoMetodos() {
        System.out.println("\n🧪 Demo de métodos");
        estrellas1();
        estrellas2(5);
        int a = 12, b = 5;
        int r = suma(a, b);
        System.out.printf(Locale.ROOT, "suma(%d,%d) = %d%n", a, b, r);
        System.out.printf(Locale.ROOT, "signo(%d) = %d%n", -3, signo(-3));
        System.out.printf(Locale.ROOT, "Fibonacci(7) = %d%n", fibonacci(7));

        // Ámbito: 'x' existe solo dentro de este bloque
        {
            int x = 10;
            System.out.println("Ámbito local: x=" + x);
        }
        System.out.println("Ámbito externo: la variable 'x' ya no existe aquí");

        // Tareas:
        // 1) Crea una función que sume 1+2+...+n y pruébala con 10 y 10000.
        // 2) Crea una función 'media(double a, double b)' que devuelva la media.
        // 3) Documenta con Javadoc tus funciones y mira el "hover" en VS Code.
    }

    // ═══════════════════════════════════════════════════════════════
    // * ✅ NUEVO — CSV CON OBJETOS: guardar y cargar objetos Java
    // ═══════════════════════════════════════════════════════════════
    //
    // ? Hasta ahora el CSV era solo texto plano (strings fijos).
    // ? Ahora aprenderemos a CONVERTIR OBJETOS ↔ CSV:
    // ?
    // ? Guardar: recorremos la lista → escribimos los campos de cada objeto
    // separados por ;
    // ? Cargar: leemos cada línea → hacemos split() → reconstruimos el objeto
    // ?
    // ? ¿Por qué ";" y no ","?
    // ! Si los datos contienen comas (ej: apellidos "García, Ana"), el CSV rompe.
    // ! El punto y coma es más seguro para datos en español.

    /**
     * Guarda una lista de Alumnos en un CSV con cabecera.
     * Formato de cada fila: nombre;apellidos;nota;aprobado
     */
    public static void csvGuardarAlumnos(String rutaCSV) {

        // * Lista de objetos de ejemplo para guardar
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Ana", "García Martínez", 8.5));
        alumnos.add(new Alumno("Borja", "López Sánchez", 4.2));
        alumnos.add(new Alumno("Carla", "Rodríguez Pérez", 9.75));
        alumnos.add(new Alumno("David", "Fernández Gil", 5.0));
        alumnos.add(new Alumno("Elena", "Martínez Ruiz", 3.8));

        Path p = Paths.get(rutaCSV);
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {

            // ? Cabecera del CSV — describe los campos de cada columna
            bw.write("nombre;apellidos;nota;aprobado");
            bw.newLine();

            // ? Por cada objeto Alumno → escribimos sus campos separados por ;
            for (Alumno a : alumnos) {
                bw.write(a.getNombre() + ";"
                        + a.getApellidos() + ";"
                        + String.format(Locale.ROOT, "%.2f", a.getNota()) + ";"
                        + a.isAprobado());
                bw.newLine();
            }

            System.out.println("✅ Guardados " + alumnos.size() + " alumnos en: " + p.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("❌ Error guardando CSV: " + e.getMessage());
        }

        // ! ✅ TAREA ALUMNO:
        // * 1. Añade un campo 'curso' (String) a la clase Alumno.
        // * Actualiza la cabecera y la línea de escritura.
        // * 2. Haz que el programa pida los alumnos al usuario con Scanner
        // * en vez de tener la lista hardcodeada.
    }

    /**
     * Carga una lista de Alumnos desde un CSV.
     * Usa csvGuardarAlumnos() primero para generar el fichero.
     */
    public static void csvCargarAlumnos(String rutaCSV) {
        Path p = Paths.get(rutaCSV);
        if (!Files.exists(p)) {
            System.out.println("❌ El archivo no existe. Usa primero la opción 18.");
            return;
        }

        List<Alumno> cargados = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {

            // ? Saltamos la cabecera (primera línea)
            String cabecera = br.readLine();
            System.out.println("📋 Cabecera: " + cabecera);

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank())
                    continue; // ? ignoramos líneas vacías

                // ? Dividimos la línea por el separador ;
                String[] partes = linea.split(";");

                // ? Reconstruimos el objeto con los datos:
                // ? partes[0] = nombre
                // ? partes[1] = apellidos
                // ? partes[2] = nota (String → double con parseDouble)
                // ? partes[3] = aprobado (lo ignoramos, Alumno lo calcula solo)
                String nombre = partes[0];
                String apellidos = partes[1];
                double nota = Double.parseDouble(partes[2]);

                cargados.add(new Alumno(nombre, apellidos, nota));
            }

        } catch (IOException e) {
            System.out.println("❌ Error cargando CSV: " + e.getMessage());
            return;
        }

        // * Mostrar los alumnos cargados en tabla
        System.out.println("\n✅ Alumnos cargados (" + cargados.size() + " registros):");
        System.out.println("  ┌────────────────────────────────────────────────────────┐");
        for (Alumno a : cargados) {
            System.out.println("  │ " + a);
        }
        System.out.println("  └────────────────────────────────────────────────────────┘");

        // * Estadística: media de notas usando stream
        // ? .mapToDouble extrae la nota de cada alumno, .average() calcula la media
        double media = cargados.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0);
        System.out.printf(Locale.ROOT, "  📊 Media de notas: %.2f%n", media);

        long aprobados = cargados.stream().filter(Alumno::isAprobado).count();
        long suspensos = cargados.size() - aprobados;
        System.out.println("  ✅ Aprobados: " + aprobados + "  ❌ Suspensos: " + suspensos);

        // ! ✅ TAREA ALUMNO:
        // * 1. Muestra el alumno con la nota más alta (busca con un bucle o stream).
        // * 2. Ordena los alumnos por nota de mayor a menor antes de mostrarlos.
        // * 3. Guarda solo los alumnos SUSPENSOS en un nuevo fichero "suspensos.csv".
    }

    // ═══════════════════════════════════════════════════════════════
    // * ✅ NUEVO — JSON MANUAL: leer y escribir JSON sin Gson/Jackson
    // ═══════════════════════════════════════════════════════════════
    //
    // ? JSON (JavaScript Object Notation) es el formato estándar para
    // ? intercambiar datos en APIs y guardar configuraciones.
    // ?
    // ? Estructura de un array de objetos JSON:
    // ? {
    // ? "alumnos": [
    // ? { "nombre": "Ana", "nota": 8.5, "aprobado": true },
    // ? { "nombre": "Borja", "nota": 4.2, "aprobado": false }
    // ? ]
    // ? }
    // ?
    // ? En producción se usa Gson o Jackson (ver 📘_UT12b_ConsumoAPIs_REST.md).
    // ! Este método es SOLO DIDÁCTICO para entender la estructura antes de usar
    // librerías.

    public static void jsonDemo(String rutaJson) {

        // * ─── PARTE 1: ESCRIBIR JSON ───
        System.out.println("\n📌 PARTE 1 — Escribir JSON manualmente");

        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Ana", "García", 8.5));
        alumnos.add(new Alumno("Borja", "López", 4.2));
        alumnos.add(new Alumno("Carla", "Rodríguez", 9.75));

        // ? Construimos el JSON con StringBuilder
        // ? Cada alumno = un objeto {} dentro del array []
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"alumnos\": [\n");

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno a = alumnos.get(i);
            json.append("    {\n");
            json.append("      \"nombre\": \"" + a.getNombre() + "\",\n");
            json.append("      \"apellidos\": \"" + a.getApellidos() + "\",\n");
            json.append("      \"nota\": " + String.format(Locale.ROOT, "%.2f", a.getNota()) + ",\n");
            json.append("      \"aprobado\": " + a.isAprobado() + "\n");
            json.append("    }");
            if (i < alumnos.size() - 1)
                json.append(","); // ? coma entre elementos
            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}\n");

        // ? Guardamos en fichero
        Path p = Paths.get(rutaJson);
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            bw.write(json.toString());
            System.out.println("✅ JSON guardado en: " + p.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error guardando JSON: " + e.getMessage());
            return;
        }

        // ? Mostramos el JSON generado
        System.out.println("\n📄 Contenido JSON generado:");
        System.out.println(json);

        // * ─── PARTE 2: LEER JSON ───
        System.out.println("📌 PARTE 2 — Leer JSON manualmente (sin Gson)");
        System.out.println("? En producción: JsonObject obj = JsonParser.parseString(texto).getAsJsonObject();\n");

        // ? Leemos el fichero completo como un String (Java 11+)
        String contenido;
        try {
            contenido = Files.readString(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("❌ Error leyendo JSON: " + e.getMessage());
            return;
        }

        // ? Extraemos campos buscando el patrón "campo": valor
        System.out.println("Extracción de campos:");
        String[] campos = { "nombre", "apellidos", "nota", "aprobado" };
        int buscarDesde = 0;
        int numAlumno = 1;

        while (true) {
            int idx = contenido.indexOf("\"nombre\"", buscarDesde);
            if (idx == -1)
                break; // ? no hay más objetos

            System.out.println("  Alumno #" + numAlumno + ":");
            for (String campo : campos) {
                String valor = extraerCampoJson(contenido, campo, idx);
                System.out.println("    " + campo + " → " + valor);
            }

            buscarDesde = contenido.indexOf("}", idx) + 1;
            numAlumno++;
        }

        // ! ✅ TAREA ALUMNO:
        // * 1. Añade el campo "curso" al JSON generado.
        // * 2. Añade la librería Gson al proyecto y reescribe la lectura:
        // * Gson gson = new Gson();
        // * JsonObject obj = JsonParser.parseString(contenido).getAsJsonObject();
        // * JsonArray arr = obj.getAsJsonArray("alumnos");
        // * 3. Crea un fichero "config.json" con nombre_app, version, modo_debug.
        // * Léelo al arrancar y muestra la configuración.
    }

    /**
     * Extrae el valor de un campo JSON usando índices de String.
     * Solo para uso didáctico — en producción usar Gson/Jackson.
     */
    private static String extraerCampoJson(String json, String campo, int desdePos) {
        String buscar = "\"" + campo + "\"";
        int inicio = json.indexOf(buscar, desdePos);
        if (inicio == -1)
            return "N/A";

        inicio = json.indexOf(':', inicio) + 1;
        while (inicio < json.length() && json.charAt(inicio) == ' ')
            inicio++;

        boolean esString = json.charAt(inicio) == '"';
        if (esString)
            inicio++;

        int fin;
        if (esString) {
            fin = json.indexOf('"', inicio);
        } else {
            fin = inicio;
            while (fin < json.length() && json.charAt(fin) != ','
                    && json.charAt(fin) != '\n' && json.charAt(fin) != '}')
                fin++;
        }

        if (fin == -1 || fin <= inicio)
            return "N/A";
        return json.substring(inicio, fin).trim();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 👤 CLASE ALUMNO — usada por los métodos CSV con objetos y JSON
// ═══════════════════════════════════════════════════════════════
// ? Clase de dominio: representa un alumno con nombre, apellidos y nota.
// ? El campo 'aprobado' se calcula automáticamente en el constructor.

class Alumno {

    // * Atributos privados
    private String nombre;
    private String apellidos;
    private double nota; // ? nota de 0 a 10
    private boolean aprobado; // ? calculado automáticamente: nota >= 5

    // * Constructor
    public Alumno(String nombre, String apellidos, double nota) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nota = nota;
        this.aprobado = nota >= 5.0; // ? umbral de aprobado
    }

    // * Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getNota() {
        return nota;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    // * toString — se llama con System.out.println(alumno)
    @Override
    public String toString() {
        String estado = aprobado ? "✅ Aprobado" : "❌ Suspenso";
        return String.format(Locale.ROOT, "%-10s %-22s Nota: %5.2f  %s",
                nombre, apellidos, nota, estado);
    }
}
