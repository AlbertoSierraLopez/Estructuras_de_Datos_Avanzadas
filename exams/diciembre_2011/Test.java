package exams.diciembre_2011;

public class Test {
    public static void main(String[] args) {

        ContenedorDeBoundingBox boxes = new ContenedorDeBoundingBox();

        boxes.insertar(new BoundingBox(0, 0, 1, 1));
        boxes.insertar(new BoundingBox(0, 0, 2, 3));
        boxes.insertar(new BoundingBox(0, 0, 3, 1));

        for (BoundingBox b : boxes.encontrar(6)) {
            System.out.println(b.toString());
        }

    }
}
