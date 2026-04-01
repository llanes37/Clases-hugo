/*
 * ******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 EJERCICIOS DE REPASO / RECUPERACIÓN — POO COMPLETA (1º DAM)
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 * ******************************************************************************************
 *
 *  📋 CONTENIDO:
 *  Este archivo contiene 5 EJERCICIOS COMPLETOS de tipo examen/recuperación
 *  que cubren todos los conceptos de POO vistos en el curso.
 *
 *  Cada ejercicio incluye:
 *  ✅ Enunciado completo con especificaciones detalladas.
 *  ✅ Clases con atributos, constructores y métodos especificados.
 *  ✅ Relaciones entre clases (composición, agregación).
 *  ✅ equals/hashCode, toString, colecciones (ArrayList, HashSet, HashMap).
 *  ✅ Clase gestora con lógica de negocio avanzada.
 *
 *  🎯 NIVEL: Examen / Recuperación 1º DAM
 *  📖 CONCEPTOS: Clases, objetos, encapsulación, constructores sobrecargados,
 *               equals/hashCode, toString, ArrayList, HashSet, HashMap,
 *               composición, agregación, métodos de búsqueda y filtrado.
 *
 *  💡 INSTRUCCIONES PARA EL ALUMNO:
 *  1. Lee el enunciado completo ANTES de empezar a programar.
 *  2. Crea cada clase en un archivo .java separado o dentro de este.
 *  3. Implementa TODOS los métodos indicados.
 *  4. Crea un main() que pruebe cada funcionalidad.
 *  5. Los enunciados están en los comentarios — el código lo escribes TÚ.
 *
 *  🚀 ¡Ánimo! Si dominas estos ejercicios, dominas la POO.
 * ******************************************************************************************
 */

public class UT5_POO_Ejercicios_Recuperacion {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  📋 EJERCICIOS DE POO — RECUPERACIÓN 1º DAM             ║");
        System.out.println("║  Lee los enunciados en los comentarios y ¡programalos!   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Este archivo contiene 5 ejercicios tipo examen.");
        System.out.println("Lee los comentarios con los enunciados y programa las clases.");
        System.out.println();
        System.out.println("📌 Ejercicio 1: Hospital (Paciente + Médico + GestionHospital)");
        System.out.println("📌 Ejercicio 2: Biblioteca (Libro + Socio + GestionBiblioteca)");
        System.out.println("📌 Ejercicio 3: Tienda Online (Producto + Cliente + Pedido + GestionTienda)");
        System.out.println("📌 Ejercicio 4: Flota de Vehículos (Vehiculo + Conductor + GestionFlota)");
        System.out.println("📌 Ejercicio 5: Plataforma de Streaming (Contenido + Suscriptor + GestionPlataforma)");

