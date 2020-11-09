package material.tree.binarytree;

import material.Position;
import material.tree.binarytree.BinaryTree;

public class BinaryTreeUtils<E> {
	
	private BinaryTree<E> tree;
	
	public BinaryTreeUtils(BinaryTree<E> tree) {
		this.tree = tree;
	}
	
	/** 
	  * Given a tree the method returns a new tree where all left children 
	  * become right children and vice versa
	*/
	public BinaryTree<E> mirror() {
		throw new RuntimeException("Not yet implemented");
	}
	/** 
	  * Determines whether the element e is the tree or not
	*/
	public boolean contains (E e) {
		throw new RuntimeException("Not yet implemented");
	}
	/** 
	  * Determines the level of a node in the tree (root is located at level 1)
	*/
	public int level(Position<E> p) {
		throw new RuntimeException("Not yet implemented");
	}

}
