package code.generic.QueueingFunctions;

import java.util.PriorityQueue;

import code.Node;

@FunctionalInterface
public interface QueuingFunction {
    PriorityQueue<Node> apply(int depth);
}