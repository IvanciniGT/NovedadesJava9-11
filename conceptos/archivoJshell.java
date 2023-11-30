import java.util.List;

public class App {
    public static void main(String[] args) {
        String nombre = "Ivan";
        System.out.println(nombre);
        List<Integer> numeros = List.of(1, 2, 3, 4);
        System.out.println(volumenDeUnPrisma(1, 2, 3));
        volumenDeUnPrisma(1, 2, 3);
    }
    static double volumenDeUnPrisma ( double base1, double base2, double altura){
        return altura * areaDeLaBase(base1, base2);
    }
    static double areaDeLaBase ( double lado1, double lado2){
        return lado1 * lado2;
    }
}