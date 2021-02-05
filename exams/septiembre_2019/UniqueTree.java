package exams.septiembre_2019;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;

import java.util.*;

public class UniqueTree <E> extends LinkedTree<E> {
    private Map<E, Integer> elementMap;

    public UniqueTree() {
        super();
        elementMap = new HashMap<>();
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        Position<E> root = super.addRoot(e);
        elementMap.put(e, 0);
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        Integer n = elementMap.get(element);
        if (n != null) {
            elementMap.put(element, n+1);
            return null;
        } else {
            Position<E> pos = super.add(element, p);
            elementMap.put(element, 0);
            return pos;
        }
    }

    @Override
    public void remove(Position<E> p) {
        super.remove(p);
        elementMap.remove(p.getElement());
    }

    private class PreorderUniqueIterator<E> implements Iterator<Position<E>> {
        private NAryTree<E> tree;
        private Deque<Position<E>> stack;

        public PreorderUniqueIterator(NAryTree<E> tree) {
            this.tree = tree;
            stack = new ArrayDeque<>();
            stack.push(tree.root());
        }

        public PreorderUniqueIterator(NAryTree<E> tree, Position<E> node) {
            this.tree = tree;
            stack = new ArrayDeque<>();
            stack.push(node);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Position<E> next() {
            Position<E> next = stack.pop();

            Deque<Position<E>> auxStack = new ArrayDeque<>();
            for (Position<E> p : tree.children(next)) {
                auxStack.push(p);
            }
            for (Position<E> s : auxStack) {
                stack.push(s);
            }

            return next;
        }
    }
}
