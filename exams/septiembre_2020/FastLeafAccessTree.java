package exams.septiembre_2020;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.util.HashSet;
import java.util.Set;

public class FastLeafAccessTree<E> extends LinkedTree<E> {

    Set<Position<E>> leaves;

    public FastLeafAccessTree() {
        super();
        leaves = new HashSet<>();
    }

    public Iterable<Position<E>> getLeaves() {
        return leaves;
    }


    /* Los únicos métodos de LinkedTree que cambian: */
    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        Position<E> node = super.addRoot(e);
        leaves.add(node);
        return node;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        Position<E> node = super.add(element, p);
        leaves.add(node);
        leaves.remove(p);
        return node;
    }

    @Override
    public void remove(Position<E> p) {
        super.remove(p);
        leaves.remove(p);
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        super.moveSubtree(pOrig, pDest);
        leaves.add(pOrig);
        leaves.remove(pDest);
    }
}
