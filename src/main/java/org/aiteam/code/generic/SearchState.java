package org.aiteam.code.generic;

/**
 * Represents a state in the search problem
 * 
 * @param value The value of the state in the specific problem (e.g. Array of
 *              bottles in the Water Sort problem)
 */
public class SearchState {
    private final Object value;

    public SearchState(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
