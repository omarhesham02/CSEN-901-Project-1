package org.aiteam.code.generic;

public class Node {

    /** The state this node corresponds to. */
    private final SearchState state;

    /** The parent node of this node. */
    private final Node parent;

    /** The operator applied from the parent state to reach this state. */
    private final Operator operator;

    /** The depth of this node in the search tree. */
    private final int depth;

    /** The cost of the path from the initial state to this node. */
    private final int pathCost;

    public Node(SearchState state, Node parent, Operator operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public Node(SearchState state) {
        this(state, null, null, 0, 0);
    }

    public SearchState getState() {
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

    @Override
    public String toString() {
        return state.toString();
    }
}
