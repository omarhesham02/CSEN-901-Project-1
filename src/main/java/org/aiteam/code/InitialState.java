package org.aiteam.code;

public class InitialState {
    private final int numberOfBottles;
    private final int bottleCapacity;
    private final State state;

    public InitialState(int numberOfBottles, int bottleCapacity, State state) {
        this.numberOfBottles = numberOfBottles;
        this.bottleCapacity = bottleCapacity;
        this.state = state;
    }

    public int getNumberOfBottles() {
        return numberOfBottles;
    }

    public int getBottleCapacity() {
        return bottleCapacity;
    }

    public State getState() {
        return state;
    }
}
