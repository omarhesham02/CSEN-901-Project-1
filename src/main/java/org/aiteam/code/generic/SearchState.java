package org.aiteam.code.generic;

/**
 * Represents a state in the search problem
 * 
 * @param <V> The value of the state in the specific problem (e.g. Array of
 *            bottles in the Water Sort problem)
 */
public abstract class SearchState<V> {
    private final V value;

    public SearchState(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
