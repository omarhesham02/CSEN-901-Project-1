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
import code.generic.QueueingFunctions.QueueingFunction;
import code.watersort.WaterSortState;

public abstract class GenericSearch {

    public static int nodesExpanded;
    private static final Set<SearchState> expandedStates = new HashSet<>();

    public static Node generalSearch(Problem problem, QueueingFunction queueingFunction, boolean visualize)
            throws CloneNotSupportedException {
        nodesExpanded = 0;
        int nodesVisited = 0;
        Node solutionNode = null;
        expandedStates.clear();

        Node initialNode = makeNode(problem.getInitialState());
        PriorityQueue<Node> nodes = queueingFunction.apply();
        nodes.add(initialNode);

        while (!nodes.isEmpty()) {
            Node currentNode = removeFront(nodes);
            currentNode.setOrderOfVisiting(nodesVisited++);
            if (problem.goalTestFn(currentNode)) {
                solutionNode = currentNode;
                break;
            }
            List<Node> expandedNodes = expand(currentNode, problem.getOperators());
            nodes.addAll(expandedNodes);
        }
        if (visualize)
            showSolutionTree(initialNode, solutionNode, problem);
        return solutionNode;
    }

    private static Node makeNode(SearchState state) {
        return new Node(state);
    }

    private static Node removeFront(Queue<Node> nodes) {
        return nodes.poll();
    }

    private static List<Node> expand(Node parentNode, List<Operator> operators) throws CloneNotSupportedException {
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

            }
        }

        parentNode.setChildren(nodes); // for visualization
        return nodes;
    }

    private static void showSolutionTree(Node node, Node solutionNode, Problem problem)
            throws CloneNotSupportedException {
        printTree(node, "", true, solutionNode, problem);
        System.out.println("Node numbers correspond to the order of visiting not creation.");
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
                stateText += " SOLUTION";
            else
                stateText += " Candidate";
            stateText += ", " + "Depth: " + node.getDepth() + ", Path Cost: " + node.getPathCost();
        }

        String operatorText = node.getOperator() == null ? "" : node.getOperator().toString() + " --->  ";
        String lineStart = isTail ? "└──> " : "├──> ";
        String order = "visited: " + node.getOrderOfVisiting();
        System.out.println(prefix + lineStart + operatorText + order + " " + stateText + "\n");

        List<Node> children = node.getChildren();
        for (int i = 0; i < children.size() - 1; i++) {
            printTree(children.get(i), prefix + (isTail ? "    " : "│   "), false, solutionNode, problem);
        }
        if (!children.isEmpty()) {
            printTree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true, solutionNode,
                    problem);
        }
    }

}