        // ! ═══════════════════════════════════════════════════════════════
        // ! IMPLEMENTA AQUÍ TU CÓDIGO PARA PROBAR CADA EJERCICIO
        // ! ═══════════════════════════════════════════════════════════════
    }
}

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║ ║
// ║ 📋 EJERCICIO 1: SISTEMA DE GESTIÓN HOSPITALARIA ║
// ║ ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Paciente
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Paciente con los siguientes atributos:
// ?
// ? - dni (String): DNI del paciente. Formato: 8 números + 1 letra.
// ? - nombre (String): Nombre completo del paciente.
// ? - edad (int): Edad del paciente. Debe ser mayor que 0.
// ? - grupoSanguineo (String): Grupo sanguíneo (A+, A-, B+, B-, AB+, AB-, O+,
// O-).
// ? - alergias (ArrayList<String>): Lista de alergias del paciente.
// ? - ingresado (boolean): Indica si el paciente está ingresado o no.
// ?
// ? CONSTRUCTORES:
// ? - Constructor con parámetros: dni, nombre, edad, grupoSanguineo.
// ? → Un paciente se crea SIN alergias y NO ingresado.
// ? - Constructor sobrecargado: dni y nombre solamente.
// ? → Edad = 0, grupoSanguineo = "Desconocido".
// ?
// ? MÉTODOS:
// ? - Getters para todos los atributos.
// ? - NO hay setters (datos inmutables tras creación).
// ? - addAlergia(String alergia): Añade una alergia a la lista.
// ? → Devuelve boolean: false si ya existía esa alergia.
// ? - removeAlergia(String alergia): Elimina una alergia.
// ? → Devuelve boolean: false si no existía.
// ? - ingresar(): Cambia el estado a ingresado.
// ? → Devuelve boolean: false si ya estaba ingresado.
// ? - darAlta(): Cambia el estado a no ingresado.
// ? → Devuelve boolean: false si ya estaba dado de alta.
// ? - tieneAlergia(String alergia): Devuelve true si tiene esa alergia.
// ? - equals y hashCode: Dos pacientes son iguales si tienen el mismo DNI.
// ? - toString: Muestra todos los datos del paciente.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Medico
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Medico con los siguientes atributos:
// ?
// ? - numeroColegiado (String): Número de colegiado del médico.
// ? - nombre (String): Nombre completo.
// ? - especialidad (String): Cardiología, Pediatría, Urgencias, etc.
// ? - pacientesAsignados (HashSet<Paciente>): Pacientes asignados al médico.
// ? - maxPacientes (int): Número máximo de pacientes que puede atender.
// ? → Es una constante (final) que se define al crear el médico.
// ?
// ? CONSTRUCTORES:
// ? - Constructor con parámetros: numeroColegiado, nombre, especialidad,
// maxPacientes.
// ? → Un médico se crea SIN pacientes asignados.
// ?
// ? MÉTODOS:
// ? - Getters para todos los atributos.
// ? → getPacientesAsignados() devuelve un String con los datos de sus
// pacientes,
// ? NO devuelve el HashSet directamente.
// ? - asignarPaciente(Paciente p): Asigna un paciente al médico.
// ? → Devuelve boolean: false si ya tiene el máximo de pacientes
// ? o si el paciente ya estaba asignado.
// ? - desasignarPaciente(Paciente p): Quita un paciente del médico.
// ? → Devuelve boolean: false si el paciente no estaba asignado.
// ? - tienePaciente(Paciente p): Devuelve true si el paciente está asignado.
// ? - getNumeroPacientes(): Devuelve cuántos pacientes tiene asignados.
// ? - equals y hashCode: Dos médicos son iguales si tienen el mismo
// numeroColegiado.
// ? - toString: Muestra datos del médico y número de pacientes asignados.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE GestionHospital
// * ════════════════════════════════════════════════════════════════
//
// ? Crea la clase GestionHospital que gestiona el hospital mediante un
// ? HashMap<Medico, List<Paciente>> donde la clave es un Médico y el valor
// ? es la lista de pacientes que tiene ingresados.
// ?
// ? MÉTODOS:
// ? - addMedico(Medico m): Añade un médico al hospital.
// ? → Devuelve boolean: false si ya existía (no borrar sus pacientes).
// ? - ingresarPaciente(Medico m, Paciente p): Ingresa un paciente con un
// médico.
// ? → Devuelve boolean: false si el médico no existe o el paciente
// ? ya estaba ingresado con ese médico.
// ? - darAlta(Paciente p): Busca al paciente en todo el hospital y le da el
// alta.
// ? → Devuelve el nombre del médico que lo tenía, o null si no se encontró.
// ? - getPacientesMedico(Medico m): Devuelve lista de pacientes de un médico.
// ? - buscarPaciente(String dni): Busca un paciente por DNI en todo el
// hospital.
// ? → Devuelve el Paciente o null si no existe.
// ? - medicoConMasPacientes(): Devuelve el médico que tiene más pacientes.
// ? - pacientesConAlergia(String alergia): Devuelve un HashSet con todos los
// ? pacientes del hospital que tienen esa alergia.
// ? - totalPacientesIngresados(): Devuelve el número total de pacientes.

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║ ║
// ║ 📋 EJERCICIO 2: SISTEMA DE GESTIÓN DE BIBLIOTECA ║
// ║ ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Libro
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Libro con los siguientes atributos:
// ?
// ? - isbn (String): Código ISBN del libro (identificador único).
// ? - titulo (String): Título del libro.
// ? - autor (String): Nombre del autor.
// ? - genero (String): Género literario (Novela, Ciencia Ficción, etc.).
// ? - paginas (int): Número de páginas.
// ? - prestado (boolean): Indica si está prestado o no.
// ? - vecesLeido (int): Contador de cuántas veces se ha prestado.
// ?
// ? CONSTRUCTORES:
// ? - Constructor con parámetros: isbn, titulo, autor, genero, paginas.
// ? → Un libro se crea NO prestado y con vecesLeido = 0.
// ? - Constructor sobrecargado: isbn, titulo, autor.
// ? → genero = "General", paginas = 0.
// ?
// ? MÉTODOS:
// ? - Getters para todos los atributos.
// ? - prestar(): Cambia a prestado e incrementa vecesLeido.
// ? → Devuelve boolean: false si ya estaba prestado.
// ? - devolver(): Cambia a no prestado.
// ? → Devuelve boolean: false si no estaba prestado.
// ? - esPopular(): Devuelve true si vecesLeido >= 5.
// ? - equals y hashCode: Dos libros son iguales si tienen el mismo ISBN.
// ? - toString: Muestra todos los datos del libro y su estado.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Socio
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Socio con los siguientes atributos:
// ?
// ? - numeroSocio (int): Número identificador del socio.
// ? - nombre (String): Nombre completo.
// ? - email (String): Correo electrónico.
// ? - librosPrestados (ArrayList<Libro>): Libros que tiene actualmente.
// ? - maxLibros (int): Máximo de libros simultáneos (constante, final).
// ?
// ? CONSTRUCTORES:
// ? - Constructor con parámetros: numeroSocio, nombre, email, maxLibros.
// ? → Se crea SIN libros prestados.
// ?
// ? MÉTODOS:
// ? - Getters para todos los atributos.
// ? - prestarLibro(Libro libro): Añade un libro a los prestados del socio.
// ? → Devuelve boolean: false si ya tiene el máximo o si el libro
// ? ya estaba en su lista.
// ? - devolverLibro(Libro libro): Quita un libro de los prestados.
// ? → Devuelve boolean: false si no tenía ese libro.
// ? - tieneLibro(Libro libro): Devuelve true si tiene ese libro prestado.
// ? - getNumLibrosPrestados(): Devuelve cuántos libros tiene.
// ? - puedePrestar(): Devuelve true si aún puede coger más libros.
// ? - equals y hashCode: Dos socios son iguales si tienen el mismo numeroSocio.
// ? - toString: Muestra datos del socio y cuántos libros tiene.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE GestionBiblioteca
// * ════════════════════════════════════════════════════════════════
//
// ? Gestiona la biblioteca mediante:
// ? - Un HashMap<Socio, ArrayList<Libro>> (socio → sus libros prestados).
// ? - Un HashSet<Libro> con TODOS los libros del catálogo.
// ?
// ? MÉTODOS:
// ? - addLibroCatalogo(Libro libro): Añade un libro al catálogo.
// ? → Devuelve boolean.
// ? - addSocio(Socio socio): Registra un socio nuevo.
// ? → Devuelve boolean: false si ya existía.
// ? - prestarLibro(Socio socio, Libro libro): Presta un libro a un socio.
// ? → Devuelve boolean: false si socio no existe, libro no está en catálogo,
// ? libro ya está prestado, o socio tiene el máximo.
// ? - devolverLibro(Socio socio, Libro libro): Devuelve el libro.
// ? → Devuelve boolean.
// ? - librosDisponibles(): Devuelve un HashSet con los libros NO prestados.
// ? - librosPorGenero(String genero): Devuelve ArrayList con libros de ese
// género.
// ? - socioConMasLibros(): Devuelve el socio con más libros prestados.
// ? - librosPopulares(): Devuelve HashSet de libros con vecesLeido >= 5.
// ? - buscarLibroPorTitulo(String titulo): Busca parcial (contains,
// ignoreCase).
// ? → Devuelve ArrayList de libros que coincidan.

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║ ║
// ║ 📋 EJERCICIO 3: TIENDA ONLINE ║
// ║ ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Producto
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Producto con los siguientes atributos:
// ?
// ? - referencia (String): Código de referencia único (ej: "PRD-001").
// ? - nombre (String): Nombre del producto.
// ? - precio (double): Precio en euros. Debe ser > 0.
// ? - stock (int): Unidades disponibles. Debe ser >= 0.
// ? - categoria (String): Electrónica, Ropa, Hogar, Alimentación, etc.
// ? - descuento (double): Porcentaje de descuento (0-100). Por defecto 0.
// ?
// ? CONSTRUCTORES:
// ? - Constructor completo: referencia, nombre, precio, stock, categoria.
// ? → descuento = 0.
// ? - Constructor sobrecargado: referencia, nombre, precio.
// ? → stock = 0, categoria = "Sin categoría".
// ?
// ? MÉTODOS:
// ? - Getters para todos los atributos.
// ? - setDescuento(double porcentaje): Aplica descuento (validar 0-100).
// ? - getPrecioFinal(): Devuelve precio con descuento aplicado.
// ? - vender(int cantidad): Reduce stock.
// ? → Devuelve boolean: false si no hay suficiente stock.
// ? - reponer(int cantidad): Aumenta stock (validar cantidad > 0).
// ? - hayStock(): Devuelve true si stock > 0.
// ? - equals y hashCode: Por referencia.
// ? - toString: Muestra datos con precio final formateado.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Cliente
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Cliente con los siguientes atributos:
// ?
// ? - idCliente (int): Identificador único.
// ? - nombre (String): Nombre completo.
// ? - email (String): Correo electrónico.
// ? - direccion (String): Dirección de envío.
// ? - vip (boolean): Si es cliente VIP (15% descuento extra).
// ? - comprasRealizadas (int): Contador de compras. Se incrementa con cada
// pedido.
// ?
// ? CONSTRUCTORES:
// ? - Con parámetros: idCliente, nombre, email, direccion.
// ? → vip = false, comprasRealizadas = 0.
// ? - Sobrecargado con vip: idCliente, nombre, email, direccion, vip.
// ?
// ? MÉTODOS:
// ? - Getters para todos.
// ? - getDescuentoCliente(): Devuelve 15.0 si es VIP, 0.0 si no.
// ? - incrementarCompras(): Suma 1 al contador.
// ? → Si llega a 10 compras, automáticamente se convierte en VIP.
// ? - equals y hashCode: Por idCliente.
// ? - toString: Muestra datos y si es VIP.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Pedido
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Pedido con los siguientes atributos:
// ?
// ? - numeroPedido (int): Se genera automáticamente con un contador static.
// ? - cliente (Cliente): El cliente que hace el pedido (COMPOSICIÓN).
// ? - productos (HashMap<Producto, Integer>): Producto → cantidad pedida.
// ? - fechaPedido (String): Fecha del pedido en formato "dd/MM/yyyy".
// ? - entregado (boolean): Si el pedido ha sido entregado.
// ?
// ? CONSTRUCTORES:
// ? - Con parámetros: cliente, fechaPedido.
// ? → numeroPedido se genera automáticamente.
// ? → Se crea SIN productos y NO entregado.
// ?
// ? MÉTODOS:
// ? - addProducto(Producto p, int cantidad): Añade producto al pedido.
// ? → Si el producto ya estaba, SUMA la cantidad.
// ? → Devuelve boolean: false si no hay stock suficiente.
// ? → Reduce el stock del producto al añadir.
// ? - removeProducto(Producto p): Elimina producto del pedido.
// ? → Devuelve la cantidad devuelta al stock, o -1 si no existía.
// ? - calcularSubtotal(): Suma precio × cantidad de cada producto.
// ? - calcularTotal(): Subtotal menos descuento VIP del cliente.
// ? - marcarEntregado(): Cambia a entregado e incrementa compras del cliente.
// ? - getNumProductos(): Devuelve cuántos productos diferentes hay.
// ? - toString: Muestra resumen completo del pedido con total.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE GestionTienda
// * ════════════════════════════════════════════════════════════════
//
// ? Gestiona mediante:
// ? - Un HashSet<Producto> con el catálogo.
// ? - Un ArrayList<Cliente> con los clientes registrados.
// ? - Un ArrayList<Pedido> con todos los pedidos.
// ?
// ? MÉTODOS:
// ? - addProducto(Producto p): Añade al catálogo. Devuelve boolean.
// ? - addCliente(Cliente c): Registra cliente. Devuelve boolean.
// ? - crearPedido(Cliente c, String fecha): Crea un nuevo pedido.
// ? → Devuelve el Pedido creado o null si el cliente no existe.
// ? - productosPorCategoria(String categoria): Devuelve ArrayList filtrado.
// ? - clientesVIP(): Devuelve ArrayList de clientes VIP.
// ? - pedidosPendientes(): Devuelve ArrayList de pedidos no entregados.
// ? - facturacionTotal(): Suma el total de todos los pedidos entregados.
// ? - productoSinStock(): Devuelve HashSet de productos con stock = 0.
// ? - clienteMasGastador(): Devuelve el cliente con más compras realizadas.

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║ ║
// ║ 📋 EJERCICIO 4: FLOTA DE VEHÍCULOS ║
// ║ ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Vehiculo
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Vehiculo con los siguientes atributos:
// ?
// ? - matricula (String): Matrícula del vehículo (identificador único).
// ? - marca (String): Marca del vehículo.
// ? - modelo (String): Modelo del vehículo.
// ? - kilometraje (double): Kilómetros actuales.
// ? - tipoCombustible (String): "Gasolina", "Diésel", "Eléctrico", "Híbrido".
// ? - consumo (double): Litros/100km (o kWh/100km si eléctrico).
// ? - disponible (boolean): Si está disponible para asignarse.
// ? - revisiones (ArrayList<String>): Historial de revisiones (fecha +
// descripción).
// ?
// ? CONSTRUCTORES:
// ? - Completo: matricula, marca, modelo, tipoCombustible, consumo.
// ? → kilometraje = 0, disponible = true, sin revisiones.
// ?
// ? MÉTODOS:
// ? - Getters para todos.
// ? - conducir(double km): Añade kilómetros al kilometraje.
// ? → Devuelve double: el combustible consumido (km * consumo / 100).
// ? → Solo se puede conducir si está disponible.
// ? - addRevision(String descripcion): Añade una revisión al historial.
// ? - necesitaRevision(): Devuelve true si tiene más de 15000 km
// ? desde la última revisión (o nunca fue revisado).
// ? - asignar(): Cambia disponible a false. Devuelve boolean.
// ? - liberar(): Cambia disponible a true. Devuelve boolean.
// ? - equals y hashCode: Por matrícula.
// ? - toString: Muestra datos y estado.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Conductor
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Conductor con los siguientes atributos:
// ?
// ? - dni (String): DNI del conductor.
// ? - nombre (String): Nombre completo.
// ? - licencia (String): Tipo de licencia (B, C, D, etc.).
// ? - kmTotales (double): Kilómetros totales conducidos.
// ? - vehiculoAsignado (Vehiculo): El vehículo actual (puede ser null).
// ?
// ? CONSTRUCTORES:
// ? - Con parámetros: dni, nombre, licencia.
// ? → kmTotales = 0, vehiculoAsignado = null.
// ?
// ? MÉTODOS:
// ? - Getters para todos.
// ? - asignarVehiculo(Vehiculo v): Asigna un vehículo al conductor.
// ? → Devuelve boolean: false si el conductor ya tiene vehículo
// ? o si el vehículo no está disponible.
// ? - liberarVehiculo(): Libera el vehículo actual.
// ? → Devuelve el Vehiculo liberado, o null si no tenía.
// ? - realizarViaje(double km): Conduce km con su vehículo asignado.
// ? → Devuelve double: combustible consumido, o -1 si no tiene vehículo.
// ? → Actualiza kmTotales del conductor.
// ? - tieneVehiculo(): Devuelve true si tiene un vehículo asignado.
// ? - equals y hashCode: Por DNI.
// ? - toString: Muestra datos y vehículo asignado (si tiene).

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE GestionFlota
// * ════════════════════════════════════════════════════════════════
//
// ? Gestiona mediante:
// ? - Un HashSet<Vehiculo> con toda la flota.
// ? - Un HashMap<Conductor, Vehiculo> con las asignaciones activas.
// ?
// ? MÉTODOS:
// ? - addVehiculo(Vehiculo v): Añade vehículo a la flota. Devuelve boolean.
// ? - addConductor(Conductor c): Registra conductor. Devuelve boolean.
// ? - asignarVehiculo(Conductor c, Vehiculo v): Asigna vehículo a conductor.
// ? → Devuelve boolean.
// ? - liberarVehiculo(Conductor c): Libera el vehículo del conductor.
// ? → Devuelve el Vehiculo liberado o null.
// ? - vehiculosDisponibles(): Devuelve HashSet de vehículos sin conductor.
// ? - vehiculosNecesitanRevision(): Devuelve HashSet de vehículos que
// ? necesitan revisión.
// ? - conductorMasKm(): Devuelve el conductor con más km totales.
// ? - consumoTotalFlota(): Suma el consumo estimado de toda la flota
// ? basándose en su kilometraje actual.
// ? - vehiculosPorCombustible(String tipo): Filtra por tipo de combustible.

