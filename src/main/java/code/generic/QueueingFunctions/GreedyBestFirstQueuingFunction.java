package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import code.Node;
import code.generic.HeuristicFunction;
import code.generic.QueueUtils;

public class GreedyBestFirstQueuingFunction implements QueuingFunction {
    private final HeuristicFunction heuristicFunction;

    public GreedyBestFirstQueuingFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public PriorityQueue<Node> apply(int depth) {
        Comparator<Node> comparator = Comparator.comparingInt(heuristicFunction::apply);
        return QueueUtils.createQueueWithDepthLimit(comparator, depth);
    }
}