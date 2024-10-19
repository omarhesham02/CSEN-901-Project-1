package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;

import code.Node;
import code.generic.HeuristicFunction;

public class GreedyBestFirstQueuingFunction implements QueuingFunction {
    private final HeuristicFunction heuristicFunction;

    public GreedyBestFirstQueuingFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(heuristicFunction::apply));
    }
}