package exams.junio_2016.usecase;

import material.Position;
import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.util.*;

import static java.lang.Math.ceil;

public class ElectoralCollege {
    private static final int A = 65;

    private Set<Voter> votantes;
    private BinarySearchTree<Voter> votantesOrdenados;
    private Map<String, List<Voter>> mesas;
    private int nVotantes;


    public ElectoralCollege() {
        votantesOrdenados = new LinkedBinarySearchTree<>();
        votantes = new HashSet<>();
        mesas = new HashMap<>();
        nVotantes = 0;
    }

    public void addVoter(String dni, String calle, String nombre) {
        Voter v = new Voter(dni, nombre, calle, null);
        votantesOrdenados.insert(v);
        votantes.add(v);
        nVotantes++;
    }

    public void makeStationDistribution() {
        int nMesas =(int) ceil(nVotantes / 800.0);
        int votantesPorMesa = nVotantes / nMesas;
        Iterator<Position<Voter>> it = votantesOrdenados.iterator();

        for (int i = 0; i < nMesas; i++) {
            String mesa = Character.toString((char) A+i);
            List<Voter> voterList = new ArrayList<>();
            // Como se recorre el arbol ordenado se insertan ordenadamente los votantes en la lista,
            // por lo que la lista está ordenada y no hace falta usar otro ABB

            for (int j = 0; j < votantesPorMesa; j++) {
                Voter v = it.next().getElement();
                v.setMesa(mesa);
                voterList.add(v);
            }

            mesas.put(mesa, voterList);
        }
    }

    public Iterable<Voter> getAllVoters() {
        return votantes;
    }

    public Iterable<Voter> getStationVoters(String mesa) {
        return mesas.get(mesa);
    }

}
