package com.curso.diccionarios.ficheros;

import com.curso.diccionarios.Diccionario;
import com.curso.diccionarios.SuministradorDeDiccionarios;

import java.util.Optional;

public class SuministradorDeDiccionariosDesdeFicheros implements SuministradorDeDiccionarios {
    @Override
    public boolean tienesDiccionarioDe(String idioma) {
        return false;
    }

    @Override
    public Optional<Diccionario> getDiccionario(String idioma) {
        return Optional.empty();
    }
}
