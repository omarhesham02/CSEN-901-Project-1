package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;
import java.util.LinkedList;

import org.aiteam.code.generic.Node;

public class IDSQueueingFunction implements QueueingFunction {
    @Override
    public Queue<Node> apply(Queue<Node> queue, Node node) {
        // Implement the IDS specific logic here
        // For now, let's just add the node to the queue and return it
        queue.add(node);
        return queue;
    }
}