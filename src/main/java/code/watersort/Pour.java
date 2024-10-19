package code.watersort;

import code.WaterSortSearch;
import code.generic.Operator;
import code.generic.OperatorResult;
import code.generic.SearchState;

public class Pour implements Operator {
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
        // Check if the state is a WaterSortState
        if (!(state instanceof WaterSortState waterSortState))
            return false;

        // Check if the source bottle is empty or the destination bottle is full
        if (waterSortState.getBottles()[from].isEmpty() || waterSortState.getBottles()[to].isFull())
            return false;

        // Check if the top layers of the source and destination bottles have the same color or if the destination bottle is empty
        Color topColor = waterSortState.getBottles()[from].getTopLayer();
        Color destTopColor = waterSortState.getBottles()[to].isEmpty() ? null : waterSortState.getBottles()[to].getTopLayer();

        return waterSortState.getBottles()[to].isEmpty() || topColor.equals(destTopColor);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public OperatorResult apply(SearchState state) throws CloneNotSupportedException {
        if (!(state instanceof WaterSortState waterSortState))
            throw new IllegalArgumentException("Input state is not a WaterSortState");

        if (!isApplicable(waterSortState))
            throw new IllegalArgumentException("Operator is not applicable to the given state");

        return pour((WaterSortState) state, from, to);

    }

 private static OperatorResult pour(WaterSortState state, int from, int to) throws CloneNotSupportedException {
    // Check if the source bottle is empty or the destination bottle is full
    Bottle[] bottles = state.getBottles();
    if (bottles[from].isEmpty() || bottles[to].isFull()) {
        return new OperatorResult(null, 0);
    }

    // Check if the top layers of the source and destination bottles have the same color or if the destination bottle is empty
    Color topColor = bottles[from].getTopLayer();
    Color destTopColor = bottles[to].isEmpty() ? null : bottles[to].getTopLayer();
    if (!bottles[to].isEmpty() && !topColor.equals(destTopColor)) {
        return new OperatorResult(null, 0);
    }

    // Copy the bottles to avoid changing the original state
        Bottle[] bottleCopies = new Bottle[bottles.length];
        for (int i = 0; i < bottles.length; i++) {
            bottleCopies[i] = bottles[i].clone();
        }
    // Pop the top layer from the source bottle
    int pouredLayers = 0;
    while (!bottleCopies[from].isEmpty() && bottleCopies[from].getTopLayer().equals(topColor) && !bottleCopies[to].isFull()) {
        Color color = bottleCopies[from].layers().pop();
        bottleCopies[to].layers().push(color);
        pouredLayers++;
    }

    return new OperatorResult(new WaterSortState(bottleCopies), pouredLayers);
    }

    @Override
    public String toString() {
        return "pour_" + from + "_" + to;
    }
}