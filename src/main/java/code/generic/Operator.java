package code.generic;

public interface Operator {

    int getCost();
    OperatorResult apply(SearchState state) throws CloneNotSupportedException;
    boolean isApplicable(SearchState state);
}