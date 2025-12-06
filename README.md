# TEST_getTechno

![Build Docker Image](https://github.com/IsmaelPrado/TEST_getTechno/actions/workflows/build-docker.yml/badge.svg)

## Descripción

Proyecto de prueba técnica implementado en **Java Spring Boot**.
El proyecto simula un sistema de gestión de **personas** y sus **facturas**, utilizando una **base de datos H2 embebida**, con endpoints REST, manejo de errores personalizado y logging.

Se incluye **Dockerfile** para contenerizar la aplicación y un **workflow en GitHub Actions** para construir y publicar la imagen en Docker Hub: https://hub.docker.com/repository/docker/ismaelprado/test-gettechno

---

## Tecnologías utilizadas

* Java 17
* Spring Boot 3.3.3
* H2 Database (embebida)
* Spring Data JPA
* Spring Validation (Jakarta Bean Validation)
* Lombok
* Swagger/OpenAPI
* SLF4J (Logging)
* JUnit 5 + Mockito (Testing)
* Docker
* GitHub Actions

---

## Estructura del proyecto

* **DirectorioRestService**: REST Controller para exponer los endpoints de personas.
* **FacturaRestService**: REST Controller para exponer los endpoints de facturas.
* **VentasService**: Servicio de negocio para gestionar facturas. (IMPL e INTERFAZ)
* **DirectorioService**: Servicio de negocio para gestionar personas (crear, buscar, eliminar).(IMPL e INTERFAZ)
* **Persona** y **Factura**: Modelos de dominio.
* **PersonaRepository** y **FacturaRepository**: Interacción con la base de datos H2.
* **GlobalExceptionHandler**: Manejo global de errores y validaciones.
* **Dockerfile**: Contenerización de la aplicación.
* **.github/workflows/docker.yml**: Pipeline de CI/CD para construir y publicar imagen Docker.

---

## Endpoints principales

### Personas

* `POST /api/personas` → Crear nueva persona
* `GET /api/buscar/personas` → Listar personas (Paginación)
* `GET /api/buscar/persona` → Listar persona
* `DELETE /api/personas/eliminar` → Eliminar persona (envía `PersonaResponse` indicando cuál fue eliminada)

### Facturas

* `POST /api/facturas` → Crear factura
* `POST /api/facturas/buscar` → Obtener facturas por persona (Paginación)

---

## Base de datos

* **H2** (embebida)
* Acceso desde la consola en `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* Usuario: `sa`
* Password: (vacío)

---

## Tests

Se implementaron al menos **3 pruebas unitarias** usando **JUnit 5 + Mockito**, incluyendo:

* Eliminación de persona existente
* Búsqueda de persona por identificación
* Manejo de facturas asociadas a persona

Ejecutar tests:

```bash
./gradlew test
```

---

## Docker

### Construir imagen localmente

```bash
docker build -t ismaelprado/test-gettechno:latest .
```

### Ejecutar contenedor

```bash
docker run -p 8080:8080 ismaelprado/test-gettechno:latest
```

Acceder a la API en `http://localhost:8080`.

### Imagen pública

La imagen está publicada en Docker Hub:

```bash
docker pull ismaelprado/test-gettechno:latest
```

---

## GitHub Actions

El workflow `docker.yml`:

* Se ejecuta en cada push o pull request a la rama `main`
* Compila el proyecto con Gradle
* Construye la imagen Docker
* Publica la imagen en Docker Hub

Badge de estado:

![Build Docker Image](https://github.com/IsmaelPrado/TEST_getTechno/actions/workflows/build-docker.yml/badge.svg)

---

## Notas

* Todos los endpoints usan **respuestas consistentes** con `ApiResponse` y códigos propios (`ApiStatusCode`).
* Se utiliza **SLF4J** para logging.
* Se manejan errores de validación (`@Valid`) y JSON inválido de forma centralizada en `GlobalExceptionHandler`.

---

## Autor

**Ismael Prado**
Email: [vansestilo200@gmail.com](mailto:vansestilo200@gmail.com)
GitHub: [https://github.com/IsmaelPrado](https://github.com/IsmaelPrado)
