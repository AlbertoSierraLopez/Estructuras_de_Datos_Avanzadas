package exams.junio_2016.usecase;

public class Voter implements Comparable<Voter> {

    private String dni;
    private String nombre;
    private String calle;
    private String mesa;

    public Voter(String dni, String nombre, String calle, String mesa) {
        this.dni = dni;
        this.nombre = nombre;
        this.calle = calle;
        this.mesa = mesa;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalle() {
        return calle;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    @Override
    public int compareTo(Voter v) {
        return dni.compareTo(v.dni);
    }
}
