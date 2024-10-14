package code.generic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import code.Node;

public abstract class Problem {
    private final SearchState initialState;
    private final List<Operator> operators;
    private final String strategy;

    public Problem(SearchState initialState, List<Operator> operators, String strategy) {
        this.initialState = initialState;
        this.operators = operators;
        this.strategy = strategy;
    }

    public Problem(SearchState initialState, String strategy) {
        this.initialState = initialState;
        this.strategy = strategy;
        this.operators = new LinkedList<>();
    }

    /**
     * Goal test function for a generic search problem.
     */
    public abstract boolean goalTestFn(Node node);

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

    public void addOperator(Operator operator) {
        this.operators.add(operator);
    }

    public String getStrategy() {
        return strategy;
    }

}
