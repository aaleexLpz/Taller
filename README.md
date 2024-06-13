# Taller
## Descripción

Este proyecto es un sistema de gestión para un taller de automóviles, desarrollado con **Spring Boot** y **Thymeleaf**. Permite gestionar clientes, sus coches, y las intervenciones realizadas en los mismos, ofreciendo funcionalidades como agregar, modificar, eliminar y listar clientes, coches e intervenciones. Aunque en el caso de las intervenciones, no se permite modificarlas, ya que, por lógica de negocio se cree que es prescindible.

## Características

- Gestión de clientes: agregar, modificar, eliminar y listar clientes.
- Gestión de coches: agregar, modificar, eliminar y listar coches.
- Gestión de intervenciones: agregar, eliminar y listar intervenciones.
- Asociar coches a clientes.
- Asociar intervenciones a coches.
- Interfaz web para la administración del sistema.

## Tecnologías Utilizadas
- Java 11+
- Spring Boot
- Spring MVC
- Spring Security
- JDBC
- MySQL
- Thymeleaf
- Maven

## Estructura del Proyecto
El proyecto está organizado en los siguientes paquetes:

- `controller`: Contiene los controladores de Spring MVC.
- `service`: Contiene la lógica de negocio.
- `repository`: Contiene las clases de acceso a datos utilizando JDBC.
- `dto`: Contiene los Data Transfer Objects (DTOs) para transferir datos entre capas.
- `model`: Contiene las entidades del dominio.

## Endpoints
## Información General
- GET /quienesSomos: Muestra la página de información sobre nosotros.
- GET /contacto: Muestra la página de contacto.
- GET /productos: Muestra la página de productos.
- GET /index: Muestra la página de inicio.

## Clientes
- GET /clientes-page: Listar todos los clientes.
- GET /nuevo-cliente: Mostrar el formulario para crear un nuevo cliente.
- GET /cliente/{id}: Ver detalles de un cliente por ID.
- POST /guardar-cliente: Guardar los cambios en un cliente existente.
- POST /guardar-cliente-nuevo: Crear un nuevo cliente.
- POST /eliminar-cliente/{id}: Eliminar un cliente por ID.

## Coches
- GET /nuevo-coche/{codCliente}: Mostrar el formulario para crear un nuevo coche asociado a un cliente.
- GET /coche/{id}: Ver detalles de un coche por ID.
- POST /guardar-coche: Guardar los cambios en un coche existente.
- POST /guardar-coche-nuevo: Crear un nuevo coche.
- POST /eliminar-coche/{id}: Eliminar un coche por ID.

## Intervenciones
- GET /nueva-intervencion/{id}: Mostrar el formulario para crear una nueva intervención para un coche.
- GET /intervencion/{id}: Ver detalles de una intervención por ID.
- POST /guardar-intervencion-nueva: Crear una nueva intervención.
- POST /eliminar-intervencion/{id}: Eliminar una intervención por ID.
