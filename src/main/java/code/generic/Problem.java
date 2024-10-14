package code.generic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import code.Node;

public abstract class Problem {
    private final SearchState initialState;
    private List<Operator> operators;

    public Problem(SearchState initialState, List<Operator> operators) {
        this.initialState = initialState;
        this.operators = operators;
    }

    public Problem(SearchState initialState) {
        this.initialState = initialState;
        this.operators = new LinkedList<>();
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

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public void addOperator(Operator operator) {
        this.operators.add(operator);
    }

}
