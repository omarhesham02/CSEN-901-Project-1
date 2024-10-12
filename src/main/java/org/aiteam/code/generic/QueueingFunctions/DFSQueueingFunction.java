package org.aiteam.code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;

import org.aiteam.code.generic.Node;

public class DFSQueueingFunction implements QueueingFunction {
    // @Override
    // public Queue<Node<T, V>> apply(Node<T, V> initialNode) {
    // PriorityQueue<Node<T, V>> priorityQueue = new PriorityQueue<>(
    // (node1, node2) -> Integer.compare(node2.depth(), node1.depth()));
    // priorityQueue.add(initialNode);
    // return priorityQueue;
    // }
    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        // Add the node to the front of the queue (DFS behavior)
//        LinkedList<Node> linkedList = (LinkedList<Node>) queue;
//        linkedList.addFirst(node);
        queue.addAll(nodes);
        return queue;
    }
}
