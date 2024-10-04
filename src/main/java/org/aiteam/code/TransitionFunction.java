package org.aiteam.code;

@FunctionalInterface
public interface TransitionFunction {
    State apply(State currentState, Operator operator);
}
