package org.aiteam.code.generic;

import java.util.List;

public abstract class Problem {
    private final SearchState initialState;
    protected List<Operator> operators;

    public Problem(SearchState initialState) {
        this.initialState = initialState;
    }

    /**
     * Goal test function for a generic search problem.
     */
    public abstract boolean isGoalNode(Node node);

    /**
     * Path cost function for a generic search problem.
     */
    public abstract int pathCost(Node node);

    /**
     * Transition function for a generic search problem.
     */

    public SearchState getInitialState() {
        return initialState;
    }

    public List<Operator> getOperators() {
        return operators;
    }
}