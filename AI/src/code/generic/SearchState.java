package code.generic;

/**
 * Represents a state in the search problem
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
