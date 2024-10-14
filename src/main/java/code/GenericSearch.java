package code;

import java.util.*;

import code.generic.Operator;
import code.generic.OperatorResult;
import code.generic.Problem;
import code.generic.SearchState;
import code.generic.QueueingFunctions.AStar1QueueingFunction;
import code.generic.QueueingFunctions.AStar2QueueingFunction;
import code.generic.QueueingFunctions.BFSQueueingFunction;
import code.generic.QueueingFunctions.DFSQueueingFunction;
import code.generic.QueueingFunctions.GREEDY1QueueingFunction;
import code.generic.QueueingFunctions.GREEDY2QueueingFunction;
import code.generic.QueueingFunctions.IDSQueueingFunction;
import code.generic.QueueingFunctions.QueueingFunction;
import code.generic.QueueingFunctions.UCSQueueingFunction;

public abstract class GenericSearch {

    public static int nodesExpanded = 0;
    private static Set<SearchState> exploredStates = new HashSet<>();

    public static Node generalSearch(Problem problem, QueueingFunction queueingFunction, boolean visualize) {

        Node solutionNode = null;
        Node initialNode = makeNode(problem.getInitialState());

        Queue<Node> nodes = makeQ(initialNode);

        while (!nodes.isEmpty()) {
            Node currentNode = removeFront(nodes);

            if (problem.goalTestFn(currentNode)) {
                solutionNode = currentNode;
                break;
            }
            nodes = queueingFunction.apply(nodes, expand(currentNode, problem.getOperators()));
        }
        if (visualize)
            showSolutionTree(initialNode);
        return solutionNode;
    }

    private static ArrayDeque<Node> makeQ(Node node) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(node);
        return q;
    }

    private static Node makeNode(SearchState state) {
        return new Node(state);
    }

    private static Node removeFront(Queue<Node> nodes) {
        return nodes.poll();
    }

    public static QueueingFunction getQueueingFunction(String strategy) {
        return switch (strategy) {
            case "BF" -> new BFSQueueingFunction();
            case "DF" -> new DFSQueueingFunction();
            case "ID" -> new IDSQueueingFunction();
            case "UC" -> new UCSQueueingFunction();
            case "GR1" -> new GREEDY1QueueingFunction();
            case "GR2" -> new GREEDY2QueueingFunction();
            case "AS1" -> new AStar1QueueingFunction();
            case "AS2" -> new AStar2QueueingFunction();
            default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
        };
    }

    private static List<Node> expand(Node parentNode, List<Operator> operators) {
        List<Node> nodes = new LinkedList<>();

        for (Operator operator : operators) {
            if (operator.isApplicable(parentNode.getState())) {
                OperatorResult operatorResult = operator.apply(parentNode.getState());
                // Avoid exploring the same state again
                if (exploredStates.contains(operatorResult.getState())) {
                    continue;
                }

                Node childNode = new Node(
                        operatorResult.getState(),
                        parentNode,
                        operator,
                        parentNode.getDepth() + 1,
                        parentNode.getPathCost() + operatorResult.getOperatorCost());
                nodes.add(childNode);
                exploredStates.add(operatorResult.getState());
            }
        }

        nodesExpanded++;
        parentNode.setChildren(nodes); // for visualization
        return nodes;
    }

    private static void showSolutionTree(Node node) {
        printTree(node, "", true);
        System.out.println("Node numbers correspond to the order of creation.");
    }

    private static void printTree(Node node, String prefix, boolean isTail) {
        String stateText = "[" + node.getState().toString().replace(";", "    ") + "]\n";
        String operatorText = node.getOperator() == null ? "" : node.getOperator().toString() + " --->  ";
        System.out.println(
                prefix + (isTail ? "└── " : "├──") + operatorText + node.getName() + "   "
                        + stateText);
        List<Node> children = node.getChildren();
        for (int i = 0; i < children.size() - 1; i++) {
            printTree(children.get(i), prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            printTree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true);
        }
    }

}