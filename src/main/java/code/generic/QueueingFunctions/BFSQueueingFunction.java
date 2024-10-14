package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import code.Node;

public class BFSQueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        // Add the node to the end of the queue (BFS behavior)
        queue.addAll(nodes);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getDepth).reversed());
        priorityQueue.addAll(queue);
        return priorityQueue;
    }
}