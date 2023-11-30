package com.dni;

import java.util.Optional;

public interface DNIUtils {
    Optional<DNI> parsearDNI(String dni);
    Optional<DNI> crearDNI(int numero, char letra);
    Optional<DNI> crearDNI(int numero);
    Optional<ErrorEnDNI> validar(String dni);
    Optional<String> formatear(int dni, FormatoDNI formato);
    Optional<String> formatear(String dni, FormatoDNI formato);
    Optional<String> formatear(int numero, char letra, FormatoDNI formato);
    String formatear(DNI dni, FormatoDNI formato);
}
