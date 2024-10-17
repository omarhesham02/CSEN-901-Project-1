package code.generic.QueueingFunctions;

import code.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EnqueueAtEnd implements QueuingFunction {
  @Override
  public PriorityQueue<Node> apply() {
    return new PriorityQueue<>(Comparator.comparingInt(Node::getDepth));
  }
}
