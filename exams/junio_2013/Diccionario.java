package exams.junio_2013;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diccionario {
    // Las letras del mapa están en mayúscula, y las palabras de los árboles están en minúscula

    private Map<Character, BinarySearchTree<String>> diccionario;

    public Diccionario() {
        diccionario = new HashMap<>();
    }

    public void insertarPalabra(String palabra) {
        Character letra = Character.toUpperCase(palabra.charAt(0));
        BinarySearchTree<String> palabras = diccionario.get(letra);
        if (palabras == null) {
            palabras = new LinkedBinarySearchTree<>();
            diccionario.put(letra, palabras);
        }
        palabras.insert(palabra.toLowerCase());
    }

    public Iterable<String> autocompletar(String palabra) {
        Character letra = Character.toUpperCase(palabra.charAt(0));
        BinarySearchTree<String> palabras = diccionario.get(letra);

        List<String> list = new ArrayList<>();
        if (!palabras.isEmpty()) {
            for (Position<String> pos : palabras.findRange(palabra.toLowerCase(), "z")) {
                if (pos.getElement().startsWith(palabra.toLowerCase())) {
                    list.add(pos.getElement());
                }
            }
        }
        return list;
    }

}
