/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2026
 *  🔹 UT12b: CONSUMO DE APIs REST CON JAVA — HttpClient + JSON
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  📋 ¿QUÉ ES UNA API REST?
 *  Una API REST (Representational State Transfer) es un servicio web que expone datos
 *  a través de HTTP. Tú haces una petición → el servidor responde con datos en JSON.
 *
 *  Ejemplos reales:
 *    - Google Maps → coordenadas de una dirección
 *    - OpenWeather  → el tiempo de tu ciudad
 *    - Spotify API  → canciones de un artista
 *    - PokéAPI      → datos de cualquier Pokémon (la que usamos aquí — GRATIS)
 *    - GitHub API   → repositorios de un usuario
 *
 *  🎯 CONCEPTOS QUE SE APRENDEN:
 *  ✅ ¿Qué son GET, POST, PUT, DELETE? (métodos HTTP)
 *  ✅ ¿Qué es JSON? ¿Cómo leerlo?
 *  ✅ HttpClient, HttpRequest, HttpResponse (Java 11+, sin dependencias)
 *  ✅ Parseo básico de JSON con String
 *  ✅ Petición GET a una API real (PokéAPI)
 *  ✅ Petición POST simulada (JSONPlaceholder)
 *  ✅ Manejo de errores de red (códigos HTTP, excepciones)
 *  ✅ Patrón de uso: separar la lógica HTTP del main()
 *
 *  ⚙️ REQUISITO:
 *  Java 11 o superior (HttpClient fue añadido en Java 11).
 *  NO necesita librerías externas — usa solo el JDK.
 *  ¡Ejecuta directamente con Code Runner o Terminal!
 * ******************************************************************************************
 */

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// * ════════════════════════════════════════════════════════════════
// * 📖 TEORÍA: MÉTODOS HTTP
// * ════════════════════════════════════════════════════════════════
//
// ? Los cuatro métodos HTTP más usados en REST:
// ?
// ?   GET    → OBTENER datos        (leer sin modificar)
// ?   POST   → CREAR un recurso     (enviar datos nuevos)
// ?   PUT    → ACTUALIZAR un recurso (reemplazar datos existentes)
// ?   DELETE → ELIMINAR un recurso
// ?
// ? En Java, HttpClient gestiona todos estos métodos.

// * ════════════════════════════════════════════════════════════════
// * 📖 TEORÍA: ¿QUÉ ES JSON?
// * ════════════════════════════════════════════════════════════════
//
// ? JSON (JavaScript Object Notation) es el formato estándar para
// ? intercambiar datos en APIs REST. Es texto plano, muy legible.
// ?
// ? Ejemplo de respuesta JSON de la PokéAPI:
// ? {
// ?   "name": "pikachu",
// ?   "id": 25,
// ?   "base_experience": 112,
// ?   "height": 4,
// ?   "weight": 60,
// ?   "types": [
// ?     { "type": { "name": "electric" } }
// ?   ]
// ? }
// ?
// ? Las llaves {} son objetos, los corchetes [] son arrays.
// ? Para parsear JSON en Java (producción) se usa Gson o Jackson.
// ? En este archivo usamos String para no necesitar dependencias.

// ═══════════════════════════════════════════════════════════════
// * 🌐 CLASE: ServicioHTTP
// ═══════════════════════════════════════════════════════════════
// ? Encapsula toda la lógica de peticiones HTTP.
// ? Principio: separar la lógica de red del main().

class ServicioHTTP {

    // * El HttpClient es el "navegador" de Java
    // ? Reutilizable — se crea una vez y se usa para varias peticiones
    private HttpClient cliente;

    public ServicioHTTP() {
        // ? Builder pattern — construimos el cliente con opciones
        this.cliente = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // ? Espera máx. 10 seg para conectar
                .build();
    }

