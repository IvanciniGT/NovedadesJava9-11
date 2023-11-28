
# JAVA (novedades 9, 10, 11)

Java: Lenguaje de programación.
      --------

Lenguaje: Implica una gramática, sintaxis, semántica, etc.

## Características de JAVA

### Lenguajes compilados / interpretados

Compilado:      Proceso que transforma un lenguaje de alto nivel a un lenguaje de bajo nivel (Assembler, C, C++, etc)
                Al disponer ya de un código que es capaz de comunicarse sin problemas con SO... rara vez escribimos código que interactue directamente con el hardware (driver, SO,...) los programas creados en lenguajes compilados son más rápidos que los interpretados.... EN GENERAL y con gloriosas excepciones: JAVA
Interpretado:   La compilación se hace en tiempo de ejecución (PHP, Python, etc) a través de un interprete.

JAVA es Compilado --> BYTE-CODE (Interpretado por un interprete que corre dentro de la JVM - JIT)
    La compilación ofrece muchas ventajas, entre ellas, nos da la posibilidad de hacer un chequeo de errores antes de ejecutar el programa.
    La interpretación nos da la posibilidad de ejecutar el programa en cualquier SO que tenga un interprete para el lenguaje.

### Lenguajes de tipado estático / dinámico

Estático:       Las variables tienen asociado un tipo de dato y este no puede cambiar durante la ejecución del programa....
Dinámico:       Las variables no tienen tipo.

```java
String texto = "hola";  // La variable texto de tipo String se le asigna el valor hola! DESASTRE
                        // Asignar la variable de tipo String texto al valor "hola", que también es de tipo String
```
Lo primero que se ejecuta:
    - "hola"            Crea un objeto en memoria de tipo String con el valor "hola"
                        La memoria es como un cuaderno de cuadros, cada cuadro tiene una dirección.
                        Le pedimos en última instancia al SO que nos de un cuadro de memoria para guardar un valor. 
    - String texto      Crea una variable de tipo String con nombre "texto"
    - =                 Asignar la variable al valor
  
```java
texto = "adios";
```
    - "adios"               Crea un objeto en memoria de tipo String con el valor "adios"
                            Este dato (valor, objeto) se guarda donde estaba escrito el "hola" o en otro sitio? En otro sitio.
                                En este punto tenemos en memoria 2 objetos de tipo texto "hola" y "adios"
    - texto                 Accede a la variable texto (despegar el postit de donde estuviera pegado antes)
    - =                     Asignar la variable al nuevo valor.
                            En el caso de JAVA esta asignación podemos realizarla ya que la variable permite apuntar a cualquier objeto de tipo String.
                                 En este momento el objeto con valor "hola" queda huérfano, no hay ninguna variable que apunte a él, por lo que se convierte en basura (garbage). Y quizás si ... o quizás no... el recolector de basura (garbage collector) lo elimine de memoria.
JAVA es un lenguaje que hace un destrozo gigantesco a la memoria RAM. Dicho de otra forma... el mismo programa escrito en C necesitaría mucha menos memoria que el escrito en JAVA.
Esto es bueno o malo? Ni bueno ni malo.. Es un feature de JAVA.
La gracia de JAVA es que al programador se olvida (en cierta parte) de la gestión de memoria, y se centra en la lógica del programa.

```js
var texto = "hola";
texto = 4;                  // Funcionaría sin problemas
```
En el caso de JS, la variable texto no tiene tipo, por lo que puede apuntar a cualquier objeto de cualquier tipo.

```java
// Desde java 10, podemos hacer uso de la palabra var a la hora de definir variables. PERO CUIDADO... NO ES EL VAR DE JS!
// Java sigue siendo un lenguaje de tipado estático.
var texto = "hola";         // Sigue siendo de tipo String... Aquí usamos otro concepto de JAVA, la inferencia de tipos.
texto = 4;                  // NO Funcionaría / no compilaría
```

No siempre es una buena práctica el usar esto. Cuando tiene sentido usar la palabra var? 
Hace 40 años, el programador más friki, el que escribía código que solo él entendía, era el mejor programador. 
Hoy en día, ese mejor programador está de patitas en la calle el primer día.
Quiero programadores que escriban código que sea fácil de entender, que sea fácil de mantener, que sea fácil de modificar, por cualquier otro programador.
```java
var informe = generarInforme(); // Tipo de dato? NPI
```
- Cuando asigno el tipo directamente en la declaración de la variable.

