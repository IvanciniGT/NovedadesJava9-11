
# Met. ágiles

Son un reemplazo / alternativa a las metodologías tradicionales de desarrollo de software.

Met. tradicionales eran la metodología en cascada y sus variantes (Espiral, V, etc).

    Toma de requerimientos -> Plan -> Diseño -> Implementación -> Pruebas -> Mantenimiento
                                                                             ^
                                                                             Aquí es donde el cliente veía el 
                                                                             producto por primera vez

                              Tras la toma de requisitos, planificaba todo el proyecto: Diagramas de Gannt, etc.

El gran cambio de mentalidad de las metodologías ágiles consiste en:
Entregar de forma incremental el producto a mi cliente.

Dia 1 - Empiezo el proyecto
Dia 15 - Primer paso a producción del sistema... Sistema 100% funcional...          Sprint 1
         Quizás solo con un 5% de la funcionalidad
         - Batería enorme de pruebas de producción 5%
         - Instalación del sistema en un entorno de pruebas
         - Instalación del sistema en producción
Dia 30 - Segundo paso a producción del sistema... Sistema 100% funcional...         Sprint 2
         +10% de la funcionalidad
         - Batería enorme de pruebas de producción: 5 +10%
         - Instalación del sistema en un entorno de pruebas
         - Instalación del sistema en producción
Dia 45 - Tercer paso a producción del sistema... Sistema 100% funcional...          Sprint 3
         +5% de la funcionalidad
         - Batería enorme de pruebas de producción 15% + 5%
         - Instalación del sistema en un entorno de pruebas
         - Instalación del sistema en producción
Dia 60 - Cuarto paso a producción del sistema... Sistema 100% funcional...
         +15% de la funcionalidad
         - Batería enorme de pruebas de producción 20% + 15%
         - Instalación del sistema en un entorno de pruebas
         - Instalación del sistema en producción

No tiene sentido hablar de porcentajes de funcionalidad, ya que cuándo está un producto de software acabado al 100%? El sistema nace... y le voy añadiendo funcionalidad, cambios, mantenimiento, etc.... hasta que en un momento dado muera... Lo trato como a un ser vivo.

Esta forma de trabajo es incompatible con crear un sistema MONOLITICO. Es decir, un sistema que lo tengo todo en un mismo proyecto, en un mismo repositorio, en un mismo servidor, etc.

Las metodologías ágiles nos obligan a diseñar un sistema mediante componentes débilmente acoplados.
Una arquitectura muy usada a día de hoy que hace uso de este concepto es la de Microservicios.

El software funcionando es la MEDIDA principal de progreso. ---> INDICADOR DE PROGRESO

la MEDIDA principal de progreso es el software funcionando
Lo que de usar para medir el grado de avance (cómo voy) en el proyecto es el "software funcionando".

Antiguamente, los jefes de proyecto medían el progreso, usando técnicas tan sofisticadas como:
- Preguntar al desarrollador: "Cuánto te queda para acabar?" Tiene ya los 5 requisitos que le pedí?
    Los desarrolladores, que somos buena gente, mentimos más que hablamos !
- Contar el número de líneas de código que se han escrito.

"software funcionando"? Software que funciona?
Quién dice que el software funciona? Pruebas.

---

# Dev --> ops

Es una cultura, una filosofía, un movimiento en pro de la AUTOMATIZACION.
- Integración Continua
- Entrega Continua
- Despliegue continuo.

---

# Proyecto 1: Montar una aplicación de consola que permita buscar palabras en un diccionario de un determinado idioma.

Si la palabra existe que me diga que existe y me muestre los significados
Si la palabra no existe que me diga que no existe y me muestre las palabras más parecidas.

## REPOs GIT  /  Cuantos archivos .jar voy a entregar al cliente  /  Cuantos proyectos maven (pom.xml) voy a crear?

Hace años... habríamos creado solo 1:
- 1 repo de git
- 1 proyecto maven
- 1 archivo .jar

Es decir, habríamos creado un sistema MONOLITICO.

Hoy en día esto no nos lo planteamos... ni de coña!
Queremos un sistema com componentes débilmente acoplados, que puedan evolucionar de forma independiente.

