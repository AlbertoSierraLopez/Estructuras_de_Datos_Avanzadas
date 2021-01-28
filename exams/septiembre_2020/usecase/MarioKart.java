package exams.septiembre_2020.usecase;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.*;

public class MarioKart {

    private BinarySearchTree<Item> objects;
    private Map<String, Cup> cups;

    public MarioKart() {
        objects = new LinkedBinarySearchTree<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return - o1.getScore().compareTo(o2.getScore());
            }
        });
        cups = new HashMap<>();
    }

    public void addObjectUsed(String objName, int score) {
        objects.insert(new Item(objName, score));
    }

    public Iterable<String> getBestNObjects(int n) {
        List<String> list = new ArrayList<>();
        Iterator<Position<Item>> it = objects.iterator();

        int i = 0;
        while (it.hasNext() && i < n) {
            list.add(it.next().getElement().getName());
            i++;
        }

        return list;
    }

    public void addCupInfo(String cupName, int level) {
        cups.put(cupName, new Cup(cupName, level));
    }

    public void printCupInfo(String cupName) {
        Cup cup = cups.get(cupName);
        if (cup != null) {
            System.out.println(cup.toString());
        }
    }

    public void newCupCompleted(String cupName) {
        Cup cup = cups.get(cupName);
        if (cup != null) {
            cup.addCompletion();
        }
    }

    public Iterable<String> getCupWonNTimes(int n) {
        List<String> list = new ArrayList<>();

        for (Cup cup : cups.values()) {
            if (cup.getTimesCompleted() >= n) {
                list.add(cup.getName());
            }
        }

        return list;
    }
}
