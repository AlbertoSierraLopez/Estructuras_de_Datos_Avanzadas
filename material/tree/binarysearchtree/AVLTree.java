package material.tree.binarysearchtree;

import material.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * AVLTree class - implements an AVL Tree by extending a binary search tree.
 *
 * @author A. Duarte, J. Vélez
 */
public class AVLTree<E> implements BinarySearchTree<E> {
	
	//We need this class to store the height of each BTNode
    private class AVLInfo<T> implements Comparable<AVLInfo<T>>, Position<T>{

        private int height;
        private T element;
        private Position<AVLInfo<T>> pos;

        AVLInfo(T element) {
            this.element = element;
            this.pos = null;
            this.height = 1;
        }
        
        public void setTreePosition(Position<AVLInfo<T>> pos){
        	this.pos = pos;
        }
        
        public Position<AVLInfo<T>> getTreePosition(){
        	return this.pos;
        }


        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public T getElement() {
            return element;
        }

        @Override
        public int compareTo(AVLInfo<T> o) {
            if (element instanceof Comparable && o.element instanceof Comparable) {
                Comparable <T> c1 = (Comparable<T>) element;
                return c1.compareTo(o.element);
                
            } else {
                throw new ClassCastException("Element is not comparable");
            }
        }

        @Override
        public String toString(){
            return this.getElement().toString();
        }
    }
    

    private class AVLTreeIterator<T> implements Iterator<Position<T>> {

        private Iterator<Position<AVLInfo<T>>> it;

        public AVLTreeIterator(Iterator<Position<AVLInfo<T>>> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Position<T> next() {
            Position<AVLInfo<T>> aux = it.next();
            return aux.getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    
    private LinkedBinarySearchTree<AVLInfo<E>> bst;
    private ReestructurableBinaryTree<AVLInfo<E>> resBT;


    public AVLTree(){
    	this(new DefaultComparator<>());
    }

    /**
     * Creates a BinarySearchTree with the given comparator.
     *
     * @param c the comparator used to sort the nodes in the tree
     */
    public AVLTree(Comparator<E> c) {
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
     * Returns whether a node has balance factor between -1 and 1.
     */
    private boolean isBalanced(Position<AVLInfo<E>> p) {

        throw new RuntimeException("Not implemented.");
    }

    /**
     * Return a child of p with height no smaller than that of the other child.
     */
    private Position<AVLInfo<E>> tallerChild(Position<AVLInfo<E>> p) {

        throw new RuntimeException("Not implemented.");
    }

    private void calculateHeight(Position<AVLInfo<E>> p) {
        throw new RuntimeException("Not implemented.");
    }

    /**
     * Rebalance method called by insert and remove. Traverses the path from p
     * to the root. For each node encountered, we recompute its height and
     * perform a trinode restructuring if it's unbalanced.
     */
    private void rebalance(Position<AVLInfo<E>> zPos) {
        throw new RuntimeException("Not implemented.");
    }

    /**
     * Inserts an item into the dictionary and returns the newly created entry.
     */
    @Override
    public Position<E> insert(E e) {
        throw new RuntimeException("Not implemented.");
    }

    /**
     * Removes and returns an entry from the dictionary.
     */
    @Override
    public E remove(Position<E> pos) throws RuntimeException {
        throw new RuntimeException("Not implemented.");
    }

    @Override
	public Iterator<Position<E>> iterator() {
        throw new RuntimeException("Not implemented.");
	}
    
    /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    private AVLInfo<E> checkPosition(Position<E> p) throws RuntimeException {
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

