package com.curso.diccionarios.ficheros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilidadesTest {

    @Test
    @DisplayName("Normalizar una palabra")
    void normalizar() {
        String palabraNormalizada = Utilidades.normalizar("Hola");
        String palabraNormalizada2 = Utilidades.normalizar("HOLA");
        String palabraNormalizada3 = Utilidades.normalizar("hola");
        String palabraNormalizada4 = Utilidades.normalizar("HOla");
        assertEquals("hola", palabraNormalizada);
        assertEquals(palabraNormalizada, palabraNormalizada2);
        assertEquals(palabraNormalizada, palabraNormalizada3);
        assertEquals(palabraNormalizada, palabraNormalizada4);
    }

    @Test
    void distanciaDeLevenshtein() {
        String palabra1 = "Hola";
        String palabra2 = "Halo";
        int distancia = Utilidades.distanciaDeLevenshtein(palabra1, palabra2);
        assertEquals(2, distancia);
    }

    @Test
    void existeFicheroDeIdioma() {
        assertTrue(Utilidades.existeFicheroDeIdioma("ES"));
    }

    @Test
    void leerFicheroDeIdioma() {
        var palabras = Utilidades.leerFicheroDeIdioma("ES");
        assertTrue(palabras.isPresent());
        assertFalse(palabras.get().isEmpty());
    }
}