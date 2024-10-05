package org.aiteam.code.generic;

import java.util.List;

public abstract class Problem<T, V> {
    private final State<V> initialState;
    protected List<Operator<T>> operators;

    public Problem(State<V> initialState) {
        this.initialState = initialState;
    }

    /**
     * Goal test function for a generic search problem.
     */
    public abstract boolean isGoalNode(Node<T, V> node);

    /**
     * Path cost function for a generic search problem.
     */
    public abstract int pathCost(Node<T, V> node);

    /**
     * Transition function for a generic search problem.
     */
    public abstract State<V> transition(State<V> state, Operator<T> operator);

    public State<V> getInitialState() {
        return initialState;
    }

    public List<Operator<T>> getOperators() {
        return operators;
    }
}
