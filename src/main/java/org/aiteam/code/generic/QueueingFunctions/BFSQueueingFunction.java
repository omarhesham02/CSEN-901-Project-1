package org.aiteam.code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.aiteam.code.generic.Node;

public class BFSQueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, Set<Node> nodes) {
        // Add the node to the end of the queue (BFS behavior)
        queue.addAll(nodes);
        return queue;
    }
}
