package material.tree.binarytree;

import material.Position;
import material.tree.iterators.BFSIterator;
import material.tree.iterators.InorderBinaryTreeIterator;

import java.util.*;

public class ArrayBinaryTree<E> implements BinaryTree<E> {
    private class BTPos<E> implements Position<E> {
        private E element;
        private int rank;
        private ArrayBinaryTree<E> myTree;

        public BTPos(E element, int rank, ArrayBinaryTree<E> myTree) {
            this.element = element;
            this.rank = rank;
            this.myTree = myTree;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public ArrayBinaryTree<E> getMyTree() {
            return myTree;
        }

        public void setMyTree(ArrayBinaryTree<E> myTree) {
            this.myTree = myTree;
        }
    }

    private BTPos<E>[] array;
    private int size;

    public ArrayBinaryTree() {
        array = (BTPos<E>[]) new BTPos[10];
        array[0] = null;    // La posición 0 no se utiliza
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("The tree is empty");
        }
        return array[1];
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        if (isRoot(v)) {
            throw new RuntimeException("No parent");
        }
        BTPos<E> node = checkPosition(v);
        return array[node.getRank()/2];
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        List<Position<E>> children = new ArrayList<>();
        BTPos<E> node = checkPosition(v);

        if (array[node.getRank()*2] != null) {
            children.add(array[node.getRank()*2]);
        }
        if (array[node.getRank()*2+1] != null) {
            children.add(array[node.getRank()*2+1]);
        }

        return children;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        BTPos<E> node = checkPosition(v);
        return array[node.getRank()*2] != null || array[node.getRank()*2+1] != null;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        BTPos<E> node = checkPosition(v);
        return array[node.getRank()*2] == null && array[node.getRank()*2+1] == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return v == array[1];
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if (array[1] != null) {
            throw new RuntimeException("Tree already has a root");
        }
        BTPos<E> root = new BTPos<>(e, 1, this);
        array[1] = root;
        size++;
        return root;
    }

    @Override
    public Position<E> left(Position<E> p) throws RuntimeException {
        if (!hasLeft(p)) {
            throw new RuntimeException("No left child");
        }
        BTPos<E> node = checkPosition(p);
        return array[node.getRank()*2];
    }

    @Override
    public Position<E> right(Position<E> p) throws RuntimeException {
        if (!hasRight(p)) {
            throw new RuntimeException("No right child");
        }
        BTPos<E> node = checkPosition(p);
        return array[node.getRank()*2+1];
    }

    @Override
    public boolean hasLeft(Position<E> p) {
        BTPos<E> node = checkPosition(p);
        return array[node.getRank()*2] != null;
    }

