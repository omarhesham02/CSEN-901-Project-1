package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;
import java.util.LinkedList;

import org.aiteam.code.generic.Node;

public class IDSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    @Override
    public Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T, V> node) {
        // Implement the IDS specific logic here
        // For now, let's just add the node to the queue and return it
        queue.add(node);
        return queue;
    }
}