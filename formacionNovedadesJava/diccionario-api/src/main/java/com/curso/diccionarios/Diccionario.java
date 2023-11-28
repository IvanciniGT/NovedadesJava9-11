package com.curso.diccionarios;

import java.util.List;
import java.util.Optional;

public interface Diccionario {
    String getIdioma();
    boolean existe(String palabra);
    Optional<List<String>> getSignificados(String palabra);
    List<String> getPalabrasSimilaresA(String palabra);
}
