
# 📘 GUÍA DEL CURSO DE PROGRAMACIÓN EN JAVA
> Autor: Joaquín Rodríguez Llanes  
> Año: 2025  
> Versión del curso: 1.2  
> Estado: ✅ Actualizado hasta la UT20 + bloque POO avanzado (Feb 2026)

---

## 🧠 OBJETIVO DEL CURSO

Aprender programación en Java **desde cero** hasta tener un nivel suficiente para:
- Crear estructuras de control.
- Usar funciones, clases, ficheros y excepciones.
- Manejar colecciones, enums, paquetes y fechas.
- Trabajar con bases de datos (JDBC/SQLite) y arquitectura por capas.
- Desarrollar APIs REST con Spring Boot (2º DAM/DAW).
- Entregar un **proyecto final completo** con menús y persistencia.

---

## 🧰 REQUISITOS Y PREPARACIÓN DEL ENTORNO

> Si es tu primera vez configurando todo, comienza por aquí: 📘 `📘_UT0_Guia_Instalacion_Entorno.md` (instalación paso a paso de JDK, VS Code, extensiones, SQLite, Maven y Spring Boot).

### ✅ Necesitas tener instalado:
- [x] **JDK 17 o superior** (Recomendado: OpenJDK 23)
- [x] **Visual Studio Code** o IntelliJ/NetBeans/Eclipse
- [x] **Better Comments** (colores en comentarios)
- [x] (Opcional) **Code Runner** (ejecución rápida en clase)
- [x] (Recomendado) **Extension Pack for Java** (VS Code)
- [x] (Para UT19/UT20) **Maven** instalado en PATH
- [x] (Para probar UT20) **Postman/Thunder Client** para APIs

---

## 🗂️ ESTRUCTURA DEL CURSO

Cada unidad trae archivo(s) con comentarios didácticos y ejercicios. Resumen:

