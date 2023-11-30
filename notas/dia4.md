# Librería para trabajar con DNIs Españoles.

- Funciones
    interface DNIUtils
        Optional<DNI> parsearDNI(String dni)
        Optional<DNI> crearDNI(int numero, char letra)
        Optional<DNI> crearDNI(int numero)
        Optional<ErrorEnDNI> validar(String dni)
        Optional<String> normalizar/formatear(int dni, FormatoDeDNI)
        Optional<String> normalizar/formatear(String dni, FormatoDeDNI) // murcielago
        Optional<String> normalizar/formatear(int numero, char letra, FormatoDeDNI)
        String normalizar/formatear(DNI dni, FormatoDeDNI)

De un DNI sacar el número, la letra 

    class FormatoDeDNI {
        mayusculas
        puntos
        ceros
        seperador
    }
    class DNI{
        getNumero()
        getLetra()
    }
    interfaz ErrorEnDNI{
        NúmeroMuyGrande
        Letra incorrecta
        Separador inválido
        Error puntuación
        Formato inválido
    }

23000002 | 23
         ----------
       2   1000000
       ^
El resto estaría entre 0-22 

LetrasControlDNI = "TRWAGMYFPDXBNJZSQVHLCKE"


SJDHSKSHDF92
12.345.678-T
12.345.678 T
12.345.678$T MAL
12345678T
00123456T
123456T

12.345.678 T
12345678 T

12.345678 T
12345.678 T
123.45678 T




# JShell JAVA 9

# Java 11 se rediseña completamente el API de petición HTTP, aplicándose el patrón de diseño Builder.

- HttpRequest
- HttpResponse
