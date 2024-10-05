package org.aiteam.code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bottle {
    private Color[] layers;
    private int currentCapacity = 0;

    public Bottle(ArrayList<Color> layers, int capacity) {
        this.layers = layers.toArray(new Color[0]);
    }

    public Bottle() {

    }

    public Color getTopLayer() {
        return layers[currentCapacity];
    }

    int getCurrentCapacity() {
        return currentCapacity;
    }


    int getMaximumCapacity() {
        return Utils.BOTTLE_CAPACITY;
    }

    public void removeTopLayer() {
        layers[currentCapacity] = Color.EMPTY;
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
