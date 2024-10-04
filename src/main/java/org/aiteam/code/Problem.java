package org.aiteam.code;

import java.util.List;

public class Problem {
    private State initialState;
    private List<Operator> operators;
    private TransitionFunction transitionFunction;
    private GoalTestFunction goalTestFunction;
    private PathCostFunction pathCostFunction;

    public Problem(State initialState,
                   List<Operator> operators,
                   TransitionFunction transitionFunction,
                   GoalTestFunction goalTestFunction,
                   PathCostFunction pathCostFunction)
    {
        this.initialState = initialState;
        this.operators = operators;
        this.transitionFunction = transitionFunction;
        this.goalTestFunction = goalTestFunction;
        this.pathCostFunction = pathCostFunction;
    }
}
