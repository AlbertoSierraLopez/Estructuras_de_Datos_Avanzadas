package exams.junio_2016.usecase;

public class Test {
    public static void main(String[] args) {

        ElectoralCollege instance = new ElectoralCollege();
        instance.addVoter("111111111A", "c/ Clavel", "Juan Perez Perez");
        instance.addVoter("222222222A", "c/ Petunia", "Andres Martin Palomo");
        instance.addVoter("333333333B", "c/ Lila", "Pedro Serrano Vega");
        instance.addVoter("444444444C", "c/ Leonidas", "Daniel Vela Amor");

        instance.makeStationDistribution();

        System.out.println("Votantes");
        Iterable<Voter> resultA = instance.getAllVoters();
        for (Voter v : resultA) {
            System.out.println(v.getNombre());
        }
        System.out.println("\nMesa A (orden)");
        Iterable<Voter> resultB = instance.getStationVoters("A");
        for (Voter v : resultB) {
            System.out.println(v.getNombre());
        }
    }
}
