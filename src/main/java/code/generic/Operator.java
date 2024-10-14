package code.generic;

public interface Operator {
    OperatorResult apply(SearchState state);

    boolean isApplicable(SearchState state);
}