package code.generic.QueueingFunctions;

import java.util.PriorityQueue;

import code.Node;

@FunctionalInterface
public interface QueueingFunction {
    PriorityQueue<Node> apply();
}