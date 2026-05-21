# Curso XML + XSD + XPath (1 DAM) - Documento completo

Este documento contiene teoria, practica y soluciones en un solo lugar.

---

## 0) Como usar este documento

Este curso esta pensado para 1 DAM, pero se puede seguir sin ser de FP si te interesa trabajar con datos.

Convencion estilo "Better Comments" (para leer mas rapido):

- ! Importante: idea clave o alerta
- ? Nota: aclaracion util
- * Ejemplo: bloque demostrativo
- TODO Alumno: tarea a resolver
- SOLUCION: una posible respuesta

Archivos que vas a tocar de verdad:

- `data/biblioteca.xml`
- `data/biblioteca.xsd`
- `data/biblioteca_invalida.xml`
- `Clase_XML_XSD_XPath.html` (laboratorio XPath)

---

## 1) Objetivo y mentalidad (1 DAM)

En 1 DAM, XML se aprende mejor como un flujo:

1. XML: representar datos (estructura)
2. XSD: validar que los datos cumplen reglas
3. XPath: consultar los datos (hacer preguntas)

Tu meta no es memorizar etiquetas. Tu meta es:

- poder leer un XML y entenderlo
- poder detectar errores de estructura (bien formado)
- poder validar y corregir con un XSD (valido)
- poder responder preguntas con XPath

---

## 2) UT0 - Entorno y herramientas (VS Code)

### 2.1 Extensiones recomendadas

- XML: `XML` (Red Hat). Autocompleta, formatea y valida con XSD.
- XPath (opcional): cualquier extension que permita evaluar XPath sobre un XML.

Si no quieres instalar XPath en VS Code:

- abre `Clase_XML_XSD_XPath.html` en el navegador y usa el laboratorio.

### 2.2 Enlazar el XSD desde el XML (para validar mas facil)

En `data/biblioteca.xml` veras esto en el root:

```xml
<biblioteca version="1.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:noNamespaceSchemaLocation="biblioteca.xsd">
```

! Importante: esto NO valida por si solo. Solo ayuda a que herramientas sepan que XSD usar.

### 2.3 Validar sin extension (opcional, Windows)

? Nota: esto es opcional. Si usas VS Code con la extension XML, puedes ignorarlo.

Puedes validar con PowerShell usando .NET (sin instalar nada extra):

```powershell
$base = "C:\\ruta\\Curso_XML_XSD_XPath_1DAM\\data"
$xsd  = "$base\\biblioteca.xsd"
$xml  = "$base\\biblioteca.xml"

$schemas = New-Object System.Xml.Schema.XmlSchemaSet
$null = $schemas.Add($null, $xsd)

$settings = New-Object System.Xml.XmlReaderSettings
$settings.Schemas = $schemas
$settings.ValidationType = [System.Xml.ValidationType]::Schema
$settings.ValidationFlags = [System.Xml.Schema.XmlSchemaValidationFlags]::ReportValidationWarnings `
  -bor [System.Xml.Schema.XmlSchemaValidationFlags]::ProcessIdentityConstraints

$errors = New-Object System.Collections.Generic.List[string]
$handler = [System.Xml.Schema.ValidationEventHandler]{
  param($sender,$e)
  $errors.Add("$($e.Severity): $($e.Message)")
}
$settings.add_ValidationEventHandler($handler)

$reader = [System.Xml.XmlReader]::Create($xml, $settings)
try { while($reader.Read()){} } finally { $reader.Close() }
$errors
```

Si la lista sale vacia, el XML es valido segun el XSD.

---

## 3) UT1 - XML: lo minimo para no romperte

### 3.1 Bien formado vs valido

- Bien formado: cumple reglas basicas de XML (etiquetas cerradas, un solo root, comillas, etc.)
- Valido: ademas cumple un esquema (XSD)

Ejemplo de error de bien formado:

```xml
<libro>
  <titulo>Algo</titulo>
<!-- Falta cerrar libro -->
```

Eso ni siquiera se puede "leer" como XML correcto.

Ejemplo de error de validez (XSD):

```xml
<libro id="L1">
  <titulo>Algo</titulo>
  <paginas>muchas</paginas> <!-- deberia ser numero -->
