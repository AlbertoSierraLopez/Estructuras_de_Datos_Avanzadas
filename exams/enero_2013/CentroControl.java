package exams.enero_2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentroControl {

    private Map<String, List<Multa>> matriculas;
    private Map<Integer, List<Multa>> radares;
    private Integer mejorRadar;

    public CentroControl() {
        matriculas = new HashMap<>();
        radares = new HashMap<>();
        mejorRadar = null;
    }

    public void setMulta(Multa m) {
        List<Multa> listM = matriculas.get(m.getMatricula());
        if (listM == null) {
            listM = new ArrayList<>();
            matriculas.put(m.getMatricula(), listM);
        }
        listM.add(m);

        List<Multa> listR = radares.get(m.getIdRadar());
        if (listR == null) {
            listR = new ArrayList<>();
            radares.put(m.getIdRadar(), listR);
        }
        listR.add(m);

        if (mejorRadar == null) {
            mejorRadar = m.getIdRadar();
        } else {
            int nMultas = listR.size();
            if (nMultas > radares.get(mejorRadar).size()) {
                mejorRadar = m.getIdRadar();
            }
        }
    }

    public float getCuantia(String matricula) {
        int sum = 0;
        for (Multa m : matriculas.get(matricula)) {
            sum += m.getImporteAPagar();
        }
        return sum;
    }

    public int getRadarMasValioso() {
        return mejorRadar;
    }
}
