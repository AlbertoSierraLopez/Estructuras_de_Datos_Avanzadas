package exams.enero_2016.usecase;

import material.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URJCInvest {
    private Map<String, OrganizationChart> map;

    public URJCInvest() {
        map = new HashMap<>();
    }

    public OrganizationChart searchCompany(String name) {
        return map.get(name);
    }

    public Iterable<Employee> getGrantHolders(String name) {
        OrganizationChart org = map.get(name);
        if (org == null) {
            throw new RuntimeException("404");
        }
        return org.getGrantHolders();
    }

    public Iterable<Employee> getChiefs(String name, Employee emp) {
        OrganizationChart org = map.get(name);
        if (org == null) {
            throw new RuntimeException("404");
        }
        return org.getChiefs(emp.getPosition());
    }

    public Iterable<Employee> getEmployees(String cargo) {
        List<Employee> list = new ArrayList<>();
        for (OrganizationChart org : map.values()) {
            list.addAll(org.getEmployees(cargo));
        }
        return list;
    }
}
