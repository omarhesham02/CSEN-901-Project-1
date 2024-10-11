package org.aiteam.code.generic;

import java.util.PriorityQueue;
import java.util.Queue;

public class DFSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    @Override
    public Queue<Node<T, V>> apply(Node<T, V> initialNode) {
        PriorityQueue<Node<T, V>> priorityQueue = new PriorityQueue<>((node1, node2) -> Integer.compare(node2.depth(), node1.depth()));
        priorityQueue.add(initialNode);
        return priorityQueue;
    }
}
