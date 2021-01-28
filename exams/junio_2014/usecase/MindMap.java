package exams.junio_2014.usecase;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.util.ArrayList;
import java.util.List;

public class MindMap {
    private LinkedTree<Label> tree;

    public MindMap() {
        tree = new LinkedTree<>();
    }

    public Position<Label> insertLabel(Position<Label> p, Label label) {
        if (tree.isEmpty()) {
            return tree.addRoot(label);
        } else {
            return tree.add(label, p);
        }
    }

    public void removeLabel(Position<Label> label) {
        tree.remove(label);
    }

    public Position<Label> centralLabel() {
        if (!tree.isEmpty()) {
            return tree.root();
        } else {
            return null;
        }
    }

    public Iterable<Position<Label>> labels(Position<Label> label) {
        List<Position<Label>> list = new ArrayList<>();
        for (Position<Label> p : tree) {
            list.add(p);
        }
        return list;
    }
}
