package exams.junio_2015;

import java.util.*;
import java.util.Map.Entry;

public class HashTableMapSC <K, V> implements Iterable<Entry<K, V>> {
    /* Ejercicio 2: a) */
    private List<Entry<K, V>>[] map;    // Usa Entry porque no quiero porgramarme la clase privada HashEntry
    private int n;                      // Entry es una interfaz y no se puede instanciar, por eso put usa AbstractMap.SimpleEntry<K, V>
    private long scale, shift;          // El mapa debería usar AbstractMap.SimpleEntry<K, V> internamente y solo devolver Entry en los métodos para que se la quede el usuario
    private int prime, capacity;        // Internamente siempre usar la clase privada

    /* Ejercicio 2: b) */
    private class MapIterator<K, V> implements Iterator<Entry<K, V>> {

        private List<Entry<K, V>>[] map;
        private int posMap;
        private int posList;

        public MapIterator(List<Entry<K, V>>[] map, int NElems) {
            this.map = map;
            posMap = 0;
            if (NElems > 0) {
                goToNextList();
            } else {
                posMap = map.length;
            }
        }

        private void goToNextList() {
            while (posMap < map.length && (map[posMap] == null || map[posMap].isEmpty())) {
                posMap++;
            }
            posList = 0;
        }


        @Override
        public boolean hasNext() {
            return posMap < map.length;
        }

        @Override
        public Entry<K, V> next() {
            Entry<K, V> next =  map[posMap].get(posList);
            posList++;
            if (posList == map[posMap].size()) {
                posMap++;
                goToNextList();
            }
            return next;
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new MapIterator<>(map, n);
    }

    /* Ejercicio 2: c) */
    public V remove(K key) {
        if (map[hashValue(key)] == null) {
            return null;
        }

        for (Entry<K, V> ent : map[hashValue(key)]) {
            if (ent.getKey().equals(key)) {
                map[hashValue(key)].remove(ent);
                n--;
                if (n/(capacity/2) < 0.75) {
                    rehash(capacity/2);
                }
                return ent.getValue();
            }
        }

        return null;
    }

    /* Métodos ya implementados de la clase HashTableMapSC para que no de problemas*/
    public HashTableMapSC(int p, int cap) {
        prime = p;
        capacity = cap;
        n = 0;
        map = (List<Entry<K, V>>[]) new ArrayList[capacity];
        Random r = new Random();
        this.scale = r.nextInt(prime - 1) + 1;
        shift = r.nextInt(prime);
    }


    private int hashValue(K key) {
        return (int) (((scale * key.hashCode() + shift) % prime) % capacity);
    }

    private void rehash(int newCapacity) {
        List<Entry<K, V>>[] oldMap = map;
        int oldN = n;

        capacity = newCapacity;
        n = 0;
        map = (List<Entry<K, V>>[]) new ArrayList[capacity];
        Random r = new Random();
        scale = r.nextInt(prime - 1) + 1;
        shift = r.nextInt(prime);

        MapIterator<K, V> it = new MapIterator<>(oldMap, oldN);
        while (it.hasNext()) {
            Entry<K , V> next = it.next();
            put(next.getKey(), next.getValue());
        }
    }

    public V put(K key, V value) {
        List<Entry<K, V>> entryList = map[hashValue(key)];
        if (!(entryList == null)) {
            for (Entry<K ,V> ent : entryList) {
                if (ent.getKey().equals(key)) {
                    return ent.setValue(value);
                }
            }
        } else {
            entryList = map[hashValue(key)] = new ArrayList<>();
        }
        Entry<K, V> newEntry = new AbstractMap.SimpleEntry<>(key, value);
        entryList.add(newEntry);
        n++;
        if (n >= 0.75*capacity) {
            rehash(capacity*2);
        }
        return null;
    }

}
