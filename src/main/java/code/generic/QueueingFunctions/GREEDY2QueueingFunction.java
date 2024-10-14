package code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import code.Node;

public class GREEDY2QueueingFunction implements QueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        queue.addAll(nodes);
        return queue;
    }
}