package exams.diciembre_2011;

public class BoundingBox {
    private int[] coordenada1;
    private int[] coordenada2;
    private int area;

    public BoundingBox(int x1, int y1, int x2, int y2) {
        coordenada1 = new int[2];
        coordenada1[0] = x1;
        coordenada1[1] = y1;

        coordenada2 = new int[2];
        coordenada2[0] = x2;
        coordenada2[1] = y2;

        area = Math.abs(x2-x1) * Math.abs(y2 - y1);
    }

    public int[] getCoordenada1() {
        return coordenada1;
    }

    public int[] getCoordenada2() {
        return coordenada2;
    }

    public int getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "[" + coordenada1[0] + "," + coordenada1[1] + "] [" + coordenada2[0] + "," + coordenada2[1] + "]";
    }
}
