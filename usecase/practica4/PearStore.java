package usecase.practica4;

import java.util.Map;
import java.util.HashMap;

public class PearStore {

    private String name;
    private Integer id;
    private Map<Product, Integer> unitsMap;
    private Map<Product, Double> scoreMap;

    public PearStore(String name, int id) {
        this.name = name;
        this.id = id;
        unitsMap = new HashMap<Product, Integer>();
        scoreMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Integer getUnits(Product product) {
        return unitsMap.get(product);
    }

    public Double getScore(Product product) {
        return scoreMap.get(product);
    }

    public void putUnits(Product product, Integer units) {
        unitsMap.put(product, units);
    }

    public void putScore(Product product, double score) {
        scoreMap.put(product, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        PearStore p;
        try {
            p = (PearStore) obj;
        } catch (ClassCastException ex) {
            return false;
        }
        return (p.getId() == id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
