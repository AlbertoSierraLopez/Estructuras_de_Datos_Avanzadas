package exams.septiembre_2020;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.util.HashSet;
import java.util.Set;

public class FastLeafAccessTree<E> {

    LinkedTree<E> tree;
    Set<Position<E>> leaves;

    public FastLeafAccessTree() {
        tree = new LinkedTree<>();
        leaves = new HashSet<>();
    }

    public Iterable<Position<E>> getLeaves() {
        return leaves;
    }


    /* Los únicos métodos de LinkedTree que cambian: */

    public Position<E> addRoot(E e) throws RuntimeException {
        Position<E> node = tree.addRoot(e);
        leaves.add(node);
        return node;
    }

    public Position<E> add(E element, Position<E> p) {
        Position<E> node = tree.add(element, p);
        leaves.add(node);
        leaves.remove(p);
        return node;
    }

    public void remove(Position<E> p) {
        leaves.remove(p);
        tree.remove(p);
    }

    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        leaves.add(pOrig);
        leaves.remove(pDest);
        tree.moveSubtree(pOrig, pDest);
    }
}
