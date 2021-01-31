package exams.enero_2020.usecase;

public class Main {
    public static void main(String[] args) {
        LaResistencia resistencia = new LaResistencia();

        resistencia.addVisit("M. Rajoy", 12000, "Marzo");
        resistencia.addVisit("Antonio Larroca", 500000, "Junio");
        resistencia.addVisit("Jorge Niño-Grande", 20000, "Junio");
        resistencia.addVisit("Gerardo Picado", 1000000, "Septiembre");
        resistencia.addVisit("Santi Caballo", 75000, "Diciembre");
        resistencia.addVisit("Horse Luis", 1000000, "Septiembre");

        System.out.println("Over Median");
        for (String famoso : resistencia.overMedian()) {
            System.out.println(famoso);
        }

        System.out.println("\nMoney in Junio");
        System.out.println(resistencia.moneyInMonth("Junio") + " $");

        System.out.println("\nVisits in Junio");
        for (String famoso : resistencia.visitsInMonth("Junio")) {
            System.out.println(famoso);
        }
    }
}
