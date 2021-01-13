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
    Map<String, FamilyMember> namesMap;
    LinkedTree<FamilyMember> familiesTree;
//  Map<String, LinkedTree<FamilyMember>> familiesMap;

    public GameOfThrones() {
        memberMap = new HashMap<>();
        namesMap = new HashMap<>();
        familiesTree = new LinkedTree<>();
        familiesTree.addRoot(null); // La raíz del arbol de familas no debería ser accesible, no es nada
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
            namesMap.put(split[2] + " " + split[3], member);

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
        Position<FamilyMember> familyHead = null;
        // Encontrar la familia
        for (Position<FamilyMember> p : familiesTree.children(familiesTree.root())) {
            if (p.getElement().getSurname().equals(surname)) {
                familyHead = p;
                break;
            }
        }


        if (familyHead == null) {
            throw new RuntimeException("The family doesn't exist");
        }

        // Crear el nuevo arbol a devolver
        LinkedTree<FamilyMember> subTree = new LinkedTree<>();
        Position<FamilyMember> subRoot = subTree.addRoot(familyHead.getElement());
        createTree(subRoot, familyHead, subTree);

        return subTree;
    }

    private void createTree(Position<FamilyMember> subNode, Position<FamilyMember> node, LinkedTree<FamilyMember> subTree) {
        for (Position<FamilyMember> child : familiesTree.children(node)) {
            Position<FamilyMember> newSubNode = subTree.add(child.getElement(), subNode);
            createTree(newSubNode, child, subTree);
        }
    }

    public String findHeir(String surname){
        Position<FamilyMember> familyHead = null;
        // Encontrar la familia
        for (Position<FamilyMember> p : familiesTree.children(familiesTree.root())) {
            if (p.getElement().getSurname().equals(surname)) {
                familyHead = p;
                break;
            }
        }

        if (familyHead == null) {
            throw new RuntimeException("The family doesn't exist");
        }

        String heir = null;
        String femaleHeir = null;
        // Encontrar al heredero/a
        int maleHeirAge = -1;
        int femaleHeirAge = -1;
        for (Position<FamilyMember> child : familiesTree.children(familyHead)) {
            if (child.getElement().getGender() == 'M' && child.getElement().getAge() > maleHeirAge) {
                heir = child.getElement().getName() + " " + child.getElement().getSurname();
                maleHeirAge = child.getElement().getAge();
            } else if (child.getElement().getGender() == 'F' && child.getElement().getAge() > femaleHeirAge) {
                femaleHeir = child.getElement().getName() + " " + child.getElement().getSurname();
                femaleHeirAge = child.getElement().getAge();
            }
        }

        if (heir != null) {
            return heir;
        } else if (femaleHeir != null){
            return femaleHeir;
        } else {
            throw new RuntimeException("No heir was found");
        }
    }

    public void changeFamily(String memberName, String newParent){
        Position<FamilyMember> pOrig = namesMap.get(memberName).getPosition();
        Position<FamilyMember> pDest = namesMap.get(newParent).getPosition();

        familiesTree.moveSubtree(pOrig, pDest);
    }

    public boolean areFamily(String name1, String name2){
        Position<FamilyMember> p1 = namesMap.get(name1).getPosition();
        Position<FamilyMember> p2 = namesMap.get(name2).getPosition();

        return getFamilyHead(p1) == getFamilyHead(p2);
    }

    private Position<FamilyMember> getFamilyHead(Position<FamilyMember> p) {
        while (!familiesTree.isRoot(familiesTree.parent(p))) {
            p = familiesTree.parent(p);
        }
        return p;
    }
}
