package usecase.practica2;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameOfThrones {
    Map<String, FamilyMember> memberMap;
    LinkedTree<FamilyMember> familiesTree;
//  Map<String, LinkedTree<FamilyMember>> familiesMap;

    public GameOfThrones() {
        memberMap = new HashMap<>();
        familiesTree = new LinkedTree<>();
        familiesTree.addRoot(null); // La raí del arbol de familas no debería ser accesible, no es nada
    }

    public void loadFile(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        Scanner scanner = new Scanner(file);
        String[] split;

        String line = scanner.nextLine();
        // Leer miembros
        while (line.charAt(0) != '-') {
            split = line.split(" ");

            FamilyMember member = new FamilyMember(split[0], split[2], split[3], split[4].charAt(1), Integer.parseInt(split[5]),null);
            memberMap.put(split[0], member);

            line = scanner.nextLine();
        }
        // Leer raíces
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            line = scanner.nextLine();
            FamilyMember member = memberMap.get(line);
            Position<FamilyMember> pos = familiesTree.add(member, familiesTree.root());
            member.setPosition(pos);
        }
        // Leer parentescos
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            split = line.split(" ");
            FamilyMember parent = memberMap.get(split[0]);
            FamilyMember child = memberMap.get(split[2]);
            Position<FamilyMember> pos = familiesTree.add(child, parent.getPosition());
            child.setPosition(pos);
        }
    }

    public LinkedTree<FamilyMember> getFamily(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public String findHeir(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public void changeFamily(String memberName, String newParent){
        throw new RuntimeException("Not yet implemented");
    }

    public boolean areFamily(String name1, String name2){
        throw new RuntimeException("Not yet implemented");
    }
}
