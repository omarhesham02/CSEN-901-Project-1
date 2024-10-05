package org.aiteam.code;

import java.util.List;


public class WaterSortProblem extends Problem implements Problem.GoalTestFunction, Problem.PathCostFunction, Problem.TransitionFunction {
    @Override
    public int PathCostFunction(State state) {
        return 0;
    }

    public WaterSortProblem(State initialState, List<Operator> operators, TransitionFunction transitionFunction, GoalTestFunction goalTestFunction, PathCostFunction pathCostFunction) {
        super(initialState, operators, transitionFunction, goalTestFunction, pathCostFunction);
    }

    @Override
    public boolean isGoalState(State state) {
        WaterSortSearchState waterSortSearchState = (WaterSortSearchState) state;
        return waterSortSearchState.isGoalState();
    }

    @Override
    public int PathCostFunction(Node node) {
        return node.getParent().getPathCost() + node.getOperatorCost();
    }

    @Override
    public State TransitionFunction(State state, Operator operator) {
        return null;
    }
}