// ╔═══════════════════════════════════════════════════════════════════════════╗
// ║ ║
// ║ 📋 EJERCICIO 5: PLATAFORMA DE STREAMING ║
// ║ ║
// ╚═══════════════════════════════════════════════════════════════════════════╝

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Contenido
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Contenido con los siguientes atributos:
// ?
// ? - idContenido (String): Identificador único (ej: "MOV-001", "SER-005").
// ? - titulo (String): Título del contenido.
// ? - tipo (String): "Película" o "Serie".
// ? - genero (String): Acción, Comedia, Drama, Ciencia Ficción, etc.
// ? - duracion (int): Duración en minutos (películas) o nº episodios (series).
// ? - puntuacion (double[]): Array de puntuaciones de usuarios (0.0 a 10.0).
// ? → Se implementa como un array de tamaño fijo (ej: 100),
// ? con un contador de cuántas puntuaciones se han dado.
// ? - contadorPuntuaciones (int): Cuántas puntuaciones se han registrado.
// ?
// ? CONSTRUCTORES:
// ? - Completo: idContenido, titulo, tipo, genero, duracion.
// ? → Se crea SIN puntuaciones (array vacío de 100 posiciones).
// ?
// ? MÉTODOS:
// ? - Getters para todos.
// ? - puntuar(double nota): Añade una puntuación al array.
// ? → Devuelve boolean: false si la nota no está entre 0-10
// ? o si se ha alcanzado el máximo de puntuaciones.
// ? - getPuntuacionMedia(): Calcula y devuelve la media de las puntuaciones.
// ? → Devuelve 0.0 si no hay ninguna puntuación.
// ? - esBienValorado(): Devuelve true si la media es >= 7.0.
// ? - equals y hashCode: Por idContenido.
// ? - toString: Muestra datos con puntuación media formateada.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE Suscriptor
// * ════════════════════════════════════════════════════════════════
//
// ? Implementa la clase Suscriptor con los siguientes atributos:
// ?
// ? - username (String): Nombre de usuario único.
// ? - nombre (String): Nombre real.
// ? - plan (String): "Básico", "Estándar", "Premium".
// ? - listaReproduccion (ArrayList<Contenido>): Contenidos en "Mi Lista".
// ? - historial (HashSet<Contenido>): Contenidos ya vistos.
// ?
// ? CONSTRUCTORES:
// ? - Con parámetros: username, nombre, plan.
// ? → Se crea SIN lista de reproducción y SIN historial.
// ?
// ? MÉTODOS:
// ? - Getters para todos.
// ? - addALista(Contenido c): Añade a la lista de reproducción.
// ? → Devuelve boolean: false si ya estaba en la lista.
// ? - removeDeList(Contenido c): Quita de la lista.
// ? → Devuelve boolean.
// ? - marcarVisto(Contenido c): Añade al historial de vistos.
// ? → Lo quita automáticamente de la lista de reproducción si estaba.
// ? → Devuelve boolean: false si ya lo había visto.
// ? - haVisto(Contenido c): Devuelve true si está en el historial.
// ? - getTotalVistos(): Devuelve el número de contenidos vistos.
// ? - puedeVerContenido(): Devuelve true según el plan:
// ? → Básico: máximo 1 pantalla (simplificado: siempre true).
// ? → Estándar: true.
// ? → Premium: true.
// ? - equals y hashCode: Por username.
// ? - toString: Muestra datos, plan y nº de contenidos vistos.

