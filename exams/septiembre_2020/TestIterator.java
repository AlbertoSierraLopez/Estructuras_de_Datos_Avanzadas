package exams.septiembre_2020;

import material.Position;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.Random;

public class TestIterator {

    public static void main(String[] args) {
        LinkedBinarySearchTree<Integer> lbst = new LinkedBinarySearchTree<>();
        Random rnd = new Random(1234);
        for (int i = 0; i < 10; i++) {
            lbst.insert(rnd.nextInt(100));
        }
        for (Position<Integer> pos : lbst) {
            System.out.println(pos.getElement());
        }
    }
}
