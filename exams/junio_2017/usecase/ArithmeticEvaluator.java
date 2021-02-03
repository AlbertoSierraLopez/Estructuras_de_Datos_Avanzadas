package exams.junio_2017.usecase;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

public class ArithmeticEvaluator {
    private BinaryTree<String> expTree; // El árbol es completo

    public ArithmeticEvaluator(String exp) {
        expTree = build(exp);
    }

    private BinaryTree<String> build(String exp) {
        BinaryTree<String> subTree = new LinkedBinaryTree<>();

        // Encontrar siguiente operador
        String[] operators = new String[] {"+", "-", "*"};
        int indexOperator = -1;
        int i = 0;
        while (indexOperator == -1 && i < operators.length) {
            // Se usa lastIndexOf porque, como la expresión se lee de izquierda a derecha, se tiene que
            // construir de derecha a izquierda y empezando por los operadores con menor prioridad.
            indexOperator = exp.lastIndexOf(operators[i]);
            i++;
        }

        if (indexOperator == -1) {  // Es un número
            subTree.addRoot(exp);

        } else {                    // Es una expresión
            // Crear subcadenas izquierda y derecha
            String left = exp.substring(0, indexOperator);
            String right = exp.substring(indexOperator + 1, exp.length());

            // Crear subárbol
            Position<String> root = subTree.addRoot(exp.substring(indexOperator, indexOperator + 1));
            subTree.attachLeft(root, build(left));
            subTree.attachRight(root, build(right));
        }

        return subTree;
    }

    public BinaryTree<String> getTree() {
        return expTree;
    }

    public int evaluate() {
        if (!expTree.isEmpty()) {
            return evaluateAux(expTree.root());
        } else {
            return 0;
        }
    }

    private int evaluateAux(Position<String> node) {
        // El árbol es completo, o tiene 2 hijo o ninguno
        if (expTree.isLeaf(node)) {
            return Integer.parseInt(node.getElement());

        } else {
            int left  = evaluateAux(expTree.left(node));
            int right = evaluateAux(expTree.right(node));

            switch (node.getElement()) {
                case "+": return left + right;
                case "-": return left - right;
                case "*": return left * right;
                default:  return 0;
            }
        }
    }
}
