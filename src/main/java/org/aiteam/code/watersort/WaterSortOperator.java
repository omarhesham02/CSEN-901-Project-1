package org.aiteam.code.watersort;

import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.SearchState;

public interface WaterSortOperator extends Operator {

    WaterSortState apply(SearchState state);

    boolean isApplicable(SearchState state);
}