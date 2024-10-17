package code.generic.QueueingFunctions;

import code.Node;
import code.utils.PriorityQueueWithDepthLimit;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EnqueueAtFrontWithDepthLimit implements QueuingFunction {
  public int depthLimit = 0;

  public EnqueueAtFrontWithDepthLimit(int depthLimit){
    this.depthLimit = depthLimit;
  }

  @Override
  public PriorityQueue<Node> apply() {
    return new PriorityQueueWithDepthLimit(Comparator.comparingInt(Node::getDepth).reversed(),depthLimit);
  }
}
