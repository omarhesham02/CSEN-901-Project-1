package code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;

import code.Node;

public class UCSQueueingFunction implements QueueingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(Node::getPathCost));
    }
}