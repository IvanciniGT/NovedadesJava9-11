// ESTO ES El proyecto jigSaw de Java 9
// Que añade modularidad a Java

// Módulo: Es una agrupación de paquetes... que distribuyo mediante un JAR
module diccionario.api {

    exports com.curso.diccionarios; // Donde defino el API de lo que es un diccionario
    // Para que otros módulos puedan importar las clases e interfaces públicas de este paquete
    requires lombok; // Requiere el módulo lombok
}