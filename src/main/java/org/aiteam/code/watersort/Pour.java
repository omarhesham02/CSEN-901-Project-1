package org.aiteam.code.watersort;

import org.aiteam.code.generic.OperatorResult;
import org.aiteam.code.generic.SearchState;

public class Pour implements WaterSortOperator {
    private final int from;
    private final int to;

    public Pour(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isApplicable(SearchState state) {
        if (!(state instanceof WaterSortState waterSortState))
            return false;

        Bottle[] bottles = waterSortState.getBottles();
        boolean src_not_empty = bottles[from].getCurrentSize() > 0;
        int pouredAmount = bottles[from].peekTopLayerGroup().getSize();
        boolean dest_wont_overflow = bottles[to].getCurrentSize() + pouredAmount <= WaterSortSearch.bottleCapacity;
        boolean same_colour = bottles[from].getTopLayer().equals(bottles[to].getTopLayer());
        return src_not_empty && dest_wont_overflow && (same_colour || bottles[to].isEmpty());

    }

    @Override
    public OperatorResult apply(SearchState state) {
        if (!(state instanceof WaterSortState waterSortState))
            throw new IllegalArgumentException("input state is not a WaterSortState");

        Bottle[] bottles = waterSortState.getBottles();
        if (!isApplicable(waterSortState))
            throw new IllegalArgumentException("Operator is not applicable to the given state.");

        return pour(bottles, from, to);

    }

    private static OperatorResult pour(Bottle[] bottles, int from, int to) {
        // Clone the bottles to avoid changing the original state
        Bottle[] bottleClones = bottles.clone();
        LayerGroup groupToPour = bottleClones[from].peekTopLayerGroup();
        bottleClones[to].addLayerGroup(groupToPour);
        return new OperatorResult(new WaterSortState(bottleClones), groupToPour.getSize());
    }

    @Override
    public String toString() {
        return "pour_" + from + "_" + to;
    }
}