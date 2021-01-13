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
		BinaryTree<E> newTree = new LinkedBinaryTree<>();
		if (!tree.isEmpty()) {
			Position<E> newRoot = newTree.addRoot(tree.root().getElement());
			buildMirror(newRoot, tree.root(), newTree);
		}
		return newTree;
	}

	private void buildMirror(Position<E> newNode, Position<E> oldNode, BinaryTree<E> newTree) {
		if (tree.hasLeft(oldNode)) {
			Position<E> oldLeft = tree.left(oldNode);
			Position<E> newRight = newTree.insertRight(newNode, oldLeft.getElement());
			buildMirror(newRight, oldLeft, newTree);
		}
		if (tree.hasRight(oldNode)) {
			Position<E> oldRight = tree.right(oldNode);
			Position<E> newLeft = newTree.insertLeft(newNode, oldRight.getElement());
			buildMirror(newLeft, oldRight, newTree);
		}
	}

	/** 
	  * Determines whether the element e is in the tree or not
	*/
	public boolean contains (E e) {
		for (Position<E> p : tree) {
			if (p.getElement().equals(e)) return true;
		}
		return false;
	}

	/** 
	  * Determines the level of a node in the tree (root is located at level 1)
	*/
	public int level(Position<E> p) {
		if (tree.isRoot(p)) {
			return 1;
		} else {
			return 1 + level(tree.parent(p));
		}
	}

}
