package exams.enero_2016;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test {
    private LinkedBinaryTree<Integer> tree;
    private Position<Integer>[] pos;
    private BinaryTreeExtension<Integer> ext;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree = new LinkedBinaryTree<>();
        pos = new Position[12];
        ext = new BinaryTreeExtension<>();

        pos[0] = tree.addRoot(15);
        pos[1] = tree.insertLeft(pos[0], 12);
        pos[2] = tree.insertRight(pos[0], 59);
        pos[3] = tree.insertLeft(pos[1], 3);
        pos[4] = tree.insertRight(pos[1], 14);
        pos[5] = tree.insertLeft(pos[3], 1);
        pos[6] = tree.insertRight(pos[3], 9);
        pos[7] = tree.insertLeft(pos[4], 13);
        pos[8] = tree.insertLeft(pos[2], 23);
        pos[9] = tree.insertRight(pos[2], 60);
        pos[10] = tree.insertLeft(pos[9], 29);
        pos[11] = tree.insertRight(pos[9], 80);
    }

    @org.junit.jupiter.api.Test
    void isPerfectTest() {
        assertFalse(ext.isPerfect(tree));
        tree.remove(pos[4]);
        assertTrue(ext.isPerfect(tree));
    }

    @org.junit.jupiter.api.Test
    void isOddTest() {
        assertTrue(ext.isOdd(tree));
        tree.remove(pos[4]);
        assertFalse(ext.isOdd(tree));
    }

}
