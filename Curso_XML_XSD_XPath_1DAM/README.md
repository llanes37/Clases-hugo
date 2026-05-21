# :bookmark_tabs: Curso XML + XSD + XPath (1 DAM) - Guia

Este curso cubre la parte de datos de Lenguajes de Marcas: **XML**, **validacion con XSD** y **consultas con XPath**.

---

## :triangular_flag_on_post: Contenido

1. :rocket: Quickstart (en 5 minutos)
2. :open_file_folder: Mapa de archivos
3. :mortar_board: Ruta del curso (paso a paso)
4. :calendar: Plan sugerido (3 sesiones)
5. :wrench: Extensiones recomendadas (VS Code)
6. :white_check_mark: Como validar XML con XSD
7. :trophy: Resultado final esperado
8. :warning: Si te pierdes (regla de oro)

---

## :rocket: Quickstart (en 5 minutos)

1. Abre esta carpeta en VS Code.
2. Abre `Curso_XML_XSD_XPath_Completo.md` y sigue el orden.
3. Abre `data/biblioteca.xml` y localiza: `libro`, `socio`, `prestamo`.
4. Abre `Clase_XML_XSD_XPath.html` en el navegador y prueba:
   - `//libro/titulo`
   - `count(//prestamo)`
5. Abre `data/biblioteca_invalida.xml` y valida contra `data/biblioteca.xsd` (debe fallar).

---

## :open_file_folder: Mapa de archivos

| Archivo | Para que sirve |
|---|---|
| `README.md` | Guia rapida (este archivo) |
| `Curso_XML_XSD_XPath_Completo.md` | Teoria + ejercicios + soluciones (todo en uno) |
| `Clase_XML_XSD_XPath.html` | Clase HTML + laboratorio XPath en navegador |
| `data/biblioteca.xml` | XML valido para practicar |
| `data/biblioteca.xsd` | XSD que valida `biblioteca.xml` |
| `data/biblioteca_invalida.xml` | XML bien formado pero invalido (para practicar errores) |

---

## :mortar_board: Ruta del curso (paso a paso)

1. **Lee y practica** desde `Curso_XML_XSD_XPath_Completo.md`.
2. **Dataset**: explora `data/biblioteca.xml` (estructura + ids + referencias).
3. **XPath**:
   - en navegador: abre `Clase_XML_XSD_XPath.html`
   - en VS Code: usa una extension si quieres (opcional)
4. **XSD**:
   - lee `data/biblioteca.xsd`
   - valida `data/biblioteca_invalida.xml`
   - corrige errores hasta que sea valido

---

## :calendar: Plan sugerido (1 DAM)

- Sesion 1: XML bien formado + leer dataset + primera validacion
- Sesion 2: XPath (basico e intermedio)
- Sesion 3: XSD (restricciones + key/keyref) + proyecto final

---

## :wrench: Extensiones recomendadas (VS Code)

- XML: `XML` (Red Hat). Autocompleta, formatea y valida con XSD.
- XPath (opcional): cualquier extension que permita evaluar XPath sobre un XML.

Nota: si no quieres instalar XPath en VS Code, usa el laboratorio del HTML.

---

## :white_check_mark: Como validar XML con XSD (idea general)

Objetivo: comprobar si un XML es **valido** segun un esquema.

En VS Code (flujo tipico):

1. Abre `data/biblioteca_invalida.xml`.
2. Abre `data/biblioteca.xsd` en otra pestana.
3. Valida con la extension de XML (segun la extension, puede ser automatico o con comando).
4. Lee el error y arreglalo en el XML.

:information_source: `data/biblioteca.xml` incluye `xsi:noNamespaceSchemaLocation="biblioteca.xsd"` para ayudar a las herramientas.

---

## :trophy: Resultado final esperado

Al terminar el curso deberias ser capaz de:

- escribir XML bien formado
- explicar la diferencia entre bien formado y valido
- escribir o modificar un XSD real (restricciones y tipos)
- validar un XML contra un XSD y corregir errores
- hacer consultas XPath (filtros, atributos, funciones como `count` y `sum`)

---

## :warning: Si te pierdes

Regla de oro:

1. primero que el XML sea bien formado
2. luego que sea valido con XSD
3. y despues, XPath para consultar
