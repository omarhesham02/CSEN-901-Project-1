package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;

import code.Node;
import code.watersort.WaterSortProblem;

public class AStar1QueuingFunction implements QueuingFunction {
    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(node -> node.getPathCost() + WaterSortProblem.getHeuristicOne(node)));
    }
}

//function that reorders only new nodes and original queue
//all other search strategies calles the general search and pass their own queing function
