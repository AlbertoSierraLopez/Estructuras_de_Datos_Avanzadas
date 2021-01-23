package usecase.practica4;

import material.maps.HashTableMapSC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PearRegister {

    HashTableMapSC<Product, Iterable<PearStore>> pearMap;

    public PearRegister() {
        pearMap = new HashTableMapSC<>();
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

                    // Crear pearStore y añadir a la lista
                    String storeName = line[0];
                    int id = Integer.parseInt(line[1]);
                    int units = Integer.parseInt(line[2]);
                    double score = Double.parseDouble(line[3]);
                    PearStore store = new PearStore(storeName, id, units, score);
                    storeList.add(store);
                }

                // Crear entrada en pearMap
                pearMap.put(product, storeList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product producto, Iterable<PearStore> stores){
        pearMap.put(producto, stores);
    }

    public void addSalesInPearStore(Product producto, PearStore store, int units, double score){
        Iterable<PearStore> storeList = pearMap.get(producto);
        if (storeList != null) {
            Iterator<PearStore> it = storeList.iterator();
            boolean found = false;

            while (!found && it.hasNext()) {
                PearStore nextStore = it.next();
                if (nextStore.equals(store)) {
                    found = true;
                    nextStore.setUnits(units);
                    nextStore.setScore(score);
                }
            }
        }
    }

    public double getScoreOfProduct(Product producto){
        double score = 0.0;
        int numElems = 0;

        Iterable<PearStore> storeList = pearMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                score += store.getScore();
                numElems++;
            }
        }

        return score / numElems;
    }

    public PearStore getGreatestSeller(Product producto){
        PearStore bestStore = null;
        int maxUnits = -1;

        Iterable<PearStore> storeList = pearMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                if (store.getUnits() > maxUnits) {
                    bestStore = store;
                }
            }
        }

        return bestStore;
    }

    public int getUnits(Product producto){
        int units = 0;

        Iterable<PearStore> storeList = pearMap.get(producto);
        if (storeList != null) {
            for (PearStore store : storeList) {
                units += store.getUnits();
            }
        }

        return units;
    }

    public boolean productExists(Product product){
        return pearMap.get(product) != null;
    }
}
