package code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;

import code.Node;

@FunctionalInterface
public interface QueueingFunction {
    Queue<Node> apply(Queue<Node> queue, List<Node> node);
}