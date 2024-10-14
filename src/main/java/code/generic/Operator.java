package code.generic;

public interface Operator {
    OperatorResult apply(SearchState state) throws CloneNotSupportedException;

    boolean isApplicable(SearchState state);
}