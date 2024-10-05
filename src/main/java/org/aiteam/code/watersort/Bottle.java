package org.aiteam.code.watersort;

import java.util.Arrays;

public class Bottle {
    // TODO: Maybe convert to Stack? Then implement the needed convenience methods
    private final Color[] layers;
    private int currentCapacity = 0;

    public Bottle(Color[] layers) {
        this.layers = layers;
    }

    public Color getTopLayer() {
        return layers[currentCapacity];
    }

    int getCurrentCapacity() {
        return currentCapacity;
    }

    int getMaximumCapacity() {
        return layers.length;
    }

    public void removeTopLayer() {
        layers[currentCapacity] = Color.e;
    }

    public void addTopLayer(Color sourceTopLayer) {
        layers[currentCapacity] = sourceTopLayer;
    }

    public String toString() {
        return Arrays.toString(layers);
    }

    public Color[] getLayers() {
        return layers;
    }
}
