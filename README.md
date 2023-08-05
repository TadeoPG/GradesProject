# Sistema Web para un colegio

NOTA: El proyecto aún se encuentra en desarrollo

Este proyecto se centra en la gestión de matrículas, registro de cursos, aulas y calificaciones de cada estudiante

# Flujo del negocio

El usuario se acerca a la institución y la recepcionista lo atiende

# Tecnologías usadas

### Frontend

* Angular 16
* HTML
* CSS
* Javascript
* Typescript

### Backend

* Java 17
* Spring Boot 3
* Spring Data JPA

### Base de datos

* SQL Server

# Desplegando el proyecto

Para desplegar correctamente el proyecto deberás de seguir estos pasos:

1. __Prerrequisitos__: Tener previamente instalado Java 17, SQL Server 19, Angular 16 y Node.js en el sistema operativo.
2. __Crear la base de datos__: Es necesario crear la base de datos manualmente antes de ejecutar el Backend ya que
   el `spring.jpa.hibernate.ddl-auto=update` está en update.
3. __Modificar el archivo `application.properties`__: Configurar el URL, usuario y contraseña de la base de datos
   especificada anteriormente.

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=XXXXXX;TrustServerCertificate=True
spring.datasource.username=XXXXX
spring.datasource.password=XXXXX
```

4. __Instalar el la carpeta `node_modules`__: Al clonar este repositorio no se tendrá la carpeta `node_modules` por lo
   que se tendrá que instalar manualmente.
   Abrir la terminal en el Visual Studio Code que es donde se tendrá corriendo el proyecto Angular y ejecutar lo
   siguiente:

```
npm install
```


# Endpoints

Una vez ejecutado el backend se puede probar algunos endpoints se pueden utilizar las siguientes URL en el cliente o navegador:

### GET

1. ``localhost:8080/alumnos``: Obtiene el listado de alumnos. 

[![](https://user-images.githubusercontent.com/2389286/236301770-16f46d4f-4e23-4db5-9462-f578ec31e751.svg)](http://localhost:8080/alumnos)
2. ``localhost:8080/cursos``: Obtiene el listado de cursos.
   
[![](https://user-images.githubusercontent.com/2389286/236301770-16f46d4f-4e23-4db5-9462-f578ec31e751.svg)](http://localhost:8080/cursos)
3. ``localhost:8080/aulas``: Obtiene el listado de aulas.

[![](https://user-images.githubusercontent.com/2389286/236301770-16f46d4f-4e23-4db5-9462-f578ec31e751.svg)](http://localhost:8080/aulas)
4. ``localhost:8080/calificaciones``: Obtiene el listado de calificaciones.

[![](https://user-images.githubusercontent.com/2389286/236301770-16f46d4f-4e23-4db5-9462-f578ec31e751.svg)](http://localhost:8080/calificaciones)