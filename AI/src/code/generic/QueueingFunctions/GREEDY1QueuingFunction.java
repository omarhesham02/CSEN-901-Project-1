package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;

import code.Node;
import code.watersort.WaterSortProblem;

public class GREEDY1QueuingFunction implements QueuingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(WaterSortProblem::getHeuristicOne));
    }
}