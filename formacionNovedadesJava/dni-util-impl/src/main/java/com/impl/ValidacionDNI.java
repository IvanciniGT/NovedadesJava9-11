package com.impl;

import com.dni.ErrorEnDNI;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ValidacionDNI {

    ErrorEnDNI error ;
    char letra;
    int numero;

}
