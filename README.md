# Sistema de Gestión de Libros en Java

Este proyecto es una aplicación de escritorio en Java que implementa un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar una base de datos mySQL de libros utilizando **JDBC**, **ResultSet actualizable**, y una **interfaz gráfica** construida con **Swing**.
La base de datos contiene una única tabla llamada **"libros"** sobre la que se van a realizar operaciones.
Dicha tabla contiene información sobre el **id, titulo, autor y año de publicación**.

El usuario gestionará los registros de libros en la base de datos MySQL con las siguientes operaciones CRUD:
- **Crear** un nuevo libro insertando el registro en la tabla libros.
- **Leer** todos los libros registrados en la tabla libros.
- **Actualizar** los datos de un libro específico.
- **Eliminar** un libro del sistema gestor.

Mediante los botones de la interfaz gráfica el programa invitará al usuario de forma intuitiva a insertar, modificar o eliminar libros.

Se utilizan **JDBC** y un **ResultSet actualizable** (`ResultSet.TYPE_SCROLL_SENSITIVE`, `ResultSet.CONCUR_UPDATABLE`) para manejar todas las operaciones con la base de datos.

## Requisitos

Para ejecutar el proyecto, necesitas:
- Java 8 o superior
- MySQL
- Maven para gestionar las dependencias

Esta es la dependencia Maven de MySQL JDBC para el `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.32</version>
</dependency>
