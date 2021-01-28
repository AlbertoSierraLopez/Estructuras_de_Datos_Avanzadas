package exams.septiembre_2020.usecase;

public class Cup {

    private String name;
    private int level;
    private int timesCompleted;

    public Cup(String name, int level) {
        this.name = name;
        this.level = level;
        timesCompleted = 0;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getTimesCompleted() {
        return timesCompleted;
    }

    public void addCompletion() {
        timesCompleted++;
    }

    @Override
    public String toString() {
        return name + " (" + level + "): " + timesCompleted +" times.";
    }
}
