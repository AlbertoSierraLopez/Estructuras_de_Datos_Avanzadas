package exams.diciembre_2014;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;

public class LinkedTreeExtension<E> {

    public void clear(NAryTree<E> tree) {
        if (!tree.isEmpty()) {
            tree.remove(tree.root());
        }
    }

    public NAryTree<E> copy(NAryTree<E> tree) {
        NAryTree<E> copy = new LinkedTree<>();
        if (!tree.isEmpty()) {
            Position<E> copyRoot = copy.addRoot(tree.root().getElement());
            copyAux(copyRoot, tree.root(), copy, tree);
        }
        return copy;
    }

    private void copyAux(Position<E> copyNode, Position<E> node, NAryTree<E> copyTree, NAryTree<E> tree) {
        for (Position<E> child : tree.children(node)) {
            Position<E> childCopy = copyTree.add(child.getElement(), copyNode);
            copyAux(childCopy, child, copyTree, tree);
        }
    }

}
