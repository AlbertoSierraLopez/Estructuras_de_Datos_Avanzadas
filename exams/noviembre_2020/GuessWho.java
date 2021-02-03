package exams.noviembre_2020;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuessWho {
    private BinaryTree<String> tree;
    private List<Position<String>> list;
    private Iterator<Position<String>> iterator;

    public GuessWho() {
        tree = new LinkedBinaryTree<>();
        list = new ArrayList<>();
        list.add(null);
    }

    public void loadGame(String path) {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line = null;
            int index = 1;
            while ((line = bf.readLine()) != null) {
                // Process the line read
                if (!line.equals("NADA")) {
                    if (tree.isEmpty()) {
                        list.add(tree.addRoot(line));
                    } else {
                        if (index % 2 == 0) {
                            list.add(tree.insertLeft(list.get(index / 2), line));
                        } else {
                            list.add(tree.insertRight(list.get(index / 2), line));
                        }
                    }

                } else {
                    list.add(null);
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Initializes an iterator through the game, starting at
    the first question
     */
    public void resetIterator() {
        iterator = tree.iterator();
    }

    /*
    Returns true if there are more question to be read
    by the iterator, or false otherwise
     */
    public boolean hasMoreQuestions() {
        return iterator.hasNext();
    }

    /*
    Returns the next question to be read from the game
     */
    public String nextQuestion() {
        return iterator.next().getElement();
    }

    /*
    Returns the maximum number of questions (without including the response)
    that can be performed in the game
     */
    public int longestGame() {
        return longestGameAux(tree.root()) - 1; // Restar la respuesta
    }

    private int longestGameAux(Position<String> p) {
        int left = 0;
        int right = 0;
        if (tree.hasLeft(p)) {
            left = longestGameAux(tree.left(p));
        }
        if (tree.hasRight(p)) {
            right = longestGameAux(tree.right(p));
        }
        return 1 + Math.max(left, right);
    }

    /*
    Returns the number of responses stored in this game
     */
    public int responses() {
        int sum = 0;    // Contar el número de hojas
        for (Position<String> p : tree) {
            if (tree.isLeaf(p)) {
                sum+=1;
            }
        }
        return sum;
    }

    public String solve(String path) {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line = null;
            Position<String> cursor = tree.root();

            while ((line = bf.readLine()) != null) {
                // Process the line read
                if (line.equals("SI") && tree.hasLeft(cursor)) {
                    cursor = tree.left(cursor);
                } else if (line.equals("NO") && tree.hasRight(cursor)) {
                    cursor = tree.right(cursor);
                } else {
                    return "INCORRECTO";
                }
            }
            return cursor.getElement();

        } catch (IOException e) {
            e.printStackTrace();
            return "INCORRECTO";
        }
    }

    public String showGame() {
        StringBuilder string = new StringBuilder();
        showGameAux(tree.root(), 0, string);
        return string.toString();
    }

    private void showGameAux(Position<String> p, int depth, StringBuilder string) {
        for (int i = 0; i < depth; i++) {
            string.append("\t");
        }
        string.append(p.getElement() + "\n");

        if (tree.hasLeft(p)) {
            showGameAux(tree.left(p), depth + 1, string);
        }
        if (tree.hasRight(p)) {
            showGameAux(tree.right(p), depth + 1, string);
        }
    }
}

