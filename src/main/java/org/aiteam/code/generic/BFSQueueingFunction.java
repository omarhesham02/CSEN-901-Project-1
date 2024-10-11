package org.aiteam.code.generic;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    @Override
    public Queue<Node<T, V>> apply(Node<T, V> initialNode) {
        PriorityQueue<Node<T, V>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::depth));
        priorityQueue.add(new Node<>(initialNode.getState()));
        return priorityQueue;
    }
}
