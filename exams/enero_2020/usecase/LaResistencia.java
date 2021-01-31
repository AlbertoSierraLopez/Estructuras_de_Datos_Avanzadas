package exams.enero_2020.usecase;

import material.Position;
import material.ordereddictionary.BSTOrderedDict;
import material.ordereddictionary.Entry;
import material.ordereddictionary.OrderedDictionary;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.*;

public class LaResistencia {
    private int maxDinero;
    private OrderedDictionary<Integer, String> dineroDict;
    private Map<String, Set<String>> mesMap;
    private Map<String, Integer> invitadosMap;

    public LaResistencia() {
        dineroDict = new BSTOrderedDict<>();
        mesMap = new HashMap<>();
        invitadosMap = new HashMap<>();
        maxDinero = -1;
    }

    public void addVisit(String nombre, int dinero, String mes) {
        // Dinero
        if (dinero > maxDinero) {
            maxDinero = dinero;
        }
        dineroDict.insert(dinero, nombre);

        // Mes
        Set<String> visitasMes = mesMap.get(mes);
        if (visitasMes == null) {
            visitasMes = new HashSet<>();
            mesMap.put(mes, visitasMes);
        }
        visitasMes.add(nombre);

        // Invitados
        invitadosMap.put(nombre, dinero);
    }

    public Iterable<String> overMedian() {
        List<Integer> listDinero = new ArrayList<>();
        for (Entry<Integer, String> ent : dineroDict.findRange(0, maxDinero)) {
            listDinero.add(ent.getKey());
        }

        int index = listDinero.size() / 2;
        int mediana;
        if (listDinero.size() % 2 == 0) {
            mediana = (listDinero.get(index) + listDinero.get(index-1)) / 2;
        } else {
            mediana = listDinero.get(index);
        }

        List<String> famosos = new ArrayList<>();
        for (Entry<Integer, String> ent : dineroDict.findRange(mediana+1, maxDinero)) {
            famosos.add(ent.getValue());
        }
        return famosos;
    }

    public int moneyInMonth(String month) {
        int sum = 0;
        for (String famoso : mesMap.get(month)) {
            sum += invitadosMap.get(famoso);
        }
        return sum;
    }

    public Iterable<String> visitsInMonth(String month) {
        return mesMap.get(month);
    }
}
