package org.aiteam.code;

import java.util.ArrayList;
import java.util.List;

public class WaterSortSearch extends GenericSearch {


    public static String solve(String initialState, String strategy, boolean visualize) {
        InitialState parsedInitialState = Utils.parseInitialState(initialState);
        int numberOfBottles = parsedInitialState.getNumberOfBottles();
        int bottleCapacity = parsedInitialState.getBottleCapacity();
        State initState = parsedInitialState.getState();

        List<Operator<?>> operators = new ArrayList<>();
        Pour pourOperator = new Pour(new Bottle(), new Bottle());
        operators.add(pourOperator);

        Problem problem = new Problem(
            parsedInitialState.getState(),
            operators,
        );

        return generalSearch(problem, strategy, visualize);
    }
}
