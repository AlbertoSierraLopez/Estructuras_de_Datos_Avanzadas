package exams.junio_2013;

public class TestAutentificador {
    public static void main(String[] args) {

        Autentificador autentificador = new Autentificador();

        autentificador.registrar("Alberto", "1234");
        autentificador.registrar("Alejandro", "0000");
        autentificador.registrar("Amador", "4321");
        autentificador.registrar("Antonio", "0022");

        autentificador.mostrarInfo();

        autentificador.ingresar();

    }
}
