# liter-alura
Liter-Alura es una aplicación de consola en Java que actúa como un catálogo de libros. El usuario puede interactuar mediante un menú textual para buscar libros y autores, o explorar libros por idioma, utilizando datos obtenidos desde una API pública.

## 🎯  Objetivo
Desarrollar una aplicación que permita al usuario consultar información sobre libros a través de un menú interactivo en consola. Los datos se obtienen consumiendo una API externa, y se almacenan en una base de datos para futuras consultas.

## 📋 Funcionalidades principales

El sistema ofrece las siguientes opciones al usuario:

Elija la opción a través de su número:
1. Buscar libro por título
2.  Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma
0. Salir

## ⚙️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Hibernate / JPA
- PostgreSQL
- Jackson
- API pública: [Gutendex API](https://gutendex.com/)
- Maven

## 🧩 Estructura del desarrollo

1. **Configuración del ambiente Java**  
   Preparación del entorno con JDK, Maven y PostgreSQL.

2. **Creación del proyecto**  
   Estructura modular en Java con paquetes organizados por funcionalidades: modelo, servicio, repositorio y vista principal.

3. **Consumo de la API**  
   Se realiza conexión con la API de Gutendex para obtener información en formato JSON.

4. **Persistencia en base de datos**  
   Se almacena la información relevante de libros y autores usando Hibernate y JPA.

5. **Interacción con el usuario**  
   El usuario navega por el sistema mediante un menú textual desde la consola.

## 💾 Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/IvaniaSandoval/liter-alura.git
   
2. Configura el archivo application.properties con tus credenciales de PostgreSQL.

