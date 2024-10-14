package code.watersort;

import code.WaterSortSearch;
import code.generic.OperatorResult;
import code.generic.SearchState;

public class Pour implements WaterSortOperator {
    private final int from;
    private final int to;

    public Pour(int from, int to) {
        if (from == to)
            throw new IllegalArgumentException("Cannot create a pour operator with same 'from' and 'to' index");

        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isApplicable(SearchState state) {
        if (!(state instanceof WaterSortState waterSortState))
            return false;

        Bottle[] bottles = waterSortState.getBottles();
        // ---------------------------- src not empty
        int pouredAmount = 0;
        try {
            pouredAmount = bottles[from].peekTopLayerGroup().getSize();
        } catch (IllegalStateException e) {
            // happens when peekTopLayerGroup() is called on an empty bottle
            return false;
        }

        // ---------------------------- dest wont overflow
        boolean dest_wont_overflow = bottles[to].getCurrentSize() + pouredAmount <= WaterSortSearch.bottleCapacity;

        // ---------------------------- src top & dest top have same color
        Color src_layer = bottles[from].getTopLayer();
        Color dest_layer;
        try {
            dest_layer = bottles[to].getTopLayer();
        } catch (IllegalStateException e) {
            dest_layer = null;
        }
        boolean same_colour = src_layer == dest_layer;
        // ----------------------------- final check
        return dest_wont_overflow && (same_colour || bottles[to].isEmpty());

    }

    @Override
    public OperatorResult apply(SearchState state) {
        if (!(state instanceof WaterSortState waterSortState))
            throw new IllegalArgumentException("Input state is not a WaterSortState");

        Bottle[] bottles = waterSortState.getBottles();

        if (!isApplicable(waterSortState))
            throw new IllegalArgumentException("Operator is not applicable to the given state");

        return pour(bottles, from, to);

    }

    private static OperatorResult pour(Bottle[] bottles, int from, int to) {
        // Copy the bottles to avoid changing the original state
        Bottle[] bottleCopies = new Bottle[bottles.length];

        for (int i = 0; i < bottles.length; i++)
            bottleCopies[i] = bottles[i].copy();

        LayerGroup groupToPour = bottleCopies[from].popTopLayerGroup();
        bottleCopies[to].addLayerGroup(groupToPour);

        return new OperatorResult(new WaterSortState(bottleCopies), groupToPour.getSize());
    }

    @Override
    public String toString() {
        return "pour_" + from + "_" + to;
    }
}