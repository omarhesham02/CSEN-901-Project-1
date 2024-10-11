package org.aiteam.code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import org.aiteam.code.generic.Node;

public class BFSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    // @Override
    // public Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T,V>
    // initialNode) {
    // PriorityQueue<Node<T, V>> priorityQueue = new
    // PriorityQueue<>(Comparator.comparingInt(Node::depth));
    // priorityQueue.add(new Node<>(initialNode.getState()));
    // return priorityQueue;
    // }

    @Override
    public Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T, V> node) {
        // Add the node to the end of the queue (BFS behavior)
        queue.add(node);
        return queue;
    }
}
