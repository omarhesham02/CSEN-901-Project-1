package code.watersort.heuristics;

import code.Node;
import code.generic.HeuristicFunction;
import code.watersort.Bottle;
import code.watersort.Color;
import code.watersort.WaterSortState;

public class DifferentBottlesLeftHeuristic implements HeuristicFunction {

    /**
     * An admissible heuristic function that estimates a cost based on
     * the current number of bottles that have layers with different colors
     */

    @Override
    public int apply(Node node) {

        if (!(node.getState() instanceof WaterSortState))
            throw new IllegalArgumentException("The state must be of type WaterSortState");

        Bottle[] bottles = (Bottle[]) node.getState().getValue();
        int cost = 0;

        for (Bottle bottle : bottles) {
            if (bottle.isEmpty())
                continue;

            Color topColor = bottle.getTopLayer();

            for (int i = 0; i < bottle.layers().size(); i++)
                if (!topColor.equals(bottle.layers().get(i)))
                    cost++;
        }

        return cost;
    }
}