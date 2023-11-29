package com.curso.diccionarios;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
/**
 * @version 1.1.0
 */
public interface Diccionario {
    String getIdioma();
    boolean existe(String palabra);
    Optional<List<String>> getSignificados(@NonNull String palabra);

    /**
     * Devuelve una lista de palabras similares a la palabra pasada como parámetro
     * @param palabra  Palabra de la que se quieren obtener las similares
     * @return         Lista de palabras similares a la palabra pasada como parámetro.
     * @since 1.0.0
     */
    List<String> getPalabrasSimilaresA(@NonNull String palabra);

    /**
     * Devuelve la cantidad de significados de una palabra
     * @param palabra   Palabra de la que se quiere saber la cantidad de significados
     * @return        Cantidad de significados de la palabra. Si la palabra no existe, devuelve un Optional vacío
     * @since 1.1.0
     */
    // FACILITAR LA MANTENIBILIDAD
    default Optional<Integer> getCantidadSignificados(@NonNull String palabra) { // Java 8. Los métodos default en interfaces
        // Este tipo de funciones, son funciones de INSTANCIA... no son como las de que hemos puesto en la interfaz Utilidades.. que son estáticas,
        // Es decir, que no dependen de una instancia. ESTAS SI:
        // Son funciones que heredarán als clases que implementen esta interfaz... y habrá que llamarlas a través de una instancia de la clase
        // CUIDADO CON ESTO, porque poca gente entiende realmente para que vale "default", igual que poca gente entiende
        // Por qué no usar getter y setter es una mala práctica.
        // Las funciones default deberían tener esta pinta:
        throw new RuntimeException("Not implemented yet");
        // En este caso, es una función que se podría calcular desde otras funciones.... CASO EXCEPCIONAL
        //return getSignificados(palabra).map(List::size);
    }
    default void nuevaPalabraTemporal(@NonNull String palabra, @NonNull List<String> significados) {
    }
}
