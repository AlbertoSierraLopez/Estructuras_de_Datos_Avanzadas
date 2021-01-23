package usecase.practica4;

public class PearStore {

    private String name;
    private int id;
    private int units;
    private double score;

    public PearStore(String name, int id) {
        this(name, id, 0, 0.0);
    }

    public PearStore(String name, int id, int units, double score) {
        this.name = name;
        this.id = id;
        this.units = units;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getUnits() {
        return units;
    }

    public double getScore() {
        return score;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        PearStore p;
        try {
            p = (PearStore) obj;
        } catch (ClassCastException ex) {
            return false;
        }
        return (p.getId() == id);
    }
}
