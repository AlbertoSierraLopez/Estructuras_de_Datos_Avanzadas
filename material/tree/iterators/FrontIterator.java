package material.tree.iterators;

import material.Position;
import material.tree.Tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * Front iteartor for trees.
 *
 * @param <T>
 * @author jvelez, JD. Quintana
 */
public class FrontIterator<T> implements Iterator<Position<T>> {
    private Tree<T> tree;
    private Queue<Position<T>> queue;

    public FrontIterator(Tree<T> tree) {
        this.tree = tree;
        queue = new ArrayDeque<>();
        queue.add(tree.root());
    }

    public FrontIterator(Tree<T> tree, Position<T> node) {
        this.tree = tree;
        queue = new ArrayDeque<>();
        queue.add(node);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breath-first search
     */
    @Override
    public Position<T> next() {
        Position<T> node;
        do {
            node = queue.poll();
            for (Position<T> child : tree.children(node)) {
                queue.add(child);
            }
        } while (tree.isInternal(node));
        return node;
    }

}
