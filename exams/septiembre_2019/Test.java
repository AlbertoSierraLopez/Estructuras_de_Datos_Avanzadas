package exams.septiembre_2019;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");
        map.put(3, "a");
        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "c");

        ReverseMap<Integer, String> reverseMap = new ReverseMap<>();
        Map<String, List<Integer>> reverse = reverseMap.createReverse(map);
        for (Map.Entry<String, List<Integer>> ent : reverse.entrySet()) {
            System.out.print(ent.getKey() + ":");
            for (Integer i : ent.getValue()) {
                System.out.print(" " + i);
            }
            System.out.println();
        }

    }
}
