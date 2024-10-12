package org.aiteam.code.generic;

import org.aiteam.code.watersort.WaterSortState;

public interface Operator {

    SearchState apply(SearchState state);

    boolean isApplicable(SearchState state);
}