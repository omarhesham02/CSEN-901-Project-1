package org.aiteam.code.watersort;

import org.aiteam.code.generic.Node;
import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.Problem;
import org.aiteam.code.generic.State;

import java.util.ArrayList;

public class WaterSortProblem extends Problem<Integer, Bottle[]> {

    public WaterSortProblem(State<Bottle[]> initialState) {
        super(initialState);

        // TODO: Pass the actual operators in a list here somehow
        this.operators = new ArrayList<>();
    }

    /**
     * Check if the current state is the goal state
     * @return true if the current state is the goal state, false otherwise
     * A state is a goal state if all the layers in every bottle is the same color
     */
    @Override
    public boolean isGoalNode(Node<Integer, Bottle[]> node) {
        Bottle[] bottles = node.state().getValue();

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

    @Override
    public int pathCost(Node<Integer, Bottle[]> node) {
        return node.parent().pathCost() + node.operatorCost();

    }

    @Override
    public State<Bottle[]> transition(State<Bottle[]> state, Operator<Integer> operator) {
        return null;
    }
}
