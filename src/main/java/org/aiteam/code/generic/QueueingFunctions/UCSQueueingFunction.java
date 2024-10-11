package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

import org.aiteam.code.generic.Node;

public class UCSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    @Override
    public Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T, V> node) {
        // Implement the UCS specific logic here
        // For now, let's just add the node to the queue and return it
        queue.add(node);
        return queue;
    }
}