module diccionario.ficheros {
    requires diccionario.api; // Donde defino el API de lo que es un diccionario
    // Aquí es donde marco las dependencias de mi módulo
    // Esas dependencias tendrán un reflejo en maven
    //exports com.curso.diccionarios.ficheros; // Donde defino la implementación de un diccionario
    // No quiero que nadie pueda importar los ficheros de este módulo
    // YO, AUTOR DE ESTE MÓDULO, no permito que nadie me toque los ficheros (que los importe)
    provides com.curso.diccionarios.SuministradorDeDiccionarios
            with com.curso.diccionarios.ficheros.SuministradorDeDiccionariosDesdeFicheros;
    // Informa a la JVM de que ofrezco una implementación de la interfaz SuministradorDeDiccionarios
    // De forma que si alguien por ahí le pide una implementación de SuministradorDeDiccionarios
    // y yo estoy en el classpath, la JVM genere una instancia de esta clase y se la devuelva
    // Sin que el otro módulo tenga que saber nada de mí ->
    //         Componentes desacoplados / Respeto al principio de inversión de dependencias
    requires lombok;
}