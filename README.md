Para ejecutar la aplicación, se debe configurar el puerto de TOMCAT, en mi caso apunta al 9090 pero por defecto está configurado en 8080.

El archivo application.properties contiene la configuracion

server.port=9090

spring.datasource.url=jdbc:h2:/Users/sura/Proyectos/springboot/h2;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.h2.console.enabled=true

En `<spring.datasource.url>` se debe configurar con el PATH asociado a la BD (.mv) del motor H2

El archivo table.sql contiene el script de creación sql de la tabla USUARIO
