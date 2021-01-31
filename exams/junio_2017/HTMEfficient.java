package exams.junio_2017;

import material.maps.Entry;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HTMEfficient<K, V> {
    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        public U setValue(U val) {
            U oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof HashEntry)) {
                return false;
            }
            HashEntry<T, U> ent;
            try {
                ent = (HashEntry<T, U>) o;
            } catch (ClassCastException e) {
                return false;
            }
            return key.equals(ent.getKey()) && value.equals(ent.getValue());
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    private Map<K, HashEntry<K, V>>[] map;
    int numElems;
    int prime, capacity;
    long scale, shift;

    public HTMEfficient(int p, int cap) {
        prime = p;
        capacity = cap;
        numElems = 0;
        map = (Map<K, HashEntry<K, V>>[]) new Map[capacity];
        Random r = new Random();
        this.scale = r.nextInt(prime - 1) + 1;
        shift = r.nextInt(prime);
    }

    public HTMEfficient() {
        this(109345121, 1000);
    }

    public HTMEfficient(int cap) {
        this(109345121, cap);
    }

    private int hashValue(K key) {
        return (int) (((scale * key.hashCode() + shift) % prime) % capacity);
    }

    public V put(K key, V value) {
        int hashcode = hashValue(key);
        Map<K, HashEntry<K, V>> entryMap = map[hashcode];
        if (entryMap == null) {
            entryMap = new HashMap<>();
            map[hashcode] = entryMap;
        }
        HashEntry<K, V> entry = new HashEntry<>(key, value);
        HashEntry<K, V> old = entryMap.put(key, entry);
        if(old != null) {
            return old.getValue();
        } else {
            return null;
        }
    }

    public V get(K key) {
        int hashcode = hashValue(key);
        Map<K, HashEntry<K, V>> entryMap = map[hashcode];
        if (entryMap != null) {
            HashEntry<K, V> entry = entryMap.get(key);
            if (entry != null) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
        int hashcode = hashValue(key);
        Map<K, HashEntry<K, V>> entryMap = map[hashcode];
        if (entryMap != null) {
            HashEntry<K, V> entry = entryMap.remove(key);
            if (entry != null) {
                return entry.getValue();
            }
        }
        return null;
    }

}
