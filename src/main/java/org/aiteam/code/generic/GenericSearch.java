package org.aiteam.code.generic;

import java.util.Queue;

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
    public static <T, V> String generalSearch(Problem<T, V> problem, QueueingFunction<T, V> queueingFunction,
            boolean visualize) {

        Node node = new Node<>(problem.getInitialState());
        Queue<Node<T, V>> frontier = queueingFunction.apply(node);

        while (!frontier.isEmpty()) {
            Node<T, V> currentNode = frontier.poll();
            if (problem.isGoalNode(currentNode)) {
                return currentNode.toString();
            }
            for (Operator<T> operator : problem.getOperators()) {
                if (operator.isApplicable(currentNode.getState())) {
                    SearchState<V> newState = (SearchState<V>) operator.apply(currentNode.getState());
                    Node<T, V> newNode = new Node<>(newState, currentNode, operator,
                            currentNode.depth() + 1,
                            currentNode.pathCost() + 1);
                    frontier.add(newNode);
                }
            }
        }
        return "Failure";

    }

    public static <T, V> QueueingFunction<T, V> getQueueingFunction(String strategy) {
        switch (strategy) {
            case "BF":
                return new BFSQueueingFunction<>();
            case "DF":
                return new DFSQueueingFunction<>();
            case "ID":
                return new IDSQueueingFunction<>();
            case "UC":
                return new UCSQueueingFunction<>();
            case "GR1":
                return new GREEDY1QueueingFunction<>();
            case "GR2":
                return new GREEDY2QueueingFunction<>();
            case "AS1":
                return new AStar1QueueingFunction<>();
            case "AS2":
                return new AStar2QueueingFunction<>();
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);

        }
    }
}