package usecase.practica4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PearRegister {

    Map<Product, List<PearStore>> productMap;
    Map<Integer, PearStore> storeMap;

    public PearRegister() {
        productMap = new HashMap<>();
        storeMap = new HashMap<>();
    }

    public void loadFile(String pathToFile){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            String[] line = reader.readLine().split(" ");
            int nProducts = Integer.parseInt(line[0]);

            for (int i = 0; i < nProducts; i++) {
                line = reader.readLine().split(" ");

                // Crear Product
                String productName = line[0];
                int year = Integer.parseInt(line[1]);
                int nStores = Integer.parseInt(line[2]);
                Product product = new Product(productName, year);

                // Crear List<PearStore>
                List<PearStore> storeList = new ArrayList<>();

                for (int j = 0; j < nStores; j++) {
                    line = reader.readLine().split(" ");

                    // Crear o recoger pearStore y añadir a la lista
                    String storeName = line[0];
                    int id = Integer.parseInt(line[1]);
                    int units = Integer.parseInt(line[2]);
                    double score = Double.parseDouble(line[3]);

                    PearStore store = storeMap.get(id);
                    if (store == null) {
                        store = new PearStore(storeName, id);
                        storeMap.put(id, store);
                    }

                    store.putUnits(product, units);
                    store.putScore(product, score);

                    storeList.add(store);
                }

                // Crear entrada en pearMap
                productMap.put(product, storeList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product producto, Iterable<PearStore> stores){
        productMap.put(producto, (List<PearStore>) stores);
    }

    public void addSalesInPearStore(Product producto, PearStore store, int units, double score){
        store.putUnits(producto, units);
        store.putScore(producto, score);

        productMap.get(store).add(store);
        storeMap.putIfAbsent(store.getId(), store);
    }

    public double getScoreOfProduct(Product producto){
        double score = 0.0;
        int numElems = 0;

        Iterable<PearStore> storeList = productMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                score += store.getScore(producto);
                numElems++;
            }
        }

        return score / numElems;
    }

    public PearStore getGreatestSeller(Product producto){
        PearStore bestStore = null;
        int maxUnits = -1;

        Iterable<PearStore> storeList = productMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                if (store.getUnits(producto) > maxUnits) {
                    bestStore = store;
                }
            }
        }

        return bestStore;
    }

    public int getUnits(Product producto){
        int units = 0;

        Iterable<PearStore> storeList = productMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                units += store.getUnits(producto);
            }
        }

        return units;
    }

    public boolean productExists(Product product){
        return productMap.get(product) != null;
    }
}
