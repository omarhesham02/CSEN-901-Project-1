package code.generic.QueueingFunctions;

import code.Node;
import code.generic.QueueUtils;
import code.utils.PriorityQueueWithDepthLimit;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DLSQueuingFunction implements QueuingFunction {

  @Override
  public PriorityQueue<Node> apply(int depth) {
    Comparator<Node> comparator = Comparator.comparingInt(Node::getDepth).reversed();
    return QueueUtils.createQueueWithDepthLimit(comparator, depth);
  }
}
