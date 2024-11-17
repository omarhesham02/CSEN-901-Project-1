package code.watersort;

import code.generic.SearchState;

import java.util.Arrays;

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

        for (int i = 0; i < bottles.length; i++)
            if (!bottles[i].equals(otherBottles[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        // Two states are equal if they contain the same bottles array
        return Arrays.hashCode((Bottle[]) getValue());
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean colored) {
        StringBuilder sb = new StringBuilder();
        Bottle[] bottles = (Bottle[]) getValue();

        for (Bottle bottle : bottles)
            sb.append(bottle.toString(colored)).append(";");
        return sb.toString();
    }

    public String getVerticalView(String middleText) throws CloneNotSupportedException {
        StringBuilder result = new StringBuilder("\n");
        Bottle[] bottles = (Bottle[]) getValue();
        Bottle[] bottlesCopy = new Bottle[bottles.length];
        for (int i = 0; i < bottles.length; i++) {
            bottlesCopy[i] = bottles[i].clone();
            while (!bottlesCopy[i].layers().isFull()) {
                bottlesCopy[i].layers().push(null);
            }
        }
        int bottleMaxSize = bottlesCopy[0].layers().getMaxCapacity();
        for (int i = 0; i < bottleMaxSize; i++) {
            for (Bottle bottle : bottlesCopy) {
                Color color = bottle.layers().pop();
                result.append("[ ");
                // result += color == null ? " " : color.toString().toUpperCase();
                result.append(color == null ? " ".repeat(6) : color.getFullName(true));
                result.append(" ]");
                result.append("   ");
            }
            if (i == bottleMaxSize / 2) {
                result.append(middleText);
            }
            result.append("\n");

        }

        return result.toString();

    }
}
