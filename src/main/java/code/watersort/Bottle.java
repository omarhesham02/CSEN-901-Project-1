package code.watersort;

import code.WaterSortSearch;
import code.generic.FixedSizeStack;

public class Bottle implements Cloneable {
    private FixedSizeStack<Color> layers;

    public Bottle(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    // ---------------------------------------------------- single layer operations
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

    // -------------------------------------------------------- LayerGroup
    // operations
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
        while (topLayerGroupSize-- > 0) {
            layers.pop();
        }
        return topLayerGroup;
    }

    public void addLayerGroup(LayerGroup layerGroup) {
        int layerGroupSize = layerGroup.getSize();
        int final_size = layers.size() + layerGroupSize;
        if (final_size > WaterSortSearch.bottleCapacity)
            throw new IllegalStateException("bottle will overflow if we added the layer !");
        while (layerGroupSize-- > 0) {
            layers.push(layerGroup.getColor());
        }
    }

    // ------------------------------------------------------- common
    public int getCurrentSize() {
        return layers.size();
    }

    public void setLayers(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    public boolean isEmpty() {
        return layers.isEmpty();
    }

    public Bottle clone () throws CloneNotSupportedException {
        return new Bottle(layers.clone());
    }

    // equals
    public boolean equals(Object obj) {
        if (!(obj instanceof Bottle other))
            return false;
        return layers.equals(other.layers);
    }

    // hashcode
    public int hashCode() {
        return layers.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int emptyColorsCount = WaterSortSearch.bottleCapacity - layers.size();
        while (emptyColorsCount-- > 0) {
            sb.append("e,");
        }

        for (int i = layers.size() - 1; i >= 0; i--) {
            sb.append(layers.get(i) + ",");
        }
        // remove last comma
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
