package material.test.practica2;

import material.Position;
import material.tree.iterators.FrontIterator;
//import material.tree.iterators.InternalNodeIterator;
import material.tree.iterators.PostorderIterator;
import material.tree.iterators.PreorderIterator;
import material.tree.narytree.LinkedTree;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/* Tests hechos por mí */
class IteratorTest {
    private LinkedTree<Integer> tree;
    private Position<Integer>[] pos;

    PostorderIterator<Integer> postIterator;
    PreorderIterator<Integer> preIterator;
    FrontIterator<Integer> frontIterator;
    //InternalNodeIterator<Integer> internalIterator;

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
    }


    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // Not required
    }

    @org.junit.jupiter.api.Test
    void postorderIteratorTest() {
        postIterator = new PostorderIterator<>(tree);
        int[] expected = {5, 1, 6, 9, 10, 11, 7, 2, 8, 3, 4, 0};
        int i = 0;
        while (postIterator.hasNext()) {
            assertEquals(postIterator.next().getElement(), expected[i]);
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void preorderIteratorTest() {
        preIterator = new PreorderIterator<>(tree);
        int[] expected = {0, 1, 5, 2, 6, 7, 9, 10, 11, 3, 8, 4};
        int i = 0;
        while (preIterator.hasNext()) {
            assertEquals(preIterator.next().getElement(), expected[i]);
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void preorderIteratorStartPositionTest() {
        preIterator = new PreorderIterator<>(tree, pos[2]);
        int[] expected = {2, 6, 7, 9, 10, 11};
        int i = 0;
        while (preIterator.hasNext()) {
            assertEquals(preIterator.next().getElement(), expected[i]);
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void preorderIteratorPredicateTest() {
        Predicate<Position<Integer>> isEven = p -> p.getElement() % 2 != 0;
        preIterator = new PreorderIterator<>(tree, pos[0], isEven);
        int[] expected = {1, 5, 7, 9, 11, 3};
        int i = 0;
        while (preIterator.hasNext()) {
            assertEquals(preIterator.next().getElement(), expected[i]);
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void frontIteratorTest() {
        frontIterator = new FrontIterator<>(tree);
        int[] expected = {4, 5, 6, 8, 9, 10, 11};
        int i = 0;
        while (frontIterator.hasNext()) {
            assertEquals(frontIterator.next().getElement(), expected[i]);
            i++;
        }
    }
    /*
    @org.junit.jupiter.api.Test
    void internalNodeIteratorTest() {
        internalIterator = new InternalNodeIterator<>(tree);
        int[] expected = {0, 1, 2, 7, 3};
        int i = 0;
        while (internalIterator.hasNext()) {
            assertEquals(internalIterator.next().getElement(), expected[i]);
            i++;
        }
    }
    */
}
