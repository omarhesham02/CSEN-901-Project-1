// src/main/java/org/aiteam/code/generic/QueueingFunctions/IDSQueueingFunction.java
package org.aiteam.code.generic.QueueingFunctions;

import java.util.List;
import java.util.Queue;
import org.aiteam.code.generic.Node;

public class IDSQueueingFunction implements DepthQueueingFunction {

    @Override
    public Queue<Node> apply(Queue<Node> queue, List<Node> nodes, int depth) {
        for (Node node : nodes) {
            if (node.getDepth() <= depth) {
                queue.add(node);
            }
        }
        return queue;
    }
}