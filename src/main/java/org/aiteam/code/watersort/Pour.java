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
        if (state instanceof WaterSortState waterSortState) {
            Bottle[] bottles = waterSortState.getBottles();
            return bottles[from].getCurrentCapacity() > 0
                    && bottles[to].getCurrentCapacity() < bottles[to].getMaximumCapacity();
        }
        return false;
    }

    @Override
    public OperatorResult apply(SearchState state) {
        if (state instanceof WaterSortState waterSortState) {
            Bottle[] bottles = waterSortState.getBottles();
            if (isApplicable(waterSortState))
                return pour(bottles, from, to);
            return null;
        }
        return null;
    }

    private static OperatorResult pour(Bottle[] bottles, int from, int to) {
        Bottle source = bottles[from];
        Bottle destination = bottles[to];

        int layersPoured = 0;

        while (source.getCurrentCapacity() > 0
                && destination.getCurrentCapacity() < destination.getMaximumCapacity()
                && (destination.getCurrentCapacity() == 0 || source.getTopLayer().equals(destination.getTopLayer())
        )) {
            destination.addTopLayer(source.removeTopLayer());
            layersPoured++;
        }

        return new OperatorResult(new WaterSortState(bottles), layersPoured);
    }
}