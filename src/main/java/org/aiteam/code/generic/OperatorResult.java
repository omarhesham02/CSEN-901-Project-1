package org.aiteam.code.generic;

public class OperatorResult {
    private final SearchState state;
    private final int operatorCost;

    public OperatorResult(SearchState state, int operatorCost) {
        this.state = state;
        this.operatorCost = operatorCost;
    }

    public SearchState getState() {
        return state;
    }

    public int getOperatorCost() {
        return operatorCost;
    }
}
