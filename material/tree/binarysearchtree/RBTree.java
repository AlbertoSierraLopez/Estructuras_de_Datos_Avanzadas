package material.tree.binarysearchtree;

import material.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/** 
 * Realization of a red-black Tree by extending a binary search tree.
 *
 * @author A. Duarte, J. Vélez
 */

public class RBTree<E> implements BinarySearchTree<E> {

//Esta clase es necesaria para guardar el color de los nodos BTNodes
	
    private class RBInfo<T> implements Comparable<RBInfo<T>>, Position<T>{

    	private boolean isRed; // we add a color field to a BTNode
        private T element;
        private Position<RBInfo<T>> pos;

        RBInfo(T element) {
            this.element = element;
        }
        
        public void setTreePosition(Position<RBInfo<T>> pos){
        	this.pos = pos;
        }
        
        public Position<RBInfo<T>> getTreePosition(){
        	return this.pos;
        }
    	public boolean isRed() {
    		return isRed;
    	}

    	public void setRed() {
    		isRed = true;
    	}

    	public void setBlack() {
    		isRed = false;
    	}

    	public void setColor(boolean color) {
    		isRed = color;
    	}

        public T getElement() {
            return element;
        }

        @Override
        public int compareTo(RBInfo<T> o) {
            if (element instanceof Comparable && o.element instanceof Comparable) {
                Comparable <T> c1 = (Comparable<T>) element;
                return c1.compareTo(o.element);
                
            } else {
                throw new ClassCastException("Element is not comparable");
            }
        }
    }
    

    private class RBTreeIterator<T> implements Iterator<Position<T>> {

        private Iterator<Position<RBInfo<T>>> it;

        public RBTreeIterator(Iterator<Position<RBInfo<T>>> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Position<T> next() {
            Position<RBInfo<T>> aux = it.next();
            return aux.getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    
    private LinkedBinarySearchTree<RBInfo<E>> bst = new LinkedBinarySearchTree<>();
    private ReestructurableBinaryTree<RBInfo<E>> resBT = new ReestructurableBinaryTree<>();

    public RBTree(){
		throw new RuntimeException("Not implemented.");
    }


    /**
     * Creates a BinarySearchTree with the given comparator.
     *
     * @param c the comparator used to sort the nodes in the tree
     */
    public RBTree(Comparator<E> c) {
		throw new RuntimeException("Not implemented.");
    }
    
    @Override
    public Position<E> find(E value) {
		throw new RuntimeException("Not implemented.");
    }

    @Override
    public Iterable<Position<E>> findAll(E value) {
		throw new RuntimeException("Not implemented.");
    }

    @Override
    public boolean isEmpty() {
		throw new RuntimeException("Not implemented.");
    }

   
    @Override
    public int size() {
		throw new RuntimeException("Not implemented.");
    }

	/**
	 * Inserts an element into the RBTree and returns the newly created postion.
	 */
	public Position<E> insert(E e) {
		throw new RuntimeException("Not implemented.");
	}

	/** Remedies a double red violation at a given node caused by insertion. */
	protected void remedyDoubleRed(RBInfo<E> nodeZ) {
		throw new RuntimeException("Not implemented.");
	}

	/** Removes and returns an entry from the dictionary. */
	public E remove(Position<E> posV) throws RuntimeException {
		throw new RuntimeException("Not implemented.");
	}

	/** Remedies a double black violation at a given node caused by removal. */
	protected void remedyDoubleBlack(Position<RBInfo<E>> posR) {
		throw new RuntimeException("Not implemented.");
	}


	/** Returns a red child of a node. */
	protected Position<RBInfo<E>> redChild(Position<RBInfo<E>> pos) {
		throw new RuntimeException("Not implemented.");
	}

	
	
	@Override
	public Iterator<Position<E>> iterator() {
		throw new RuntimeException("Not implemented.");
	}
	
	 /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    private RBInfo<E> checkPosition(Position<E> p) throws RuntimeException {
		throw new RuntimeException("Not implemented.");
    }


	/**
	 * Ejercicio 1: findRange
	 */

	public Iterable<Position<E>> findRange(E minValue, E maxValue) throws RuntimeException{
		throw new RuntimeException("Not implemented.");
	}

	/**
	 * Ejercicio 2: first, last, successors, predecessors
	 */
	public Position<E> first() throws RuntimeException {
		throw new RuntimeException("Not implemented.");
	}

	public Position<E> last() throws RuntimeException {
		throw new RuntimeException("Not implemented.");
	}

	public Iterable<Position<E>> successors(Position<E> pos){
		throw new RuntimeException("Not implemented.");
	}

	public Iterable<Position<E>> predecessors(Position<E> pos){
		throw new RuntimeException("Not implemented.");
	}
	
}
