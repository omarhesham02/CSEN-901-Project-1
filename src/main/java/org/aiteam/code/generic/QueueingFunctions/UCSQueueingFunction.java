package org.aiteam.code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.aiteam.code.generic.Node;

public class UCSQueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, Set<Node> nodes) {
        queue.addAll(nodes);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getPathCost));
        priorityQueue.addAll(queue);
        return priorityQueue;
    }
}