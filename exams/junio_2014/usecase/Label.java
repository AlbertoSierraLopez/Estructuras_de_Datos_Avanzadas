package exams.junio_2014.usecase;

import material.Position;

public class Label {
    private String text;
    private int font;
    private int[] position;
    private int[] rgb;

    public Label(String label, int font) {
        this.text = text;
        this.font = font;
        position = new int[2];
        rgb = new int[3];
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int r, int g, int b) {
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
    }
}
