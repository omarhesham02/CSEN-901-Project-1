package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;

import code.Node;
import code.generic.HeuristicFunction;
import code.generic.Problem;

public class AStarQueuingFunction implements QueuingFunction {
    private final HeuristicFunction heuristicFunction;

    public AStarQueuingFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(node -> node.getPathCost() + heuristicFunction.apply(node)));
    }
}