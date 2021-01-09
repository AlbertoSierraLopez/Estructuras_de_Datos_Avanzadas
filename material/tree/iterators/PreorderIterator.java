package material.tree.iterators;

import material.Position;
import material.tree.Tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Generic preorder iterator for trees.
 *
 * @param <E>
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, JD. Quintana
 */
public class PreorderIterator<E> implements Iterator<Position<E>> {
    //TODO: Implementar (alumnos)
    private Tree<E> tree;
    private Queue<Position<E>> queue;
    private Predicate<Position<E>> predicate;

    public PreorderIterator(Tree<E> tree) {
        this.tree = tree;
        this.queue = new ArrayDeque<>();
        this.predicate = null;
        build(tree.root());
    }

    public PreorderIterator(Tree<E> tree, Position<E> start) {
        this.tree = tree;
        this.queue = new ArrayDeque<>();
        this.predicate = null;
        build(start);
    }

    public PreorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        this.tree = tree;
        this.queue = new ArrayDeque<>();
        this.predicate = predicate;
        build(start);
    }

    private void build(Position<E> node) {
        if (predicate == null || predicate.test(node)) {
            queue.add(node);
        }
        for (Position<E> child : tree.children(node)) {
            build(child);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Position<E> next() {
        return queue.poll();
    }

}
