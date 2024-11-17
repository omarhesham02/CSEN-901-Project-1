package code.watersort;

import code.WaterSortSearch;
import code.generic.FixedSizeStack;

public record Bottle(FixedSizeStack<Color> layers) {

    public Color getTopLayer() {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot peek top layer from an empty bottle");

        return layers.peek();
    }

    public boolean isEmpty() {
        return layers.isEmpty();
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Bottle clone() throws CloneNotSupportedException {
        return new Bottle(layers.clone());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Bottle other))
            return false;

        return layers.equals(other.layers);
    }

    /**
     * The bottle is represented as a comma-separated list of colors from top to
     * bottom.
     * For example:
     * e
     * e
     * b
     * r
     * y
     * Gets this representation --------> [e, e, b, r, y]
     */
    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean colored) {
        StringBuilder sb = new StringBuilder();

        int emptyColorsCount = WaterSortSearch.bottleCapacity - layers.size();

        while (emptyColorsCount-- > 0)
            sb.append("e,");

        for (int i = layers.size() - 1; i >= 0; i--) {
            Color color = layers.get(i);
            String colorText = color.toString(colored);
            sb.append(colorText).append(",");
        }
        // Remove last comma
        if (!sb.isEmpty())
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public boolean isFull() {
        return layers.size() == WaterSortSearch.bottleCapacity;
    }
}
