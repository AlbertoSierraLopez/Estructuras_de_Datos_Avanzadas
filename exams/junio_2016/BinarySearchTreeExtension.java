package exams.junio_2016;

import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;
import material.tree.narytree.LinkedTree;

public class BinarySearchTreeExtension<E> extends LinkedBinarySearchTree<E> {

    public LinkedTree<E> toLinkedTree() {
        LinkedTree<E> lTree = new LinkedTree<>();
        if (!this.binTree.isEmpty()) {
            Position<E> lPos = lTree.addRoot(this.binTree.root().getElement());
            toLinkedTreeAux(lPos, this.binTree.root(), lTree);
        }
        return lTree;
    }

    private void toLinkedTreeAux(Position<E> lPos, Position<E> bPos, LinkedTree<E> lTree) {
        for (Position<E> bChild : this.binTree.children(bPos)) {
            Position<E> lChild = lTree.add(bChild.getElement(), lPos);
            toLinkedTreeAux(lChild, bChild, lTree);
        }
    }

    public void removeRange(E min, E max) {
        for (Position<E> pos : this.findRange(min, max)) {
            this.remove(pos);
        }
    }

    public void removeRangeAlt(E min, E max) {
        Position<E> pos = findGreaterOrEqual(min);
        E oldE;
        while (comparator.compare(pos.getElement(), max) <= 0) {
            oldE = pos.getElement();
            remove(pos);
            pos = findGreaterOrEqual(oldE);
        }
    }

    private Position<E> findGreaterOrEqual(E min) {
        Position<E> pos = treeSearch(min, binTree.root());
        while (pos.getElement() == null || comparator.compare(pos.getElement(), min) < 0) {
            pos = binTree.parent(pos);
        }
        return pos;
    }



}
