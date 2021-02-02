package exams.junio_2012;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parejas {
    BinarySearchTree<Integer> tree;

    public Parejas(List<Integer> array, Integer m) {
        tree = new LinkedBinarySearchTree<>();
        for (Integer i : array) {
            tree.insert(i);
        }

        boolean primero= true;
        for (Integer i : array) {
            List<Position<Integer>> positions = (List<Position<Integer>>) tree.findAll(m-i);
            if (!positions.isEmpty() && (i != m-i || positions.size() > 1)) {
                // En caso de que i y m sean iguales, que haya encontrado dos positions y no a si mismo
                Position<Integer> pos = positions.get(0);
                // Imprimir pareja
                if (primero) {
                    primero = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print("{" + i + ", " + (m-i) + "}");

                // Eliminar el elemento para evitar duplicados en orden inverso
                tree.remove(pos);
            }
        }
        System.out.println();
    }
}
