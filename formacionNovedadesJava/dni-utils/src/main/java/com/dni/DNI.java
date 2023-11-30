package com.dni;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class DNI {

    private final int numero;
    private final char letra;

    DNI(int numero, char letra) {
        this.numero = numero;
        this.letra = letra;
    }

}
