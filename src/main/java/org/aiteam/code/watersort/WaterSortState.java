package org.aiteam.code.watersort;

import org.aiteam.code.generic.State;

public class WaterSortState extends State<Bottle[]> {
    public WaterSortState(Bottle[] value) {
        super(value);
    }

    public WaterSortState(WaterSortState state) {
        super(state.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        // Two bottles are equal if they have the same layers
        if (obj instanceof WaterSortState other) {
            Bottle[] bottles = getValue();
            Bottle[] otherBottles = other.getValue();
            if (bottles.length != otherBottles.length) {
                return false;
            }
            for (int i = 0; i < bottles.length; i++) {
                if (!bottles[i].equals(otherBottles[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
