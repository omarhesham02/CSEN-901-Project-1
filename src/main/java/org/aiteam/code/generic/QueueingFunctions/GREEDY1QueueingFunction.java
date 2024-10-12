package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

import org.aiteam.code.generic.Node;

public class GREEDY1QueueingFunction implements QueueingFunction {
    @Override
    public Queue<Node> apply(Queue<Node> queue, Node node) {
        // Implement the GREEDY1 specific logic here
        // For now, let's just add the node to the queue and return it
        queue.add(node);
        return queue;
    }
}