import java.util.function.*;        // JAVA 1.8
import java.util.stream.Collectors;
// Function<T,R>    Representa una función que acepta un argumento de tipo T y devuelve un resultado de tipo R.
// Consumer<T>      Representa una operación que acepta un único argumento de tipo T y no devuelve ningún resultado (void)
// Supplier<T>      Representa una operación que no recibe ningún argumento, pero devuelve un resultado de tipo T.
// Predicate<T>     Representa una operación que acepta un argumento de tipo T y devuelve un valor booleano (true o false).
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramacionFuncional {

    public static void main(String[] args) {
        String nombre = "Felipe";
        Function<Integer, Integer> miOperacion = ProgramacionFuncional::doblar;   // En JAVA 1.8 aparece un nuevo operador que permite referenciar funciones ::
        System.out.println(miOperacion.apply(10));


        System.out.println(doblar(20));  // Lo primero que se ejecuta es: el método doblar

        ProgramacionFuncional pf = new ProgramacionFuncional();
        miOperacion = pf::triplar;
        System.out.println(miOperacion.apply(10)); //
    
        System.out.println(pf.triplar(10));

        imprimirResultadoDeOperacion(ProgramacionFuncional::doblar, 10); // Aquí lo único que se ejecuta es: imprimirResultadoDeOperacion
        imprimirResultadoDeOperacion(pf::triplar, 30);

        // Desde que aparece la programación funcional en JAVA, TODO EL API de JAVA empieza a migrar a programación funcional

        // Para qué creamos funciones (es programación procedural)? Para reutilizar código... o mantenerlo mejor organizado.
        // Eso antes de tener programación funcional. Ahora TAMBIEN para pasarlas como parámetros a otras funciones.
        // Hay veces que solo quiero crear funciones para pasarlas como parámetros a otras funciones.... y no me trae cuenta (me complica la lectura) crearlas como métodos tradicionales.

        // En JAVA 1.8 aparece un segundo OPERADOR: OPERADOR LAMBDA
        // El operador LAMBDA -> me permitir definir expresiones lambda.
        // Qué es una expresión lambda?
        // Una expresión lambda es antetodo una expresión!

        String texto2 = "adios"; // Statement: Declaración, Sentencia, Enunciado. En Español Sentencia=Enunciado=Frase, Oración
        int numero = 5 + 7;      // Otro Statement
                     ///// Una expresión
        // Expresión: trozo de código que devuelve un valor.

        // Una lambda es un trozo de código que devuelve una referencia una función anónima creada 
        // dentro del statement en tiempo de ejecución.
        // CADA VEZ QUE VEA UNA FLECHA empiezo diciendo: DEFINE UNA FUNCION QUE ...
        Function<Integer, Integer> miOtraOperacion = (Integer numero2) -> {   // El tipo de la respuesta (return) se infiere
            return numero2 * 3;
        };
        Function<Integer, Integer> miOtraOperacion2 = (numero2) -> {  // Puedo adicionalmente inferir el tipo del dato de entrada
            return numero2 * 3;
        };
        Function<Integer, Integer> miOtraOperacion3 = numero2 -> { 
            return numero2 * 3;
        };
        Function<Integer, Integer> miOtraOperacion4 = numero2 -> numero2 * 3;
        Function<Integer, Integer> miOtraOperacion5 = (var numero2) -> numero2 * 3;

        imprimirResultadoDeOperacion(miOtraOperacion, 7);
        imprimirResultadoDeOperacion(num -> num * 9, 7);  // Me facilita la legibilidad... El no tener que ir a otro sitio a ver que hace la función
        imprimirResultadoDeOperacion(pf::porNueve, 7);


        // Me voy a crear una Lista de numeros
        /*
        List<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        */
        //List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10); // JAVA 1.5. Me obliga a meter una importación de java.util.Arrays
        // JAVA 9. teniendo en cuenta que desde java 8, en las interfaces se podían empezar a definir funciones estáticas.
        // Ojo... Las colecciones creadas con métodos .of son inmutables
        List<Integer> numeros = List.of(1,2,3,4,5,6,7,8,9,10); // JAVA 9. Me obliga a meter una importación de java.util.List

        // Pre Java 1.5 como la itero? 
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println(numeros.get(i));
        }
        // Desde JAVA 1.5, como lo itero? For-each
        for (Integer numero2 : numeros) {
            System.out.println(numero2);
        }
        // Desde java 1.8, como lo itero? .forEach funcional
        numeros.forEach(System.out::println);
        numeros.forEach(dato -> System.out.println(" - "+dato));

        // En java 1.8, toda colección se puede transforma en un Stream: Es un objeto que alberga una 
        // secuencia de valores ( igual que un list, o un array, o un set, o un map)... pero con métodos de programación funcional (map-reduce)
        List<Integer> listaNueva= numeros
            .parallelStream()               // Para cada numero
            .map(n -> n*5)                  // Lo multiplico por 5
            .filter(n -> n%2==0)            // Me quedo solo con los pares
            .collect(Collectors.toList());

        //El equivalente en programacion imperativa sería:
        List<Integer> listaNueva2 = new ArrayList<>(); // Si meto en mi máquina 8 núcleos este código a cuanto veo la CPU? 12,5% (1/8)
        for (Integer numero2 : numeros) {
            if (numero2 % 2 == 0) {
                listaNueva2.add(numero2 * 5);
            }
        }

        listaNueva.forEach(System.out::println);
        listaNueva2.forEach(System.out::println);

    }

    public static void imprimirResultadoDeOperacion(Function<Integer, Integer> operacion, int numero) {
        System.out.println(operacion.apply(numero));
    }

    public static int doblar(int numero) {
        return numero * 2;
    }

    public int triplar(int numero) {
        return numero * 3;
    }

    public int porNueve(int numero) {
        return numero * 9;
    }
}

// Puedo ejecutar directamente este fichero en la JVM... desde JAVA 11
// java ProgramacionFuncional.java // Para facilitar el aprendizaje de JAVA... 
//  que creamos programas de 1 fichero y nos evitamos el proceso de compilación
