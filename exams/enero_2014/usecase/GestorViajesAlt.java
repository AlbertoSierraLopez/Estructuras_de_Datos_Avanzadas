package exams.enero_2014.usecase;

import java.util.*;


public class GestorViajesAlt {
    private Map<String, List<Viaje>> origenes;
    private Map<String, List<Viaje>> destinos;
    private List<Viaje> viajes;
    private Set<String> ciudades;

    public GestorViajesAlt() { // O(1)
        origenes = new HashMap<>();
        destinos = new HashMap<>();
        viajes = new ArrayList<>();
        ciudades = new HashSet<>();
    }

    public void insertarViaje(Viaje viaje) {   // "O(1)"
        // Insertar en origenes
        List<Viaje> listOrigen = origenes.get(viaje.getOrigen());
        if (listOrigen == null) {
            listOrigen = new ArrayList<>();
            listOrigen.add(viaje);
            origenes.put(viaje.getOrigen(), listOrigen);
        } else {
            listOrigen.add(viaje);
        }

        // Insertar en destinos
        List<Viaje> listDestino = destinos.get(viaje.getDestino());
        if (listDestino == null) {
            listDestino = new ArrayList<>();
            listDestino.add(viaje);
            destinos.put(viaje.getDestino(), listDestino);
        } else {
            listDestino.add(viaje);
        }

        viajes.add(viaje);
        ciudades.add(viaje.getDestino());
        ciudades.add(viaje.getOrigen());
    }

    public Iterable<Viaje> getOrigenes(String ciudad) {
        return origenes.get(ciudad);
    }

    public Iterable<Viaje> getDestinos(String ciudad) {
        return destinos.get(ciudad);
    }

    public Iterable<Viaje> getViajes() {    // O(1)
        return viajes;
    }

    public Iterable<String> getCiudades() {    // O(1)
        return ciudades;
    }
}
