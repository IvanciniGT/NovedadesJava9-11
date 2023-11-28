package com.curso.diccionarios.ficheros;

import com.curso.diccionarios.Diccionario;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiccionarioDesdeFicheros implements Diccionario {

    private final Map<String, List<String>> palabras;
    private final String idioma;

    public DiccionarioDesdeFicheros(String idioma, Map<String, List<String>> palabras) {
        this.idioma = idioma;
        this.palabras = palabras;
    }

    @Override
    public String getIdioma() {
        return idioma;
    }

    @Override
    public boolean existe(String palabra) {
        return palabras.containsKey(Utilidades.normalizar(palabra));
    }

    @Override
    public Optional<List<String>> getSignificados(String palabra) { // Java 8: Optional
        return Optional.ofNullable(palabras.get(Utilidades.normalizar(palabra)));
    }

    @Override
    public List<String> getPalabrasSimilaresA(String palabra) {
        // Normalizar la palabra
        String normalizada = Utilidades.normalizar(palabra);
        return palabras.keySet()                   // Coge las palabras (sin significados)
                .parallelStream()                  // Paralelizadamente, para cada palabra   // TO DO ESTO ES JAVA 1.8
                .filter(  palabraEnDiccionario -> Math.abs(palabraEnDiccionario.length() - normalizada.length()) <= Utilidades.DISTANCIA_MAXIMA_ADMITIDA          ) // Si la palabra normalizada y la otra tienen una diferencia de longitud superior a la distancia máxima admitida fuera
                .map(    palabraEnDiccionario -> new PalabraPuntuada(palabraEnDiccionario, Utilidades.distanciaDeLevenshtein(palabraEnDiccionario, normalizada)) ) // Calculo su distancia de Levenshtein con la palabra normalizada
                .filter(  palabraPuntuada -> palabraPuntuada.distancia <= Utilidades.DISTANCIA_MAXIMA_ADMITIDA                                                    ) // Si la distancia es menor o igual que la distancia máxima admitida la mantengo... si no la quito
                .sorted( Comparator.comparing(palabraPuntuada -> palabraPuntuada.distancia )                                                                     ) // Ordenar las palabras por distancia de Levenshtein ascendente
                .limit(  Utilidades.NUMERO_MAXIMO_DE_PALABRAS_SIMILARES                                                                                          ) // Me quedo con las 10 primeras
                .map(    palabraPuntuada-> palabraPuntuada.palabra                                                                                               ) // Me quedo con la palabra (no la distancia
                .collect( Collectors.toList()                                                                                                                    ); // Las meto en una lista
    }

    private static class PalabraPuntuada {
        public final String palabra;
        public final int distancia;
        public PalabraPuntuada(String palabra, int distancia) {
            this.palabra = palabra;
            this.distancia = distancia;
        }
    }
}
