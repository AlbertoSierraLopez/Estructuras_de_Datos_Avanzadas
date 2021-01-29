package exams.enero_2016.usecase;

import material.Position;

public class Employee {
    private String empresa;
    private String nombre;
    private String cargo;
    private String descripcion;
    private Position<Employee> position;

    public Employee(String empresa, String nombre, String cargo, String descripcion) {
        this.empresa = empresa;
        this.nombre = nombre;
        this.cargo = cargo;
        this.descripcion = descripcion;
        position = null;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Position<Employee> getPosition() {
        return position;
    }

    public void setPosition(Position<Employee> position) {
        this.position = position;
    }
}
