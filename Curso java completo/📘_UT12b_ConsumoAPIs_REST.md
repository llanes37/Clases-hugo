# 📘 UT12b — Consumo de APIs REST con Java

> **Autor:** Joaquín Rodríguez Llanes
> **Nivel:** Java Intermedio-Avanzado — 1º/2º DAM
> **Prerrequisito:** `UT12_Ficheros.java` (entrada/salida) + `UT6_ExcepcionesManejoErrores.java`
> **Archivo Java:** `UT12b_ConsumoAPIs_REST.java`
> **Requisito:** Java 11 o superior (HttpClient añadido en Java 11)

---

## 🎯 ¿Por qué consumir APIs REST?

> [!IMPORTANT]
> Hoy en día, **el 90% de las aplicaciones** consumen datos de APIs externas. Aprenderlo es imprescindible para cualquier desarrollador en 2025+.

- Tu app de mensajes usa la **API de FCM** (notificaciones push)
- Tu app del tiempo usa **OpenWeatherMap API**
- Tu app de música usa **Spotify API**
- Tu app de mapas usa **Google Maps API**

Desde Java puedes conectarte a **cualquiera de estas APIs** usando `HttpClient`.

---

## 🌐 ¿Qué es REST?

**REST** (Representational State Transfer) es el estándar de comunicación entre apps web. Se basa en HTTP y usa JSON como formato de datos.

```
CLIENTE (tu programa Java)
      │
      │  GET https://api.ejemplo.com/usuarios/1
      │──────────────────────────────────────────→  SERVIDOR
      │                                               │
      │←──────────────────────────────────────────  │
         { "id": 1, "nombre": "Ana", "email": "..." }
```

---

## 📋 Los 4 métodos HTTP (CRUD)

| Método HTTP | Acción | SQL equivalente |
|-------------|--------|----------------|
| **GET** | Obtener datos | `SELECT` |
| **POST** | Crear un recurso | `INSERT` |
| **PUT / PATCH** | Actualizar | `UPDATE` |
| **DELETE** | Eliminar | `DELETE` |

---

## 🔧 HttpClient en Java 11+

`HttpClient` es la clase oficial de Java para hacer peticiones HTTP. No necesita librería externa.

### Estructura básica

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// 1. Crear el cliente (reutilizable)
HttpClient cliente = HttpClient.newHttpClient();

// 2. Construir la petición
HttpRequest peticion = HttpRequest.newBuilder()
        .uri(URI.create("https://pokeapi.co/api/v2/pokemon/pikachu"))
        .header("Content-Type", "application/json")
        .GET()
        .build();

// 3. Enviar y recibir respuesta
HttpResponse<String> respuesta = cliente.send(peticion,
        HttpResponse.BodyHandlers.ofString());

// 4. Usar la respuesta
System.out.println("Código: " + respuesta.statusCode());
System.out.println("Body: " + respuesta.body());
```

---

## 📊 Códigos HTTP más importantes

| Código | Nombre | Cuándo ocurre |
|--------|--------|--------------|
| **200** | OK | Petición correcta |
| **201** | Created | Recurso creado (POST exitoso) |
| **204** | No Content | OK pero sin respuesta (DELETE) |
| **400** | Bad Request | Petición mal formada |
| **401** | Unauthorized | Falta API key o token |
| **403** | Forbidden | Sin permisos |
| **404** | Not Found | El recurso no existe |
| **429** | Too Many Requests | Rate limit alcanzado |
| **500** | Internal Server Error | Error en el servidor |

> [!WARNING]
> Siempre comprueba el código HTTP antes de usar el body. Si el código no es 2xx, el body puede ser un mensaje de error, no los datos que esperas.

---

## 🗺️ APIs gratuitas para practicar

### Sin API key (perfectas para aprender)

| API | URL | Descripción |
|-----|-----|-------------|
| **PokéAPI** | `pokeapi.co/api/v2` | Datos de todos los Pokémon |
| **JSONPlaceholder** | `jsonplaceholder.typicode.com` | API falsa para practicar GET/POST/PUT/DELETE |
| **Open-Meteo** | `api.open-meteo.com` | El tiempo (sin registro) |
| **RestCountries** | `restcountries.com/v3.1` | Datos de todos los países |
| **TheMealDB** | `themealdb.com/api` | Recetas de cocina |

### Con API key gratuita (registro necesario)

| API | Descripción |
|-----|-------------|
| **OpenWeatherMap** | Tiempo actual y previsión |
| **NewsAPI** | Noticias actuales |
| **GitHub API** | Repositorios, usuarios... |
| **Spotify Web API** | Canciones, artistas, playlists |

---

## 📦 ¿Qué es JSON y cómo leerlo?

**JSON** (JavaScript Object Notation) es el formato estándar de las APIs REST.

```json
{
  "name": "pikachu",
  "id": 25,
  "weight": 60,
  "height": 4,
  "types": [
    { "type": { "name": "electric" } }
  ]
}
```

### Lectura manual (lo que hacemos en `UT12b_ConsumoAPIs_REST.java`)

```java
// Básico: buscar un campo en el JSON como String
String nombre = ParseadorJSON.extraerCampo(json, "name"); // "pikachu"
```

### Con Gson (forma profesional — añadir dependencia)

```java
// Con Gson es mucho más fácil y fiable
Gson gson = new Gson();
JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
String nombre = obj.get("name").getAsString();
int id = obj.get("id").getAsInt();
```

**Añadir Gson con Maven:**
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

---

## ⚙️ Petición GET paso a paso

```java
// 1. Crear el cliente (una vez, es reutilizable)
HttpClient cliente = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

