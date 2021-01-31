package exams.enero_2017;

import material.Position;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickBinaryTree<E> extends LinkedBinaryTree<E> {
    private Map<E, List<Position<E>>> map;

    public QuickBinaryTree() {
        super();
        map = new HashMap<>();
    }

    public Position<E> search(E element) {
        return map.get(element).get(0);
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        Position<E> pos = super.insertLeft(p, e);

        List<Position<E>> list = map.get(e);
        if (list == null) {
            list = new ArrayList<>();
            map.put(e, list);
        }
        list.add(pos);

        return pos;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        Position<E> pos = super.insertRight(p, e);

        List<Position<E>> list = map.get(e);
        if (list == null) {
            list = new ArrayList<>();
            map.put(e, list);
        }
        list.add(pos);

        return pos;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        E e = super.remove(p);

        map.get(e).remove(p);

        return e;
    }
}
