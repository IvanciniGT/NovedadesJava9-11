
# Novedades más representativas en cada versión de Java 

## Java 1.5

- Generics
  - Tipos Complejos paralelos a los primitivos: Integer, Double, Boolean, etc.
    - Autoboxing
- Enumerados
- For-each :: Iterable
- Anotaciones << Cambiaron la forma de programar en JAVA. Hoy en día usamos más anotaciones que líneas de código.

# Java 1.8

- Soporte para Programación funcional
  - paquete java.util.function: Interfaces funcionales: Predicate, Consumer, Supplier, Function, etc.
  - Operador :: (Method Reference)
  - Operador -> (Lambda)
- Streams
  - Operaciones con colecciones de datos de forma simplificada:
    - Toda colección la puedo llevar a un Stream (.stream())
    - Cualquier Stream lo puedo convertir a una colección (.collect())
    - Operaciones Map-Reduce
- Interfaces:
  - Posibilidad de escribir funciones estáticas públicas
  - Posibilidad de escribir métodos por defecto -> Mnto del código
- Optional (que por fin nos permiten generar un API no ambigua)
- Se permite anotar con Anotaciones los parámetros de un método (nos ayuda a ahorrar código y de paso generar un API más clara, menos ambigua)
- Un nuevo API para la gestión de fechas:
  - Hasta entonces teníamos un desmadre:
    - java.util.Date
    - java.sql.Date
    - java.util.Calendar
    - java.util.GregorianCalendar
  - Se añade dentro de JAVA la librería JodaTime: java.time
    - LocalDate
    - LocalTime
    - LocalDateTime
    - ZonedDateTime
    - Period
    - Instant           <--- Es para compatibilizar con las apis anteriores : ZonedDateTime -> Instant -> Date
    - DateTimeFormatter
    - etc.

# Java 9

- Proyecto Jigsaw: Añadir módulos a la JVM
  - La propia JVM se divide en módulos... y empezamos a tener distintas implementaciones de la JVM (Oracle, OpenJDK, Amazon Corretto, Eclipse Temurin, etc.)
    cada una con sus propios módulos.
  - En las JVM podemos comenzar a activar o desactivar módulos.
    - Hay módulos muy problemáticos que se pueden desactivar: reflection, unsafe, etc.
  - El concepto de module, que definimos en los ficheros module-info.java, nos permite definir:
    - Dependencias entre módulos
    - Los paquetes que exportamos y los que no
    - Las interfaces que ofrecemos implementadas
    - Los servicios que necesitamos <- ServiceLoader
  - Todo esto nos ayuda a crear componentes más desacoplados, más modulares, más fáciles de mantener y de testear... 
    Encaja mejor con los principios de SOLID, metodologías ágiles, DEVOPS, etc.
- En las interfaces se añade la posibilidad de crear funciones privadas estáticas
- En las interfaces de Collections se añade el método .of() para crear colecciones inmutables de forma más sencilla
- JShell: Consola interactiva de JAVA > Se metió para intentar atraer a los programadores de Python y Javascript 
  - Tenemos un entorno para jugar y aprender JAVA
  - Tenemos la posibilidad de generar scripts en JAVA

# Java 10

- Se añade la palabra reservada var para inferir el tipo de una variable. Funcionalmente no aporta nada nuevo, 
  pero sí que nos ayuda a escribir menos código.... más que eso.. a escribir un código más claro, más legible, más mantenible.... Si lo uso bien
    LA finalidad clara de nuevo fue atraer a los programadores de Python y Javascript 
- Se añade el método .orElseThrow() a la clase Optional
- En las interfaces de collections se añade el método .copyOf() para crear copias de colecciones inmutables de forma más sencilla

# Java 11

- La posibilidad de ejecutar directamente un fichero .java sin necesidad de compilarlo previamente, pasándoselos a la JVM
    Esta, de nuevo, es para pythonistas y javascriptistas
    Nuestros proyectos nunca van a tener 1 fichero y no los vamos a distribuir en ficheros .java... ---> .jar, .war, .ear, etc.
    Está bien para aprender, para hacer pruebas, para hacer scripts, etc.
- La posibilidad de usar var en los argumentos de las expresiones lambda. 
  Ésto, que parece que aporta bastante poco... y que es un sinsentido, tiene una finalidad muy clara:
      (Integer numero) -> numero * 2
      (numero) -> numero * 2
      (var numero) -> numero * 2             <<<< Es la nueva en java 11.
  Sirve para poder poner anotaciones a los parámetros de las expresiones lambda, sin necesidad de poner el tipo. 
- Se añade el método .isEmpty() a la clase Optional
- En la clase String: 
  - Se añade el método .isBlank() para saber si un String está vacío o sólo contiene espacios en blanco
  - Se añade el método .lines() para obtener un Stream de líneas de un String
- En la clase Files:
  - Se añade el método .readString() para leer un fichero de texto y obtener un String
  - Se añade el método .writeString() para escribir un String en un fichero de texto
- Reescritura del API de peticiones HTTP, aplicándose el patrón de diseño Builder, como los que hemos usado de Lombok
  
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://google.com"))
                .header("Content-Type", "application/json")
               .POST(HttpRequest.BodyPublishers.ofString("{'nombre':'pepe'}"))
                .build();

   - En la clase HttpClient, se añaden funciones de programación funcional para poder hacer peticiones HTTP de forma más sencilla:
       - .sendAsync()
       - .send()
       - .thenApply()
       - .thenAccept()
   - Los Websockets también se crean mediante patrón Builder
   
---
