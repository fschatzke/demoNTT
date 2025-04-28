Para ejecutar la aplicación, se debe configurar el puerto de TOMCAT, en mi caso apunta al 9090 pero por defecto está configurado en 8080.

El archivo application.properties contiene la configuracion

server.port=9090

spring.datasource.url=jdbc:h2:/Users/sura/Proyectos/springboot/h2;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.h2.console.enabled=true

En spring.datasource.url se debe configurar con el PATH asociado a la BD (h2.mv.db) del motor H2

El archivo table.sql contiene el script de creación sql de la tabla USUARIO

¿Como invvocar las API?
Existen 4 ENDPOINT
/users/all ->Lista todos los usuarios
users/login -> Autenticación usuario
/users/add -> Agrega un nuevo usuario
/users/update -> Actualiza información usuario

Ejemplos llamada
http://localhost:9090/users/login?email=pancho.schatzke@outlook.com ->GET
http://localhost:9090/users/all ->GET
http://localhost:9090/users/add->POST
body
{
	"nombre":"Pancho Schatzke",
	"correo":"pancho.schatzke@outlook.com",
	"contraseña":"123456xx",
	"paistelefono":[56,56,56],
	"ciudadtelefono":[9,9,9],
	"telefono":[123,1234,12345]
}

http://localhost:9090/users/update ->PUT
body
{
	"nombre":"Pancho Schatzke Formigli",
	"correo":"pancho.schatzke@outlook.com",
	"contraseña":"fox$2025"
}