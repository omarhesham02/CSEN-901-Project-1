package org.aiteam.code.generic;
import java.util.Queue;

/**
 * Represents a function that takes a node and returns a queue of nodes.
 * @param <T> The type of the state
 * @param <V> The type of the value
 */
@FunctionalInterface
public interface QueueingFunction<T, V> {
    Queue<Node<T, V>> apply(Node<T, V> initialNode);
}
