package org.aiteam.code.generic;

import java.util.Queue;

public abstract class GenericSearch {
    public static <T, V> String generalSearch(Problem<T, V> problem, QueueingFunction<T, V> queueingFunction) {

        Node node = new Node<>(problem.getInitialState());
        Queue<Node<T, V>> frontier = queueingFunction.apply(node);

        while (!frontier.isEmpty()) {
            Node<T, V> currentNode = frontier.poll();
            if (problem.isGoalNode(currentNode)) {
                return currentNode.toString();
            }
            for (Operator<T> operator : problem.getOperators()) {
                if (operator.isApplicable(currentNode.getState())) {
                    State<V> newState = (State<V>) operator.apply(currentNode.getState());
                    Node<T, V> newNode = new Node<>(newState, currentNode, operator, currentNode.depth() + 1, currentNode.pathCost() + 1);
                    frontier.add(newNode);
                }
            }
        }
        return "Failure";

}