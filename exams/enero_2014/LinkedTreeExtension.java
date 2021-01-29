package exams.enero_2014;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.util.Iterator;
import java.util.List;

public class LinkedTreeExtension<E> {

    public boolean equals(LinkedTree<E> t1, LinkedTree<E> t2) {
        if (t1.size() != t2.size()) {
            return false;
        } else {
            return equalsAux(t1, t1.root(), t2, t2.root());
        }
    }

    private boolean equalsAux(LinkedTree<E> t1, Position<E> p1, LinkedTree<E> t2, Position<E> p2) {
        if (p1.getElement().equals(p2.getElement())) {
            List<Position<E>> children1 = (List<Position<E>>) t1.children(p1);
            List<Position<E>> children2 = (List<Position<E>>) t2.children(p2);
            if (children1.size() != children2.size()) {
                return false;
            }

            boolean iguales = true;
            boolean found;
            int i = 0;
            while (iguales && i < children1.size()) {
                found = false;

                int j = 0;
                while (iguales && j < children2.size()) {
                    if (children1.get(i).getElement().equals(children2.get(j).getElement())) {
                        iguales = equalsAux(t1, children1.get(i), t2, children2.get(j));
                        found = true;
                    }
                    j++;
                }

                if (!found) {
                    iguales = false;
                }
                i++;
            }
            return iguales;
        } else {
            return false;
        }
    }

    private boolean equalsAuxAlt(LinkedTree<E> t1, Position<E> p1, LinkedTree<E> t2, Position<E> p2) {
        if (p1.getElement().equals(p2.getElement())) {
            List<Position<E>> children1 = (List<Position<E>>) t1.children(p1);
            List<Position<E>> children2 = (List<Position<E>>) t2.children(p2);

            if (children1.size() == children2.size()) {

                if (children1.containsAll(children2) && children2.containsAll(children1)) {

                    for (Position<E> c1 : children1) {
                        return equalsAuxAlt(t1, c1, t2, children2.get(children2.indexOf(c1)));
                    }
                }
            }
        }

        return false;
    }

    public LinkedTree<E> copy(LinkedTree<E> tree, Position<E> p) {
        LinkedTree<E> newTree = new LinkedTree<>();
        if (!tree.isEmpty()) {
            Position<E> newNode = newTree.addRoot(tree.root().getElement());
            copy(newNode, tree.root(), newTree, tree);
        }
        return newTree;
    }

    private void copy(Position<E> newNode, Position<E> node, LinkedTree<E> newTree, LinkedTree<E> tree) {
        for (Position<E> child : tree.children(node)) {
            Position<E> newChild = newTree.add(child.getElement(), newNode);
            copy(newChild, child, newTree, tree);
        }
    }
}
