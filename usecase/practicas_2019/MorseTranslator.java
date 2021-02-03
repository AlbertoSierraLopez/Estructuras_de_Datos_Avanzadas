package usecase.practicas_2019;


import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.Map;
import java.util.HashMap;

public class MorseTranslator {

    private BinaryTree<Character> morseTree;
    Map<Character, Position<Character>> charMap;


    /**
     * Generates a new MorseTranslator instance given two arrays:
     * one with the character set and another with their respective
     * morse code.
     *
     * @param charset
     * @param codes
     */
    public MorseTranslator(char[] charset, String[] codes) {
        morseTree = new LinkedBinaryTree<>();
        charMap = new HashMap<>();
        Position<Character> root = morseTree.addRoot(null);

        for (int i = 0; i < charset.length; i++) {
            Character character = charset[i];
            Position<Character> pos = root;
            for (Character c : codes[i].toCharArray()) {
                switch (c) {
                    case '.': {
                        if (!morseTree.hasLeft(pos)) morseTree.insertLeft(pos, null);
                        pos = morseTree.left(pos);
                        break;
                    }
                    case '-': {
                        if (!morseTree.hasRight(pos)) morseTree.insertRight(pos, null);
                        pos = morseTree.right(pos);
                        break;
                    }
                    default:
                }
            }
            morseTree.replace(pos, character);
            charMap.put(character, pos);
        }
    }

    /**
     * Decodes a String with a message in morse code and returns
     * another String in plaintext. The input String may contain
     * the characters: ' ', '-' '.'.
     *
     * @param morseMessage
     * @return a plain text translation of the morse code
     */
    public String decode(String morseMessage) {
        StringBuilder message = new StringBuilder();
        Position<Character> pos = morseTree.root();

        Character prev = null;
        for (Character c : morseMessage.toCharArray()) {
            switch (c) {
                case '.': {
                    if (morseTree.hasLeft(pos)) {
                        pos = morseTree.left(pos);
                    } else {
                        message.append(pos.getElement());
                        pos = morseTree.left(morseTree.root()); // Si ya no hay más nodos, meto la letra en la que estoy y empiezo a traducir la siguiente, pero este punto en el que estoy ya es el primer punto de la siguientep alabra, no lo puedo tirar
                    }
                    break;
                }
                case '-': {
                    if (morseTree.hasRight(pos)) {
                        pos = morseTree.right(pos);
                    } else {
                        message.append(pos.getElement());
                        pos = morseTree.right(morseTree.root());
                    }
                    break;
                }
                case ' ': {
                    if (prev == ' ') {  // Dos espacios seguidos significan un espacio en la traducción
                        message.append(' ');
                    } else {
                        message.append(pos.getElement());
                    }
                    pos = morseTree.root();
                    break;
                }
                default: {
                    message.append(pos.getElement());
                    pos = morseTree.root();
                    break;
                }
            }
            prev = c;
        }
        if (pos.getElement() != null) message.append(pos.getElement());

        return message.toString();
    }


    /**
     * Receives a String with a message in plaintext. This message
     * may contain any character in the charset.
     *
     * @param plainText
     * @return a morse code message
     */
    public String encode(String plainText) {
        StringBuilder message = new StringBuilder();

        for (Character c : plainText.toCharArray()) {
            if (c == ' ') {
                message.append(' ');
            } else {
                Position<Character> pos = charMap.get(c);
                encodeAux(pos, message);
                if (morseTree.hasLeft(pos) || morseTree.hasRight(pos)) {
                    message.append(' ');    // Carácter con ambigüedad
                }
            }
        }

        return message.toString();
    }

    private void encodeAux(Position<Character> pos, StringBuilder message) {
        if (!morseTree.isRoot(pos)) {
            Position<Character> parent = morseTree.parent(pos);
            encodeAux(parent, message);

            if (morseTree.hasLeft(parent) && morseTree.left(parent) == pos) {   // Pos es hijo izquierdo de su padre
                message.append('.');
            } else {
                message.append('-');
            }
        }
    }


}
