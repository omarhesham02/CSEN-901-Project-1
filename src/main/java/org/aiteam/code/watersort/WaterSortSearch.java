package org.aiteam.code.watersort;

import org.aiteam.code.generic.GenericSearch;
import org.aiteam.code.generic.Node;
import org.aiteam.code.generic.QueueingFunctions.QueueingFunction;

public class WaterSortSearch extends GenericSearch {

    public static int numberOfBottles;
    public static int bottleCapacity;

    public static String solve(String initialState, String strategy, boolean visualize) {
        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);


        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState);

        QueueingFunction waterSortQueueingFunction = getQueueingFunction(strategy);

        Node solutionNode = GenericSearch.generalSearch(waterSortProblem, waterSortQueueingFunction, visualize);

        if (solutionNode == null)
            return "Failure";

        return solutionNode.getState().toString();
    }
}