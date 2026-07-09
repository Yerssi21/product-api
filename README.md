# Product API

API REST desarrollada con **Spring Boot 3** para la gestión de productos, implementando operaciones CRUD, validaciones, manejo global de excepciones, documentación con Swagger y pruebas unitarias.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- Flyway
- Maven
- Jakarta Validation
- OpenAPI / Swagger
- JUnit 5
- Mockito
- MockMvc
- Lombok

---

## Funcionalidades

- Crear un producto
- Consultar un producto por ID
- Listar productos con paginación
- Actualizar un producto
- Eliminar un producto
- Validación de datos de entrada
- Manejo global de excepciones
- Migraciones con Flyway
- Documentación automática con Swagger
- Pruebas unitarias e integración

---

## Arquitectura

```
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
Database
```

Proyecto organizado siguiendo una arquitectura en capas para separar responsabilidades y facilitar el mantenimiento.

---

## Base de datos

Crear una base de datos llamada:

```sql
CREATE DATABASE productdb;
```

Luego configurar las credenciales en:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

---

## Clonar el proyecto

```bash
git clone https://github.com/Yerssi21/product-api.git
```

Entrar al proyecto:

```bash
cd product-api
```

---

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

---

## Ejecutar las pruebas

```bash
mvn test
```

---

## Documentación de la API

Una vez iniciada la aplicación, la documentación estará disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Autor

**Yerssi Yiseth Osorio Tovar**

Backend Developer