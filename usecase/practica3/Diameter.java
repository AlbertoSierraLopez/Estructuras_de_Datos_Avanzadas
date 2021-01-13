package usecase.practica3;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;

public class Diameter<E> {

	public int evalDiameter(BinaryTree<E> tree) {
		AtomicInteger diameter = new AtomicInteger(-1);
		if (!tree.isEmpty()) {
			evalDiameterAux(tree, tree.root(), diameter);
		}
		return diameter.get();
	}

	private int evalDiameterAux(BinaryTree<E> tree, Position<E> node, AtomicInteger diameter) {
		int levelLeft = 0;
		int levelRight = 0;

		if (tree.hasLeft(node)) {
			levelLeft = evalDiameterAux(tree, tree.left(node), diameter);
		}
		if (tree.hasRight(node)) {
			levelRight = evalDiameterAux(tree, tree.right(node), diameter);
		}

		int currentDiameter = 1 + levelLeft + levelRight;
		if (currentDiameter > diameter.get()) {
			diameter.set(currentDiameter);
		}

		return 1 + max(levelLeft, levelRight);
	}

	/*
	public int evalDiameter(BinaryTree<Integer> tree, Position<Integer> v1, Position<Integer> v2) {
		throw new RuntimeException("Not yet implemented");
	}
	*/
}
