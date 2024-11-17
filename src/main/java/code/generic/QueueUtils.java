package code.generic;

import code.Node;

import java.util.PriorityQueue;
import java.util.Comparator;

public class QueueUtils {
    public static PriorityQueue<Node> createQueueWithDepthLimit(Comparator<Node> comparator, int depth) {
        return new PriorityQueue<>(comparator) {
            @Override
            public boolean add(Node node) {
                if (node.getDepth() <= depth) {
                    return super.add(node);
                }
                return false;
            }
        };
    }
}