// 2. Construir la petición
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://pokeapi.co/api/v2/pokemon/1"))
        .header("Accept", "application/json")
        .GET()
        .timeout(Duration.ofSeconds(10))
        .build();

// 3. Enviar (maneja IOException e InterruptedException)
try {
    HttpResponse<String> response = cliente.send(request,
            HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
        String json = response.body();
        // → usa el JSON aquí
    } else {
        System.out.println("Error: " + response.statusCode());
    }
} catch (IOException e) {
    System.out.println("Sin conexión: " + e.getMessage());
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

## ⚙️ Petición POST paso a paso

```java
// El body que enviamos (normalmente JSON)
String bodyJson = """
    {
        "title": "Mi post",
        "body": "Contenido del post",
        "userId": 1
    }
    """;

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(bodyJson))  // ← aquí enviamos el body
        .build();

HttpResponse<String> response = cliente.send(request,
        HttpResponse.BodyHandlers.ofString());

// POST exitoso → código 201 Created
System.out.println(response.statusCode()); // 201
System.out.println(response.body());       // el recurso creado con su ID
```

> [!NOTE]
> Los **Text Blocks** (`"""..."""`) requieren Java 15+. Para Java 11-14 usa concatenación de Strings.

---

## 🔄 Petición asíncrona (non-blocking)

```java
// sendAsync() → no bloquea el hilo principal
// CompletableFuture → se ejecuta cuando llega la respuesta

cliente.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)  // cuando llegue → extrae el body
        .thenAccept(body -> {           // cuando tenga el body → úsalo
            System.out.println("Respuesta: " + body.substring(0, 100));
        })
        .join();   // espera a que termine (en main, no en producción real)
```

---

## ⚠️ Buenas prácticas al consumir APIs

> [!TIP]
> Sigue estas prácticas para código robusto.

1. **Siempre comprueba el código HTTP** antes de usar el body
2. **Siempre maneja las excepciones** (`IOException`, `InterruptedException`)
3. **Respeta el rate limit**: algunas APIs limitan peticiones/minuto (espera entre llamadas)
4. **No hardcodees la API key** en el código: usa variables de entorno o ficheros de configuración
5. **Reutiliza el `HttpClient`**: créalo una vez, úsalo muchas veces
6. **Añade timeout**: una petición sin timeout puede colgar tu app para siempre

---

## 🔗 Archivos relacionados

| Archivo | Descripción |
|---------|-------------|
| `UT12b_ConsumoAPIs_REST.java` | Código con 5 ejemplos reales |
| `UT12_Ficheros.java` | Prerrequisito: manejo de E/S |
| `UT20_SpringBoot_API_REST_JPA/` | Crear tu PROPIA API REST en Java |
| `📘_UT20_SpringBoot_API_REST_JPA.md` | Guía de Spring Boot |

---

## 🚀 Próximos pasos

Una vez dominado el consumo de APIs:

1. **Añade Gson** para parsear JSON de forma profesional
2. **Crea un proyecto** que consuma una API real (OpenWeather + tu ciudad)
3. **Aprende Spring Boot** (UT20) para crear tu propia API REST
4. **Aprende OAuth 2.0** para autenticarte con APIs que requieren token (GitHub, Google...)
