# Prueba - Global Logic

---

### Objetivo del proyecto
- Creación y obtención de usuario utilizando JWT & JPA-Hibernate. En complemento se utiliza spring-security para la validación de JWT en request necesaria.

---

### Requisitos
- Gradle
- JDK 1.8

---

## Comandos para ejecutar proyecto
1. Ubicarse en raíz de proyecto y ejecutar comando:
   

       gradle build

2. Una vez completado el paso anterior, ubicarse en carpeta \build\libs y ejecutar el siguiente comando:

    
       java -jar user-management-1.0-SNAPSHOT.JAR

3. Se levantará la aplicación con la siguiente path:


       http://localhost:8080

---

### Rutas
Las rutas contenidas en la aplicación son las siguientes:
1. <b> POST /v1/account/sign-up </b>
   
   1. Se debe enviar JSON especificado en los requerimientos:


         {
            "name": "Test name",
            "email": "emasil@test.com",
            "password": "1kKaaal8",
            "phones": [
                  {
                     "number": 92321982,
                     "citycode": 1,
                     "contrycode": "CH"
                  }
               ]
            }
   

2. <b> POST /v1/account/login </b>

   1. Se debe adjuntar Bearer token en cabecera authorization y JSON con credenciales:
   
   
      { "email": "emasil@test.com", "password": "1kKaaal8" }

---

###Extra
Está disponible la consola de la base de datos H2 en la siguiente ruta:


      http://localhost:8080/h2-console


con las crendeciales:

    - username: sa
    - password: <sin password>