</libro>
```

Puede estar bien formado, pero un XSD puede decir: paginas debe ser entero.

### 3.2 Reglas de XML (las importantes)

- Hay un solo elemento raiz (root)
- Las etiquetas se abren y se cierran: `<a></a>` o `<a/>`
- Los nombres son sensibles a mayusculas: `<Titulo>` != `<titulo>`
- Los atributos van con comillas: `id="L1"`
- Puedes anidar elementos: un elemento dentro de otro

### 3.3 Elementos vs atributos

Regla practica (no religiosa):

- Elementos: datos principales (titulo, autor, precio)
- Atributos: metadatos cortos (id, moneda, version)

Ejemplo:

```xml
<precio moneda="EUR">12.95</precio>
```

### 3.4 Comentarios y CDATA (solo lo necesario)

Comentarios:

```xml
<!-- Esto es un comentario -->
```

CDATA (para texto con caracteres especiales):

```xml
<texto><![CDATA[<hola> & "cosas" ]]></texto>
```

### 3.5 Practica XML (mini)

TODO Alumno (crear un XML desde cero):

1. Crea un archivo `alumnos.xml` (nuevo).
2. Debe tener root `<alumnos>`.
3. Debe tener al menos 2 `<alumno>` con:
   - atributo `id`
   - hijos: `nombre`, `edad`

SOLUCION (ejemplo posible):

```xml
<?xml version="1.0"?>
<alumnos>
  <alumno id="A1">
    <nombre>Ana</nombre>
    <edad>19</edad>
  </alumno>
  <alumno id="A2">
    <nombre>Luis</nombre>
    <edad>20</edad>
  </alumno>
</alumnos>
```

---

## 4) UT2 - XPath: hacer preguntas al XML

### 4.1 Idea base

XPath es como un "buscador" de nodos dentro de un XML.

Ejemplos:

- `/biblioteca` (raiz)
- `/biblioteca/libros/libro` (todos los libros)
- `//libro` (cualquier libro, en cualquier parte)
- `//libro[@id='L1']` (libro con id L1)

### 4.2 Sintaxis esencial

- `/` ruta desde raiz
- `//` busca en cualquier nivel
- `@` atributo
- `[...]` filtro (predicado)
- `text()` texto dentro de un elemento

### 4.3 Nodos vs texto (detalle que cae en examen)

! Importante: `//libro/titulo` devuelve nodos `<titulo>...</titulo>`.
Si quieres solo el texto, usa `//libro/titulo/text()`.

Ejemplo:

```xpath
//libro/titulo
//libro/titulo/text()
```

### 4.4 Operadores y funciones utiles

- `and`, `or`, `not()`
- `contains(a,b)`, `starts-with(a,b)`
- `count(...)`, `sum(...)`, `string-length(...)`

Ejemplos:

```xpath
//libro[genero='Tecnologia' and paginas > 200]
//socio[contains(email,'@')]
count(//prestamo[estado='ABIERTO'])
sum(//libro/precio)
```

### 4.5 Ejercicios XPath (biblioteca.xml)

Abre `data/biblioteca.xml` y resuelve estas consultas:

Basico:

1. Todos los libros
2. Todos los titulos
3. Libro con id L3
4. Socios de Madrid
5. Prestamos abiertos

Intermedio:

6. Libros de Fantasia
7. Libros con paginas > 200 (devuelve titulos)
8. Libros disponibles = SI (devuelve titulos)
9. Socios sin telefono (pista: `not(telefono)` o `not(telefono/text())`)
10. Prestamos que tienen fechaDevolucion (cerrados por fecha)

Funciones:

11. Cuenta libros
12. Cuenta prestamos
13. Cuenta prestamos abiertos
14. Suma precios
15. Cuenta libros de Tecnologia

SOLUCIONES:

