package exams.diciembre_2011;

import material.tree.binarysearchtree.BinarySearchTree;
import material.tree.binarysearchtree.LinkedBinarySearchTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MRUCache {

    private int cachesize;
    Map<String, MyFile> cache;
    BinarySearchTree<MyFile> lru;

    public MRUCache(int max) {
        cachesize=max;
        cache = new HashMap<>(cachesize);
        lru = new LinkedBinarySearchTree<>(new Comparator<MyFile>() {
            @Override
            public int compare(MyFile o1, MyFile o2) {
                if (o1.getLastModified() < o2.getLastModified()) {
                    return -1;
                } else if (o1.getLastModified() == o2.getLastModified()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public String getFile(String fname){
        MyFile file = cache.get(fname);

        if (file == null) { // No está en caché
            if (cache.size() == cachesize) { // La caché está llena
                MyFile deletedFile = lru.remove(lru.first());
                cache.remove(deletedFile.getName());
            }
            try {
                file = readFileFromDisk(fname);
            } catch (IOException e) {
                System.out.println("Error en la lectura del archivo");
                return "";
            }
            cache.put(fname, file);
            lru.insert(file);
        }
        return file.getContents();
    }

    protected MyFile readFileFromDisk(String fname) throws IOException {
        return new MyFile(System.currentTimeMillis(), Files.readString(Path.of(fname)), fname);
    }

    public void printMRU() {
        System.out.println("CACHE:");
        for (MyFile file : cache.values()) {
            System.out.println(file.toString());
        }
    }

    public static void main(String args[]) {
        // Number of entries in MRU cache is set to 10
        MRUCache h1=new MRUCache(10);
        for(int i=1;i<=20;i++) {
            // files are stored in a subdirectory called data
            h1.getFile("data"+ File.separatorChar+i);
        }
        h1.printMRU();
    }

}
