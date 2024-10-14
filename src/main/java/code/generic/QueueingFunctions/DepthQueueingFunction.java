package code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import code.Node;

public interface DepthQueueingFunction extends QueueingFunction {
    Queue<Node> apply(Queue<Node> queue, List<Node> nodes, int depth);

    @Override
    default Queue<Node> apply(Queue<Node> queue, List<Node> nodes) {
        return apply(queue, nodes, Integer.MAX_VALUE);
    }
}