package com.curso.diccionarios.ficheros;

import com.curso.diccionarios.Diccionario;
import com.curso.diccionarios.SuministradorDeDiccionarios;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class SuministradorDeDiccionariosDesdeFicheros implements SuministradorDeDiccionarios {

    Map<String, Diccionario> cacheDeDiccionario = new WeakHashMap<>(); // Java 1.2
                                                        // Crea referencias débiles a los datos. Si el GC entra en escena porque hace falta memoria, es libre de disponer de la información aquí guardada.
                                                  // new HashMap<>(); // Estamos montando una cache, que sirve para guardar
                                                  // información que pueda acelerar el acceso a datos
                                                  // El problema de montar una cache es? PREPARA MEMORIA !!!!
    // Vamos a montar una implementación LAZY de la carga de diccionarios

    @Override
    public boolean tienesDiccionarioDe(String idioma) {
        return cacheDeDiccionario.containsKey(idioma) || Utilidades.existeFicheroDeIdioma(idioma);
    }

    @Override
    public Optional<Diccionario> getDiccionario(String idioma) {
        if(!cacheDeDiccionario.containsKey(idioma)) { // Si no lo tengo en la cache
            // Intentaré cargarlo
            Optional<Map<String, List<String>>> palabrasDelDiccionario = Utilidades.leerFicheroDeIdioma(idioma);
            // Java 11   ... solo para que aprendais la función... Mejor montarlo con isPresent
            /*if(!palabrasDelDiccionario.isEmpty()) { // Java 11   ... solo para que aprendais la función... Mejor montarlo con isPresent
                cacheDeDiccionario.put(idioma, new DiccionarioDesdeFicheros(idioma, palabrasDelDiccionario.get()));
            }*/
            palabrasDelDiccionario.ifPresent(palabrasConSignificados -> cacheDeDiccionario.put(idioma, new DiccionarioDesdeFicheros(idioma, palabrasConSignificados)));
        }
        return Optional.ofNullable(cacheDeDiccionario.get(idioma));
    }
}
