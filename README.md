# LoginAPI - Servicio Web de Autenticación

## Descripción

LoginAPI es un servicio web desarrollado con Spring Boot que permite realizar el registro e inicio de sesión de usuarios mediante una API REST conectada a una base de datos MySQL.

El proyecto implementa validaciones, arquitectura por capas y manejo de respuestas HTTP.

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Git y GitHub
- Postman

---

## Arquitectura del proyecto

```
com.stif.loginapi

├── controller
│   └── UsuarioController
│
├── dto
│   ├── ApiResponse
│   └── UsuarioResponse
│
├── entity
│   └── Usuario
│
├── repository
│   └── UsuarioRepository
│
├── service
│   └── UsuarioService
│
└── exception
    └── GlobalExceptionHandler
```

---

## Funcionalidades

### Registro de usuarios

Endpoint:

```
POST /api/registro
```

Ejemplo:

```json
{
    "nombre": "Stiven",
    "correo": "stiven@gmail.com",
    "password": "123456"
}
```

---

### Inicio de sesión

Endpoint:

```
POST /api/login
```

Ejemplo:

```json
{
    "correo": "stiven@gmail.com",
    "password": "123456"
}
```

---

## Validaciones implementadas

- Nombre obligatorio.
- Correo obligatorio.
- Contraseña obligatoria.
- Evita correos duplicados.
- Verificación de contraseña en login.

---

## Base de datos

Motor:

MySQL

Tabla:

usuarios

Campos:

- id
- nombre
- correo
- password

---

## Autor

Stiven Garibello

Proyecto desarrollado con Spring Boot como servicio web REST.
