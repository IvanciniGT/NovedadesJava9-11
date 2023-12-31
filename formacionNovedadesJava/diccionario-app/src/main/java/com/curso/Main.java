package com.curso;

import com.curso.diccionarios.SuministradorDeDiccionarios;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al diccionario");

        if(args.length != 2) {
            System.out.println("Debes indicar el idioma del diccionario y la palabra a buscar");
            System.out.println("Ejemplo: java -jar diccionario.jar ES hola");
            System.exit(1);
        }

        String idioma = args[0];
        String palabra = args[1];

        boolean tengoAlMenosUnSuministradorDeDiccionarios = false;

        Iterable<SuministradorDeDiccionarios> suministradoresDeDiccionarios = ServiceLoader.load(SuministradorDeDiccionarios.class);
        for(SuministradorDeDiccionarios suministradorDeDiccionarios : suministradoresDeDiccionarios) {
            tengoAlMenosUnSuministradorDeDiccionarios = true;
            if (suministradorDeDiccionarios.tienesDiccionarioDe(idioma)) {
                var diccionario = suministradorDeDiccionarios.getDiccionario(idioma).orElseThrow(); // Java 10 (var... y Optional.orElseThrow)

                List<String> nuevosSignificados = new ArrayList<>();
                nuevosSignificados.add("animal de compañía");
                // Persona 1
                diccionario.nuevaPalabraTemporal("archilococo", nuevosSignificados);
                // Persona 2
                // Ahora no. Devuelvo una copia inmutable de los datos: diccionario.getSignificados("archilococo").ifPresent(List::clear);
                // Persona 3
                diccionario.getSignificados("archilococo").ifPresent(significados ->
                        System.out.println("Significados de archilococo: " + significados));


                if (diccionario.existe(palabra)) {
                    System.out.println("La palabra " + palabra + " existe en el diccionario");
                    var significados = diccionario.getSignificados(palabra);
                    System.out.println("Significados:");
                    significados.orElseThrow().forEach(significado -> System.out.println(" - " + significado)); // JAva 10. Optional.orElseThrow
                } else {
                    System.out.println("La palabra " + palabra + " no existe en el diccionario");
                    System.out.println("Palabras similares:");
                    diccionario.getPalabrasSimilaresA(palabra).forEach(palabraSimilar -> System.out.println(" - " + palabraSimilar));
                }
                System.out.println("Gracias por usar el diccionario.");
                System.exit(0);
            }
        }
        if(!tengoAlMenosUnSuministradorDeDiccionarios) {
            System.out.println("Lo siento, pero no tengo ningún suministrador de diccionarios");
            System.out.println("Gracias por usar el diccionario.");
        }else{
            System.out.println("Lo siento, pero no tengo el diccionario de " + idioma);
            System.out.println("Gracias por usar el diccionario.");
        }
        System.exit(1);
    }
}