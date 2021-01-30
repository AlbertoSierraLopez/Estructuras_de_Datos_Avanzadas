package exams.diciembre_2014.usecase;

import java.util.HashMap;
import java.util.Map;

public class Traductor {

    Map<String, Map<String, String>> traductor;

    public Traductor() {
        traductor = new HashMap<>();
    }

    public void añadir(String frase, String traduccion, String idioma) {
        Map<String, String> traducciones = traductor.get(frase);

        if (traducciones == null) {
            traducciones = new HashMap<>();
            traducciones.put(idioma, traduccion);
            traductor.put(frase, traducciones);

        } else {
            traducciones.put(idioma, traduccion);
        }
    }

    public String traducir(String frase, String idioma) throws RuntimeException {
        Map<String, String> traducciones = traductor.get(frase);
        if (traducciones == null || !traducciones.containsKey(idioma)) {
            throw new RuntimeException("No existe traducción para esta frase");
        } else {
            return traducciones.get(idioma);
        }
    }

    public String traducciones(String frase) throws RuntimeException {
        Map<String, String> traducciones = traductor.get(frase);

        if (traducciones == null) {
            throw new RuntimeException("No existe traducción para esta frase");
        } else {
            StringBuilder str = new StringBuilder();
            boolean primero = true;

            for (Map.Entry<String, String> ent : traducciones.entrySet()) {
                if (primero) {
                    primero = false;
                } else {
                    str.append(", ");
                }
                str.append("[" + ent.getValue() + ", " + ent.getKey() + "]");
            }

            return str.toString();
        }
    }

}
