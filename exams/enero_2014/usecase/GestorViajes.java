package exams.enero_2014.usecase;

import material.ordereddictionary.*;

import java.util.*;


public class GestorViajes {
    private OrderedDictionary<String, Viaje> origenes;  // Diccionarios porque hay varias entradas con la misma clave
    private OrderedDictionary<String, Viaje> destinos;  // Se podría usar Map<String, List<Viajes>>
    private List<Viaje> viajes;
    private Set<String> ciudades;

    public GestorViajes() { // O(1)
        origenes = new BSTOrderedDict<>();
        destinos = new BSTOrderedDict<>();
        viajes = new ArrayList<>();
        ciudades = new HashSet<>();
    }

    public void insertarViaje(Viaje viaje) {   // "O(1)"
        origenes.insert(viaje.getOrigen(), viaje);
        destinos.insert(viaje.getDestino(), viaje);
        viajes.add(viaje);
        ciudades.add(viaje.getDestino());
        ciudades.add(viaje.getOrigen());
    }

    public Iterable<Viaje> getDestinos(String ciudad) { // O(log n)
        List<Viaje> list = new ArrayList<>();
        for (Entry<String, Viaje> ent : destinos.findAll(ciudad)) {
            list.add(ent.getValue());
        }
        return list;
    }

    public Iterable<Viaje> getOrigenes(String ciudad) { // O(log n)
        List<Viaje> list = new ArrayList<>();
        for (Entry<String, Viaje> ent : origenes.findAll(ciudad)) {
            list.add(ent.getValue());
        }
        return list;
    }

    public Iterable<Viaje> getViajes() {    // O(1)
        return viajes;
    }

    public Iterable<String> getCiudades() {    // O(1)
        return ciudades;
    }
}
