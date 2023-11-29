package com.curso.diccionarios.ficheros;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Utilidades {

    int DISTANCIA_MAXIMA_ADMITIDA = 2;
    int NUMERO_MAXIMO_DE_PALABRAS_SIMILARES = 10;

    static String normalizar(String palabra) { // Java 8... Métodos static PÚBLICOS en interfaces
        return palabra.toLowerCase().trim();
    }

    static int distanciaDeLevenshtein(String str1, String str2) {
        return computeLevenshteinDistance(str1.toCharArray(), str2.toCharArray());
    }
    private static int computeLevenshteinDistance(char [] str1, char [] str2) { // Java 9: Métodos privados static en interfaces
        // En JAVA 8 dejaron meter método static PERO SOLO PÚBLICOS (=CAGADA) en interfaces
        int [][]distance = new int[str1.length+1][str2.length+1];
        for(int i=0;i<=str1.length;i++)
            distance[i][0]=i;
        for(int j=0;j<=str2.length;j++)
            distance[0][j]=j;
        for(int i=1;i<=str1.length;i++){
            for(int j=1;j<=str2.length;j++){
                distance[i][j]= minimum(distance[i-1][j]+1,
                        distance[i][j-1]+1,
                        distance[i-1][j-1]+
                                ((str1[i-1]==str2[j-1])?0:1));
            }
        }
        return distance[str1.length][str2.length];
    }
    private static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static Optional<URL> getRutaDeFicheroDePalabras(String idioma) {
        return Optional.ofNullable(Utilidades.class.getClassLoader().getResource("diccionario." + idioma + ".txt"));
    }

    static boolean existeFicheroDeIdioma(String idioma) {
        return getRutaDeFicheroDePalabras(idioma).isPresent();
    }

    static Optional<Map<String, List<String>>> leerFicheroDeIdioma(String idioma) {
        Optional<URL> potencialRutaDeFicheroDePalabras = getRutaDeFicheroDePalabras(idioma);
        if(potencialRutaDeFicheroDePalabras.isPresent()) {
            // Tengo fichero... me toca leerlo
            try {
                String contenido = Files.readString(Path.of(potencialRutaDeFicheroDePalabras.get().getPath())); // JAVA 11: Clase Files. Métodos readString y writeString
                return Optional.of(contenido.lines() // JAVA 11. Método lines en String // Partirlo en lineas de texto
                        .parallel() // Para cada linea en paralelo
                        .filter( linea -> !linea.isBlank() ) // JAVA 11. isBlank en String.  Si la linea no está en blanco
                        .map( linea -> linea.split("=") )// Separar la palabra de los significados, por el carácter =
                        .collect( Collectors.toMap(
                                arrayPartes -> normalizar(arrayPartes[0]), // La palabra normalizada
                                arrayPartes -> List.of(arrayPartes[1].split("\\|")), // Los significados separados por el carácter |
                                (lista1DeSignificados, lista2DeSignificados) -> { // Si hay dos palabras iguales, se juntan las 2 listas de significados en 1
                                    return Stream.concat(lista1DeSignificados.stream(), lista2DeSignificados.stream()).collect(Collectors.toList());
                                }
                        ) ));
                // Separar los significados por el carácter |
                // Montar un Map<String, List<String>> con la palabra y los significados
            }catch (IOException e){
                // Esto implicaría un bug en el fichero del diccionario
                e.printStackTrace(); // Esto habría que llevarlo a un log
            }
        }
        return Optional.empty();
    }
}
