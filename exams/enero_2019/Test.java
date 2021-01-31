package exams.enero_2019;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test {
    private BinaryTree<String> tree;
    private Position<String>[] pos;
    private Examen<String> ex;

    @BeforeEach
    void setUp() {
        tree = new LinkedBinaryTree<>();
        pos = new Position[8];

        pos[0] = tree.addRoot("A");
        pos[1] = tree.insertLeft(pos[0], "B");
        pos[2] = tree.insertRight(pos[0], "C");
        pos[3] = tree.insertLeft(pos[1], "D");
        pos[4] = tree.insertRight(pos[1], "E");
        pos[5] = tree.insertLeft(pos[4], "G");
        pos[6] = tree.insertRight(pos[4], "H");
        pos[7] = tree.insertRight(pos[2], "F");

        ex = new Examen<>();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // Not required
    }

    @org.junit.jupiter.api.Test
    void levelsComplete() {
        List<Integer> result = Arrays.asList(new Integer[] {1, 3, 4});
        assertEquals(result, ex.levelsComplete(tree));

        tree.remove(pos[6]);
        result = Arrays.asList(new Integer[] {1, 4});
        assertEquals(result, ex.levelsComplete(tree));
    }
}
