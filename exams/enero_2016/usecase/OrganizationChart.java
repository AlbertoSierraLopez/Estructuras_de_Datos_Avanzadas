package exams.enero_2016.usecase;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;

import java.util.*;

public class OrganizationChart {
    private NAryTree<Employee> tree;
    private Set<Employee> set;                  // Set de hojas (becarios)
    private Map<String, List<Employee>> map;    // Mapa de cargos

    public OrganizationChart() {
        tree = new LinkedTree<>();
        set = new HashSet<>();
        map = new HashMap<>();
    }

    private void insertMap(Employee employee) {
        List<Employee> list = map.get(employee.getCargo());
        if (list == null) {
            list = new ArrayList<>();
            list.add(employee);
            map.put(employee.getCargo(), list);
        } else {
            list.add(employee);
        }
    }

    public Position<Employee> insertCEO(Employee employee) {
        set.add(employee);
        insertMap(employee);
        Position<Employee> pos = tree.addRoot(employee);
        employee.setPosition(pos);
        return pos;
    }

    public Position<Employee> insertEmployee(Position<Employee> chief, Employee employee) {
        set.add(employee);
        set.remove(chief.getElement());
        Position<Employee> pos = tree.add(employee, chief);
        employee.setPosition(pos);
        return pos;
    }

    public void removeEmployee(Position<Employee> employee) {
        tree.remove(employee);
        set.remove(employee);
        map.get(employee.getElement().getCargo()).remove(employee);
    }

    public Iterable<Employee> getGrantHolders() {
        return set;
    }

    public Iterable<Employee> getChiefs(Position<Employee> emp) {
        List<Employee> list = new ArrayList<>();

        while (!tree.isRoot(emp)) {
            emp = tree.parent(emp);
            list.add(emp.getElement());
        }

        return list;
    }

    public List<Employee> getEmployees(String cargo) {
        return map.get(cargo);
    }
}
