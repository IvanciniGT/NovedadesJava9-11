package com.impl;

import com.dni.DNI;
import com.dni.DNIUtils;
import com.dni.ErrorEnDNI;
import com.dni.FormatoDNI;

import java.util.List;
import java.util.Optional;

public class DNIUtilsImpl implements DNIUtils {

    private static final List<Character> SEPARADORES_VALIDOS = List.of('-', ' ');

    @Override
    public Optional<DNI> parsearDNI(String dni) {
        ValidacionDNI validacionDNI = analizarDNI(dni);
        if(validacionDNI.getError()!=null){
            return Optional.empty();
        }
        return Optional.of(new DNI(validacionDNI.getNumero(), validacionDNI.getLetra()));
    }

    @Override
    public Optional<DNI> crearDNI(int numero, char letra) {
        letra = Character.toUpperCase(letra);
        if(letra!=getLetraAsociadaAlNumero(numero)){
            return Optional.empty();
        }
        return Optional.of(new DNI(numero, letra));
    }

    @Override
    public Optional<DNI> crearDNI(int numero) {
        return crearDNI(numero, getLetraAsociadaAlNumero(numero));
    }

    @Override
    public Optional<String> formatear(int dni, FormatoDNI formato) {
        return crearDNI(dni).map(dni1 -> formatear(dni1, formato));
    }

    @Override
    public Optional<String> formatear(String dni, FormatoDNI formato) {
        return parsearDNI(dni).map(dni1 -> formatear(dni1, formato));
    }

    @Override
    public Optional<String> formatear(int numero, char letra, FormatoDNI formato) {
        return crearDNI(numero, letra).map(dni -> formatear(dni, formato));
    }

    @Override
    public String formatear(DNI dni, FormatoDNI formato) {
        String aDevolver = formato.getSeparador()!=null? String.valueOf(formato.getSeparador()) :"" + dni.getLetra();
        String parteNumerica = String.valueOf(dni.getNumero());
        if(formato.isCerosDelante())
            parteNumerica = "00000000".substring(parteNumerica.length()) + parteNumerica;
        if(formato.isPuntos()){
            int posicion = parteNumerica.length() - 3;
            while(posicion>0){
                parteNumerica = parteNumerica.substring(0, posicion) + "." + parteNumerica.substring(posicion);
                posicion-=3;
            }
        }
        return parteNumerica + aDevolver;
    }
    @Override
    public Optional<ErrorEnDNI> validar(String dni) {
        return Optional.ofNullable(analizarDNI(dni).getError());
    }
    private ValidacionDNI analizarDNI(String dni) {
        if(dni==null)
            return ValidacionDNI.builder().error(ErrorEnDNI.DNI_NULO).build();
        if(dni.isEmpty())
            return ValidacionDNI.builder().error(ErrorEnDNI.DNI_VACIO).build();
        if(dni.length()<2)
            return ValidacionDNI.builder().error(ErrorEnDNI.FORMATO_INCORRECTO).build();
        char letra = dni.charAt(dni.length() - 1);
        if (Character.isDigit(letra)) {
            return ValidacionDNI.builder().error(ErrorEnDNI.FALTA_LETRA).build();
        }
        int finalNumero = dni.length() - 1;
        char anterior = dni.charAt(dni.length() - 2);
        if (!Character.isDigit(anterior)) {
            // Tendrá que ser un separador valido
            if(!SEPARADORES_VALIDOS.contains(anterior)) {
                return ValidacionDNI.builder().error(ErrorEnDNI.SEPARADOR_INCORRECTO).build();
            }
            finalNumero--;
        }
        String parteNumerica = dni.substring(0, finalNumero);
        if(!parteNumerica.matches("^(([0-9]{1,8})|([0-9]{1,2}([.][0-9]{3}){2})|([0-9]{1,3}[.][0-9]{3}))$")) {
            if(parteNumerica.matches("^[0-9.]$")){
                return ValidacionDNI.builder().error(ErrorEnDNI.SIGNOS_DE_PUNTOS_INCORRECTOS).build();
            }else{
                return ValidacionDNI.builder().error(ErrorEnDNI.FORMATO_INCORRECTO).build();
            }
        }
        int numero = Integer.parseInt(parteNumerica.replace(".", ""));
        // La letra la podrían haber puesto en minúscula
        letra = Character.toUpperCase(letra);
        if(getLetraAsociadaAlNumero(numero)!=letra){
            return ValidacionDNI.builder().error(ErrorEnDNI.LETRA_INCORRECTA).build();
        }
        return ValidacionDNI.builder().numero(numero).letra(letra).build();
    }

    private static char getLetraAsociadaAlNumero(int numero){
        return "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numero % 23);
    }

}
