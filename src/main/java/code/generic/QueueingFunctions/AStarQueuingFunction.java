package code.generic.QueueingFunctions;

import code.Node;
import code.generic.HeuristicFunction;
import code.generic.QueueUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarQueuingFunction implements QueuingFunction {
    private final HeuristicFunction heuristicFunction;

    public AStarQueuingFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public PriorityQueue<Node> apply(int depth) {
        Comparator<Node> comparator = Comparator.comparingInt(node -> node.getPathCost() + heuristicFunction.apply(node));
        return QueueUtils.createQueueWithDepthLimit(comparator, depth);
    }
}