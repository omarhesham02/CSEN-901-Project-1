package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;

import code.Node;

public class BFSQueueingFunction implements QueueingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
    }
}