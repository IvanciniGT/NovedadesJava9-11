module diccionario.app {

    requires diccionario.api; // Donde defino el API de lo que es un diccionario

    // Oye... yo necesito de un módulo que me proporcione una implementación de SuministradorDeDiccionarios
    // Me da igual de donde venga... pero necesito una implementación de SuministradorDeDiccionarios
    // La JVM es la que se encarga de buscar en el classpath un módulo que ofrezca
    // una implementación de SuministradorDeDiccionarios
    // crear una instancia de esa implementación
    // y pasármela a mí cuando la pida. TA CHAN !!!!!!
    uses com.curso.diccionarios.SuministradorDeDiccionarios;

}