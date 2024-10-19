package code.utils;

import code.Node;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueWithDepthLimit extends PriorityQueue<Node> {
  private int depthLimit;

  // Constructor with depth limit and default ordering
  public PriorityQueueWithDepthLimit(int depthLimit) {
    super(); // Call to the default PriorityQueue constructor
    this.depthLimit = depthLimit;
  }

  // Constructor with comparator and depth limit
  public PriorityQueueWithDepthLimit(Comparator<? super Node> comparator, int depthLimit) {
    super(comparator); // Call the PriorityQueue constructor with comparator
    this.depthLimit = depthLimit;
  }

  @Override
  public boolean addAll(Collection<? extends Node> nodes) {
    boolean modified = false;
    for (Node node : nodes) {
      if (node.getDepth() <= depthLimit) {
        modified |= super.add(node); // Add nodes only if within the depth limit
      }
    }
    return modified;
  }

  @Override
  public boolean add(Node node) {
    if (node.getDepth() <= depthLimit) {
      return super.add(node);
    }
    return false;
  }
}
