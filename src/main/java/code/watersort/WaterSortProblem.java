package code.watersort;

import code.Node;
import code.generic.Problem;
import code.generic.SearchState;

public class WaterSortProblem extends Problem {

    public WaterSortProblem(SearchState initialState, String strategy) {
        super(initialState, strategy);

        addAllOperatorPermutations(initialState);
    }

    /**
     * Check if the current state is the goal state.
     * A state is a goal state if all the layers in every bottle in this state has
     * the same color.
     *
     * @return true if the current state is the goal state, false otherwise
     */
    @Override
    public boolean goalTestFn(Node node) {
        Bottle[] bottles = (Bottle[]) node.getState().getValue();

        for (Bottle bottle : bottles) {
            if (bottle.isEmpty())
                continue;

            Color topColor = bottle.getTopLayer();

            for (int i = 0; i < bottle.getLayers().size(); i++)
                if (!topColor.equals(bottle.getLayers().get(i)))
                    return false;
        }

        return true;
    }

    private void addAllOperatorPermutations(SearchState initialState) {
        WaterSortState state = (WaterSortState) initialState;
        Bottle[] bottles = state.getBottles();

        for (int i = 0; i < bottles.length; i++)
            for (int j = 0; j < bottles.length; j++)
                if (i != j)
                    this.addOperator(new Pour(i, j));
    }
}