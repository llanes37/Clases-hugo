# UT DTD - Document Type Definition (1 DAM)

DTD es el sistema mas antiguo para definir la estructura de un XML.
Aparece en el curriculum antes que XSD porque historicamente fue el primero.
Es mas limitado que XSD pero mas simple de leer y escribir.

Convencion de lectura:
- ! Importante: idea clave o alerta
- ? Nota: aclaracion util
- * Ejemplo: bloque demostrativo
- TODO Alumno: tarea a resolver
- SOLUCION: una posible respuesta

---

## 1) Que es un DTD y para que sirve

Un DTD (Document Type Definition) define:
- que elementos puede tener un XML
- en que orden pueden aparecer
- cuantas veces se pueden repetir
- que atributos tienen y de que tipo

! Importante: DTD NO define tipos de datos reales (no distingue entero de texto).
Si necesitas validar que `edad` es un numero, necesitas XSD.
Para estructuras simples, DTD es suficiente y mas facil de escribir.

### DTD vs XSD (resumen rapido)

| Caracteristica | DTD | XSD |
|---|---|---|
| Tipos de datos | No (todo es texto) | Si (int, date, decimal...) |
| Sintaxis | Propia (no XML) | XML |
| Expresividad | Basica | Alta |
| Edad | Antigua (SGML) | Moderna |
| Se sigue usando | Si (HTML, XHTML) | Si (la mayoria de proyectos) |

? Nota: en el mundo real hoy se usa XSD. DTD se estudia para entender documentacion antigua y el curriculo oficial.

---

## 2) DTD interno vs externo

### 2.1 DTD interno (dentro del propio XML)

