package exams.enero_2013;

public class Multa {
    private String Matricula;
    private float importeAPagar;
    private int idRadar;

    public Multa(String matricula, float importeAPagar, int idRadar) {
        Matricula = matricula;
        this.importeAPagar = importeAPagar;
        this.idRadar = idRadar;
    }

    public String getMatricula() {
        return Matricula;
    }

    public float getImporteAPagar() {
        return importeAPagar;
    }

    public int getIdRadar() {
        return idRadar;
    }
}
