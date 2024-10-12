package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;

import org.aiteam.code.generic.Node;

@FunctionalInterface
public interface QueueingFunction {
    Queue<Node> apply(Queue<Node> queue, Node node);
}