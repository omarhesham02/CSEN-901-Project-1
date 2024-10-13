package org.aiteam.code.watersort;

import org.aiteam.code.generic.FixedSizeStack;

public class Bottle implements Cloneable {
    private FixedSizeStack<Color> layers;

    public Bottle(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    public Color getTopLayer() {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot peek top layer from an empty bottle");
        return layers.peek();
    }

    public Color removeTopLayer() {
        if (layers.isEmpty())
            throw new IllegalStateException("Cannot remove top layer from an empty bottle");
        return layers.pop();
    }

    public int getCurrentCapacity() {
        return layers.size();
    }

    public FixedSizeStack<Color> getLayers() {
        return layers;
    }

    public void setLayers(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    public boolean isEmpty() {
        return layers.isEmpty();
    }

    public void addTopLayer(Color sourceTopLayer) {
        if (layers.isFull())
            throw new IllegalStateException("Cannot add layer to a full bottle");
        layers.push(sourceTopLayer);
    }

    @Override
    public Bottle clone() {
        try {
            Bottle clone = (Bottle) super.clone();
            clone.layers = layers.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // equals
    public boolean equals(Object obj) {
        if (!(obj instanceof Bottle other))
            return false;
        return layers.equals(other.layers);
    }

    @Override
    public String toString() {
        /*
         * remember the message from whatsapp :
         * -
         * e
         * e
         * b --------> [e, e, b, r, y]
         * r
         * y
         * -
         * 
         */
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
