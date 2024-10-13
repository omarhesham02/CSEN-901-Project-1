package org.aiteam.code.generic;

import java.util.HashSet;
import java.util.List;
import org.aiteam.code.generic.Operator;
import java.util.Set;

public abstract class Problem {
    private final SearchState initialState;
    private Set<Operator> operators;

    public Problem(SearchState initialState, List<Operator> operators) {
        this.initialState = initialState;
        this.operators = operators;
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

    public Set<Operator> getOperators() {
        return operators;
    }
=======
    public void setOperators(Set<Operator> operators) {
        this.operators = operators;
  
    public void addOperator(Operator operator) {
      this.operators.add(operator);
    }
  
    }
}