```java
Map<String,List<String>> listado = new HashMap<String,List<String>>(); // Esto era un desastre
Map<String,List<String>> listado = new HashMap<>();                    // Esto es más corto (operador diamante)... pero aberrante
                                                                       // El tipo de dato se infiere de la declaración de la variable.
                                                                       // Conceptualmente tiene sentido que sea lo contrario.
generarInforme(new HashMap<>())                                        // Este código ni compila
var listado = new HashMap<String,List<String>>();                      // Esto es GUAY
```

El `var` lo uso cuando me aporta legibilidad al código. Cuando no me aporta legibilidad, no lo uso.

```java
var texto = "hola";
String texto = "hola";
```

### Paradigmas de programación

Una forma de escribir un programa:
- Programación imperativa:              Cuando damos instrucciones al computador que deben ejecutarse secuencialmente.
                                        A veces necesitamos romper la secuencialidad, para ello usamos estructuras de control (if, for, while, etc)
- Programación procedural:              Cuando el lenguaje me permite definir funciones (métodos, subrutinas, procedimiento) y invocarlas posteriormente
- Programación funcional:               Cuando el lenguaje me permite que una variable apunte a una función
                                        para posteriormente ejecutar la función desde la variable.
                                        El tema no es lo que es la programación funcional...
                                        Si no lo que me permite hacer la programación funcional.

                                        Desde el momento que puedo referenciar a una función, puedo pasarla como parámetro a otra función.... O puedo crear una función que devuelva otra función.
                                        Y AQUI ES CUANDO LA CABEZA EXPLOTA !
- Programación Orientada a Objetos:     Cuando el lenguaje me permite definir mis propios tipos de datos... con sus propiedades y sus funciones.
                                                         Lo que lo caracteriza              Sus funciones especiales
                                             String      una secuencia de caracteres        .toUpperCase(), .toLowerCase(), .length(), .charAt(), .indexOf()
                                             Date        dia, mes, año                      .esBisiesto(), .getDiaSemana()
                                             Usuario     nombre, apellidos, edad, dni       .doLogin(), doLogout()
- Programación declarativa:             Cuando delego al lenguaje la responsabilidad de cómo conseguir el resultado que quiero.

En los lenguaje naturales (los que hablamos los humanos), también tenemos paradigmas.
- Felipe, pon una silla debajo de la ventana    --> Imperativo
  Lo cierto es que cada día más odiamos la programación imperativa.

    Felipe si no hay ventana... coge un mazo y haz una ventana.
    Felipe, si hay algo que no sea una silla debajo de la ventana, quítalo.
    Felipe SI no hay una silla debajo de la ventana, 
        Felipe, si no hay sillas, vete al ikea y compra una silla.
        pon una silla debajo de la ventana --> Imperativa

- Felipe, quiero una silla debajo de la ventana --> Declarativo
    Con un paradigma declarativo, me centro en lo que quiero, no en cómo conseguirlo.
    Delego la responsabilidad de cómo conseguirlo a otra persona (felipe) 

Las herramientas, lenguajes, frameworks, etc... que usamos hoy en día (que más triunfan), nos permiten escribir código declarativo:
En java se puede? SI, desde JAVA 1.5, con la introducción de las Anotaciones: SpringBoot, Hibernate, etc.
Ejemplos: Spring, Angular, Terraform, ansible, kubernetes, docker-compose

---

## Qué tal os parece JAVA como lenguaje?

Extraordinariamente Verboso. 
Lento en ejecución. Hoy en día es mentira. Hace 25 años no. En la versión 1.2 de JAVA se incluye dentro del JIT una extensión llamada HotSpot. Básicamente es una cache de código compilado. El código que se ejecuta más veces se compila y se guarda en la cache. 
Esto hace que JAVA sea un lenguaje muy rápido en ejecución (una vez calentado el JIT).

---

# SonarQube:

Herramienta de calidad de código.
Hace lo que hace 20 años hacía un programador senior. Revisar el código... y dar el visto bueno.
Uno de los indicators de calidad de código es la complejidad cognitiva: Cómo de complejo es para un humano entender un código.

---

# Cagadas de JAVA. Parte 1

## Getters y setters

