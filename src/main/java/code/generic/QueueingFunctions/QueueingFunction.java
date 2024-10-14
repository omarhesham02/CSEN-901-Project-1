package code.generic.QueueingFunctions;

import java.util.Set;

import code.Node;

import java.util.List;
import java.util.Queue;

@FunctionalInterface
public interface QueueingFunction {
    Queue<Node> apply(Queue<Node> queue, List<Node> node);
}