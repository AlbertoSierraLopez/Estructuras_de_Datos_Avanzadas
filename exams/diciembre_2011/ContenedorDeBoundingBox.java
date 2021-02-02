package exams.diciembre_2011;

import material.ordereddictionary.BSTOrderedDict;
import material.ordereddictionary.Entry;
import material.ordereddictionary.OrderedDictionary;

import java.util.ArrayList;
import java.util.List;

public class ContenedorDeBoundingBox {
    private OrderedDictionary<Integer, BoundingBox> diccionario;

    public ContenedorDeBoundingBox() {
        diccionario = new BSTOrderedDict<>();
    }

    public void insertar(BoundingBox b) {
        diccionario.insert(b.getArea(), b);
    }

    public Iterable<BoundingBox> encontrar(int area) {
        List<BoundingBox> list = new ArrayList<>();
        for (Entry<Integer, BoundingBox> ent : diccionario.findAll(area)) {
            list.add(ent.getValue());
        }
        return list;
    }
}