// * ════════════════════════════════════════════════════════════════
// * 📖 ENUNCIADO: CLASE GestionPlataforma
// * ════════════════════════════════════════════════════════════════
//
// ? Gestiona mediante:
// ? - Un HashSet<Contenido> con todo el catálogo.
// ? - Un HashMap<Suscriptor, HashSet<Contenido>> con lo que ha visto cada uno.
// ?
// ? MÉTODOS:
// ? - addContenido(Contenido c): Añade al catálogo. Devuelve boolean.
// ? - addSuscriptor(Suscriptor s): Registra suscriptor. Devuelve boolean.
// ? - puntuarContenido(Suscriptor s, Contenido c, double nota):
// ? → Solo puede puntuar si el suscriptor ha visto ese contenido.
// ? → Devuelve boolean.
// ? - topValorados(int n): Devuelve los n contenidos con mejor puntuación
// media.
// ? → Devuelve ArrayList ordenado de mayor a menor media.
// ? - contenidoPorGenero(String genero): Filtra por género.
// ? - suscriptorMasActivo(): Devuelve el suscriptor que más contenidos ha
// visto.
// ? - contenidoNoVistoPor(Suscriptor s): Devuelve HashSet de contenidos
// ? del catálogo que este suscriptor NO ha visto aún.
// ? - peliculasVsSeries(): Devuelve un String con:
// ? → "Películas: X | Series: Y | Total: Z"
// ? - eliminarContenidoMalValorado(double umbral): Elimina del catálogo
// ? todos los contenidos con media < umbral.
// ? → Devuelve HashSet con los eliminados.

