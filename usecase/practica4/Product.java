package usecase.practica4;

public class Product {

    private String name;
    private int year;

    public Product(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Product p;
        try {
            p = (Product) obj;
        } catch (ClassCastException ex) {
            return false;
        }
        return (p.getName().equals(name)) && (p.getYear() == year);
    }

    @Override
    public String toString() {
        return name + " " + year;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
