package org.aiteam.code.watersort;

import org.aiteam.code.generic.Operator;

public interface WaterSortOperator extends Operator<WaterSortState> {

    WaterSortState apply(WaterSortState state);

    boolean isApplicable(WaterSortState state);
}