package material.test.practica3;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.BinaryTreeUtils;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeUtilsTest {
    private BinaryTreeUtils<Character> utils;
    private LinkedBinaryTree<Character> tree;
    private Position<Character>[] pos;

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

        utils = new BinaryTreeUtils<>(tree);
    }

    @org.junit.jupiter.api.Test
    void mirrorTest() {
        BinaryTree<Character> mirroredTree = utils.mirror();

        List<Character> treeString = new ArrayList<>();
        for (Position<Character> p : tree) {
            treeString.add(p.getElement());
        }
        List<Character> mirroredTreeString = new ArrayList<>();
        for (Position<Character> p : mirroredTree) {
            mirroredTreeString.add(p.getElement());
        }

        for (int i = 0; i < mirroredTreeString.size(); i++) {
            assertEquals(mirroredTreeString.get(i), treeString.get(treeString.size()-1-i));
        }
    }

    @org.junit.jupiter.api.Test
    void containsTest() {
        assertTrue(utils.contains('b'));
        assertTrue(utils.contains('-'));
        assertFalse(utils.contains('x'));
        assertFalse(utils.contains('%'));
    }

    @org.junit.jupiter.api.Test
    void levelTest() {
        assertEquals(1, utils.level(pos[0]));
        assertEquals(3, utils.level(pos[8]));
        assertEquals(4, utils.level(pos[5]));
    }
}
