package material.test.practica4;


import usecase.practica4.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PearRegisterTest {
    private static PearRegister register;

    @org.junit.jupiter.api.BeforeAll
    static void loadFile() {
        register = new PearRegister();
        String path = "src/PearSalesFile.txt";
        register.loadFile(path);
    }


    @org.junit.jupiter.api.Test
    void addProductAndProductExists() {
        Product producto = new Product("NewYMak", 2021);
        PearStore pearStore = new PearStore("CarabanchelPearStore", 123456);
        List<PearStore> pearStoreList = new ArrayList<>();
        pearStoreList.add(pearStore);
        register.addProduct(producto, pearStoreList);
        assertTrue(register.productExists(producto));
    }

    @org.junit.jupiter.api.Test
    void getScoreOfProduct() {
        Product producto = new Product("MakBukPro", 2019);
        assertEquals(4.1, Math.round(register.getScoreOfProduct(producto) * 10) / 10.0);
    }

    @org.junit.jupiter.api.Test
    void getGreatestSeller() {
        Product producto = new Product("MakBukPro", 2019);
        PearStore ps = register.getGreatestSeller(producto);
        PearStore toTest = new PearStore("PrincipePioPearStore", 123444);
        assertEquals(ps, toTest);
    }

    @org.junit.jupiter.api.Test
    void getUnits() {
        Product producto = new Product("MakBukPro", 2019);
        int units = register.getUnits(producto);
        assertEquals(2837, units);
    }
}