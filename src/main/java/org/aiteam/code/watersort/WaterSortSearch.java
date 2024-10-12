package org.aiteam.code.watersort;

import org.aiteam.code.generic.GenericSearch;
import org.aiteam.code.generic.Node;
import org.aiteam.code.generic.Operator;
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
            return "NOSOLUTION";

        System.out.println("Solution: " + solutionNode);

        StringBuilder planSb = new StringBuilder();

        while (solutionNode.getParent() != null) {
            Operator operator = solutionNode.getOperator();
            // TODO: Fix extra comma error
            planSb.insert(0, operator.toString() + ",");
            solutionNode = solutionNode.getParent();
        }

        String plan = planSb.toString();
        String pathCost = solutionNode.getPathCost() + "";
        String nodesExpanded = GenericSearch.nodesExpanded + "";

        return plan + ";" + pathCost + ";" + nodesExpanded;
    }
}