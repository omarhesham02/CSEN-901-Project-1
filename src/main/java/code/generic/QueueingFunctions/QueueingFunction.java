package code.generic.QueueingFunctions;
import code.Node;
import java.util.List;
import java.util.PriorityQueue;

@FunctionalInterface
public interface QueueingFunction {
    PriorityQueue<Node> apply();
}