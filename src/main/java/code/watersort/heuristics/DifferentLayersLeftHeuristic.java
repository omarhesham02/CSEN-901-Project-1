package code.watersort.heuristics;

import code.Node;
import code.generic.HeuristicFunction;
import code.watersort.Bottle;
import code.watersort.WaterSortState;

public class DifferentLayersLeftHeuristic implements HeuristicFunction {

    /**
     * Proposed admissible heuristic function
     * COST: for all bottles sum the number of layers that are different from the majority common layer in the bottle
     * Example: if we have a bottle with 2 layers red and one yellow then cost is 1 for that bottle as yellow is different from majority
     * */

    @Override
    public int apply(Node node) {

        if (!(node.getState() instanceof WaterSortState))
            throw new IllegalArgumentException("The state must be of type WaterSortState");


        Bottle[] bottles = (Bottle[]) node.getState().getValue();
            int cost = 0;

            for (Bottle bottle : bottles) {
                if (bottle.isEmpty())
                    continue;

                // has number of layers per each color
                int[] colorLayersCount = new int[5];
                int maxColorCount = 0;
                int maxIndex = 0;

                for (int i = 0; i < bottle.layers().size(); i++){
                    switch (bottle.layers().get(i)) {
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
