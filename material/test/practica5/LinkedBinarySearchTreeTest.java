package material.test.practica5;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedBinarySearchTreeTest {
    BinarySearchTree<Integer> tree;
    Position<Integer>[] pos;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        tree = new LinkedBinarySearchTree<>();
        pos = new Position[7];

        pos[4] = tree.insert(4);
        pos[2] = tree.insert(2);
        pos[5] = tree.insert(5);
        pos[1] = tree.insert(1);
        pos[3] = tree.insert(3);
        pos[6] = tree.insert(6);
        pos[0] = tree.insert(0);
    }

    @org.junit.jupiter.api.Test
    void firstTest() {
        assertEquals(pos[0], tree.first());
        tree.remove(pos[1]);
        assertEquals(pos[0], tree.first());
        tree.remove(pos[0]);
        assertEquals(pos[2], tree.first());
    }

    @org.junit.jupiter.api.Test
    void lastTest() {
        assertEquals(pos[6], tree.last());
        tree.remove(pos[5]);
        assertEquals(pos[6], tree.last());
        tree.remove(pos[6]);
        assertEquals(pos[4], tree.last());
    }

    @org.junit.jupiter.api.Test
    void successorsTest() {
        for (Position<Integer> p : tree.successors(pos[4])) {   // Sucesores de 3
            System.out.println(p.getElement());
        }
    }

    @org.junit.jupiter.api.Test
    void predecessorsTest() {
        for (Position<Integer> p : tree.predecessors(pos[4])) { // Predecesores de 3
            System.out.println(p.getElement());
        }
    }

    @org.junit.jupiter.api.Test
    void findRangeTest() {
        for (Position<Integer> p : tree.findRange(2, 5)) {
            System.out.println(p.getElement());
        }
    }

}
