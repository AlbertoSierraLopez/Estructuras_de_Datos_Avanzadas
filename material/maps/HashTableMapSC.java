package material.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, Sergio Pérez
 */
public class HashTableMapSC<K, V> implements Map<K, V> {
    //TODO: Ejercicio para los alumnos, implementar todo

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

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {

        private List<HashEntry<T, U>>[] map;
        private int numElems;
        int posMap;
        int posBucket;

        public HashTableMapIterator(List<HashEntry<T, U>>[] map, int numElems) {
            this.map = map;
            this.numElems = numElems;
            if (numElems == 0) {
                posMap = map.length;
            } else {
                posMap = goToNextBucket(0);
                posBucket = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return posMap < map.length;
        }

        @Override
        public Entry<T, U> next() {
            Entry<T, U> entry = map[posMap].get(posBucket);

            // Buscar el siguiente
            posBucket++;
            if (posBucket == map[posMap].size()) {   // Este bucket ya no tiene más elementos
                posMap = goToNextBucket(posMap+1);
                posBucket = 0;
            }
            return entry;
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }

        /**
         * Returns the index of the next position starting starting from a given index.
         * (if the parameter is already a valid position then does nothing)
         */
        private int goToNextBucket(int i) {
            while (i < map.length && (map[i] == null || map[i].isEmpty())) {
                i++;
            }
            return i;
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new RuntimeException("Not yet implemented");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }


    /**
     * The array of Lists.
     */
    private List<HashEntry<K, V>>[] map;

    private int numElems;
    private long scale, shift;
    private int prime, capacity;

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public HashTableMapSC() {
        this(109345121, 1000);
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
        this(109345121, cap);
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p   prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        prime = p;
        capacity = cap;
        numElems = 0;
        map = (List<HashEntry<K, V>>[]) new ArrayList[capacity];
        Random r = new Random();
        this.scale = r.nextInt(prime - 1) + 1;
        shift = r.nextInt(prime);
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return the hash value
     */
    protected int hashValue(K key) {
        return (int) (((scale * key.hashCode() + shift) % prime) % capacity);
    }

    @Override
    public int size() {
        return numElems;
    }

    @Override
    public boolean isEmpty() {
        return numElems == 0;
    }

    @Override
    public V get(K key) {
        checkKey(key);
        List<HashEntry<K, V>> entryList = map[hashValue(key)];

        if (!(entryList == null)) {
            // Comprobar si existe
            for (HashEntry<K ,V> ent : entryList) {
                if (ent.getKey().equals(key)) {
                    // Existe -> devolver
                    return ent.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        List<HashEntry<K, V>> entryList = map[hashValue(key)];

        if (!(entryList == null)) {
            // Comprobar si existe
            for (HashEntry<K ,V> ent : entryList) {
                if (ent.getKey().equals(key)) {
                    // Existe -> sustituir
                    return ent.setValue(value);
                }
            }
        } else {
            entryList = map[hashValue(key)] = new ArrayList<>();
        }

        // Sabemos que no existe
        HashEntry<K, V> newEntry = new HashEntry<>(key, value);
        entryList.add(newEntry);
        numElems++;
        if (numElems >= 0.75*capacity) {
            rehash(capacity*2);
        }

        return null;
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        List<HashEntry<K, V>> entryList = map[hashValue(key)];

        if (!(entryList == null)) {
            // Comprobar si existe
            for (HashEntry<K ,V> ent : entryList) {
                if (ent.getKey().equals(key)) {
                    // Existe -> eliminar
                    V oldValue = ent.getValue();
                    entryList.remove(ent);
                    numElems--;
                    return oldValue;
                }
            }
        }

        return null;
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableMapIterator<K, V>(map, numElems);
    }

    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new HashTableMapKeyIterator<K, V>(new HashTableMapIterator<K, V>(map, numElems));
            }
        };
    }

    @Override
    public Iterable<V> values() {
        return new Iterable<V>() {
            @Override
            public Iterator<V> iterator() {
                return new HashTableMapValueIterator<>(new HashTableMapIterator<K, V>(map, numElems));
            }
        };
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new HashTableMapIterator<K, V>(map, numElems);
            }
        };
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        // We cannot check the second test (i.e., k instanceof K) since we do not know the class K
        if (k == null) {
            throw new IllegalStateException("Invalid key: null.");
        }
    }

    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     */
    protected void rehash(int newCap) {
        List<HashEntry<K, V>>[] oldMap = map;
        int oldNumElems = numElems;

        capacity = newCap;
        map = (List<HashEntry<K,V>>[]) new ArrayList[capacity];
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        numElems = 0;

        HashTableMapIterator<K, V> it = new HashTableMapIterator<K, V>(oldMap, oldNumElems);
        while (it.hasNext()) {
            Entry<K, V> ent = it.next();
            put(ent.getKey(), ent.getValue());
        }
    }
}