// * ════════════════════════════════════════════════════════════════
// * 📋 RESUMEN DE CONCEPTOS POR EJERCICIO
// * ════════════════════════════════════════════════════════════════
//
// ? ┌──────────────────────────────────────────────────────────────────┐
// ? │ EJERCICIO │ CLASES │ CONCEPTOS CLAVE │
// ? ├───────────┼───────────────┼────────────────────────────────────│
// ? │ 1. Hosp. │ 3 clases │ HashSet, HashMap, búsqueda por DNI │
// ? │ 2. Biblio │ 3 clases │ HashSet+HashMap, filtrado, búsqueda│
// ? │ 3. Tienda │ 4 clases │ HashMap<Prod,Int>, static, VIP │
// ? │ 4. Flota │ 3 clases │ Composición, null, cálculos │
// ? │ 5. Stream │ 3 clases │ Array de puntuaciones, top-N │
// ? └──────────────────────────────────────────────────────────────────┘
//
// ! CONSEJO PARA EL ALUMNO:
// ! 1. Empieza SIEMPRE por las clases simples (Paciente, Libro, Producto...).
// ! 2. Luego la clase intermedia (Medico, Socio, Pedido...).
// ! 3. Al final la clase gestora (GestionHospital, GestionBiblioteca...).
// ! 4. Prueba CADA método en el main() antes de pasar al siguiente.
// ! 5. Haz equals/hashCode ANTES de usar HashSet/HashMap.
