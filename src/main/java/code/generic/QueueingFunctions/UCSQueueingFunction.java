package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import code.Node;

public class UCSQueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        queue.addAll(nodes);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getPathCost));
        priorityQueue.addAll(queue);
        return priorityQueue;
    }
}