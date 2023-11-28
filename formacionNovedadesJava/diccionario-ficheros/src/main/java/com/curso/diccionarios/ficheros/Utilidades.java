package com.curso.diccionarios.ficheros;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    static boolean existeFicheroDeIdioma(String idioma) {
    }

    static Optional<Map<String, List<String>>> leerFicheroDeIdioma(String idioma) {
    }
}
