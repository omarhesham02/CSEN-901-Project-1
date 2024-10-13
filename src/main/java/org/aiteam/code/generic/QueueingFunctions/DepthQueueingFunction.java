package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;
import java.util.Set;

import org.aiteam.code.generic.Node;

public interface DepthQueueingFunction extends QueueingFunction {
    Queue<Node> apply(Queue<Node> queue, Set<Node> nodes, int depth);

    @Override
    default Queue<Node> apply(Queue<Node> queue, Set<Node> nodes) {
        return apply(queue, nodes, Integer.MAX_VALUE);
    }
}