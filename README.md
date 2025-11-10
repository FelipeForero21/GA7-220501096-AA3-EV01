# Standalone Module - CRUD Usuario

Proyecto Java Spring Boot que implementa un módulo web con arquitectura por capas y operaciones CRUD para la entidad `Usuario`.

## Requisitos
- JDK 17+
- Maven 3.9+

## Ejecutar con H2 (por defecto)
```bash
# Ejecutar desde la carpeta del proyecto
# (ubicación: standalone-module)
mvn spring-boot:run
```
La consola H2 estará disponible en `http://localhost:8080/h2-console` (usuario: `sa`, sin contraseña).

Si te encuentras en la carpeta padre, en Windows PowerShell puedes hacer:
```powershell
cd .\standalone-module\
mvn spring-boot:run
```

## Cambiar a MySQL (opcional)
1. Crear una BD `standalone_db`.
2. Editar `src/main/resources/application.properties` comentando H2 y descomentando las propiedades MySQL.
3. Ejecutar:
```bash
mvn spring-boot:run
```

## Endpoints REST
- `GET /api/usuarios` listar todos
- `GET /api/usuarios/{id}` obtener por id
- `POST /api/usuarios` crear
- `PUT /api/usuarios/{id}` actualizar
- `DELETE /api/usuarios/{id}` eliminar

Cuerpo JSON ejemplo:
```json
{
  "nombre": "Juan Perez",
  "email": "juan@example.com",
  "rol": "ADMIN"
}
```

## Estructura por capas
- `controller` expone endpoints REST.
- `service` maneja lógica de negocio y validaciones.
- `repository` acceso a datos con Spring Data JPA.
- `model` entidades JPA.
- `exception` manejo global de errores.


## Estándares
- Nombres significativos.
- Convenciones de Java (camelCase, PascalCase en clases, constantes en MAYÚSCULAS_si aplica).
- Comentarios en secciones importantes del código.

## Compilar y ejecutar como JAR
```bash
mvn clean package
java -jar target/standalone-module-0.0.1-SNAPSHOT.jar
```

## Probar con curl
```bash
# Listar
curl http://localhost:8080/api/usuarios

# Crear
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Nuevo","email":"nuevo@example.com","rol":"USER"}'

# Actualizar
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Admin Editado","email":"admin@example.com","rol":"ADMIN"}'

# Eliminar
curl -X DELETE http://localhost:8080/api/usuarios/2
```

## Probar con PowerShell
```powershell
# Listar
Invoke-RestMethod http://localhost:8080/api/usuarios

# Crear
Invoke-RestMethod -Uri http://localhost:8080/api/usuarios -Method Post `
  -ContentType "application/json" `
  -Body '{"nombre":"Nuevo","email":"nuevo@example.com","rol":"USER"}'

# Actualizar
Invoke-RestMethod -Uri http://localhost:8080/api/usuarios/1 -Method Put `
  -ContentType "application/json" `
  -Body '{"nombre":"Admin Editado","email":"admin@example.com","rol":"ADMIN"}'

# Eliminar
Invoke-RestMethod -Uri http://localhost:8080/api/usuarios/2 -Method Delete
```

## Códigos de respuesta y errores
- 200 OK / 201 Created / 204 No Content para operaciones exitosas.
- 400 Bad Request: validaciones fallidas (`nombre`, `email`, `rol`).
- 404 Not Found: recurso no existe.
- 409 Conflict: email duplicado (único por restricción).
- Las respuestas de error incluyen `timestamp`, `status`, `error`, `mensaje` y, si aplica, `errores` o `detalle`.

## Notas MySQL (opcional)
1. Crear BD `standalone_db` y credenciales.
2. En `application.properties`, comentar H2 y descomentar el bloque MySQL.
3. Ajustar `spring.datasource.username`/`password`.
4. Mantener `spring.jpa.hibernate.ddl-auto=update` para desarrollo.

## Troubleshooting
- Si `data.sql` falla con "Table not found":
  - Asegúrate de tener `spring.jpa.defer-datasource-initialization=true` (ya configurado).
- Si Maven no reconoce `spring-boot:run`:
  - Usa el parent `spring-boot-starter-parent` (ya configurado) o ejecuta `mvn -U clean package`.
- Ver SQL en consola: `spring.jpa.show-sql=true` y `hibernate.format_sql=true`.
