package org.aiteam.code.watersort;

import org.aiteam.code.generic.GenericSearch;

public class WaterSortSearch extends GenericSearch {

    public static String solve(String initialState, String strategy, boolean visualize) {

        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);

        WaterSortProblem waterSortProblem = new WaterSortProblem(
            parsedInitialState
        );

        return generalSearch(waterSortProblem, strategy, visualize);
    }
}
