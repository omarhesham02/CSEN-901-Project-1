package code.watersort;

import code.WaterSortSearch;
import code.generic.FixedSizeStack;

public class Bottle {
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

    public LayerGroup peekTopLayerGroup() {
        FixedSizeStack<Color> layersCopy = layers.copy();

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

    public LayerGroup popTopLayerGroup() {
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

    public Bottle copy() {
        return new Bottle(layers.copy());
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
     * The bottle is represented as a comma-separated list of colors from top to bottom.
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
        StringBuilder sb = new StringBuilder();

        int emptyColorsCount = WaterSortSearch.bottleCapacity - layers.size();

        while (emptyColorsCount-- > 0)
            sb.append("e,");

        for (int i = layers.size() - 1; i >= 0; i--)
            sb.append(layers.get(i)).append(",");

        // Remove last comma
        if (!sb.isEmpty())
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    // TODO: Remove this main method
    // Test equality of 2 bottles
    public static void main(String[] args) {
        // ----------------------------------- test equality of 2
        // different bottles with same colors
        FixedSizeStack<Color> layers1 = new FixedSizeStack<>(3);
        layers1.push(Color.r);
        layers1.push(Color.g);
        layers1.push(Color.b);
        Bottle bottle1 = new Bottle(layers1);

        FixedSizeStack<Color> layers2 = new FixedSizeStack<>(3);
        layers2.push(Color.r);
        layers2.push(Color.g);
        layers2.push(Color.b);
        Bottle bottle2 = new Bottle(layers2);

        System.out.println("The two different Bottles with same colors are equal: " + bottle1.equals(bottle2));

        // ----------------------------------test equality with copy
        Bottle bottle3 = bottle1.copy();
        System.out.println("The bottle and its copy are equal: " + bottle1.equals(bottle3));

        // --------------------------------- manipulate the clone and test equality
        bottle3.popTopLayer();
        System.out.println("Manipulating the clone makes it different from the original: " + !bottle1.equals(bottle3));
    }
}
