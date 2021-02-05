package exams.septiembre_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseMap <K, V> {

    public Map<V, List<K>> createReverse(Map<K, V> map) {
        Map<V, List<K>> reverseMap = new HashMap<>();

        for (Map.Entry<K, V> ent : map.entrySet()) {
            List<K> reverseKeys = reverseMap.get(ent.getValue());
            if (reverseKeys == null) {
                reverseKeys = new ArrayList<>();
                reverseMap.put(ent.getValue(), reverseKeys);
            }
            reverseKeys.add(ent.getKey());
        }

        return reverseMap;
    }

}
