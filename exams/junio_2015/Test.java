package exams.junio_2015;

import java.util.Map.Entry;

public class Test {
    public static void main(String[] args) {

        HashTableMapSC<Integer, String> map = new HashTableMapSC<>(11, 11);

        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");

        for (Entry<Integer, String> entry : map) {
            System.out.println(entry.getValue());
        }

        map.remove(0);
    }
}
