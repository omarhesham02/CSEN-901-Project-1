package org.aiteam.code.generic.QueueingFunctions;

import java.util.Queue;

import org.aiteam.code.generic.Node;

/**
 * Represents a function that takes a queue of nodes and a node, and returns a
 * queue of nodes.
 * 
 * @param <T> The type of the state
 * @param <V> The type of the value
 */
@FunctionalInterface
public interface QueueingFunction<T, V> {
    Queue<Node<T, V>> apply(Queue<Node<T, V>> queue, Node<T, V> node);
}