package exams.junio_2012;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConjuntoDifuso {

    private double[] valores;

    // Valores viene ordenado en orden ascendente
    public ConjuntoDifuso(double[] valores) {
        this.valores = valores;
    }

/*
    public ConjuntoDifuso(double[] valores) {
        binTree = new LinkedBinaryTree<>();
        List<Double> list = new ArrayList<>();
        for (double d : valores) {
            list.add(d);
        }
        int mid = (list.size()-1)/2;
        Position<Double> root = binTree.addRoot(list.get(mid));
        contructor(root, binTree, list, 0, mid-1, true);
        contructor(root, binTree, list, mid, list.size()-1, false);
    }

    private void contructor(Position<Double> pos, BinaryTree<Double> binTree, List<Double> list, int bot, int top, boolean isLeft) {
        if (bot <= top) {
            int mid = (bot+top)/2;
            Double valor = list.get(mid);
            Position<Double> newPos;
            if (isLeft) {
                newPos = binTree.insertLeft(pos, valor);
            } else {
                newPos = binTree.insertRight(pos, valor);
            }
            contructor(newPos, binTree, list, 0, mid-1, true);
            contructor(newPos, binTree, list, mid, list.size()-1, false);
        }
    }
*/

    public double busquedaAproximada(double valor) {
        return busquedaAproximadaAux(valor, null, 0, valores.length-1);
    }

    private double busquedaAproximadaAux(double valor, Double last, int bot, int top) {
        if (bot > top) {
            return last;
        } else {
            int mid = (bot + top) / 2;
            Double current = valores[mid];
            if ((last != null) && (Math.abs(Math.abs(valor) - Math.abs(last)) < Math.abs(Math.abs(valor) - Math.abs(current)))) {
                return last;
            } else {
                if (current > valor) {
                    return busquedaAproximadaAux(valor, current, bot, mid-1);
                } else {
                    return busquedaAproximadaAux(valor, current, mid+1, top);
                }
            }
        }
    }

}
