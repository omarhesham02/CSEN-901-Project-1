// src/main/java/org/aiteam/code/watersort/WaterSortSearch.java
package org.aiteam.code.watersort;

import org.aiteam.code.generic.GenericSearch;
import org.aiteam.code.generic.QueueingFunctions.QueueingFunction;

public class WaterSortSearch extends GenericSearch {

    public static String solve(String initialState, String strategy, boolean visualize) {
        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState);

        QueueingFunction waterSortQueueingFunction = getQueueingFunction(strategy);
        return GenericSearch.generalSearch(waterSortProblem, waterSortQueueingFunction, visualize);
    }
}