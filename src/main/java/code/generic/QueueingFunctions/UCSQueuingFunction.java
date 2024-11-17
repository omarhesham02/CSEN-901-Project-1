package code.generic.QueueingFunctions;

import code.Node;
import code.generic.QueueUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UCSQueuingFunction implements QueuingFunction {

  @Override
  public PriorityQueue<Node> apply(int depth) {
    Comparator<Node> comparator = Comparator.comparingInt(Node::getPathCost);
    return QueueUtils.createQueueWithDepthLimit(comparator, depth);
  }
}
