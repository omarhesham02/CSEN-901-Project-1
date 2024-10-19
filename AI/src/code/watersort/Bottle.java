package code.watersort;

import code.WaterSortSearch;
import code.generic.FixedSizeStack;

public class Bottle implements Cloneable {
    private final FixedSizeStack<Color> layers;

    public Bottle(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    public FixedSizeStack<Color> getLayers() {
        return layers;
    }

    public Color getTopLayer() {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot peek top layer from an empty bottle");

        return layers.peek();
    }

    public Color popTopLayer() {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot remove top layer from an empty bottle");

        return layers.pop();
    }

    public LayerGroup peekTopLayerGroup() throws CloneNotSupportedException {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot peek top LayerGroup from an empty bottle");
        FixedSizeStack<Color> layersCopy = layers.clone();
        Color topColor = layersCopy.peek();
        int groupSize = 0;

        while (!layersCopy.isEmpty()) {
            Color curColor = layersCopy.pop();

            if (curColor.equals(topColor))
                groupSize++;
            else
                break;
        }

        if (groupSize == 0)
            return null;

        return new LayerGroup(topColor, groupSize);
    }

    public LayerGroup popTopLayerGroup() throws CloneNotSupportedException {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot remove top LayerGroup from an empty bottle");

        LayerGroup topLayerGroup = peekTopLayerGroup();
        int topLayerGroupSize = topLayerGroup.getSize();

        while (topLayerGroupSize-- > 0)
            layers.pop();

        return topLayerGroup;
    }

    public void addLayerGroup(LayerGroup layerGroup) {
        int layerGroupSize = layerGroup.getSize();
        int final_size = layers.size() + layerGroupSize;

        if (final_size > WaterSortSearch.bottleCapacity)
            throw new IllegalStateException("bottle will overflow if we added the layer !");

        while (layerGroupSize-- > 0)
            layers.push(layerGroup.getColor());
    }

    public int getCurrentSize() {
        return layers.size();
    }

    public boolean isEmpty() {
        return layers.isEmpty();
    }

    public Bottle clone() throws CloneNotSupportedException {
        return new Bottle(layers.clone());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Bottle other))
            return false;

        return layers.equals(other.layers);
    }

    public int hashCode() {
        return layers.hashCode();
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
}
