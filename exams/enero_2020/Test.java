package exams.enero_2020;

import material.Position;
import material.tree.binarytree.LinkedBinaryTree;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {

    private LinkedBinaryTree<Character> tree;
    private Position<Character>[] pos;
    private Examen<Character> ex;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree = new LinkedBinaryTree<>();
        pos = new Position[9];

        pos[0] = tree.addRoot('+');
        pos[1] = tree.insertLeft(pos[0], '*');
        pos[2] = tree.insertRight(pos[0], '*');
        pos[3] = tree.insertLeft(pos[1], '2');
        pos[4] = tree.insertRight(pos[1], '-');
        pos[5] = tree.insertLeft(pos[4], 'a');
        pos[6] = tree.insertRight(pos[4], '1');
        pos[7] = tree.insertLeft(pos[2], '3');
        pos[8] = tree.insertRight(pos[2], 'b');

        ex = new Examen<>();
    }

    @org.junit.jupiter.api.Test
    void findHalf() {
        for (Position<Character> p : ex.findHalf(tree)) {
            System.out.println(p.getElement());
        }
    }

}
