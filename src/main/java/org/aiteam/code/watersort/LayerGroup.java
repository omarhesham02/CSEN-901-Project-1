package org.aiteam.code.watersort;

public class LayerGroup {
    private Color color;
    private int size;

    public LayerGroup(Color color, int size) {
        this.color = color;
        this.size = size;
    }

    public Color[] getComponents() {
        Color[] components = new Color[size];
        for (int i = 0; i < size; i++) {
            components[i] = color;
        }
        return components;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
