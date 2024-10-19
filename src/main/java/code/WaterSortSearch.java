package code;

import java.util.LinkedList;
import java.util.List;

import code.generic.HeuristicFunction;
import code.generic.Operator;
import code.watersort.*;

import static code.generic.SearchStrategies.executeSearchStrategy;

import java.io.PrintStream;
import java.io.FileOutputStream;
import code.utils.ConditionalPrintStream;
import code.watersort.heuristics.DifferentBottlesLeftHeuristic;
import code.watersort.heuristics.DifferentLayersLeftHeuristic;

public class WaterSortSearch extends GenericSearch {

    public static int numberOfBottles;
    public static int bottleCapacity;

    public static String solve(String initialState, String strategy, boolean visualize) throws CloneNotSupportedException {

        numberOfBottles = 0;
        bottleCapacity = 0;

        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState, strategy);

        HeuristicFunction heuristic1 = new DifferentBottlesLeftHeuristic();
        HeuristicFunction heuristic2 = new DifferentLayersLeftHeuristic();

        Node solutionNode = switch(strategy) {
            case "AS1", "GR1" -> executeSearchStrategy(waterSortProblem, strategy, GenericSearch.INFINITY, heuristic1, visualize);
            case "AS2", "GR2" -> executeSearchStrategy(waterSortProblem, strategy, GenericSearch.INFINITY, heuristic2, visualize);
            default -> executeSearchStrategy(waterSortProblem, strategy, GenericSearch.INFINITY, null, visualize);
};


        if (solutionNode == null)
            return "NOSOLUTION";

        // TODO: Why is this done with a linked list?
        // answer : because adding a string to the beginning of a stringBuilder is O(n)
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

    // Please dont delete this main method until the last moment
    public static void main(String[] args) {
        String state = "5;4;" + "o,b,r,o;" + "o,b,r,r;" +
                "b,r,o,b;" + "e,e,e,e;" + "e,e,e,e;";
        String strategy = "BF";

        // ------------------------- below part is to print to BOTH the console and the
        // visualization.txt file -------------------------

        try {
            PrintStream originalOut = System.out;
            PrintStream fileOut = new PrintStream(new FileOutputStream("src/main/java/code/visualization.txt"));
            ConditionalPrintStream dualOut = new ConditionalPrintStream(originalOut, fileOut);

            System.setOut(dualOut);
            String solution = solve(state, strategy, true);
            System.out.println(
                    "\n-------------------------------------------------------------- Solution text required :");
            System.out.println(solution);
            System.setOut(originalOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
