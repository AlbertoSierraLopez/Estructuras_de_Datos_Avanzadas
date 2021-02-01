package exams.junio_2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Autentificador {

    public Map<String, String> info;

    public Autentificador() {
        info = new HashMap<>();
    }

    public String registrar(String nombre, String password) {
        return info.put(nombre, password);
    }


    public void mostrarInfo() {
        for (Map.Entry<String, String> ent : info.entrySet()) {
            System.out.println("[" + ent.getKey() + ", " + ent.getValue() + "]");
        }
    }

    public boolean ingresar() {
        int intentos = 0;

        try {
            while (intentos < 3) {
                intentos++;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Nombre: ");
                String nombre = reader.readLine();

                if (info.containsKey(nombre)) {

                    System.out.print("Contraseña: ");
                    String password = reader.readLine();

                    if (info.get(nombre).equals(password)) {
                        System.out.println("Bienvenido, " + nombre);
                        return true;
                    }
                }

                System.out.println("Credenciales incorrectos, le quedan " + (3 - intentos) + " intentos");
            }

            System.out.println("Acceso Denegado");
            return false;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
