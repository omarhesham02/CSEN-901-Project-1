package org.aiteam.code.watersort;

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
        return bottles.equals(otherBottles);
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
}
