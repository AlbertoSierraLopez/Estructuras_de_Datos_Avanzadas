package material.tree.narytree;

import material.Position;
import material.tree.iterators.BFSIterator;

import java.util.*;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez, Jesús Sánchez-Oro
 * @param <E> the elements stored in the tree
 */
public class LCRSTree<E> implements NAryTree<E> {

    private class LCRSNode<E> implements Position<E> {
        private E element;
        private LCRSNode<E> parent;
        private LCRSNode<E> RSibling;
        private LCRSNode<E> LChild;
        private LCRSTree<E> myTree;

        public LCRSNode(E element, LCRSNode<E> parent, LCRSNode<E> RSibling, LCRSNode<E> LChild, LCRSTree<E> myTree) {
            this.element = element;
            this.parent = parent;
            this.RSibling = RSibling;
            this.LChild = LChild;
            this.myTree = myTree;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public LCRSNode<E> getParent() {
            return parent;
        }

        public void setParent(LCRSNode<E> parent) {
            this.parent = parent;
        }

        public LCRSNode<E> getRSibling() {
            return RSibling;
        }

        public void setRSibling(LCRSNode<E> RSibling) {
            this.RSibling = RSibling;
        }

        public LCRSNode<E> getLChild() {
            return LChild;
        }

        public void setLChild(LCRSNode<E> LChild) {
            this.LChild = LChild;
        }

        public LCRSTree getMyTree() {
            return myTree;
        }

        public void setMyTree(LCRSTree<E> myTree) {
            this.myTree = myTree;
        }
    }

    private LCRSNode<E> root;
    private int size;

    public LCRSTree() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        if (isRoot(v)) {
            throw new RuntimeException("The node has not parent");
        }
        LCRSNode<E> node = checkPosition(v);
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        List<Position<E>> children = new ArrayList<>();
        LCRSNode<E> cursor = node.getLChild();

        while (cursor != null) {
            children.add(cursor);
            cursor = cursor.getRSibling();
        }

        return children;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        return node.LChild != null;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        LCRSNode<E> node = checkPosition(v);
        return node.LChild == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        return node == root;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        LCRSNode<E> node = new LCRSNode<>(e, null, null, null, this);
        root = node;
        size = 1;
        return node;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new BFSIterator<>(this);
    }

    @Override
    public E replace(Position<E> p, E e) {
        LCRSNode<E> node = checkPosition(p);
        E oldElem = node.getElement();
        node.setElement(e);
        return oldElem;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        LCRSNode<E> n1 = checkPosition(p1);
        LCRSNode<E> n2 = checkPosition(p2);

        E e1 = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(e1);
    }

    /**
     * Validates the given position, casting it to TreeNode if valid
     *
     * @param p the position to be converted
     * @return the position casted to TreeNode
     * @throws IllegalStateException if the position is not valid
     */
    private LCRSNode<E> checkPosition(Position<E> p)
            throws IllegalStateException {
        if (p == null || !(p instanceof LCRSNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        LCRSNode<E> aux = (LCRSNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        LCRSNode<E> parent = checkPosition(p);
        LCRSNode<E> node = new LCRSNode<>(element, parent, null, null, this);

        LCRSNode<E> cursor = parent.getLChild();
        if (cursor == null) {
            parent.setLChild(node);
        } else {
            while (cursor.getRSibling() != null) {
                cursor = cursor.getRSibling();
            }
            cursor.setRSibling(node);
        }

        size++;
        return node;
    }

    @Override
    public void remove(Position<E> p) {
        LCRSNode<E> node = checkPosition(p);

        if (!isRoot(p)) {
            // Eliminar nodo de los hijos del padre
            LCRSNode<E> parent = node.getParent();
            LCRSNode<E> cursor = parent.getLChild();
            LCRSNode<E> prev = cursor;
            while (cursor != null && cursor != node) {
                prev = cursor;
                cursor = cursor.getRSibling();
            }
            if (cursor != null) {
                prev.setRSibling(cursor.getRSibling());
            }
            cursor.setParent(null);
        } else {
            root = null;
        }

        // Actualizar size
        Iterator<Position<E>> it = new BFSIterator<>(this, node);
        int count = 0;
        while (it.hasNext()) {
            count++;
            checkPosition(it.next()).setMyTree(null);
        }
        size -= count;
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        //TODO: Practica 2 Ejercicio 1
        LCRSNode<E> nOrig = checkPosition(pOrig);
        LCRSNode<E> nDest = checkPosition(pDest);
        if (nOrig == nDest) {
            throw new RuntimeException("Both positions are the same");
        }
        if (isRoot(pOrig)) {
            throw new RuntimeException("Root node can't be moved");
        }

        // Comprobar que pDest no es descendiente de pOrig
        LCRSNode<E> cursor = nDest;
        while (!isRoot(cursor)) {
            if (cursor == nOrig) {
                throw new RuntimeException("Target position can't be a sub tree of origin");
            }
            cursor = cursor.getParent();
        }

        // Realizar el trasvase
        // Eliminar nodo de los hijos del padre
        cursor = nOrig.getParent().getLChild();
        LCRSNode<E> prev = cursor;
        while (cursor != null && cursor != nOrig) {
            prev = cursor;
            cursor = cursor.getRSibling();
        }
        if (cursor != null) {
            prev.setRSibling(cursor.getRSibling());
        }
        cursor.setParent(null);

        // Añadir pOrig como hijo de pDest
        cursor = nDest.getLChild();
        if (cursor == null) {
            nDest.setLChild(nOrig);
        } else {
            while (cursor.getRSibling() != null) {
                cursor = cursor.getRSibling();
            }
            cursor.setRSibling(nOrig);
        }

        nOrig.setParent(nDest);
    }
}
