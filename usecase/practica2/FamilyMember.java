package usecase.practica2;

import material.Position;

public class FamilyMember {
    private String id;
    private String name;
    private String surname;
    private char gender;
    private int age;
    private Position<FamilyMember> position;

    public FamilyMember(String id, String name, String surname, char gender, int age, Position<FamilyMember> position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Position<FamilyMember> getPosition() {
        return position;
    }

    public void setPosition(Position<FamilyMember> position) {
        this.position = position;
    }
}
