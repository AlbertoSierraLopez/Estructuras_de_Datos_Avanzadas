package exams.enero_2020;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.narytree.NAryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Examen<E> {

    public void removeFrontier(NAryTree<E> tree) {
        if (!tree.isEmpty()) {
            removeFrontierAux(tree, tree.root());
        }
    }

    private void removeFrontierAux(NAryTree<E> tree, Position<E> node) {
        if (tree.isLeaf(node)) {
            tree.remove(node);
        } else {
            for (Position<E> child : tree.children(node)) {
                removeFrontierAux(tree, child);
            }
        }
    }

    public Iterable<Position<E>> findHalf(BinaryTree<E> tree) {
        if (!tree.isEmpty()) {
            Map<Integer, List<Position<E>>> heightMap = new HashMap<>();
            int maxHeight = findHalfAux(tree, tree.root(), heightMap);
            return heightMap.get(maxHeight/2);
        } else {
            return null;
        }
    }

    private int findHalfAux(BinaryTree<E> tree, Position<E> node, Map<Integer, List<Position<E>>> heightMap) {
        int left = 0;
        int right = 0;

        if (tree.hasLeft(node)) {
            left = findHalfAux(tree, tree.left(node), heightMap);
        }

        if (tree.hasRight(node)) {
            right = findHalfAux(tree, tree.right(node), heightMap);
        }

        int height = Math.max(left, right) + 1;

        List<Position<E>> heightList = heightMap.get(height);
        if (heightList == null) {
            heightList = new ArrayList<>();
            heightMap.put(height, heightList);
        }
        heightList.add(node);

        return height;
    }

}
