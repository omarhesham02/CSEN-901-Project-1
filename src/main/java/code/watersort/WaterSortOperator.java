package code.watersort;

import code.generic.Operator;
import code.generic.OperatorResult;
import code.generic.SearchState;

public interface WaterSortOperator extends Operator {
    boolean isApplicable(SearchState state);
    OperatorResult apply(SearchState state);
}