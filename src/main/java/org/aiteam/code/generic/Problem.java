package org.aiteam.code.generic;

import java.util.List;

public abstract class Problem {
    private final SearchState initialState;
    private List<Operator> operators;

    public Problem(SearchState initialState) {
        this.initialState = initialState;
    }

    /**
     * Goal test function for a generic search problem.
     */
    public abstract boolean goalTestFn(Node node);

    /**
     * Path cost function for a generic search problem.
     */
    // public abstract int pathCostFn(Node node);

    /**
     * Transition function for a generic search problem.
     */
    // public abstract Node transitionFn(Node node, Operator operator);

    public SearchState getInitialState() {
        return initialState;
    }

    public List<Operator> getOperators() {
        return operators;
    }
}