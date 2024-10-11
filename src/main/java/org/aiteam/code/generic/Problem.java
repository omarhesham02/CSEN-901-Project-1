package org.aiteam.code.generic;

import java.util.List;

public abstract class Problem<T, V> {
    private final SearchState<V> initialState;
    protected List<Operator<T>> operators;

    public Problem(SearchState<V> initialState) {
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

    public SearchState<V> getInitialState() {
        return initialState;
    }

    public List<Operator<T>> getOperators() {
        return operators;
    }
}