package exams.enero_2016;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTreeExtension <E> {

    public boolean isPerfect(BinaryTree<E> tree) {
        if (!tree.isEmpty()) {
            return isPerfectAux(tree, tree.root());
        } else {
            return true;
        }
    }

    private boolean isPerfectAux(BinaryTree<E> tree, Position<E> node) {
        if (tree.hasLeft(node) && tree.hasRight(node)) {
            return isPerfectAux(tree, tree.left(node)) && isPerfectAux(tree, tree.right(node));
        } else if (tree.isLeaf(node)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOdd(BinaryTree<E> tree) {
        if (!tree.isEmpty() && !tree.isLeaf(tree.root())) {
            if (tree.hasLeft(tree.root()) && tree.hasRight(tree.root())) {
                return descendants(tree, tree.left(tree.root())) > descendants(tree, tree.right(tree.root()));
            }
            if (tree.hasRight(tree.root())) {
                return false;
            }
        }
        return true;
    }

    private int descendants(BinaryTree<E> tree, Position<E> node) {
        int count = 0;
        if (tree.hasLeft(node)) {
            count += descendants(tree, tree.left(node));
        }
        if (tree.hasRight(node)) {
            count += descendants(tree, tree.right(node));
        }
        return count + 1;
    }

    private class InternalNodeIterator<E> implements Iterator<Position<E>> {
        private BinaryTree<E> tree;
        private Deque<Position<E>> nodeDeque;

        public InternalNodeIterator(BinaryTree<E> tree) {
            this.tree = tree;
            nodeDeque = new ArrayDeque<>();
            if (tree.isInternal(tree.root())) {
                nodeDeque.add(tree.root());
            }
        }



        public InternalNodeIterator(BinaryTree<E> tree, Position<E> node) {
            this.tree = tree;
            nodeDeque = new ArrayDeque<>();
            if (tree.isInternal(node)) {
                nodeDeque.add(node);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeDeque.isEmpty();
        }

        @Override
        public Position<E> next() {
            Position<E> pos = nodeDeque.poll();
            for (Position<E> c : tree.children(pos)) {
                if (tree.isInternal(c)) {
                    nodeDeque.add(c);
                }
            }
            return pos;
        }
    }

}