| Unidad | Archivo/Carpeta                          | Contenido principal                                                          |
|--------|------------------------------------------|-------------------------------------------------------------------------------|
| UT0    | `UT0_IntroduccionJava.java`              | Instalación, entorno, primeros pasos                                          |
| UT1    | `UT1_VariablesTiposOperadores.java`      | Variables, tipos y operadores                                                 |
| UT2    | `UT2_ControlFlujo.java`                  | Estructuras condicionales `if`, `switch`                                      |
| UT3    | `UT3_Bucles.java`                        | Bucles `for`, `while`, `do while`                                             |
| UT4    | `UT4_Funciones.java`                     | Funciones, parámetros, retorno, recursividad                                  |
| UT4b   | `UT4_FuncionesAvanzado.java`             | Funciones avanzadas: recursividad, sobrecarga, funciones sobre colecciones    |
| UT5    | `UT5_ClasesObjetos.java`                 | POO: clases, atributos, constructores, encapsulación                          |
| UT5b   | `UT5_ClasesObjetosAvanzado.java`         | Composición, ArrayList de objetos, constructores sobrecargados, `static`      |
| UT5c   | `UT5_HerenciaPolimorfismoInterfaces.java`| Herencia (`extends`), polimorfismo (`@Override`), interfaces                  |
| UT5 🏋️ | `UT5_POO_EjemploResuelto_Ordenadores.java` | **Ejercicio resuelto tipo examen:** Periferico + Ordenador (HashSet, Random) |
| UT5 🏋️ | `UT5_POO_EjemploResuelto_RedSocial.java`   | **Ejercicio resuelto tipo examen:** Usuario + Foto + GestionFotos (HashMap)  |
| UT5 📝 | `UT5_POO_Practica_Ordenadores.java`        | Ejercicio guiado (16 TODOs) — rellena el código y compara con el resuelto    |
| UT5 📝 | `UT5_POO_Practica_RedSocial.java`          | Ejercicio guiado (25 TODOs) — rellena el código y compara con el resuelto    |
| UT5 🎯 | `UT5_POO_Ejercicios_Recuperacion.java`     | **5 ejercicios completos** nivel examen/recuperación (Hospital, Biblioteca, Tienda, Flota, Streaming) |
| UT6    | `UT6_ExcepcionesManejoErrores.java`      | Manejo de errores, try-catch-finally, excepciones personalizadas              |
| UT6b   | `UT6b_Testing_JUnit.java`                | **Testing con JUnit 5**: patrón AAA, @Test, @BeforeEach, assertThrows, @ParameterizedTest |
| UT7    | `UT7_CadenasTexto.java`                  | Operaciones con Strings                                                       |
| UT8    | `UT8_ArraysYStrings.java`                | Arrays, multidimensionales y más sobre cadenas                                |
| UT9    | `UT9_StringsAvanzados.java`              | `.split()`, `.replaceAll()`, regex básicos                                   |
| UT10   | `UT10_MathYFechas.java`                  | Clase Math, fechas con LocalDate y Calendar                                  |
| UT11   | `UT11_ExpresionesRegulares.java`         | Regex, validación de datos con patrones                                      |
| UT12   | `UT12_Ficheros.java`                     | Leer, escribir y manejar ficheros                                            |
| UT12b  | `UT12b_ConsumoAPIs_REST.java`            | **APIs REST con HttpClient**: GET/POST, PokéAPI, JSONPlaceholder, JSON, errores HTTP |
| UT13   | `UT13_ColeccionesAvanzadas.java`         | Listas, mapas, sets y colecciones complejas                                  |
| UT14   | `UT14_EnumYConstantes.java`              | Enumeraciones y uso de constantes                                            |
| UT15   | `UT15_ModularidadYPaquetes.java`         | Paquetes, modularidad y clases externas                                      |
| UT16   | `UT16_ProyectoFinal.java`                | Proyecto final (menús, lógica, ficheros, colecciones)                         |
| UT17   | `UT17_JavaSQL_Basico_Sqlite.java`        | JDBC/SQLite básico: conexión, INSERT/SELECT/UPDATE/DELETE                      |
| UT18   | `UT18_JavaSQL_Intermedio_Sqlite.java`    | JDBC intermedio: FKs, JOINs, validaciones y transacciones                      |
| UT19   | `UT19_ArquitecturaCapas_JDBC/`           | Proyecto Maven por capas (modelo, repo JDBC, servicio, CLI) + JUnit + logging |
| UT20   | `UT20_SpringBoot_API_REST_JPA/`          | Spring Boot API REST + JPA/Hibernate + Validación + Swagger                    |

---

## 🚀 ¿CÓMO EJECUTAR?

### OPCIÓN 1: ✅ Recomendado — Code Runner
> Ideal para clase: ejecución instantánea.

1) Instala `Code Runner`.
2) En ajustes, activa: `code-runner.runInTerminal: true` (necesario para `Scanner`).
3) Abre un `.java` y pulsa ▶️ “Run Code”.

### OPCIÓN 2: Terminal (UT0–UT18, archivos sueltos)
```powershell
# Compilar
javac NombreArchivo.java

# Ejecutar
java NombreArchivo
```
Ejemplo:
```powershell
javac UT18_JavaSQL_Intermedio_Sqlite.java
java -cp ".;libs\sqlite-jdbc-3.36.0.3.jar" UT18_JavaSQL_Intermedio_Sqlite
```

### OPCIÓN 3: Maven (UT19)
- Abre la carpeta `UT19_ArquitecturaCapas_JDBC` como proyecto Maven.
- Ejecuta la clase `com.curso.ut19.Application` desde el IDE (menú CLI).
- Tests: `mvn -q test`

### OPCIÓN 4: Spring Boot (UT20)
```powershell
# Desde la carpeta UT20_SpringBoot_API_REST_JPA
mvn spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui/index.html

---

## 📝 LEYENDA BETTER COMMENTS (usada en todo el curso)
- `//!` Sección/título importante
- `?` Teoría y explicación
- `*` Consejos y buenas prácticas
- `TODO` Tareas para el alumno
- `NOTE` Notas y consideraciones

---

## 📦 PROYECTOS INCLUIDOS

### Proyecto final práctico (UT16, MVC sin Maven)
- Carpeta: `cursos/Curso java completo/proyecto-final`
- Instrucciones en `README.md` y scripts: `build.bat`, `run.bat`, `package.bat`.
- Repasa POO, colecciones, ficheros (CSV), fechas, excepciones, validaciones (regex) y paquetes.

