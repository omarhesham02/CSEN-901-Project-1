package org.aiteam.code.generic.QueueingFunctions;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import org.aiteam.code.generic.Node;

public class DFSQueueingFunction<T, V> implements QueueingFunction<T, V> {
    // @Override
    // public Queue<Node<T, V>> apply(Node<T, V> initialNode) {
    // PriorityQueue<Node<T, V>> priorityQueue = new PriorityQueue<>(
    // (node1, node2) -> Integer.compare(node2.depth(), node1.depth()));
    // priorityQueue.add(initialNode);
    // return priorityQueue;
    // }
    @Override
    public Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T, V> node) {
        // Add the node to the front of the queue (DFS behavior)
        LinkedList<Node<T, V>> linkedList = (LinkedList<Node<T, V>>) queue;
        linkedList.addFirst(node);
        return linkedList;
    }
}
