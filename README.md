#Prueba - Global Logic

---

###Objetivo del proyecto
- Creación y obtención de usuario utilizando JWT & JPA-Hibernate. En complemento se utiliza spring-security para la validación de JWT en request necesaria.

###Requisitos
- Gradle
- JDK 1.8

## Comandos para ejecutar proyecto
1. Ubicarse en raíz de proyecto y ejecutar comando:
   
    gradle build
2. Una vez completado el paso anterior, ubicarse en carpeta \build\libs y ejecutar el siguiente comando:
java -jar user-management-1.0-SNAPSHOT.JAR

Se levantará la aplicación con la siguiente path:
http:://localhost:8080

###Rutas
Las rutas contenidas en la aplicación son las siguientes:
1. POST /v1/account/signup
   1. Se debe enviar JSON especificado en los requerimientos
2. GET /v1/account/login
   1. Se debe adjuntar Bearer token en cabecera authorization

###Extra
Está disponible la consola de la base de datos H2 en la siguiente ruta:
http://localhost:8080/h2-console

con las crendeciales:
- username: sa
- password: <sin password>