package exams.junio_2012;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        ConjuntoDifuso conjunto = new ConjuntoDifuso(new double[] {1.9, 2.5, 3.2, 4.1, 5.0});
        System.out.println(conjunto.busquedaAproximada(4.1));

        Parejas parejas = new Parejas(new ArrayList<>(Arrays.asList(1, 3, 12, 23, 1, 2, 2)), 4);

    }
}
