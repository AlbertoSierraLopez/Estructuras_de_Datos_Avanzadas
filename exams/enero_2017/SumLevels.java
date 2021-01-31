package exams.enero_2017;

import material.Position;
import material.tree.narytree.NAryTree;

import java.util.HashSet;
import java.util.Set;

public class SumLevels {

    public int sumLevels(NAryTree<Integer> tree, int[] levels) {
        Set<Integer> levelSet = new HashSet<>();
        for (int i : levels) {
            levelSet.add(i);
        }

        if (!tree.isEmpty()) {
            return sumLevelsAux(tree, tree.root(), 0, levelSet);
        } else {
            return 0;
        }
    }

    private int sumLevelsAux(NAryTree<Integer> tree, Position<Integer> node, int level, Set<Integer> levelSet) {
        int sum = 0;
        for (Position<Integer> child : tree.children(node)) {
            sum += sumLevelsAux(tree, child, level + 1, levelSet);
        }
        if (levelSet.contains(level)) {
            return sum + node.getElement();
        } else {
            return sum;
        }
    }

}
