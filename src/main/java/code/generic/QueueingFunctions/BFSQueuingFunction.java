package code.generic.QueueingFunctions;

import code.Node;

import java.util.Comparator;
import java.util.PriorityQueue;
import code.generic.QueueUtils;

public class BFSQueuingFunction implements QueuingFunction {

  @Override
  public PriorityQueue<Node> apply(int depth) {
    Comparator<Node> comparator = Comparator.comparingInt(Node::getDepth);
    return QueueUtils.createQueueWithDepthLimit(comparator, depth);
  }
}
