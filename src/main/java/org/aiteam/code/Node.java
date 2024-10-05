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

    /** The cost of the operator applied to reach this state. */
    private int operatorCost;

    public Node(State state, Node parent, Operator operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Operator getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getOperatorCost() {
        return operatorCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

}
