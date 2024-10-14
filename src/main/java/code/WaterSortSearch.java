package code;

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

    public static String solve(String initialState, String strategy, boolean visualize)
            throws CloneNotSupportedException {

        numberOfBottles = 0;
        bottleCapacity = 0;

        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState, strategy);
        QueueingFunction waterSortQueueingFunction = getQueueingFunction(strategy);

        Node solutionNode = GenericSearch.generalSearch(waterSortProblem, waterSortQueueingFunction, visualize);

        if (solutionNode == null)
            return "NOSOLUTION";

        // TODO: Why is this done with a linked list?
        // answer : because adding a node to the beginning of a stringBuilder is O(n)
        // while adding to the beginning of a linkedList is O(1)
        LinkedList<String> planBuilder = new LinkedList<>();

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

    public static void main(String[] args) {
        try {
            String state = "3;" +
                    "4;" +
                    "r,y,r,y;" +
                    "y,r,y,r;" +
                    "e,e,e,e;";
            String strategy = "BF";
            System.out.println(solve(state, strategy, true));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}