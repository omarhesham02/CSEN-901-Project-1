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

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getMaximumCapacity() {
        return layers.length;
    }

    public Color removeTopLayer() {
        // Remove the top layer and return it
        Color topLayer = layers[currentCapacity];
        layers[currentCapacity] = null;
        currentCapacity--;
        return topLayer;
    }

    public void addTopLayer(Color sourceTopLayer) {
        layers[currentCapacity] = sourceTopLayer;
        currentCapacity++;
    }

    public String toString() {
        return Arrays.toString(layers);
    }

    public Color[] getLayers() {
        return layers;
    }
}
