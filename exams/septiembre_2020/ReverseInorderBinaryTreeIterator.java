package exams.septiembre_2020;

import material.Position;
import material.tree.Tree;
import material.tree.binarytree.BinaryTree;

import java.util.*;

public class ReverseInorderBinaryTreeIterator<T> implements Iterator<Position<T>> {

    private BinaryTree<T> tree;
    private Deque<Position<T>> nodeStack;

    public ReverseInorderBinaryTreeIterator(BinaryTree<T> tree) {
        this.tree = tree;
        nodeStack = new ArrayDeque<>();
        goToLastInRight(tree.root());
    }


    public ReverseInorderBinaryTreeIterator(BinaryTree<T> tree, Position<T> node) {
        this.tree = tree;
        nodeStack = new ArrayDeque<>();
        goToLastInRight(node);
    }

    private void goToLastInRight(Position<T> node) {
        while (tree.hasRight(node)) {
            nodeStack.push(node);
            node = tree.right(node);
        }
        nodeStack.push(node);
    }

    @Override
    public boolean hasNext() {
        return !nodeStack.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breath-first search
     */
    @Override
    public Position<T> next() {
        Position<T> nextNode = nodeStack.pop();
        if (tree.hasLeft(nextNode)) {
            goToLastInRight(tree.left(nextNode));
        }
        return nextNode;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not implemented.");
    }

}