4 repos de git / proyectos maven / archivos .jar... al menos estos... sino más!

## Componentes del sistema

### Api de comunicación con el backend -> Interfaces            diccionarios-api.jar

    package com.curso.diccionarios;

    public interface Diccionario {
        String getIdioma();
        boolean existe(String palabra);
        // List<String> getSignificados(String palabraQueExiste);
            // Si alguien es capaz de explicarme cómo se comporta esta función se puede ir del curso.
            // Qué devuelve? 
            //  Manzana? List<String>
            //  Archilococo?
            //      Lista vacía  \ Comportamiento ambiguo. La única forma de salir de la ambigüedad leyendo la documentación :$ !!!!
            //                   / o lo que es mejor... mirando el código fuente de la función. :$$$$ !!!!
            //      null        /
            //      Excepción   CAGADA: NUNCA JAMAS USO una Exception para controlar lógica predecible.
            //                  Una Exception es muy cara de generar: Lo primero es hacer un volcado del ThreadStack
        // La forma buena sale en Java 1.8... Optional
        Optional<List<String>> getSignificados(String palabra);
        List<String> getPalabrasSimilaresA(String palabra);  manana -> [manzana, manzano, mañana, ananá]
                                                             murcielajo -> [murciélago]
                                                             $$983# -> []
    }

    public interface SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionarioDe(String idioma);
    }



### Backend: Gestión de esos Diccionarios... quizás hoy los diccionarios y sus palabras los guardo en ficheros txt ( y mañana en json... yaml... xml... o una BBDD)   ----> diccionario-ficheros.jar

    package com.curso.diccionarios.ficheros.diccionario;

    public class DiccionarioDesdeFichero implements Diccionario {
        // TODO
    }

    package com.curso.diccionarios.ficheros.suministrador;

    public class SuministradorDeDiccionariosDesdeFicheros implements SuministradorDeDiccionarios {

        public SuministradorDeDiccionariosDesdeFicheros() {
            // TODO
        }

        // TODO
        // new DiccionarioDesdeFichero(...); -> Lo guardo en una cache.
    }

### Frontend: Consola... quizás el día de mañana una web, una app móvil, etc.

    package com.curso.app;

    import com.curso.diccionarios.SuministradorDeDiccionarios;
    import com.curso.diccionarios.Diccionario;
    //import com.curso.diccionarios.ficheros.SuministradorDeDiccionariosFactory;

    public class App {
        public static void main(String[] args) {
            // TODO
        }

        public void procesarPeticion(String idioma, String palabra) {
            // algo de código
            boolean existe = false;
            SuministradorDeDiccionarios suministrador = /**/;
            if(suministrador.tienesDiccionarioDe(idioma)) {
                Diccionario diccionario = suministrador.getDiccionarioDe(idioma).get();
                existe = diccionario.existe(palabra);
            }
            new DiccionarioDesdeFichero(...);

            // Más código
        }
    }

    app.jar -> diccionarios-api.jar <- diccionario-ficheros.jar
        |                                    ^
        +------------------------------------+

### Ficheros de palabras en Español

---

# Solid

Son 5 principios de diseño de software que nos ayudan a crear sistemas de software que sean:
- Fáciles de mantener
- Fáciles de evolucionar
- Fáciles de entender

Ya que favorecen la creación de sistemas de software con componentes débilmente acoplados.

SOLID es un acrónimo que se forma con la primera letra de cada uno de los 5 principios... ideado por Robert C. Martin (Uncle Bob)

S - Single Responsability Principle
O - Open Closed Principle
L - Liskov Substitution Principle
I - Interface Segregation Principle
D - Dependency Inversion Principle

---
# D - Dependency Inversion Principle

Los componentes de alto nivel de un sistema no deben depender de implementaciones concretas de los componentes de bajo nivel, sino de abstracciones (interfaces, API).

Para conseguir respectar el principio de inversión de dependencias, podemos usar un patrón de diseño llamado Inyección de Dependencias.

# Inyección de Dependencias

Patrón de desarrollo de software por el cuál una clase no crea instancias de los objetos que necesita... sino que le son suministradas.