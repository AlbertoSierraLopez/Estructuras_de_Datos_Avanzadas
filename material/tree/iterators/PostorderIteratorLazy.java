package material.tree.iterators;

import material.Position;
import material.tree.Tree;

import java.util.*;

/**
 * Generic preorder iterator for trees.
 *
 * @param <E>
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, JD. Quintana
 */
public class PostorderIteratorLazy<E> implements Iterator<Position<E>> {
    //TODO: Implementar (alumnos)
    private Tree<E> tree;
    private Deque<Position<E>> stack;
    Set<Position<E>> visited;

    public PostorderIteratorLazy(Tree<E> tree) {
        this.tree = tree;
        this.stack = new ArrayDeque<>();
        visited = new HashSet<>();
        stack.push(tree.root());
    }

    public PostorderIteratorLazy(Tree<E> tree, Position<E> start) {
        this.tree = tree;
        this.stack = new ArrayDeque<>();
        visited = new HashSet<>();
        stack.push(start);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> node = stack.pop();
        if (visited.contains(node)) {
            return node;
        } else {
            visited.add(node);
            stack.push(node);
            Deque<Position<E>> auxStack = new ArrayDeque<>();
            for (Position<E> child : tree.children(node)) {
                auxStack.push(child);
            }
            while (!auxStack.isEmpty()) {
                stack.push(auxStack.pop());
            }
            return next();
        }
    }
}
