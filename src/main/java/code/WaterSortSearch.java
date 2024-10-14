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

        System.out.println("Solution: " + solutionNode);

        LinkedList<String> planBuilder = new LinkedList<String>();
        while (solutionNode.getParent() != null) {
            Operator operator = solutionNode.getOperator();
            planBuilder.addFirst(operator.toString());
            solutionNode = solutionNode.getParent();
        }

        String plan = formPlanString(planBuilder);
        String pathCost = solutionNode.getPathCost() + "";
        String nodesExpanded = GenericSearch.nodesExpanded + "";

        return plan + ";" + pathCost + ";" + nodesExpanded;
    }

    public static String formPlanString(List<String> list) {
        String result = list.toString();
        // alter the output of LinkedList.toString() to suit our desirable format;
        result = result.substring(1, result.length() - 1);
        result = result.replace(" ", "");
        return result;

    }

    public static void main(String[] args) {
        // test case in project desc

        String initState = "5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
        String strategy = "BF";
        boolean visualize = true;

        System.out.println(solve(initState, strategy, visualize));
    }

}