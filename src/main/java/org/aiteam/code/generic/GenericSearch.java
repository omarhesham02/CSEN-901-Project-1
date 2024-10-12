package org.aiteam.code.generic;

import java.util.*;

import org.aiteam.code.generic.QueueingFunctions.AStar1QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.AStar2QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.BFSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.DFSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.GREEDY1QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.GREEDY2QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.IDSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.UCSQueueingFunction;

public abstract class GenericSearch {

    public static String generalSearch(Problem problem, QueueingFunction queueingFunction, boolean visualize) {
        Queue<Node> nodes = makeQ(makeNode(problem.getInitialState()));

        while (!nodes.isEmpty()) {
            Node currentNode = removeFront(nodes);

            if (problem.goalTestFn(currentNode))
                return currentNode.toString();

            nodes = queueingFunction.apply(nodes, expand(currentNode, problem.getOperators()));
        }

        return "Failure";
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

    private static Node expand(Node parentNode, List<Operator> operators) {
        for (Operator operator : operators) {
            if (operator.isApplicable(parentNode.getState())) {
                OperatorResult operatorResult = operator.apply(parentNode.getState());
                Node childNode = new Node(
                    operatorResult.getState(),
                    parentNode,
                    operator,
                    parentNode.depth + 1,
                    parentNode.pathCost + operatorResult.getOperatorCost()
                );
            }
        }

        return null;
    }
}