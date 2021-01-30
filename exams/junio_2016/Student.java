package exams.junio_2016;

public class Student implements Comparable<Student> {

    String name;
    String surname;
    int exp;

    public Student(String name, String surname, int exp) {
        this.name = name;
        this.surname = surname;
        this.exp = exp;
    }

    @Override
    public int compareTo(Student s) {
        if (exp < s.exp) {
            return -1;
        } else if (exp == s.exp) {
            return 0;
        } else {
            return 1;
        }
    }
}
