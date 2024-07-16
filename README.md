# LiretAlura Challenge, (buscador de Libros usando Springboot)

## Descripción
LiterSearchingApp es una aplicación de consola Java que interactúa con la API Gutendex para buscar y gestionar información sobre libros y autores. Utiliza Spring Boot y JPA para manejar la persistencia de datos en una base de datos PostgreSQL.

## Características
- Búsqueda de libros por título
- Listado de todos los libros almacenados en la base de datos local
- Listado de autores
- Búsqueda de autores vivos en un año específico
- Estadísticas de libros por idioma

## Requisitos previos
- Java 17 o superior
- Maven
- PostgreSQL

## Configuración
1. Clone el repositorio:
   ```
  https://github.com/Xolot-32/LiterSerchingApp
   ```

2. Configure la base de datos PostgreSQL:
   - Cree una nueva base de datos llamada `gutendex_db`
   - Actualice el archivo `src/main/resources/application.properties` con sus credenciales de PostgreSQL:
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/gutendex_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. Compile el proyecto:
   ```
   mvn clean install
   ```

## Uso
1. Ejecute la aplicación:
   ```
   java -jar target/gutendex-client-0.0.1-SNAPSHOT.jar
   ```

2. Siga las instrucciones en la consola para interactuar con la aplicación. Las opciones disponibles son:
   1. Buscar libro por título
   2. Listar todos los libros
   3. Listar autores
   4. Listar autores vivos en un año específico
   5. Mostrar estadísticas de libros por idioma
   0. Salir

## Estructura del proyecto
- `src/main/java/com/example/gutendexclient/`
  - `GutendexClientApplication.java`: Punto de entrada de la aplicación y lógica del menú principal
  - `model/`: Contiene las clases de entidad (Book.java, Author.java)
  - `repository/`: Interfaces de repositorio para interactuar con la base de datos
  - `service/`: Servicios para la lógica de negocio (GutendexService.java, DatabaseService.java)
  - `util/`: Utilidades como JsonParser.java

## Tecnologías utilizadas
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson (para parsing JSON)
- Java HttpClient

## Contribución
Las contribuciones son bienvenidas. Por favor, abra un issue para discutir cambios importantes antes de crear un pull request.

## Licencia
Este proyecto está bajo la Licencia MIT. Consulte el archivo `LICENSE` para más detalles.
