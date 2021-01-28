package exams.junio_2014;

import material.Position;
import material.tree.binarytree.BinaryTree;

import javax.swing.text.Element;
import java.util.*;

public class ArrayListBinaryTree<E> implements BinaryTree<E> {

    /* Ejercicio 2: a) */
    private class BTPos<E> implements Position<E> {

        private E element;
        private int pos;
        private int parentPos;
        private int leftPos, rightPos;

        public BTPos(E element, int pos, int parentPos) {
            this.element = element;
            this.pos = pos;
            this.parentPos = parentPos;
            this.leftPos = -1;
            this.rightPos = -1;
        }

        @Override
        public E getElement() {
            return element;
        }
    }

    private List<BTPos<E>> tree;
    private Set<Integer> spaces;
    private int root;
    private int size;

    public ArrayListBinaryTree() {
        tree = (ArrayList<BTPos<E>>) new ArrayList();
        this.spaces = new HashSet<>();
        root = -1;
        size = 0;
    }

    /* Ejercicio 2: b) */
    public void removeSubtree (Position<E> p) {
        BTPos<E> node = checkPosition(p);

        if (hasLeft(p))  removeSubtree(left(p));
        if (hasRight(p)) removeSubtree(right(p));

        tree.remove(node.pos);
        spaces.add(node.pos);
        size--;
    }

    private BTPos<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof BTPos)) {
            throw new RuntimeException("The position is invalid");
        }
        return (BTPos<E>) p;
    }

    /* Ejercicio 2: c) */
    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTPos<E> parent = checkPosition(p);
        if (hasLeft(p)) {
            throw new RuntimeException("The node already has left child.");
        }

        BTPos<E> node;
        if (spaces.isEmpty()) {
            node = new BTPos<>(e, size, parent.pos);  // Si no hay espacios se inserta al final:   [1, 2, 3,  ,  ,  ,  ]
        } else {                                            //                                           size = 3 ^
            node = new BTPos<>(e, spaces.iterator().next(), parent.pos);
            spaces.remove(node.pos);
        }

        parent.leftPos = node.pos;
        tree.set(node.pos, node);
        size++;
        return node;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        return null;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        return null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return false;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return false;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return false;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> left(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> right(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public boolean hasLeft(Position<E> p) {
        return false;
    }

    @Override
    public boolean hasRight(Position<E> p) {
        return false;
    }

    @Override
    public E replace(Position<E> p, E e) {
        return null;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        return null;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {

    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        return null;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {

    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {

    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return null;
    }
}
