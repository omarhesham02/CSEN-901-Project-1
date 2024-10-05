package org.aiteam.code;


public abstract class State<T> {
    private T value;

    public State(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    abstract public boolean isGoalState();
}