1. `//libro`
2. `//libro/titulo`
3. `//libro[@id='L3']`
4. `//socio[ciudad='Madrid']`
5. `//prestamo[estado='ABIERTO']`
6. `//libro[genero='Fantasia']`
7. `//libro[paginas > 200]/titulo`
8. `//libro[disponible='SI']/titulo`
9. `//socio[not(telefono)]`
10. `//prestamo[fechaDevolucion]`
11. `count(//libro)`
12. `count(//prestamo)`
13. `count(//prestamo[estado='ABIERTO'])`
14. `sum(//libro/precio)`
15. `count(//libro[genero='Tecnologia'])`

---

## 5) UT3 - XSD: validar estructura y reglas

### 5.1 Para que sirve XSD

Un XSD responde:

- que elementos existen
- en que orden
- cuantos pueden repetirse
- que tipos tienen (string, int, decimal, date)
- reglas (enumeraciones, patrones, rangos)

### 5.2 Conceptos clave (los que te van a salir)

- `xs:element`: define un elemento
- `xs:complexType`: define un elemento con hijos/atributos
- `xs:sequence`: orden de hijos
- `minOccurs` / `maxOccurs`: repeticion
- `xs:attribute`: atributo
- `xs:simpleType` + `xs:restriction`: reglas (enum, pattern)

### 5.2.1 Mini XSD desde cero (alumnos.xml)

Antes de meterte con un XSD grande, es mejor ver uno pequeno.

Ejemplo: XSD para este XML:

```xml
<?xml version="1.0"?>
<alumnos>
  <alumno id="A1">
    <nombre>Ana</nombre>
    <edad>19</edad>
  </alumno>
</alumnos>
```

XSD posible:

```xml
<?xml version="1.0"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="alumnos">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="alumno" maxOccurs="unbounded">
          <xs:complexType>
            <xs:sequence>
              <xs:element name="nombre" type="xs:string"/>
              <xs:element name="edad" type="xs:positiveInteger"/>
            </xs:sequence>
            <xs:attribute name="id" type="xs:string" use="required"/>
          </xs:complexType>
        </xs:element>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
</xs:schema>
```

! Importante: con `xs:sequence` el orden importa. Si pones `<edad>` antes que `<nombre>`, el validador puede fallar.

TODO Alumno (mini):

1. Haz que `edad` sea opcional usando `minOccurs="0"`.
2. Haz que `id` tenga patron `A[0-9]+` con un `xs:simpleType`.

### 5.3 Lee el XSD del curso (biblioteca.xsd)

Abre `data/biblioteca.xsd` y encuentra:

1. el tipo `SiNoType` (enum SI/NO)
2. el tipo `EstadoPrestamoType` (ABIERTO/CERRADO)
3. la definicion de `PrestamoType` (fechaDevolucion opcional)
4. `xs:key` y `xs:keyref` (integridad referencial)

### 5.4 Ejercicios XSD (practica mental)

1. Que pasa si escribes `<disponible>QUIZAS</disponible>`?
2. Que pasa si un prestamo apunta a `S999` o `L999`?
3. Que pasa si falta `<paginas>` en un libro?

SOLUCION:

1. falla por enumeracion
2. falla por keyref (si el validador procesa identity constraints)
3. falla si el elemento es obligatorio (minOccurs por defecto es 1)

### 5.5 Practica XSD (laboratorio)

TODO Alumno:

1. Abre `data/biblioteca_invalida.xml`.
2. Valida contra `data/biblioteca.xsd`.
3. Corrige el XML hasta que sea valido.

Pistas:

- arregla primero enumeraciones (SI/NO)
- luego arregla referencias (`S999` no existe, `L999` no existe)

### 5.6 Ampliacion XSD (biblioteca.xsd)

Estas tareas son tipicas de examen/practica de 1 DAM.

TODO Alumno (elige 2 o hazlas todas):

1. Restringe `genero` a una enumeracion: Fantasia, Tecnologia, Aventura, Drama.
2. Restringe `anio` para que sea entre 1900 y 2100.
3. Restringe `precio` para que sea >= 0.
4. Restringe `telefono` para que sean 9 digitos (solo si existe).

SOLUCION (pistas de implementacion):

