package usecase.practicas_2019;


import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VirtualFileSystem {

    private NAryTree<File> tree;
    List<Position<File>> positionList;


    public void loadFileSystem(String path) {
        tree = new LinkedTree<>();
        positionList = new ArrayList<>();

        File dir = new File(path);
        Position<File> root = tree.addRoot(dir);
        positionList.add(root);

        for (File file : dir.listFiles()) {
            if (!file.getName().startsWith(".")) {  // No se incluyen los archivos ocultos, que empiezan por "."
                loadFileSystemAux(file, root);
            }
        }
    }

    private void loadFileSystemAux(File file, Position<File> dir) {
        if (!file.getName().startsWith(".")) {   // No se incluyen los archivos ocultos, que empiezan por "."
            Position<File> subPos = tree.add(file, dir);
            positionList.add(subPos);
            if (file.isDirectory()) {
                for (File subFile : file.listFiles()) {
                    loadFileSystemAux(subFile, subPos);
                }
            }
        }
    }


    public String getFileSystem() {
        StringBuilder string = new StringBuilder();
        if (!tree.isEmpty()) {
            getFileSystemAux(tree.root(), 0, string);
        }
        return string.toString();
    }

    private void getFileSystemAux(Position<File> pos, int tabs, StringBuilder string) {
        string.append(positionList.indexOf(pos) + " ");
        for (int i = 0; i < tabs; i++) {
            string.append("\t");
        }
        string.append(pos.getElement().getName() + "\n");

        for (Position<File> c : tree.children(pos)) {
            getFileSystemAux(c, tabs+1, string);
        }
    }


    public void moveFileById(int idFile, int idTargetFolder) {
        tree.moveSubtree(positionList.get(idFile), positionList.get(idTargetFolder));
    }

    public void removeFileById(int idFile) {
        tree.remove(positionList.get(idFile));
        removeFileByIdAux(positionList.get(idFile));    // Borrar sub-arbol de la lista de posiciones
    }

    private void removeFileByIdAux(Position<File> pos) {
        for (Position<File> c : tree.children(pos)) {
            removeFileByIdAux(c);
        }
        positionList.remove(pos);
    }


    public Iterable<String> findBySubstring(int idStartFile, String substring) {
        List<String> toString = new ArrayList<>();
        if (!tree.isEmpty()) {
            findBySubstringAux(positionList.get(idStartFile), substring, toString);
        }
        return toString;
    }

    private void findBySubstringAux(Position<File> pos, String substring, List<String> toString) {
        if (pos.getElement().getName().contains(substring)) {
            toString.add(positionList.indexOf(pos) + "\t" + pos.getElement().getName());
        }
        for (Position<File> c : tree.children(pos)) {
            findBySubstringAux(c, substring, toString);
        }
    }

    public Iterable<String> findBySize(int idStartFile, long minSize, long maxSize) {
        if (maxSize < minSize) {
            throw new RuntimeException("Invalid range.");
        }
        List<String> toString = new ArrayList<>();
        if (!tree.isEmpty()) {
            findBySizeAux(positionList.get(idStartFile), minSize, maxSize, toString);
        }
        return toString;
    }

    private void findBySizeAux(Position<File> pos, long minSize, long maxSize, List<String> toString) {
        if (pos.getElement().isFile() && pos.getElement().length() >= minSize && pos.getElement().length() <= maxSize) {
            toString.add(positionList.indexOf(pos) + "\t" + pos.getElement().getName());
        }
        for (Position<File> c : tree.children(pos)) {
            findBySizeAux(c, minSize, maxSize, toString);
        }
    }

    public String getFileVirtualPath(int idFile) {
        StringBuilder toString = new StringBuilder();
        toString.append("vfs:");
        if (!tree.isEmpty()) {
            getFileVirtualPathAux(positionList.get(idFile), toString);
        }
        return toString.toString();
    }

    private void getFileVirtualPathAux(Position<File> pos, StringBuilder toString) {
        if (!tree.isRoot(pos)) {
            getFileVirtualPathAux(tree.parent(pos), toString);
        }
        toString.append("/" + pos.getElement().getName());
    }

    public String getFilePath(int idFile) {
        return positionList.get(idFile).getElement().getPath();
    }

}