```java
// Dia 1
public class Usuario {
    public String nombre;
}

// Del día 2 al 100 tengo gente usando mi clase y escribiendo este código:
Usuario  u1 = new Usuario();
u1.nombre = "Felipe";
System.out.println(u1.nombre);

// El día 101, se me ocurre la brillante idea de querer asegurar que le nombre no puede estar en blanco.
// JODIDO VOY. En JAVA la UNICA opción que tengo es meter ese código (IF) en una función... y me habría tocado escribir el código de abajo
// Al hacerlo, que le pasa a los amig@s que han estado usando mi clase? Pues que su código deja de compilar.
// En otras palabras, me van a estar buscando, kalashnikov en mano, para "hablar conmigo".
// Si es mi proyecto, voy a tener que tocar en 50 sitios.
```

En JAVA me dice que es una MUY MALA practica crear variables públicas.... y que siempre debo crearlas privadas y acceder a ellas a través de getters y setters. Y ESTO ES CIERTO.

Por si acaso el día de mañana tienes que meter código que ejecutar antes de asignar una variable o de recuperarla... creala como privada y añade setters y getters.

```java
public class Usuario {
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco");
        }
        this.nombre = nombre;
    }
}

// ....

Usuario  u1 = new Usuario();
u1.setNombre("Felipe");
System.out.println(u1.getNombre());
```

---

# JAVA en el año 2000!

Todo el mundo quería usar JAVA:
- App web: JAVA
- App escritorio: JAVA (swing)
- App móvil Android : JAVA
- Script: JAVA
- Software embebido: JAVA

El lenguaje del futuro: El lenguaje que se va a cargar a todos los demás lenguajes.

# JAVA en el año 2023!

JAVA hoy en día está agonizando:
- App web:
  - Frontal     JS
  - Backend     JAVA (--> KOTLIN)       (legacy) + Spring (paradigma declarativo)
- App escritorio: C++, C, VB, ObjectiveC (swing)
- App móvil Android : Kotlin
- Script: Python, ...
- Software embebido: C, C++, Rust, Go, ...

Qué ha pasao?

- Es un lenguaje cuya gramática está llena de cagadas.
- La adquisición de Sun Microsystems por parte de Oracle.

---

## Versiones de Java

| Versión       | Año | Propietario                     |
| ------------- | --- | ------------------------------- |
| Java 1.0      | 1996| Propiedad de Sun Microsystems   |
| Java 1.1      | 1997| Propiedad de Sun Microsystems   |
| Java 1.2      | 1998| Propiedad de Sun Microsystems   |
| Java 1.3      | 2000| Propiedad de Sun Microsystems   |
| Java 1.4      | 2002| Propiedad de Sun Microsystems   |
| Java 1.5      | 2004| Propiedad de Sun Microsystems   |
| Java 1.6      | 2006| Propiedad de Sun Microsystems   |
| Java 1.7      | 2011| Propiedad de Oracle             |
| Java 1.8      | 2014| Propiedad de Oracle (de pago)   |
| Java 9        | 2017| Propiedad de Oracle             |
| Java 10       | 2018| Propiedad de Oracle             |
| Java 11       | 2018| Propiedad de Oracle             |
| Java 12       | 2019| Propiedad de Oracle             |
| Java 13       | 2019| Propiedad de Oracle             |
| Java 14       | 2020| Propiedad de Oracle             |
| Java 15       | 2020| Propiedad de Oracle             |
| Java 16       | 2021| Propiedad de Oracle             |
| Java 17       | 2021| Propiedad de Oracle             |
| Java 18       | 2022| Propiedad de Oracle             |
| Java 19       | 2022| Propiedad de Oracle             |
| Java 20       | 2023| Propiedad de Oracle             |
| Java 21       | 2023| Propiedad de Oracle             |

En 11 años, 3 versiones de JAVA.
Desde java 9, una versión cada 6 meses. *** ACUERDO CON LA GENTE DE ORACLE ***

MySQL               --> MariaDB
OpenOffice          --> LibreOffice
Hudson              --> Jenkins

A Oracle JAVA le valía mierda... de la compra de Sun Microsystems solo le importaba una cosa: HARDWARE (SPARC, SO: Solaris, etc)
Oracle era el único gran fabricante de Software que no tenía hardware... no como HP, IBM, etc.

Google habían elegido JAVA para su plataforma Android.... y oracle les estaba puteando de cojones.
Se llegaron a acuerdos:
- Una versión de JAVA cada 6 meses
- Convertir la JVM en una especificación: OracleJDK, OpenJDK, Eclipse Temurin, Amazon Correto
- El J2EE donarlo a la fundación Jakarta para su mnto.... no donaron el nombre JAVA® -> Jakarta Enterprise Edition.

