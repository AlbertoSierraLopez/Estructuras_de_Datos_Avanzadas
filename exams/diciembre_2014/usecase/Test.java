package exams.diciembre_2014.usecase;

public class Test {
    public static void main(String[] args) {

        Traductor traductor = new Traductor();
        traductor.añadir("Hola", "Hello", "Ingles");
        traductor.añadir("Pulse aqui", "Press here to log in", "Ingles");
        traductor.añadir("Pulse aqui", "Cliquez ici pour acceder", "Frances");
        traductor.añadir("Pulse aqui", "Klicken sie hier um auf", "Aleman");

        System.out.println(traductor.traducir("Pulse aqui", "Ingles"));
        try {
            System.out.println(traductor.traducir("Adios", "Ingles"));
            System.out.println(traductor.traducir("Hola", "Aleman"));
        } catch (RuntimeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println(traductor.traducciones("Pulse aqui"));
    }
}
