package material.ordereddictionary;

import material.Position;
import material.linear.DoubleLinkedList;
import material.linear.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapDictionary<K, V> {

    private Map<K, List<V>> map;

    public MapDictionary() {
        map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Position<V> find(K key) {
        List<V> values = map.get(key);
        if (values == null) {
            return null;
        } else {
            return values.first();
        }
    }

    public Iterable<Position<V>> findAll(K key) {
        List<V> values = map.get(key);
        ArrayList<Position<V>> list = new ArrayList<>();

        Position<V> current = values.first();
        while (values.last() != current) {
            list.add(current);
            current = values.next(current);
        }
        list.add(current);

        return list;
    }

    public Position<V> insert(K key, V value) {
        List<V> values = map.get(key);

        Position<V> pos;
        if (values != null) {
            pos = values.addLast(value);
        } else {
            values = new DoubleLinkedList<>();
            pos = values.addFirst(value);
            map.put(key, values);
        }

        return pos;
    }

    public void remove(K key) {
        List<V> values = map.get(key);
        values = new DoubleLinkedList<>();
    }

}
