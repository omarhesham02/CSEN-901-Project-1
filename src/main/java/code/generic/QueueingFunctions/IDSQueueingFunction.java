// src/main/java/org/aiteam/code/generic/QueueingFunctions/IDSQueueingFunction.java
package code.generic.QueueingFunctions;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import code.Node;

public class IDSQueueingFunction implements QueueingFunction {
    private int depth = 1;

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        for (Node node : nodes) {
            if (node.getDepth() <= depth) {
                queue.add(node);
            }
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getDepth).reversed());
        priorityQueue.addAll(queue);

        ++depth;
        return priorityQueue;
    }
}