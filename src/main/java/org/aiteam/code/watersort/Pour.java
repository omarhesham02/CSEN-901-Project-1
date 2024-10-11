package org.aiteam.code.watersort;

import org.aiteam.code.generic.SearchState;

public record Pour(int from, int to) implements WaterSortOperator<WaterSortState> {

    @Override
    public WaterSortState apply(WaterSortState state) {
        // return pour(bottles, from, to);
        return null;
    }

    @Override
    public <V> boolean isApplicable(SearchState<V> state) {
        if (state instanceof WaterSortState waterSortState) {
            Bottle[] bottles = waterSortState.getBottles();
            return bottles[from].getCurrentCapacity() > 0
                    && bottles[to].getCurrentCapacity() < bottles[to].getMaximumCapacity();
        }
        return false;
    }

    public static int pour(Bottle[] bottles, int from, int to) {
        Bottle source = bottles[from];
        Bottle destination = bottles[to];

        int layersPoured = 0;
        while (source.getCurrentCapacity() > 0 && destination.getCurrentCapacity() < destination.getMaximumCapacity()
                && (destination.getCurrentCapacity() == 0 || source.getTopLayer().equals(destination.getTopLayer()))) {
            destination.addTopLayer(source.removeTopLayer());
            layersPoured++;
        }

        return layersPoured;
    }
}