package exams.septiembre_2020.usecase;

public class Item {

    private String name;
    private Integer score;

    public Item(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }
}
