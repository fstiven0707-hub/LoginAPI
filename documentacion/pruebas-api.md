\# Pruebas del Servicio Web LoginAPI



\## Registro de usuario



Endpoint:



POST http://localhost:8080/api/registro



Descripción:

Permite registrar nuevos usuarios en la base de datos.



\---



\## Inicio de sesión



Endpoint:



POST http://localhost:8080/api/login



Descripción:

Permite validar las credenciales de un usuario registrado.



\---



\## Validaciones realizadas



\- Registro exitoso de usuario.

\- Bloqueo de correo duplicado.

\- Validación de campos obligatorios.

\- Login correcto.

\- Error por contraseña incorrecta.

\- Error por usuario inexistente.



\---



\## Herramienta de pruebas



Postman