El DTD va dentro de `<!DOCTYPE ... [ ... ]>` al inicio del documento:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE alumnos [
  <!ELEMENT alumnos (alumno+)>
  <!ELEMENT alumno (nombre, edad)>
  <!ELEMENT nombre (#PCDATA)>
  <!ELEMENT edad (#PCDATA)>
  <!ATTLIST alumno id ID #REQUIRED>
]>
<alumnos>
  <alumno id="A1">
    <nombre>Ana</nombre>
    <edad>19</edad>
  </alumno>
</alumnos>
```

### 2.2 DTD externo (en un archivo .dtd separado)

El XML referencia el archivo DTD con `SYSTEM` o `PUBLIC`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE alumnos SYSTEM "alumnos.dtd">
<alumnos>
  <alumno id="A1">
    <nombre>Ana</nombre>
    <edad>19</edad>
  </alumno>
</alumnos>
```

Y en el archivo `alumnos.dtd` (sin la linea `<!DOCTYPE>`):

```dtd
<!ELEMENT alumnos (alumno+)>
<!ELEMENT alumno (nombre, edad)>
<!ELEMENT nombre (#PCDATA)>
<!ELEMENT edad (#PCDATA)>
<!ATTLIST alumno id ID #REQUIRED>
```

? Nota: `SYSTEM` significa ruta local. `PUBLIC` se usa para DTDs estandar publicadas (como HTML 4.01 o XHTML).

---

## 3) Declaracion de elementos: `<!ELEMENT>`

Esta es la declaracion mas importante. Define que puede contener cada elemento.

### 3.1 Contenidos posibles

| Sintaxis | Significado |
|---|---|
| `(#PCDATA)` | Solo texto |
| `(hijo)` | Un hijo exacto |
| `(a, b, c)` | Secuencia: a luego b luego c |
| `(a \| b \| c)` | Alternativa: uno de ellos |
| `EMPTY` | Sin contenido |
| `ANY` | Cualquier cosa |

### 3.2 Cuantificadores (cuantas veces)

| Simbolo | Significado |
|---|---|
| (sin nada) | Exactamente 1 vez |
| `?` | 0 o 1 vez (opcional) |
| `+` | 1 o mas veces |
| `*` | 0 o mas veces |

* Ejemplos:

```dtd
<!ELEMENT alumnos (alumno+)>          <!-- al menos un alumno -->
<!ELEMENT alumno (nombre, apellido, edad?, email*)>
<!ELEMENT nombre (#PCDATA)>
<!ELEMENT apellido (#PCDATA)>
<!ELEMENT edad (#PCDATA)>             <!-- opcional -->
<!ELEMENT email (#PCDATA)>            <!-- puede haber varios -->
<!ELEMENT foto EMPTY>                 <!-- sin contenido, solo atributos -->
```

### 3.3 Contenido mixto (#PCDATA + hijos)

! Importante: si mezclas texto con hijos, la sintaxis cambia y los cuantificadores son obligatorios:

```dtd
<!ELEMENT descripcion (#PCDATA | enfasis | negrita)*>
```

Esto permite texto con etiquetas mezcladas (como en HTML).
En la practica esto es poco habitual en 1 DAM.

---

## 4) Declaracion de atributos: `<!ATTLIST>`

### 4.1 Estructura basica

```dtd
<!ATTLIST elemento atributo tipo valor_por_defecto>
```

Se pueden declarar varios atributos en un mismo `<!ATTLIST>`:

```dtd
<!ATTLIST alumno
  id       ID       #REQUIRED
  activo   CDATA    "SI"
  nivel    CDATA    #IMPLIED
>
```

### 4.2 Tipos de atributos

| Tipo | Descripcion |
|---|---|
| `CDATA` | Cualquier texto |
| `ID` | Identificador unico en el documento |
| `IDREF` | Referencia a un ID existente |
| `IDREFS` | Varios IDREF separados por espacios |
| `NMTOKEN` | Nombre XML valido (sin espacios) |
| `NMTOKENS` | Varios NMTOKEN |
| `NOTATION` | Nombre de una notacion declarada |
| `(a\|b\|c)` | Enumeracion de valores posibles |

! Importante: `ID` e `IDREF` son la forma que tiene DTD de hacer integridad referencial (similar a claves foraneas). Es limitado: un `IDREF` apunta a un `ID` pero el validador comprueba que existe.

* Ejemplo de ID / IDREF:

```dtd
<!ELEMENT biblioteca (libros, prestamos)>
<!ELEMENT libros (libro+)>
<!ELEMENT prestamos (prestamo*)>
<!ELEMENT libro (titulo)>
<!ELEMENT titulo (#PCDATA)>
<!ELEMENT prestamo EMPTY>

<!ATTLIST libro
  id  ID  #REQUIRED
>
<!ATTLIST prestamo
  libroRef  IDREF  #REQUIRED
>
```

XML valido:

```xml
<biblioteca>
  <libros>
    <libro id="L1"><titulo>Dune</titulo></libro>
  </libros>
  <prestamos>
    <prestamo libroRef="L1"/>
  </prestamos>
</biblioteca>
```

Si `libroRef="L999"` y no existe ningun `id="L999"`, el validador DTD lo detecta.

### 4.3 Valores por defecto de atributos

| Valor | Significado |
|---|---|
| `#REQUIRED` | Obligatorio |
| `#IMPLIED` | Opcional, sin valor por defecto |
| `#FIXED "valor"` | Siempre vale ese valor fijo |
| `"valor"` | Opcional, por defecto ese valor |

* Ejemplos:

```dtd
<!ATTLIST libro
  id        ID              #REQUIRED
  idioma    CDATA           "es"
  edicion   CDATA           #IMPLIED
  version   CDATA           #FIXED "1.0"
>
```

* Ejemplo de enumeracion:

```dtd
<!ATTLIST libro
  disponible  (SI|NO)  "SI"
>
```

---

## 5) Entidades: `<!ENTITY>`

Las entidades son atajos de texto o referencias a archivos externos.

### 5.1 Entidades internas (texto corto)

```dtd
<!ENTITY empresa "TecnoLlanes Academia">
<!ENTITY copyright "&#169; 2025 TecnoLlanes">
```

En el XML se usan con `&nombre;`:

```xml
<autor>&empresa;</autor>
<pie>&copyright;</pie>
```

### 5.2 Entidades predefinidas (siempre disponibles en XML)

Estas no necesitan declararse, vienen con XML:

| Entidad | Caracter |
|---|---|
| `&lt;` | `<` |
| `&gt;` | `>` |
| `&amp;` | `&` |
| `&quot;` | `"` |
| `&apos;` | `'` |

? Nota: en la practica de 1 DAM, las entidades custom son un extra. Lo mas importante es recordar las predefinidas.

### 5.3 Entidades externas (menos comun)

Permiten incluir contenido de otro archivo:

```dtd
<!ENTITY cabecera SYSTEM "cabecera.xml">
```

Y en el XML: `&cabecera;`

! Importante: las entidades externas son un riesgo de seguridad (XXE - XML External Entity). En produccion se deshabilitan. Para la clase solo necesitas saber que existen.

---

## 6) Ejercicios DTD (con soluciones)

### Ejercicio 1 - DTD basico interno

TODO Alumno:

Crea un XML con DTD interno que represente una tienda con productos.
Cada producto debe tener: nombre, precio, stock (opcional).
El atributo `id` debe ser obligatorio.

SOLUCION:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tienda [
  <!ELEMENT tienda (producto+)>
  <!ELEMENT producto (nombre, precio, stock?)>
  <!ELEMENT nombre (#PCDATA)>
  <!ELEMENT precio (#PCDATA)>
  <!ELEMENT stock (#PCDATA)>
  <!ATTLIST producto id ID #REQUIRED>
]>
<tienda>
  <producto id="P1">
    <nombre>Teclado</nombre>
    <precio>25.99</precio>
    <stock>10</stock>
  </producto>
  <producto id="P2">
    <nombre>Raton</nombre>
    <precio>12.50</precio>
  </producto>
</tienda>
```

---

### Ejercicio 2 - DTD externo

TODO Alumno:

1. Crea el archivo `alumnos.dtd` con la estructura de un sistema de alumnos.
   - Cada alumno tiene: nombre, apellido, email (opcional, puede haber varios), nota (0 o 1).
   - Atributos: `id` (ID, obligatorio), `curso` (enumeracion: "1DAM"|"2DAM"|"1DAW"|"2DAW").
2. Crea `alumnos.xml` que referencie el DTD externo y tenga 3 alumnos validos.

SOLUCION (`alumnos.dtd`):

```dtd
<!ELEMENT alumnos (alumno+)>
<!ELEMENT alumno (nombre, apellido, email*, nota?)>
<!ELEMENT nombre (#PCDATA)>
<!ELEMENT apellido (#PCDATA)>
<!ELEMENT email (#PCDATA)>
<!ELEMENT nota (#PCDATA)>
<!ATTLIST alumno
  id    ID                            #REQUIRED
  curso (1DAM|2DAM|1DAW|2DAW)        #REQUIRED
>
```

SOLUCION (`alumnos.xml`):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE alumnos SYSTEM "alumnos.dtd">
<alumnos>
  <alumno id="A1" curso="1DAM">
    <nombre>Ana</nombre>
    <apellido>Garcia</apellido>
    <email>ana@ejemplo.com</email>
    <nota>8.5</nota>
  </alumno>
  <alumno id="A2" curso="2DAM">
    <nombre>Luis</nombre>
    <apellido>Perez</apellido>
  </alumno>
  <alumno id="A3" curso="1DAW">
    <nombre>Maria</nombre>
    <apellido>Lopez</apellido>
    <email>maria@a.com</email>
    <email>maria.lopez@b.com</email>
  </alumno>
</alumnos>
```

---

### Ejercicio 3 - IDREF (integridad referencial)

TODO Alumno:

Crea un DTD para una biblioteca simplificada:
- `libros`: contiene varios `libro` con `id` (ID).
- `prestamos`: contiene varios `prestamo` con `libroId` (IDREF) y `socioId` (IDREF).
- `socios`: contiene varios `socio` con `id` (ID).
- Crea un XML valido y uno invalido (con un IDREF que no existe).

SOLUCION (DTD interno):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE biblioteca [
  <!ELEMENT biblioteca (libros, socios, prestamos)>
  <!ELEMENT libros (libro+)>
  <!ELEMENT socios (socio+)>
  <!ELEMENT prestamos (prestamo*)>
  <!ELEMENT libro (titulo)>
  <!ELEMENT titulo (#PCDATA)>
  <!ELEMENT socio (nombre)>
  <!ELEMENT nombre (#PCDATA)>
  <!ELEMENT prestamo EMPTY>
  <!ATTLIST libro   id       ID     #REQUIRED>
  <!ATTLIST socio   id       ID     #REQUIRED>
  <!ATTLIST prestamo
    libroId  IDREF  #REQUIRED
    socioId  IDREF  #REQUIRED
  >
]>
<biblioteca>
  <libros>
    <libro id="L1"><titulo>Dune</titulo></libro>
    <libro id="L2"><titulo>1984</titulo></libro>
  </libros>
  <socios>
    <socio id="S1"><nombre>Ana</nombre></socio>
  </socios>
  <prestamos>
    <prestamo libroId="L1" socioId="S1"/>
  </prestamos>
</biblioteca>
```

XML invalido (para probar):

```xml
<!-- Cambia libroId="L999" y el validador debe fallar porque L999 no existe -->
<prestamo libroId="L999" socioId="S1"/>
```

---

### Ejercicio 4 - Errores tipicos (detecta el error)

TODO Alumno: encuentra el error en cada fragmento.

**Fragmento A:**
```dtd
<!ELEMENT alumnos alumno+>
```
Error: falta envolver en parentesis. Correcto: `<!ELEMENT alumnos (alumno+)>`

**Fragmento B:**
```dtd
<!ELEMENT alumno (nombre, apellido)>
<!ELEMENT alumno (email)>
```
Error: elemento `alumno` declarado dos veces. DTD no permite redeclarar.

**Fragmento C:**
```xml
<alumno id="A1" id="A2">
```
Error: el mismo atributo no puede aparecer dos veces en el mismo elemento.

**Fragmento D:**
```dtd
<!ATTLIST alumno disponible (si|no) #REQUIRED>
```
En el XML:
```xml
<alumno disponible="SI">
```
Error: la enumeracion es `si|no` (minusculas), pero el XML usa `SI`. XML y DTD son case-sensitive.

---

## 7) Validacion con VS Code

Para validar un XML con DTD en VS Code:

1. Instala la extension `XML` de Red Hat (la misma que para XSD).
2. Con el DTD referenciado en el XML (`<!DOCTYPE ... SYSTEM "archivo.dtd">`), VS Code valida automaticamente al guardar.
3. Los errores aparecen subrayados en rojo.

? Nota: si el DTD es interno (todo en el mismo archivo), tambien lo valida.

---

## 8) Comparacion DTD vs XSD (para el examen)

| Pregunta tipica | DTD | XSD |
|---|---|---|
| Como restringo tipo numerico? | No se puede | `xs:integer`, `xs:decimal`... |
| Como hago obligatorio un elemento? | Sin cuantificador (exactamente 1) | `minOccurs="1"` |
| Como hago opcional un elemento? | `?` | `minOccurs="0"` |
| Como permito varios? | `+` o `*` | `maxOccurs="unbounded"` |
| Como declaro enumeracion en elemento? | No directamente (si en atributo) | `xs:enumeration` |
| Como declaro enumeracion en atributo? | `(val1\|val2)` | `xs:enumeration` |
| Como hago referencia a otro elemento? | `IDREF` | `xs:key` + `xs:keyref` |
| Esta en formato XML? | No | Si |

---

## 9) Checklist DTD (rapido)

Si puedes hacer esto sin copiar, vas bien:

- escribir un DTD interno desde cero
- escribir un DTD externo y referenciarlo desde el XML
- usar los cuantificadores `?`, `+`, `*` correctamente
- declarar atributos con `ID`, `IDREF`, `CDATA` y enumeracion
- explicar diferencia DTD vs XSD con al menos 2 ejemplos concretos
- detectar errores tipicos en fragmentos DTD/XML
