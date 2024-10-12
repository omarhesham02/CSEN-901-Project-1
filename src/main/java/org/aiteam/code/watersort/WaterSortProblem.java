package org.aiteam.code.watersort;

import org.aiteam.code.generic.Node;
import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.Problem;
import org.aiteam.code.generic.SearchState;

import java.util.ArrayList;

public class WaterSortProblem extends Problem {

    public WaterSortProblem(SearchState initialState) {
        super(initialState);

        // Add all possible combinations of operators between distinct bottles
        // to the list of operators
        WaterSortState state = (WaterSortState) initialState;
        Bottle[] bottles = state.getBottles();
        this.operators = new ArrayList<>();
        for (int i = 0; i < bottles.length; i++) {
            for (int j = 0; j < bottles.length; j++) {
                if (i != j) {
                    this.operators.add(new Pour(i, j));
                }
            }
        }
    }

    /**
     * Check if the current state is the goal state.
     * A state is a goal state if all the layers in every bottle in this state has the same color.
     *
     * @return true if the current state is the goal state, false otherwise
     */
    @Override
    public boolean goalTestFn(Node node) {
        Bottle[] bottles = (Bottle[]) node.getState().getValue();

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
}
