package org.aiteam.code.watersort;

import org.aiteam.code.generic.FixedSizeStack;
import org.aiteam.code.generic.SearchState;

public class WaterSortState extends SearchState {

    public WaterSortState(Bottle[] value) {
        super(value);
    }

    public WaterSortState(WaterSortState state) {
        super(state.getValue());
    }

    public Bottle[] getBottles() {
        return (Bottle[]) getValue();
    }

    @Override
    public boolean equals(Object obj) {
        // Two bottles are equal if they have the same layers
        if (!(obj instanceof WaterSortState other))
            return false;

        Bottle[] bottles = (Bottle[]) getValue();
        Bottle[] otherBottles = (Bottle[]) other.getValue();
        if (bottles.length != otherBottles.length)
            return false;
        for (int i = 0; i < bottles.length; i++) {
            if (!bottles[i].equals(otherBottles[i]))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Bottle[] bottles = (Bottle[]) getValue();
        for (Bottle bottle : bottles) {
            sb.append(bottle.toString()).append(";");
        }
        return sb.toString();
    }

    // test equality of 2 watersort states
    public static void main(String[] args) {
        // Create FixedSizeStack<Color> layers for the first WaterSortState
        FixedSizeStack<Color> layers1 = new FixedSizeStack<>(3);
        layers1.push(Color.r);
        layers1.push(Color.g);
        layers1.push(Color.b);

        FixedSizeStack<Color> layers2 = new FixedSizeStack<>(3);
        layers2.push(Color.y);
        layers2.push(Color.o);

        // Create Bottle objects with the layers
        Bottle bottle1 = new Bottle(layers1);
        Bottle bottle2 = new Bottle(layers2);

        // Create the first WaterSortState with an array of bottles
        Bottle[] bottles1 = { bottle1, bottle2 };
        WaterSortState state1 = new WaterSortState(bottles1);

        // Create FixedSizeStack<Color> layers for the second WaterSortState
        FixedSizeStack<Color> layers3 = new FixedSizeStack<>(3);
        layers3.push(Color.r);
        layers3.push(Color.g);
        layers3.push(Color.b);

        FixedSizeStack<Color> layers4 = new FixedSizeStack<>(3);
        layers4.push(Color.y);
        layers4.push(Color.o);

        // Create Bottle objects with the layers
        Bottle bottle3 = new Bottle(layers3);
        Bottle bottle4 = new Bottle(layers4);

        // Create the second WaterSortState with an array of bottles
        Bottle[] bottles2 = { bottle3, bottle4 };
        WaterSortState state2 = new WaterSortState(bottles2);

        // Test the equality of the two WaterSortState objects
        boolean areEqual = state1.equals(state2);
        System.out.println("The 2 states are: " + (areEqual ? "Equal" : "Not equal"));
    }
}
