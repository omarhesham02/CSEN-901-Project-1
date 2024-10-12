package org.aiteam.code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

import org.aiteam.code.generic.Node;

public class GREEDY2QueueingFunction implements QueueingFunction {
    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        // Implement the GREEDY2 specific logic here
        // For now, let's just add the node to the queue and return it
        queue.addAll(nodes);
        return queue;
    }
}