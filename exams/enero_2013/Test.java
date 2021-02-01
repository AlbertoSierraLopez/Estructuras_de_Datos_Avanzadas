package exams.enero_2013;

public class Test {
    public static void main(String[] args) {

        CentroControl control = new CentroControl();

        control.setMulta(new Multa("111111", 50, 1));
        control.setMulta(new Multa("111112", 20, 1));
        control.setMulta(new Multa("111112", 500, 2));
        control.setMulta(new Multa("111113", 200, 2));
        control.setMulta(new Multa("111114", 750, 3));
        control.setMulta(new Multa("111114", 5000, 4));

        System.out.println("Cuantía\n" + control.getCuantia("111114"));

        System.out.println("Radar Más Valioso\n" + control.getRadarMasValioso());
    }
}