    @Override
    public boolean hasRight(Position<E> p) {
        BTPos<E> node = checkPosition(p);
        return array[node.getRank()*2+1] != null;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTPos<E> node = checkPosition(p);

        E oldValue = node.getElement();
        node.setElement(e);
        return oldValue;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws RuntimeException {
        BTPos<E> node = checkPosition(p);
        Position<E> sibling;
        if (node.getRank() % 2 == 0) {
            sibling = array[node.getRank()+1];
        } else {
            sibling = array[node.getRank()-1];
        }
        if (sibling == null) {
            throw new RuntimeException("No sibling");
        }
        return sibling;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        BTPos<E> parent = checkPosition(p);
        if (hasLeft(parent)) {
            throw new RuntimeException("Node already has a left child");
        }

        BTPos<E> newNode = new BTPos<>(e, parent.getRank()*2, this);
        array[parent.getRank()*2] = newNode;
        checkSize(newNode.getRank());
        size++;
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        BTPos<E> parent = checkPosition(p);
        if (hasRight(parent)) {
            throw new RuntimeException("Node already has a right child");
        }

        BTPos<E> newNode = new BTPos<>(e, parent.getRank()*2+1, this);
        array[parent.getRank()*2+1] = newNode;
        checkSize(newNode.getRank());
        size++;
        return newNode;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        if (hasLeft(p) && hasRight(p)) {
            throw new RuntimeException("Cannot remove node with two children");
        }

        BTPos<E> parent = checkPosition(p);
        Position<E> child;
        if (hasLeft(p)) {
            child = left(p);
        } else if (hasRight(p)) {
            child = right(p);
        } else {
            child = null;
        }

        if (child != null) {
            BTPos<E> childP = checkPosition(child);
            promoteSubtree(childP);
            childP.setRank(childP.getRank()/2);
            array[childP.getRank()] = childP;
        } else {
            array[parent.getRank()] = null;
        }

        parent.setMyTree(null);
        size--;
        return parent.getElement();
    }

    private void promoteSubtree(Position<E> node) {
        for (Position<E> p : children(node)) {
            promoteSubtree(p);

            BTPos<E> pos = checkPosition(p);
            array[pos.getRank()] = null;
            if (pos.getRank() % 2 == 0) {
                pos.setRank(pos.getRank()/2);
            } else {
                pos.setRank(pos.getRank()/2+1);
            }
            array[pos.getRank()] = pos;
        }
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTPos<E> pos1 = checkPosition(p1);
        BTPos<E> pos2 = checkPosition(p2);

        int p1Rank = pos1.getRank();
        pos1.setRank(pos2.getRank());
        pos2.setRank(p1Rank);

        array[pos1.getRank()] = pos1;
        array[pos2.getRank()] = pos2;
    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        BTPos<E> node = checkPosition(v);
        BinaryTree<E> subtree = new ArrayBinaryTree<>();

        Position<E> newRoot = subtree.addRoot(node.element);
        subTreeAux(node, newRoot, subtree);

        return subtree;
    }

    private void subTreeAux(Position<E> node, Position<E> subNode, BinaryTree<E> subtree) {
        if (hasLeft(node)) {
            Position<E> newNode = subtree.insertLeft(subNode, node.getElement());
            subTreeAux(left(node), newNode, subtree);
        }
        if (hasRight(node)) {
            Position<E> newNode = subtree.insertRight(subNode, node.getElement());
            subTreeAux(right(node), newNode, subtree);
        }
        BTPos<E> pos = checkPosition(node);
        pos.setMyTree(null);
        array[pos.getRank()] = null;
        size--;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        BTPos<E> node = checkPosition(p);
        if (tree == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }
        if (hasLeft(p)) {
            throw new RuntimeException("Node already has a left child");
        }

        Position<E> newNode = insertLeft(p, tree.root().getElement());
        attachAux(tree.root(), newNode, tree);
    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        BTPos<E> node = checkPosition(p);
        if (tree == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }
        if (hasRight(p)) {
            throw new RuntimeException("Node already has a right child");
        }

        Position<E> newNode = insertRight(p, tree.root().getElement());
        attachAux(tree.root(), newNode, tree);
    }

    private void attachAux(Position<E> subNode, Position<E> node, BinaryTree<E> subtree) {
        if (subtree.hasLeft(subNode)) {
            Position<E> newNode = insertLeft(node, subtree.left(subNode).getElement());
            attachAux(subtree.left(subNode), newNode, subtree);
        }
        if (subtree.hasRight(subNode)) {
            Position<E> newNode = insertRight(node, subtree.right(subNode).getElement());
            attachAux(subtree.right(subNode), newNode, subtree);
        }
        subtree.remove(subNode);
    }

    @Override
    public boolean isComplete() {
        for (Position<E> p : this) {
            if (isInternal(p) && (!hasLeft(p) || !hasRight(p))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }

    private BTPos<E> checkPosition(Position<E> v) {
        if (v == null || !(v instanceof BTPos)) {
            throw new RuntimeException("The position is invalid");
        }
        BTPos<E> node = (BTPos<E>) v;
        if (node.getMyTree() != this) {
            throw new RuntimeException("The node doesn't belong to the tree");
        }
        return node;
    }

    // Siempre se asegura de que haya sitio para los hijos del nodo recién insertado
    private void checkSize(int i) {
        if (array.length < i*2+2) {
            array = Arrays.copyOf(array, i*2+2);
        }
    }
}
