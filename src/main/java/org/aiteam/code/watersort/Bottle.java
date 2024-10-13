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

    // test equality of 2 bottles
    public static void main(String[] args) {
        // Create FixedSizeStack<Color> layers for the first Bottle
        FixedSizeStack<Color> layers1 = new FixedSizeStack<>(3);
        layers1.push(Color.r);
        layers1.push(Color.g);
        layers1.push(Color.b);

        // Create the first Bottle with the layers
        Bottle bottle1 = new Bottle(layers1);

        // Create FixedSizeStack<Color> layers for the second Bottle
        FixedSizeStack<Color> layers2 = new FixedSizeStack<>(3);
        layers2.push(Color.r);
        layers2.push(Color.g);
        layers2.push(Color.b);

        // Create the second Bottle with the layers
        Bottle bottle2 = new Bottle(layers2);

        // Test the equality of the two Bottle objects
        boolean areEqual = bottle1.equals(bottle2);
        System.out.println("The two Bottle objects are equal: " + areEqual);
    }
}
