package exams.enero_2019;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.*;

public class Examen<E> {

    public Iterable<Integer> levelsComplete(BinaryTree<E> tree) {
        Map<Integer, Boolean> levels = new HashMap<>();
        if (!tree.isEmpty()) {
            levelsCompleteAux(tree, tree.root(), 1, levels);
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> ent : levels.entrySet()) {
            if (ent.getValue()) {
                list.add(ent.getKey());
            }
        }
        return list;
    }

    private void levelsCompleteAux(BinaryTree<E> tree, Position<E> node, int level, Map<Integer, Boolean> levels) {
        if (!levels.containsKey(level)) {
            levels.put(level, true);
        }

        if (tree.hasLeft(node)) {
            levelsCompleteAux(tree, tree.left(node), level+1, levels);
        }
        if (tree.hasRight(node)) {
            levelsCompleteAux(tree, tree.right(node), level+1, levels);
        }

        if ((tree.hasLeft(node) && !tree.hasRight(node)) || (!tree.hasLeft(node) && tree.hasRight(node))) {
            levels.put(level, false);
        }
    }

}
