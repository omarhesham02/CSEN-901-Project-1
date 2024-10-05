package org.aiteam.code;

import java.util.List;

public abstract class Problem {

    private final State initialState;
    private final List<Operator> operators;
    private final TransitionFunction transitionFunction;
    private final GoalTestFunction goalTestFunction;
    private final PathCostFunction pathCostFunction;

    public abstract int PathCostFunction(State state);

    @FunctionalInterface
    public interface GoalTestFunction {
        boolean isGoalState(State state);
    }

    @FunctionalInterface
    public interface PathCostFunction {
        int PathCostFunction(Node node);
    }

    @FunctionalInterface
    public interface TransitionFunction {
        State TransitionFunction(State state, Operator operator);
    }

    public Problem(State initialState, List<Operator> operators, TransitionFunction transitionFunction, GoalTestFunction goalTestFunction, PathCostFunction pathCostFunction) {
        this.initialState = initialState;
        this.operators = operators;
        this.transitionFunction = transitionFunction;
        this.goalTestFunction = goalTestFunction;
        this.pathCostFunction = pathCostFunction;
    }

}
