package exams.noviembre_2020;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;

import java.util.HashSet;
import java.util.Set;

public class CommonAncestorTree <E> {
    private NAryTree<E> tree;

    public CommonAncestorTree(NAryTree<E> tree) {
        this.tree = tree;
    }

    public NAryTree<E> createCommonAncestorTree(Position<E> p1, Position<E> p2) {
        Set<Position<E>> ancestorsP1 = new HashSet<>();

        // Meter todos los ancestros de p1 en un set
        Position<E> cursor = p1;
        while (!tree.isRoot(cursor)) {
            ancestorsP1.add(cursor);
            cursor = tree.parent(cursor);
        }

        // Ir buscando un ancestro común entre los ancestros de p2
        Position<E> ancestor = tree.root(); // El ancestro es por defecto la raíz
        cursor = p2;
        while (ancestor == tree.root() && !tree.isRoot(cursor)) {
            if (ancestorsP1.contains(cursor)) {
                ancestor = cursor;
            }
            cursor = tree.parent(cursor);
        }

        // Copiar el árbol desde ancestro
        NAryTree<E> newTree = new LinkedTree<>();
        Position<E> newRoot = newTree.addRoot(ancestor.getElement());
        copy(newRoot, ancestor, newTree, tree);

        return newTree;
    }

    private void copy(Position<E> newPos, Position<E> pos, NAryTree<E> newTree, NAryTree<E> tree) {
        for (Position<E> child : tree.children(pos)) {
            Position<E> newChild = newTree.add(child.getElement(), newPos);
            copy(newChild, child, newTree, tree);
        }
    }

}
