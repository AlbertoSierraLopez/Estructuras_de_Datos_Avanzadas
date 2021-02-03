package exams.noviembre_2020;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CommonAncestorTreeTest {

    /*
                    1
                   / \
                  2   3
                 / \
                4   6
               /   / \
              5   7   8

    */
    private NAryTree<Integer> tree;
    private Position<Integer> p[];

    @BeforeEach
    void setUp() {
        tree = new LinkedTree<>();
        p = new Position[8];
        p[0] = tree.addRoot(1);
        p[1] = tree.add(2, p[0]);
        p[2] = tree.add(3, p[0]);
        p[3] = tree.add(4, p[1]);
        p[4] = tree.add(5, p[3]);
        p[5] = tree.add(6, p[1]);
        p[6] = tree.add(7, p[5]);
        p[7] = tree.add(8, p[5]);
    }


    @org.junit.jupiter.api.Test
    void test1() {
        CommonAncestorTree<Integer> cat = new CommonAncestorTree<>(tree);
        NAryTree<Integer> result = cat.createCommonAncestorTree(p[3], p[7]);
        String expected = "246578";
        StringBuilder stb = new StringBuilder();
        for (Position<Integer> p : result) {
            stb.append(p.getElement());
        }
        assertEquals(expected, stb.toString());
    }

    @org.junit.jupiter.api.Test
    void test2() {
        CommonAncestorTree<Integer> cat = new CommonAncestorTree<>(tree);
        NAryTree<Integer> result = cat.createCommonAncestorTree(p[2], p[7]);
        String expected = "12346578";
        StringBuilder stb = new StringBuilder();
        for (Position<Integer> p : result) {
            stb.append(p.getElement());
        }
        assertEquals(expected, stb.toString());
    }

    @org.junit.jupiter.api.Test
    void test3() {
        CommonAncestorTree<Integer> cat = new CommonAncestorTree<>(tree);
        NAryTree<Integer> result = cat.createCommonAncestorTree(p[6], p[7]);
        String expected = "678";
        StringBuilder stb = new StringBuilder();
        for (Position<Integer> p : result) {
            stb.append(p.getElement());
        }
        assertEquals(expected, stb.toString());
    }

    @org.junit.jupiter.api.Test
    void test4() {
        CommonAncestorTree<Integer> cat = new CommonAncestorTree<>(tree);
        NAryTree<Integer> result = cat.createCommonAncestorTree(p[5], p[7]);
        String expected = "678";
        StringBuilder stb = new StringBuilder();
        for (Position<Integer> p : result) {
            stb.append(p.getElement());
        }
        assertEquals(expected, stb.toString());
    }
}