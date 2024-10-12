package org.aiteam.code.generic;

/**
 * Represents a state in the search problem
 */
public class SearchState implements Cloneable {
    private final Object value;

    public SearchState(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public SearchState clone() {
        try {
            return (SearchState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
