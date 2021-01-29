package exams.enero_2014.usecase;

public class Viaje {
    private String origen;
    private String destino;
    private int duracion;

    public Viaje(String origen, String destino, int duracion) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDuracion() {
        return duracion;
    }
}
