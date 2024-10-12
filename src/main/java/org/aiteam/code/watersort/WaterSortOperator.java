package org.aiteam.code.watersort;

import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.OperatorResult;
import org.aiteam.code.generic.SearchState;

public interface WaterSortOperator extends Operator {
    boolean isApplicable(SearchState state);
    OperatorResult apply(SearchState state);
}