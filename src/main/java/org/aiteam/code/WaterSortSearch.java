package org.aiteam.code;

import java.util.ArrayList;
import java.util.List;

public class WaterSortSearch extends GenericSearch {


    public static String solve(String initialState, String strategy, boolean visualize) {
        WaterSortSearchState parsedInitialState = Utils.parseInitialState(initialState);
        int numberOfBottles = Utils.NUMBER_OF_BOTTLES;
        int bottleCapacity = Utils.BOTTLE_CAPACITY;
        List<Operator<?>> operators = new ArrayList<>();
        Pour pour = new Pour(new Bottle(), new Bottle());
        operators.add(pour);

        Problem problem = new Problem(
            parsedInitialState,
            operators,
        );

        return generalSearch(problem, strategy, visualize);
    }
}
