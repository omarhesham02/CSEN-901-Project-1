package code;

import java.util.Comparator;
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

    public static int nodesExpanded;

    private static final Set<SearchState> exploredStates = new HashSet<>();

    // TODO: Make this method look more like the one in the slides
    public static Node generalSearch(Problem problem, QueueingFunction queueingFunction, boolean visualize) {
        nodesExpanded = 0;
        int nodesVisited = 0;
        exploredStates.clear();

        Node solutionNode = null;

        Node initialNode = makeNode(problem.getInitialState());
        Queue<Node> nodes = new PriorityQueue<>(getPriorityQueueComparator(problem.getStrategy()));
        nodes.add(initialNode);

        while (!nodes.isEmpty()) {
            Node currentNode = removeFront(nodes);
            currentNode.setOrderOfVisiting(nodesVisited++);
            if (problem.goalTestFn(currentNode)) {
                solutionNode = currentNode;
                break;
            }
            nodes = queueingFunction.apply(nodes, expand(currentNode, problem.getOperators()));
        }

        if (visualize)
            showSolutionTree(initialNode, solutionNode);

        return solutionNode;
    }

    private static Node makeNode(SearchState state) {
        return new Node(state);
    }

    private static Node removeFront(Queue<Node> nodes) {
        return nodes.poll();
    }

    // TODO: Implement the comparators for the remaining strategies
    private static PriorityQueue<Node> getPriorityQueueComparator(String strategy) {
        return switch (strategy) {
            case "BF" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
            case "DF" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth).reversed());
            case "ID" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth).reversed());
            case "UC" -> new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));

            // TODO: Implement the comparators for the remaining strategies
            case "GR1" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
            case "GR2" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
            case "AS1" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
            case "AS2" -> new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
            default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
        };
    }

    private static List<Node> expand(Node parentNode, List<Operator> operators) {
        List<Node> nodes = new LinkedList<>();

        for (Operator operator : operators) {
            if (operator.isApplicable(parentNode.getState())) {
                OperatorResult operatorResult = operator.apply(parentNode.getState());

                // Avoid exploring the same state again
                if (exploredStates.contains(operatorResult.getState()))
                    continue;

                Node childNode = new Node(
                        operatorResult.getState(),
                        parentNode,
                        operator,
                        parentNode.getDepth() + 1,
                        parentNode.getPathCost() + operatorResult.getOperatorCost());

                nodes.add(childNode);
                exploredStates.add(operatorResult.getState());
                nodesExpanded++;
            }
        }

        // For visualization
        parentNode.setChildren(nodes);

        return nodes;
    }

    private static void showSolutionTree(Node node, Node solutionNode) {
        printTree(node, "", true, solutionNode);
        System.out.println("Node numbers correspond to the order of creation.");
    }

    private static void printTree(Node node, String prefix, boolean isTail, Node solutionNode) {
        String stateText = "[" + node.getState().toString().replace(";", "    ") + "]"
                + (node == solutionNode ? " SOLUTION" : "") + "\n";

        String operatorText = node.getOperator() == null ? "" : node.getOperator().toString() + " --->  ";

        System.out.println(
                prefix + (isTail ? "└── " : "├──") + operatorText + "visited: " + node.getOrderOfVisiting() + " "
                        + stateText);

        List<Node> children = node.getChildren();

        for (int i = 0; i < children.size() - 1; i++)
            printTree(children.get(i), prefix + (isTail ? "    " : "│   "), false, solutionNode);

        if (!children.isEmpty())
            printTree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true, solutionNode);
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
}