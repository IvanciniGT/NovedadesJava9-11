package com.curso;

import com.curso.diccionarios.SuministradorDeDiccionarios;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al diccionario");

        if(args.length == 2) {
            System.out.println("Debes indicar el idioma del diccionario y la palabra a buscar");
            System.out.println("Ejemplo: java -jar diccionario.jar ES hola");
            System.exit(1);
        }

        String idioma = args[0];
        String palabra = args[1];

        Iterable<SuministradorDeDiccionarios> suministradoresDeDiccionarios = ServiceLoader.load(SuministradorDeDiccionarios.class);
        for(SuministradorDeDiccionarios suministradorDeDiccionarios : suministradoresDeDiccionarios) {
            if (suministradorDeDiccionarios.tienesDiccionarioDe(idioma)) {
                var diccionario = suministradorDeDiccionarios.getDiccionario(idioma).get(); // Java 10
                if (diccionario.existe(palabra)) {
                    System.out.println("La palabra " + palabra + " existe en el diccionario");
                    var significados = diccionario.getSignificados(palabra);
                    System.out.println("Significados:");
                    significados.get().forEach(significado -> System.out.println(" - " + significado));
                } else {
                    System.out.println("La palabra " + palabra + " no existe en el diccionario");
                    System.out.println("Palabras similares:");
                    diccionario.getPalabrasSimilaresA(palabra).forEach(palabraSimilar -> System.out.println(" - " + palabraSimilar));
                }
                System.out.println("Gracias por usar el diccionario.");
                System.exit(0);
            }
        }
        // No he encontrado el diccionario en ninguno de los suministradores que tengo
        System.out.println("Lo siento, pero no tengo el diccionario de " + idioma);
        System.out.println("Gracias por usar el diccionario.");
        System.exit(1);
    }
}