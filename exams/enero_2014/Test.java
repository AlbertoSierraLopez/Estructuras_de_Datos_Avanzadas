package exams.enero_2014;

import material.Position;
import material.tree.narytree.LinkedTree;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

    private LinkedTree<String> tree1, tree2, tree3;
    private Position<String>[] pos1, pos2, pos3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree1 = new LinkedTree<>();
        pos1 = new Position[6];
        pos1[0] = tree1.addRoot("A");
        pos1[1] = tree1.add("B", pos1[0]);
        pos1[2] = tree1.add("C", pos1[0]);
        pos1[3] = tree1.add("D", pos1[0]);
        pos1[4] = tree1.add("E", pos1[3]);
        pos1[5] = tree1.add("F", pos1[3]);

        tree2 = new LinkedTree<>();
        pos2 = new Position[6];
        pos2[0] = tree2.addRoot("A");
        pos2[1] = tree2.add("D", pos2[0]);
        pos2[2] = tree2.add("C", pos2[0]);
        pos2[3] = tree2.add("B", pos2[0]);
        pos2[4] = tree2.add("F", pos2[1]);
        pos2[5] = tree2.add("E", pos2[1]);

        tree3 = new LinkedTree<>();
        pos3 = new Position[6];
        pos3[0] = tree3.addRoot("A");
        pos3[1] = tree3.add("B", pos3[0]);
        pos3[2] = tree3.add("C", pos3[0]);
        pos3[3] = tree3.add("D", pos3[0]);
        pos3[4] = tree3.add("E", pos3[2]);
        pos3[5] = tree3.add("F", pos3[2]);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // Not required
    }

    @org.junit.jupiter.api.Test
    void equalsTest() {
        LinkedTreeExtension<String> ext = new LinkedTreeExtension<>();
        assertTrue(ext.equals(tree1, tree2));
        assertFalse(ext.equals(tree1, tree3));
    }
}
