package code;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import code.generic.Operator;
import code.generic.OperatorResult;
import code.generic.Problem;
import code.generic.SearchState;
import code.generic.QueueingFunctions.QueuingFunction;
import code.utils.Methods;
import code.watersort.WaterSortState;

import java.lang.management.ManagementFactory;
import java.util.Comparator;

public abstract class GenericSearch {

    public static int nodesExpanded;
    private static final Set<SearchState> expandedStates = new HashSet<>();
    private static final PriorityQueue<Node> candidateSolutions = new PriorityQueue<>(
            Comparator.comparingInt(Node::getPathCost).reversed());

    public static Node generalSearch(Problem problem, QueuingFunction queuingFunction, boolean visualize)
            throws CloneNotSupportedException {
        nodesExpanded = 0;
        int nodesVisited = 0;
        Node solutionNode = null;
        expandedStates.clear();
        candidateSolutions.clear();

        // performance metrics
        Long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

        Node initialNode = makeNode(problem.getInitialState());
        PriorityQueue<Node> nodes = queuingFunction.apply();
        nodes.add(initialNode);

        while (!nodes.isEmpty()) {
            Node currentNode = removeFront(nodes);
            currentNode.setOrderOfVisiting(nodesVisited++);
            if (problem.goalTestFn(currentNode)) {
                solutionNode = currentNode;
                break;
            }
            List<Node> expandedNodes = expand(currentNode, problem.getOperators(), problem);
            nodes.addAll(expandedNodes);
        }
        if (visualize) {
            showSolutionTree(initialNode, solutionNode, problem);
            visulalizePath(solutionNode);
        }
        reportOptimality(solutionNode);
        System.out.println(
                problem.getStrategy() + " ----> "
                        + Methods.reportPerformance_Simple(startTime, startMemory, startCpuTime));

        return solutionNode;
    }

    private static Node makeNode(SearchState state) {
        return new Node(state);
    }

    private static Node removeFront(Queue<Node> nodes) {
        return nodes.poll();
    }

    private static List<Node> expand(Node parentNode, List<Operator> operators, Problem problem)
            throws CloneNotSupportedException {
        List<Node> nodes = new LinkedList<>();

        for (Operator operator : operators) {
            if (operator.isApplicable(parentNode.getState())) {
                OperatorResult operatorResult = operator.apply(parentNode.getState());
                // Avoid exploring the same state again
                if (expandedStates.contains(operatorResult.getState())) {
                    continue;
                }

                Node childNode = new Node(
                        operatorResult.getState(),
                        parentNode,
                        operator,
                        parentNode.getDepth() + 1,
                        parentNode.getPathCost() + operatorResult.getOperatorCost());
                nodes.add(childNode);
                expandedStates.add(operatorResult.getState());
                ++nodesExpanded;
                if (problem.goalTestFn(childNode)) {
                    candidateSolutions.add(childNode);
                }

            }
        }

        parentNode.setChildren(nodes); // for visualization
        return nodes;
    }

    private static void showSolutionTree(Node node, Node solutionNode, Problem problem)
            throws CloneNotSupportedException {
        System.out.println("---------------------------------------------------------- Solution Tree");
        printTree(node, "", true, solutionNode, problem);
        System.out
                .println(
                        "Nodes numbers correspond to the order of visiting, NOT creation. -1 means not visited yet.\n");
    }

    private static void printTree(Node node, String prefix, boolean isTail, Node solutionNode, Problem problem)
            throws CloneNotSupportedException {

        String stateText = ((WaterSortState) (node.getState())).toString();
        stateText = stateText.substring(0, stateText.length() - 1);
        stateText = stateText.replaceAll(",", " ");
        stateText = stateText.replaceAll(";", "     ");
        stateText = "[" + stateText + "]";
        if (problem.goalTestFn(node)) {
            if (node == solutionNode)
                stateText += " ---> SOLUTION";
            else
                stateText += " ---> Candidate";
            stateText += ", " + "Depth: " + node.getDepth() + ", Path Cost: " + node.getPathCost();
        }

        String operatorText = node.getOperator() == null ? "" : node.getOperator().toString() + " --->  ";
        String lineStart = isTail ? "└──> " : "├──> ";
        String visitingOrder = "  visited: " + node.getOrderOfVisiting();
        System.out.println(prefix + lineStart + operatorText + visitingOrder + " " + stateText + "\n");

        List<Node> children = node.getChildren();
        for (int i = 0; i < children.size() - 1; i++) {
            printTree(children.get(i), prefix + (isTail ? "    " : "│   "), false, solutionNode, problem);
        }
        if (!children.isEmpty()) {
            printTree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true, solutionNode,
                    problem);
        }
    }

    private static void reportOptimality(Node solutionNode) {
        System.out.println("---------------------------------------------------------- Optimality Report");
        System.out.println("There are " + candidateSolutions.size() + " candidate solutions.\n");
        if (solutionNode == null)
            return;

        boolean isOptimal = true;
        Node bestNode = null;
        while (!candidateSolutions.isEmpty()) {
            Node candidate = candidateSolutions.poll();
            if (candidate.getPathCost() < solutionNode.getPathCost()) {
                isOptimal = false;
                bestNode = candidate;
                break;
            }
        }
        if (isOptimal)
            System.out.println("Your solution has the least cost among all created nodes ! depth "
                    + solutionNode.getDepth() + " cost "
                    + solutionNode.getPathCost());
        else {
            System.out.println("Solution node: \n" + solutionNode.toString());
            System.out.println("Best node: \n" + bestNode.toString());
            System.out.println("Your solution is not optimal :(");

        }

    }

    public static void visulalizePath(Node solutionNode) throws CloneNotSupportedException {
        if (solutionNode == null)
            return;
        System.out.println("---------------------------------------------------------- Path to solution Visualization");
        LinkedList<Object> path = new LinkedList<>(); // nodes and operators
        path.addFirst(solutionNode);

        Node curNode = solutionNode;

        while (curNode.getParent() != null) {
            path.addFirst(curNode.getOperator());
            path.addFirst(curNode.getParent());
            curNode = curNode.getParent();
        }
        System.out.println("Path to the solution:");
        for (Object obj : path) {
            if (obj instanceof Node) {
                WaterSortState state = (WaterSortState) ((Node) obj).getState();
                String costToRoot = "Path cost to root : " + ((Node) obj).getPathCost();
                System.out.println(state.getVerticalView(costToRoot, true));
            } else {
                String operator = ((Operator) (obj)).toString();
                System.out.println(
                        "                |\n" +
                                "                |  " + operator + "\n" +
                                "                |\n" +
                                "               \\ /\n" +
                                "                V");
            }
        }

    }

}
