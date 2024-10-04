package org.aiteam.code;

public class Node {
    /** The state this node corresponds to. */
    private State state;

    /** The parent node of this node. */
    private Node parent;

    /** The operator applied from the parent state to reach this current state. */
    private Operator operator;

    /** The depth of the node in the tree. */
    private int depth;

    /** The path cost from the root to this state. */
    private int pathCost;
}
