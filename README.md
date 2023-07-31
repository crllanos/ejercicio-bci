# Evaluación BCI

Proyecto Java SpringBoot de registro de usuarios.

* implementación de los diferentes verbos HTTP.
* pruebas unitarias en la capas controller y service (coverage 80%).
* JWT para la seguridad.
* Postman collection para las llamadas a los endpoints expuestos

## Token de autenticación
El proyecto está segurizado mediante JWT. Para generar este token es necesario apuntar al endpoint de /login con las credenciales descritas en el apartado siguiente.

Una vez obtenido dicho token, debe ser ingresado como parte del request en el apartado "Authorization" de Postman, como Bearer Token.

## Roles y credenciales
Al levantar el proyecto se generan automaticamente, vía CommandLineRunner, los siguientes perfiles administrativos:

### ROLE_ADMIN
* username: bwayne
* password: batman
* grants: GET, POST (ver y crear usuarios)

### ROLE_SUPERADMIN
* username: ckent
* password: superman
* grants: GET, POST, PUT, DELETE (ver, crear, modificar y borrar usuarios)