// src/main/java/org/aiteam/code/watersort/WaterSortSearch.java
package org.aiteam.code.watersort;

import org.aiteam.code.generic.GenericSearch;
import org.aiteam.code.generic.QueueingFunction;
import org.aiteam.code.generic.BFSQueueingFunction;
public class WaterSortSearch extends GenericSearch {

    public static String solve(String initialState, String strategy, boolean visualize) {
        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        Pour pour = new Pour(0, 0);

        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState);

        // Run GenericSearch on waterSortProblem with the BFS QueueingFunction
        QueueingFunction<Bottle[], Pour> queueingFunction = new BFSQueueingFunction<>();
        return generalSearch(waterSortProblem, queueingFunction);
        return "";
    }

}