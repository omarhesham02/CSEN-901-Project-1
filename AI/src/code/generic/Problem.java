package code.generic;

import java.util.LinkedList;
import java.util.List;

import code.Node;

public abstract class Problem {
    private final SearchState initialState;
    private final List<Operator> operators;
    private final String strategy;

    public Problem(SearchState initialState, String strategy) {
        this.initialState = initialState;
        this.strategy = strategy;
        this.operators = new LinkedList<>();
    }

    public abstract boolean goalTestFn(Node node);

    public SearchState getInitialState() {
        return initialState;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public String getStrategy() {
        return strategy;
    }

    public void addOperator(Operator operator) {
        this.operators.add(operator);
    }
}