Google no perdonó... en cualquier caso... y a la larga se tomaron la venganza... o podeís llamarlo: Se protegieron frente a nuevos arrebatos por parte de Oracle:
- Montar un lenguaje nuevo ---> Kotlin, que encargaron a la gente de JetBrains (IntelliJ -> Android Studio)
- Extraer el interprete de JS del navegador Chromium, para que se pudieran correr apps JS fuera de un navegador: NODE
    Node es el equivalente a la JVM para JS

---

En versión 9 aparecen muchos cambios en JAVA... junto con las versiones 5 y 8 de JAVA son las versiones que han supuesto un mayor cambio en el lenguaje.

En versión 5 de JAVA: Iterables, Enumeraciones, 
En versión 8 de JAVA: Programación funcional, Optional, Un paquete nuevo para fechas (java.util.Date es una mierda, java.sql.Date es una mierda, Calendar-GregorianCalendar es una mierda, etc -> java.time - que es una adaptación de la antigua librería JodaTime), la posibilidad de definir código estático en interfaces.
En JAVA 9: Proyecto JIGSAW (modularización de JAVA)
            Aparece un nuevo tipo de elemento en JAVA: HAsta Java 8 incluido, en JAVA podíamos crear: paquetes, clases e interfaces
            A partir de JAVA 9, podemos crear: paquetes, clases, interfaces, módulos

            ```java
            module mimodulo {
                requires java.base;
                exports com.mimodulo;
                uses com.mimodulo.MiInterfaz;
                provides com.mimodulo.MiInterfaz with com.mimodulo.MiClase;
            }
            Algunas nuevas funcionalidades DE PALO... para atraer/mantener a los que se estaban escapando de JAVA -> JS....PYTHON
                    Cosas que funcionalmente aportan bastante poco al lenguaje
            Algunos arreglos sobre chapuzas anteriores. Crear métodos privados estáticos en interfaces.

---

# J2EE --> JEE

Se llamaba Java Enterprise Edition --> Jakarta Enterprise Edition

Es una colección de estándares acerca de cómo usar JAVA en entornos empresariales:
- JDBC
- JMS
- JPA
- Servlet
- Servidor de apps(clase web, clase empresarial)


---

## Scala y Kotlin

.scala  --> compilador ---> .class (bytecode) ---> JVM
.kt     --->

---

# MAVEN

Herramienta de automatización de tareas habituales en el desarrollo de software (JAVA):
- Compilar
- Empaquetar
- Ejecutar tests
- Mandar código a SonarQube
- Gestionar las dependencias de mi proyecto

## Estructura de un proyecto MAVEN

proyecto/
    src/
        main/
            java/
            resources/
        test/
            java/
            resources/
    target/
         classes/
         test-classes/
    pom.xml                     Es el archivo de configuración de maven para mi proyecto

## Archivo pom.xml

- Coordenadas del proyecto: groupId, artifactId, version
- Plugins. Maven no hace nada... más que solicitar a otros (plugins) tareas (goals) que deben ejecutar.
  - Compilar -> maven-compiler-plugin
  - Empaquetar -> maven-jar-plugin
  - Ejecutar tests -> maven-surefire-plugin
- Dependencias. Librerías adicionales que mi proyecto necesita para compilar, ejecutar tests, etc.
- Propiedades de configuración -> que normalmente consumen los plugins.

Al añadir nuevos plugins (maven por defecto ya viene con unos 10 preconfigurados), se añaden nuevas tareas (goals) que podemos ejecutar a través de maven.

## Goals por defecto:

clean               Borra la carpeta target
compile             Compilar con javac lo que tengo en src/main/java... generando clases que deja en target/classes
                    Copiar los archivos que tengo en src/main/resources... en target/classes
test-compile        Compilar con javac lo que tengo en src/test/java... generando clases que deja en target/test-classes
                    Copiar los archivos que tengo en src/test/resources... en target/test-classes
test                Ejecutar las clases de pruebas JUNIT que tengo en la carpeta target/test-classes
package             Empaquetar el contenido de target/classes en un jar (war, ear, o nada), para generar el artefacto de mi proyecto.
                    El artefacto se deja en target/<artifactId>-<version>.jar (war, ear, o nada)
install             Copia el artefacto generado en el repositorio local de maven (por defecto ~/.m2/repository) para que 
                    pueda ser usado por otros proyectos maven.
