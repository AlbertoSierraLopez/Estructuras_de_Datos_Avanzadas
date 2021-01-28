package exams.septiembre_2020.usecase;

public class Main {
    public static void main(String[] args) {

        MarioKart marioKart = new MarioKart();

        marioKart.addObjectUsed("Caparazón Rojo", 100);
        marioKart.addObjectUsed("Caparazón Azul", 75);
        marioKart.addObjectUsed("Seta", 25);
        marioKart.addObjectUsed("Plátano", 150);
        marioKart.addObjectUsed("Rayo", 200);
        marioKart.addObjectUsed("Triple Plátano", 75);

        marioKart.addCupInfo("Copa Champiñón", 50);
        marioKart.addCupInfo("Copa Flor", 100);
        marioKart.addCupInfo("Copa Estrella", 200);
        marioKart.addCupInfo("Copa Especial", 100);

        marioKart.newCupCompleted("Copa Champiñón");
        marioKart.newCupCompleted("Copa Champiñón");
        marioKart.newCupCompleted("Copa Estrella");
        marioKart.newCupCompleted("Copa Estrella");
        marioKart.newCupCompleted("Copa Estrella");
        marioKart.newCupCompleted("Copa Especial");

        System.out.println("Los 3 mejores objetos:");
        for (String item : marioKart.getBestNObjects(3)) {
            System.out.println(item);
        }
        System.out.println();

        marioKart.printCupInfo("Copa Especial");
        System.out.println();

        System.out.println("Las copas completadas más de 2 veces:");
        for (String cup : marioKart.getCupWonNTimes(2)) {
            System.out.println(cup);
        }
    }
}
