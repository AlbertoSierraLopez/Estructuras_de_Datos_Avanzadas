package exams.enero_2017;

import material.Position;
import material.tree.narytree.LinkedTree;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

    private LinkedTree<Integer> tree;
    private Position<Integer>[] pos;
    private SumLevels sumLevels;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree = new LinkedTree<>();
        pos = new Position[12];

        pos[0] = tree.addRoot(0);
        pos[1] = tree.add(1, pos[0]);
        pos[2] = tree.add(2, pos[0]);
        pos[3] = tree.add(3, pos[0]);
        pos[4] = tree.add(4, pos[0]);
        pos[5] = tree.add(5, pos[1]);
        pos[6] = tree.add(6, pos[2]);
        pos[7] = tree.add(7, pos[2]);
        pos[8] = tree.add(8, pos[3]);
        pos[9] = tree.add(9, pos[7]);
        pos[10] = tree.add(10, pos[7]);
        pos[11] = tree.add(11, pos[7]);

        sumLevels = new SumLevels();
    }

    @org.junit.jupiter.api.Test
    void sumLevels() {
        assertEquals(40, sumLevels.sumLevels(tree, new int[] {1, 3}));

        assertEquals(26, sumLevels.sumLevels(tree, new int[] {2}));
    }

}
