// src/main/java/org/aiteam/code/watersort/WaterSortSearch.java
package org.aiteam.code.watersort;

import java.lang.Thread.State;
import java.util.Queue;

import org.aiteam.code.generic.GenericSearch;
import org.aiteam.code.generic.Node;
import org.aiteam.code.generic.Operator;
import org.aiteam.code.generic.QueueingFunctions.AStar1QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.AStar2QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.BFSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.DFSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.GREEDY1QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.GREEDY2QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.IDSQueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.QueueingFunction;
import org.aiteam.code.generic.QueueingFunctions.UCSQueueingFunction;
import State

public class WaterSortSearch extends GenericSearch {

    public static String solve(String initialState, String strategy, boolean visualize) {
        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState);

        QueueingFunction<WaterSortState, Pour> queueingFunction = getQueueingFunction(strategy);
        return GenericSearch.generalSearch(waterSortProblem, queueingFunction);
    }

    public static <T, V> QueueingFunction<State<T>, Operator<V>> getQueueingFunction(String strategy) {
        switch (strategy) {
            case "BF":
                return new BFSQueueingFunction<State<T>, Operator<V>>();
            case "DF":
                return new DFSQueueingFunction<State<T>, Operator<V>>();
            case "ID":
                return new IDSQueueingFunction<State<T>, Operator<V>>();
            case "UC":
                return new UCSQueueingFunction<State<T>, Operator<V>>();
            case "GR1":
                return new GREEDY1QueueingFunction<State<T>, Operator<V>>();

            case "GR2":
                return new GREEDY2QueueingFunction<State<T>, Operator<V>>();
            case "AS1":
                return new AStar1QueueingFunction<State<T>, Operator<V>>();
            case "AS2":
                return new AStar2QueueingFunction<State<T>, Operator<V>>();
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);

        }
    }
}