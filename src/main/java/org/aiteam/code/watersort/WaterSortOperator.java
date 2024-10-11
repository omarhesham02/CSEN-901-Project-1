package org.aiteam.code.watersort;

import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.SearchState;

public interface WaterSortOperator<T extends SearchState<?>> extends Operator<T> {
    {

    T apply(T state);

    <T> boolean isApplicable(SearchState<T> state);
}