    // * Método GET — obtener datos de una URL
    // ? Devuelve el cuerpo de la respuesta como String
    // ? Devuelve null si hubo error de red o código HTTP de error
    public String get(String url) {
        try {
            // ? 1. Construir la petición
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json") // ? indicamos que esperamos JSON
                    .GET() // ? método HTTP: GET
                    .timeout(Duration.ofSeconds(10))
                    .build();

            // ? 2. Enviar la petición y recibir la respuesta
            // ? .send() bloquea hasta que llega la respuesta (síncrono)
            HttpResponse<String> respuesta = cliente.send(peticion,
                    HttpResponse.BodyHandlers.ofString());

            // ? 3. Comprobar el código HTTP
            int codigoHTTP = respuesta.statusCode();

            // ? Códigos 2xx = éxito (200 OK, 201 Created, 204 No Content...)
            if (codigoHTTP >= 200 && codigoHTTP < 300) {
                return respuesta.body(); // ? el cuerpo de la respuesta (el JSON)
            } else {
                System.out.println("  ⚠️ Error HTTP: " + codigoHTTP + " para URL: " + url);
                return null;
            }

        } catch (java.net.ConnectException e) {
            System.out.println("  ❌ Sin conexión a internet. Comprueba tu red.");
            return null;
        } catch (Exception e) {
            System.out.println("  ❌ Error al hacer GET: " + e.getMessage());
            return null;
        }
    }

