package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import code.generic.Operator;
import code.generic.QueueingFunctions.QueueingFunction;
import code.watersort.WaterSortProblem;
import code.watersort.WaterSortState;
import code.watersort.WaterSortUtils;

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

        LinkedList<String> planBuilder = new LinkedList<String>();

        String pathCost = solutionNode.getPathCost() + "";

        while (solutionNode.getParent() != null) {
            Operator operator = solutionNode.getOperator();
            planBuilder.addFirst(operator.toString());
            solutionNode = solutionNode.getParent();
        }

        String plan = formPlanString(planBuilder);
        String nodesExpanded = GenericSearch.nodesExpanded + "";

        return plan + ";" + pathCost + ";" + nodesExpanded;
    }

    public static String formPlanString(List<String> list) {
        String result = list.toString();
        result = result.substring(1, result.length() - 1);
        result = result.replace(" ", "");
        return result;

    }
}