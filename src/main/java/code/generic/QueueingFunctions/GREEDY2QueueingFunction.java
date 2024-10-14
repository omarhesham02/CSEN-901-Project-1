package code.generic.QueueingFunctions;
import code.Node;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GREEDY2QueueingFunction implements QueueingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new PriorityQueue<>(Comparator.comparingInt(Node::getHeuristic2));
    }
}