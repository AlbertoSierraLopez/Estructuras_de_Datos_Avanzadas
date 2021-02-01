package exams.junio_2013;

public class TestDiccionario {
    public static void main(String[] args) {

        Diccionario diccionario = new Diccionario();

        diccionario.insertarPalabra("Derelicto");
        diccionario.insertarPalabra("Colapso");
        diccionario.insertarPalabra("Túmulo");
        diccionario.insertarPalabra("Disoluto");
        diccionario.insertarPalabra("Agón");
        diccionario.insertarPalabra("Égida");
        diccionario.insertarPalabra("Diáspora");
        diccionario.insertarPalabra("Cataclismo");
        diccionario.insertarPalabra("Pavés");
        diccionario.insertarPalabra("Ádyton");
        diccionario.insertarPalabra("Pléyades");

        for (String s : diccionario.autocompletar("Pa")) {
            System.out.println(s);
        }
    }
}
