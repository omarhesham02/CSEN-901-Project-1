package code.watersort;

public class LayerGroup {
    private final Color color;
    private final int size;

    public LayerGroup(Color color, int size) {
        this.color = color;
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}
