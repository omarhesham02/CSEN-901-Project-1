package org.aiteam.code.generic;

import java.util.LinkedList;
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
    public static String generalSearch(Problem problem, QueueingFunction queueingFunction,
            boolean visualize) {

        Node node = new Node(problem.getInitialState());
        Queue<Node> frontier = queueingFunction.apply(node);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            if (problem.isGoalNode(currentNode)) {
                return currentNode.toString();
            }
            for (Operator operator : problem.getOperators()) {
                if (operator.isApplicable(currentNode.getState())) {
                    SearchState newState = (SearchState) operator.apply(currentNode.getState());
                    Node newNode = new Node(newState, currentNode, operator,
                            currentNode.depth() + 1,
                            currentNode.pathCost() + 1);
                    frontier.add(newNode);
                }
            }
        }
        return "Failure";

    }

    public static QueueingFunction getQueueingFunction(String strategy) {
        switch (strategy) {
            case "BF":
                return new BFSQueueingFunction();
            case "DF":
                return new DFSQueueingFunction();
            case "ID":
                return new IDSQueueingFunction();
            case "UC":
                return new UCSQueueingFunction();
            case "GR1":
                return new GREEDY1QueueingFunction();
            case "GR2":
                return new GREEDY2QueueingFunction();
            case "AS1":
                return new AStar1QueueingFunction();
            case "AS2":
                return new AStar2QueueingFunction();
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);

        }
    }
}