1) Enumeracion genero (crear un tipo y usarlo en LibroType):

```xml
<xs:simpleType name="GeneroType">
  <xs:restriction base="xs:string">
    <xs:enumeration value="Fantasia"/>
    <xs:enumeration value="Tecnologia"/>
    <xs:enumeration value="Aventura"/>
    <xs:enumeration value="Drama"/>
  </xs:restriction>
</xs:simpleType>
```

Y luego, dentro de LibroType:

```xml
<xs:element name="genero" type="GeneroType"/>
```

2) Rango anio (simpleType con min/max):

```xml
<xs:simpleType name="AnioType">
  <xs:restriction base="xs:positiveInteger">
    <xs:minInclusive value="1900"/>
    <xs:maxInclusive value="2100"/>
  </xs:restriction>
</xs:simpleType>
```

Y en LibroType:

```xml
<xs:element name="anio" type="AnioType"/>
```

3) Precio >= 0 (cambia base a decimal restringida):

```xml
<xs:simpleType name="PrecioType">
  <xs:restriction base="xs:decimal">
    <xs:minInclusive value="0"/>
  </xs:restriction>
</xs:simpleType>
```

Y en el elemento precio (extension base):

```xml
<xs:extension base="PrecioType">
```

4) Telefono 9 digitos:

```xml
<xs:simpleType name="TelefonoType">
  <xs:restriction base="xs:string">
    <xs:pattern value="[0-9]{9}"/>
  </xs:restriction>
</xs:simpleType>
```

Y en SocioType:

```xml
<xs:element name="telefono" type="TelefonoType" minOccurs="0"/>
```

### 5.7 Errores tipicos XSD (para evitar perder puntos)

- Olvidar `xmlns:xs="http://www.w3.org/2001/XMLSchema"`
- Usar `sequence` y cambiar el orden en el XML (falla)
- Olvidar `maxOccurs="unbounded"` cuando hay listas
- Confundir elemento vs atributo (`xs:element` vs `xs:attribute`)
- No entender que `minOccurs` por defecto es 1 (obligatorio)

---

## 6) Proyecto final (entrega 1 DAM)

Objetivo: demostrar que dominas el flujo completo.

Entrega minima:

1. Un XML valido (parte de `data/biblioteca_invalida.xml` corregido)
2. 12 consultas XPath (escritas en un documento o en comentarios)

TODO Alumno (proyecto):

1. Corrige `data/biblioteca_invalida.xml` y guardalo como `biblioteca_corregida.xml`.
2. Anade un libro nuevo (id nuevo, genero, paginas, precio, disponible).
3. Anade un socio nuevo (id nuevo).
4. Anade un prestamo que apunte al socio y libro nuevos.
5. Valida que tu XML final es valido.

Consultas XPath obligatorias:

1. Titulos de libros de Tecnologia
2. Titulos de libros con paginas > 200
3. Socios de Madrid (nombres)
4. Prestamos abiertos
5. Cuenta libros
6. Cuenta prestamos
7. Cuenta prestamos abiertos
8. Suma precios
9. Libro por id (elige uno)
10. Socios sin telefono
11. Libros disponibles
12. Prestamos con fechaDevolucion

SOLUCIONES (plantillas):

- `//libro[genero='Tecnologia']/titulo`
- `//libro[paginas > 200]/titulo`
- `//socio[ciudad='Madrid']/nombre`
- `//prestamo[estado='ABIERTO']`
- `count(//libro)`
- `count(//prestamo)`
- `count(//prestamo[estado='ABIERTO'])`
- `sum(//libro/precio)`
- `//libro[@id='L1']` (ejemplo)
- `//socio[not(telefono)]`
- `//libro[disponible='SI']`
- `//prestamo[fechaDevolucion]`

---

## 7) Checklist de dominio (rapido)

Si puedes hacer esto sin copiar, vas bien:

- escribir un XML bien formado desde cero
- explicar diferencia bien formado vs valido
- validar un XML contra un XSD
- entender por que fallan enumeraciones y keyref
- resolver 10-15 consultas XPath con filtros y funciones
