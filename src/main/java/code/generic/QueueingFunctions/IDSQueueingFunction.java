package code.generic.QueueingFunctions;

import java.util.PriorityQueue;

import code.Node;

public class IDSQueueingFunction implements QueueingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new DFSQueueingFunction().apply();
    }
}