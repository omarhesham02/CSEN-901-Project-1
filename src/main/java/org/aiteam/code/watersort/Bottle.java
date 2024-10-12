package org.aiteam.code.watersort;

import org.aiteam.code.generic.FixedSizeStack;

import java.util.Stack;

public class Bottle implements Cloneable {
    // TODO: Maybe convert to Stack? Then implement the needed convenience methods
    private FixedSizeStack<Color> layers;

    public Bottle(FixedSizeStack<Color> layers) {
        this.layers = layers;
    }

    public Color getTopLayer() {
        return layers.peek();
    }

    public Color removeTopLayer() {
        if (!layers.isEmpty())
            return layers.pop();
        return null;
    }

    public int getCurrentCapacity() {
        return layers.size();
    }

    public void addTopLayer(Color sourceTopLayer) {
       if (layers.size() == WaterSortSearch.bottleCapacity)
           return;

       layers.push(sourceTopLayer);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < layers.size(); i++) {
            sb.append(layers.get(i));
            if (i != layers.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
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
}