    // * Método POST — enviar datos a una URL
    // ? Recibe la URL y el body en formato JSON (String)
    public String post(String url, String bodyJson) {
        try {
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(bodyJson)) // ? enviamos el JSON
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> respuesta = cliente.send(peticion,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("  📤 POST → Código HTTP: " + respuesta.statusCode());
            return respuesta.body();

        } catch (Exception e) {
            System.out.println("  ❌ Error al hacer POST: " + e.getMessage());
            return null;
        }
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🔧 CLASE: ParseadorJSON
// ═══════════════════════════════════════════════════════════════
// ? Parsea JSON de forma básica usando operaciones de String.
// ? En proyectos reales se usaría Gson o Jackson (más fiable).
// ? Esta clase es solo para aprender sin dependencias externas.

class ParseadorJSON {

    // ? Extrae el valor de un campo simple: "campo": "valor" o "campo": 123
    public static String extraerCampo(String json, String campo) {
        String buscar = "\"" + campo + "\":";
        int inicio = json.indexOf(buscar);
        if (inicio == -1)
            return "N/A";

        inicio += buscar.length();

        // ? Saltamos espacios en blanco
        while (inicio < json.length() && json.charAt(inicio) == ' ')
            inicio++;

        // ? ¿Es un String (empieza por ")? o ¿Es un número/boolean?
        boolean esString = json.charAt(inicio) == '"';
        if (esString)
            inicio++; // ? saltamos la comilla inicial

        int fin;
        if (esString) {
            fin = json.indexOf("\"", inicio);
        } else {
            // ? Para números/boolean: buscar el primer , o } o ]
            fin = inicio;
            while (fin < json.length() &&
                    json.charAt(fin) != ',' &&
                    json.charAt(fin) != '}' &&
                    json.charAt(fin) != ']') {
                fin++;
            }
        }

        if (fin == -1 || fin <= inicio)
            return "N/A";
        return json.substring(inicio, fin).trim();
    }
}

// ═══════════════════════════════════════════════════════════════
// * 🖥️ PROGRAMA PRINCIPAL
// ═══════════════════════════════════════════════════════════════

public class UT12b_ConsumoAPIs_REST {

    public static void main(String[] args) {

        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║  🌐 UT12b: CONSUMO DE APIs REST EN JAVA                    ║");
        System.out.println("║  Conectamos con servicios web reales desde Java            ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝");

        ServicioHTTP http = new ServicioHTTP();

        // * ──────────────────────────────────────────────
        // * 🔵 EJEMPLO 1: GET — PokéAPI (datos de un Pokémon)
        // * ──────────────────────────────────────────────
        // ? La PokéAPI es gratuita, sin registro, sin API key.
        // ? URL: https://pokeapi.co/api/v2/pokemon/{nombre}

        System.out.println("\n📌 EJEMPLO 1 — GET: Datos de un Pokémon (PokéAPI)");
        System.out.println("   URL: https://pokeapi.co/api/v2/pokemon/pikachu\n");

        String jsonPokemon = http.get("https://pokeapi.co/api/v2/pokemon/pikachu");

        if (jsonPokemon != null) {
            // ? Extraemos campos del JSON con nuestro parseador básico
            String nombre = ParseadorJSON.extraerCampo(jsonPokemon, "name");
            String id = ParseadorJSON.extraerCampo(jsonPokemon, "id");
            String peso = ParseadorJSON.extraerCampo(jsonPokemon, "weight");
            String altura = ParseadorJSON.extraerCampo(jsonPokemon, "height");
            String experiencia = ParseadorJSON.extraerCampo(jsonPokemon, "base_experience");

            System.out.println("  ⚡ Pokémon encontrado:");
            System.out.println("  ┌────────────────────────────────────┐");
            System.out.println("  │ Nombre:      " + nombre.toUpperCase());
            System.out.println("  │ ID:          #" + id);
            System.out.println("  │ Peso:        " + peso + " (× 0.1 kg = " + (Integer.parseInt(peso) * 0.1) + " kg)");
            System.out
                    .println("  │ Altura:      " + altura + " (× 0.1 m = " + (Integer.parseInt(altura) * 0.1) + " m)");
            System.out.println("  │ Experiencia: " + experiencia + " puntos");
            System.out.println("  └────────────────────────────────────┘");

            // ? También podemos mostrar el JSON en bruto (los primeros 300 caracteres)
            System.out.println("\n  📄 JSON RAW (primeros 300 caracteres):");
            System.out.println("  " + jsonPokemon.substring(0, Math.min(300, jsonPokemon.length())) + "...");
        }

        // * ──────────────────────────────────────────────
        // * 🔵 EJEMPLO 2: GET — Varios Pokémon en bucle
        // * ──────────────────────────────────────────────

        System.out.println("\n📌 EJEMPLO 2 — GET en bucle: varios Pokémon");

        String[] pokemones = { "bulbasaur", "charmander", "squirtle", "mewtwo" };

        for (String pokemon : pokemones) {
            String json = http.get("https://pokeapi.co/api/v2/pokemon/" + pokemon);
            if (json != null) {
                String nombre = ParseadorJSON.extraerCampo(json, "name");
                String id = ParseadorJSON.extraerCampo(json, "id");
                String peso = ParseadorJSON.extraerCampo(json, "weight");
                System.out.println("  🎮 #" + id + " " + nombre.toUpperCase() + " — Peso: " + peso);
            }
            // ? Pequeña pausa para no saturar la API
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                /* ignorar */ }
        }

        // * ──────────────────────────────────────────────
        // * 🟢 EJEMPLO 3: GET — JSONPlaceholder (fake API)
        // * ──────────────────────────────────────────────
        // ? JSONPlaceholder es una API FALSA para practicar.
        // ? Devuelve datos ficticios pero con estructura real.
        // ? URL: https://jsonplaceholder.typicode.com

        System.out.println("\n📌 EJEMPLO 3 — GET: JSONPlaceholder (usuarios ficticios)");
        System.out.println("   URL: https://jsonplaceholder.typicode.com/users/1\n");

        String jsonUsuario = http.get("https://jsonplaceholder.typicode.com/users/1");

        if (jsonUsuario != null) {
            String nombre = ParseadorJSON.extraerCampo(jsonUsuario, "name");
            String email = ParseadorJSON.extraerCampo(jsonUsuario, "email");
            String telefono = ParseadorJSON.extraerCampo(jsonUsuario, "phone");
            String web = ParseadorJSON.extraerCampo(jsonUsuario, "website");

            System.out.println("  👤 Usuario obtenido de la API:");
            System.out.println("  ┌────────────────────────────────────┐");
            System.out.println("  │ Nombre:    " + nombre);
            System.out.println("  │ Email:     " + email);
            System.out.println("  │ Teléfono:  " + telefono);
            System.out.println("  │ Web:       " + web);
            System.out.println("  └────────────────────────────────────┘");
        }

        // * ──────────────────────────────────────────────
        // * 🟠 EJEMPLO 4: POST — Crear un recurso
        // * ──────────────────────────────────────────────
        // ? POST envía datos al servidor para CREAR algo nuevo.
        // ? JSONPlaceholder acepta POSTs y finge que los crea.

        System.out.println("\n📌 EJEMPLO 4 — POST: Crear un post en JSONPlaceholder");
        System.out.println("   URL: https://jsonplaceholder.typicode.com/posts\n");

        // ? Construimos el JSON a enviar (en producción usaríamos Gson)
        String nuevoPost = "{ \"title\": \"Mi primer post desde Java\", "
                + "\"body\": \"Aprendiendo a consumir APIs REST con HttpClient de Java 11+\", "
                + "\"userId\": 1 }";

        System.out.println("  📤 Enviando JSON:");
        System.out.println("  " + nuevoPost);

        String respuestaPost = http.post(
                "https://jsonplaceholder.typicode.com/posts", nuevoPost);

        if (respuestaPost != null) {
            String id = ParseadorJSON.extraerCampo(respuestaPost, "id");
            String title = ParseadorJSON.extraerCampo(respuestaPost, "title");
            System.out.println("\n  ✅ Respuesta del servidor:");
            System.out.println("  ┌────────────────────────────────────┐");
            System.out.println("  │ ID asignado: " + id);
            System.out.println("  │ Título:      " + title);
            System.out.println("  └────────────────────────────────────┘");
            System.out.println("\n  📄 JSON completo de respuesta:");
            System.out.println("  " + respuestaPost);
        }

        // * ──────────────────────────────────────────────
        // * 🔴 EJEMPLO 5: Control de errores
        // * ──────────────────────────────────────────────

        System.out.println("\n📌 EJEMPLO 5 — Manejo de errores (404 Not Found)");

        // ? Pokémon que no existe → código 404
        String jsonInvalido = http.get("https://pokeapi.co/api/v2/pokemon/pokemonfalso12345");
        if (jsonInvalido == null) {
            System.out.println("  ✅ Error manejado correctamente — el Pokémon no existe.");
        }

        // * ──────────────────────────────────────────────
        // * 💡 RESUMEN DE CÓDIGOS HTTP MÁS COMUNES
        // * ──────────────────────────────────────────────

        System.out.println("\n📌 CÓDIGOS HTTP MÁS COMUNES:");
        System.out.println("  ┌──────────────────────────────────────────────┐");
        System.out.println("  │ 200 OK           → Petición correcta        │");
        System.out.println("  │ 201 Created      → Recurso creado (POST)     │");
        System.out.println("  │ 204 No Content   → OK, sin respuesta         │");
        System.out.println("  │ 400 Bad Request  → Petición mal formada      │");
        System.out.println("  │ 401 Unauthorized → Falta autenticación       │");
        System.out.println("  │ 403 Forbidden    → Sin permisos              │");
        System.out.println("  │ 404 Not Found    → Recurso no encontrado     │");
        System.out.println("  │ 500 Server Error → Error interno del server  │");
        System.out.println("  └──────────────────────────────────────────────┘");

        // ! ✅ TAREA ALUMNO:
        // * 1. Haz un GET a https://pokeapi.co/api/v2/pokemon/{nombre}
        // * donde {nombre} lo introduce el usuario con Scanner.
        // * Muestra el nombre, ID y peso del Pokémon buscado.
        // *
        // * 2. Haz un bucle que obtenga los 10 primeros Pokémon
        // * (IDs del 1 al 10) y los muestre en una lista.
        // * URL: https://pokeapi.co/api/v2/pokemon/{id}
        // *
        // * 3. Investiga la API de OpenWeatherMap (requiere API key gratis):
        // * https://openweathermap.org/api
        // * Obtén el tiempo actual de tu ciudad.
        // *
        // * 4. (Avanzado) Añade Gson al proyecto para parsear JSON real:
        // * Gson gson = new Gson();
        // * JsonObject objeto = JsonParser.parseString(json).getAsJsonObject();
        // * String nombre = objeto.get("name").getAsString();
    }
}
