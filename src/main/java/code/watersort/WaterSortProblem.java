package code.watersort;

import code.Node;
import code.generic.Problem;
import code.generic.SearchState;

import java.util.HashSet;

public class WaterSortProblem extends Problem {

    public WaterSortProblem(SearchState initialState, String strategy) {
        super(initialState, strategy);

        addAllOperatorPermutations(initialState);
    }

    private void addAllOperatorPermutations(SearchState initialState) {
        WaterSortState state = (WaterSortState) initialState;
        Bottle[] bottles = state.getBottles();

        for (int i = 0; i < bottles.length; i++)
            for (int j = 0; j < bottles.length; j++)
                if (i != j)
                    this.addOperator(new Pour(i, j));
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

    /**
     * An admissible heuristic function that estimates a cost based on
     * the current number of bottles that have layers with different colors
     */
    public static int getHeuristicOne(Node node) {
        Bottle[] bottles = (Bottle[]) node.getState().getValue();
        int cost = 0;

        for (Bottle bottle : bottles) {
            if (bottle.isEmpty())
                continue;

            Color topColor = bottle.getTopLayer();

            for (int i = 0; i < bottle.getLayers().size(); i++)
                if (!topColor.equals(bottle.getLayers().get(i)))
                    cost++;
        }

        return cost;
    }

    /**
     * An admissible heuristic function that estimates a cost based on the summation of, for every bottle, the following:
     * (the difference between bottle current capacity and largest same-color layer in the bottle)
     */
    // it can overestimate as we can reach the goal while there are empty layers and these empty layers will be included in calculation
    // it can be admissible if we know that always there will be no empty cells in the goal state.

    /**
    * Proposed admissible heuristic function
    * COST: for all bottles sum the number of layers that are different from the majority common layer in the bottle
    * Example: if we have a bottle with 2 layers red and one yellow then cost is 1 for that bottle as yellow is different from majority
    * */
    public static int getHeuristicTwo(Node node) {
      Bottle[] bottles = (Bottle[]) node.getState().getValue();
      int cost = 0;

      for (Bottle bottle : bottles) {
        if (bottle.isEmpty())
          continue;

        // has number of layers per each color
        int[] colorLayersCount = new int[5];
        int maxColorCount = 0;
        int maxIndex = 0;

        for (int i = 0; i < bottle.getLayers().size(); i++){
          switch (bottle.getLayers().get(i)) {
            case r -> {
              colorLayersCount[0]++;
              if (colorLayersCount[0] > maxColorCount) {
                maxIndex = 0;
                maxColorCount = colorLayersCount[0];
              }
              break;
            }
            case b -> {
              colorLayersCount[1]++;
              if (colorLayersCount[1] > maxColorCount) {
                maxIndex = 1;
                maxColorCount = colorLayersCount[1];
              }
              break;
            }
            case g -> {
              colorLayersCount[2]++;
              if (colorLayersCount[2] > maxColorCount) {
                maxIndex = 2;
                maxColorCount = colorLayersCount[2];
              }
              break;
            }
            case o -> {
              colorLayersCount[3]++;
              if (colorLayersCount[3] > maxColorCount) {
                maxIndex = 3;
                maxColorCount = colorLayersCount[3];
              }
              break;
            }
            case y -> {
              colorLayersCount[0]++;
              if (colorLayersCount[0] > maxColorCount) {
                maxIndex = 0;
                maxColorCount = colorLayersCount[0];
              }
            }
          }
        }

        for(int i = 0; i < 5; i++){
          if(i!=maxIndex){
            cost+=colorLayersCount[i];
          }
        }
      }

      return cost;
    }
}