### UT19 — Arquitectura por capas + JDBC (Maven)
- Capas: modelo, repositorio (JDBC), servicio y vista (CLI).
- Logging con SLF4J/Logback, tests JUnit/Mockito.

### UT20 — Spring Boot API REST + JPA
- Entidades JPA, repositorios, controladores REST, validación y Swagger.
- Base de datos H2 en memoria para arrancar al instante.

---

## 🤔 DUDAS FRECUENTES

### ❓ `ClassNotFoundException` al ejecutar
Compilaste con `javac` pero ejecutaste con un nombre de clase distinto o faltan dependencias en el classpath (p. ej., driver SQLite).

### ❓ Maven no se reconoce en terminal
Asegúrate de que `mvn -v` funciona. Si no, instala Maven y añade el `bin/` al `PATH`.

### ❓ La API no arranca (UT20)
Puede haber otro servicio usando el puerto 8080. Cambia el puerto en `application.properties`:
`server.port=8081`.

### ❓ No veo la consola H2 (UT20)
Asegura `spring.h2.console.enabled=true` y visita `/h2-console`.

### ❓ Claves foráneas no se aplican (UT18)
Activa `PRAGMA foreign_keys=ON` al conectar.

---

## 🆕 NOVEDADES (2026-02) — Testing y APIs REST
- Añadido `UT6b_Testing_JUnit.java`: testing manual + JUnit 5 con Calculadora y CuentaBancaria.
- Añadida `📘_UT6b_Testing_JUnit.md`: patrón AAA, anotaciones, assertions, configuración Maven/VSCode.
- Añadido `UT12b_ConsumoAPIs_REST.java`: GET/POST con HttpClient, PokéAPI, JSONPlaceholder (Java 11+ sin dependencias).
- Añadida `📘_UT12b_ConsumoAPIs_REST.md`: REST, JSON, APIs gratuitas, asíncrono, buenas prácticas.

## 🆕 NOVEDADES (2026-02) — Bloque POO completo
- Añadido `UT5_ClasesObjetosAvanzado.java`: composición, ArrayList de objetos, `static`.
- Mejorado `UT5_ClasesObjetos.java`: introducción mejorada con Better Comments.
- Mejorado `UT5_HerenciaPolimorfismoInterfaces.java`: teoría y comentarios ampliados.
- Añadidos **2 ejemplos resueltos tipo examen** (Ordenadores y Red Social) con enunciado completo.
- Añadidos **2 ejercicios de práctica guiada** con TODOs numerados para que el alumno implemente.
- Añadido **pack de 5 ejercicios de recuperación** nivel examen real (Hospital, Biblioteca, Tienda, Flota, Streaming).
- Añadida `📘_UT5_POO_Ejercicios_Recuperacion.md`: guía completa para afrontar exámenes POO.
- Añadida `📘_UT5b_ClasesObjetosAvanzado.md`: guía de composición y múltiples clases.
- Creado `.agents/instructions.md`: guía de estilo para contenido generado con IA.

## 🆕 NOVEDADES (2025-11)
- Añadidas UT17 (JDBC básico) y UT18 (JDBC intermedio con FKs, JOINs y transacciones).
- Añadido UT19 (Maven, capas, JUnit, logging) con menú estilo UT17/UT18.
- Añadido UT20 (Spring Boot, JPA, Validación, Swagger) con documentación y endpoints.

### Rutas rápidas (archivos clave)
- `UT17_JavaSQL_Basico_Sqlite.java`
- `UT18_JavaSQL_Intermedio_Sqlite.java`
- `UT19_ArquitecturaCapas_JDBC/` (pom.xml, Application.java, repos, service, tests)
- `UT20_SpringBoot_API_REST_JPA/` (pom.xml, Ut20Application.java, controllers, model, repository)

---

> 🚀 ¡Ánimo! Este curso está diseñado para que aprendas de forma clara, práctica y progresiva.  
> No te rindas: paso a paso, dominarás Java, JDBC y el desarrollo de APIs profesionales.
