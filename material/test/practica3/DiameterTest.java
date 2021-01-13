package material.test.practica3;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.BinaryTreeUtils;
import material.tree.binarytree.LinkedBinaryTree;
import usecase.practica3.Diameter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiameterTest {
    BinaryTree<Integer> tree1;
    BinaryTree<Integer> tree2;
    Position<Integer>[] pos1;
    Position<Integer>[] pos2;
    Diameter diameter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree1 = new LinkedBinaryTree<>();
        tree2 = new LinkedBinaryTree<>();
        pos1 = new Position[8];
        pos2 = new Position[7];
        diameter = new Diameter();

        /* Tree 1 */
        pos1[0] = tree1.addRoot(1);
        pos1[1] = tree1.insertLeft(pos1[0], 2);
        pos1[2] = tree1.insertRight(pos1[0], 3);
        pos1[3] = tree1.insertRight(pos1[1], 4);
        pos1[4] = tree1.insertLeft(pos1[2], 5);
        pos1[5] = tree1.insertRight(pos1[2], 6);
        pos1[6] = tree1.insertLeft(pos1[4], 7);
        pos1[7] = tree1.insertRight(pos1[4], 8);

        /* Tree 2 */
        pos2[0] = tree2.addRoot(1);
        pos2[1] = tree2.insertRight(pos2[0], 2);
        pos2[2] = tree2.insertLeft(pos2[1], 3);
        pos2[3] = tree2.insertRight(pos2[1], 4);
        pos2[4] = tree2.insertLeft(pos2[2], 5);
        pos2[5] = tree2.insertRight(pos2[2], 6);
        pos2[6] = tree2.insertRight(pos2[3], 7);
    }

    @org.junit.jupiter.api.Test
    void diameterTest() {
        assertEquals(6, diameter.evalDiameter(tree1));
        assertEquals(5, diameter.evalDiameter(tree2));
    }
}
