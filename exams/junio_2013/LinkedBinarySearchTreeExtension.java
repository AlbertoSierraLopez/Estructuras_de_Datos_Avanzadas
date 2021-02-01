package exams.junio_2013;

import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

public class LinkedBinarySearchTreeExtension<E> extends LinkedBinarySearchTree<E> {

    public LinkedBinarySearchTreeExtension() {
        super();
    }

    public LinkedBinarySearchTree<E> fusion(LinkedBinarySearchTree<E> secondTree) {
        LinkedBinarySearchTree<E> newTree = new LinkedBinarySearchTree<>();
        if (!binTree.isEmpty()) {
            copyTree(binTree.root(), newTree);
        }
        if (!secondTree.isEmpty()) {
            for (Position<E> p : secondTree.successors(secondTree.first())) {
                newTree.insert(p.getElement());
            }
        }
        return newTree;
    }

    private void copyTree(Position<E> pos, LinkedBinarySearchTree<E> newTree) {
        if (binTree.hasLeft(pos)) {
            copyTree(binTree.left(pos), newTree);
        }
        newTree.insert(pos.getElement());
        if (binTree.hasRight(pos)) {
            copyTree(binTree.right(pos), newTree);
        }
    }

    public E median() {
        E[] element = (E[]) new Object[1];
        element[0] = null;
        if (!isEmpty()) {
            medianAux(binTree.root(), 0,1+(size()/2), element);
        }
        return element[0];
    }

    private int medianAux(Position<E> pos, int totalLeft, int median, E[] element) {
        if (pos.getElement() != null && element[0] == null) {
        // Hay que ignorar los nodos basura
        // Si ya se ha encontrado la ,ediana, no se recorre más el árbol
            int nodesLeft = 0;
            if (binTree.hasLeft(pos)) {
                nodesLeft += medianAux(binTree.left(pos), 0, median, element);
            }

            int currentNode = nodesLeft + totalLeft + 1;
            if (currentNode == median) {
                element[0] = pos.getElement();
            }

            int nodesRight = 0;
            if (binTree.hasRight(pos)) {
                nodesRight += medianAux(binTree.right(pos), currentNode, median, element);
            }

            return nodesLeft + nodesRight + 1;
        } else {
            return 0;
        }
    }
}
