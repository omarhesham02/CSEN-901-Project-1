package org.aiteam.code;

import java.util.ArrayList;

public class WaterSortSearchState extends State<Bottle[]> {
    public WaterSortSearchState(Bottle[] value) {
        super(value);
    }

    public WaterSortSearchState(WaterSortSearchState state) {
        super(state.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        // Two bottles are equal if they have the same layers
        if (obj instanceof WaterSortSearchState other) {
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

    /**
     * Check if the current state is the goal state
     * @return true if the current state is the goal state, false otherwise
     * A state is a goal state if all the layers in every bottle is the same color
     */
    public boolean isGoalState() {
        Bottle[] bottles = getValue();
        for (Bottle bottle : bottles) {
          // Check if the bottle is empty
            if (bottle.getCurrentCapacity() == 0) {
                continue;
            }

            // Check that all the layers in the bottle are the same color
            Color topLayer = bottle.getTopLayer();
            int i = bottle.getCurrentCapacity();

            while (i < bottle.getMaximumCapacity()) {
                if (!topLayer.equals(bottle.getTopLayer())) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Perform the transition function on the current state
     * @param state the current state
     * @param operator the operator to PathCostFunction
     * @return the new state after applying the operator
     */

    public WaterSortSearchState TransitionFunction(WaterSortSearch state, Operator operator) {
        WaterSortSearchState newState = new WaterSortSearchState(this);
        return newState;

    }
}
