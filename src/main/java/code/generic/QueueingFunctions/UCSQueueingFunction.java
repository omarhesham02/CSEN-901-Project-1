package code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;

import code.Node;

public class UCSQueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        queue.addAll(nodes);
        return queue;
    }
}