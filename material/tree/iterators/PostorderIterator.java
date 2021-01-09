package material.tree.iterators;

import material.Position;
import material.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Generic preorder iterator for trees.
 *
 * @param <E>
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, JD. Quintana
 */
public class PostorderIterator<E> implements Iterator<Position<E>> {
    //TODO: Implementar (alumnos)
    private Tree<E> tree;
    private Deque<Position<E>> stack;
    private Predicate<Position<E>> predicate;

    public PostorderIterator(Tree<E> tree) {
        this.tree = tree;
        this.stack = new ArrayDeque<>();
        this.predicate = null;
        build(tree.root());
    }

    public PostorderIterator(Tree<E> tree, Position<E> start) {
        this.tree = tree;
        this.stack = new ArrayDeque<>();
        this.predicate = null;
        build(start);
    }

    public PostorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        this.tree = tree;
        this.stack = new ArrayDeque<>();
        this.predicate = predicate;
        build(start);
    }

    private void build(Position<E> node) {
        if (predicate == null || predicate.test(node)) {
            stack.push(node);
        }
        for (Position<E> child : tree.children(node)) {
            build(child);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Position<E> next() {
        return stack.pop();
    }
}
