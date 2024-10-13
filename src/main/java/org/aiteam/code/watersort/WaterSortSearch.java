package org.aiteam.code.watersort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        LinkedList<String> planBuilder = new LinkedList<String>();

        while (solutionNode.getParent() != null) {
            Operator operator = solutionNode.getOperator();
            planBuilder.addFirst(",");
            planBuilder.addFirst(operator.toString());
            solutionNode = solutionNode.getParent();
        }
        // Remove the last comma
        if (planBuilder.size() > 0)
            planBuilder.removeLast();

        String plan = planBuilder.toString();
        String pathCost = solutionNode.getPathCost() + "";
        String nodesExpanded = GenericSearch.nodesExpanded + "";

        return plan + ";" + pathCost + ";" + nodesExpanded;
    }

    public static void main(String[] args) {
        // test case in project desc

        String init = "5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";

        System.out.println(solve(init, "BF", true));

    }

}