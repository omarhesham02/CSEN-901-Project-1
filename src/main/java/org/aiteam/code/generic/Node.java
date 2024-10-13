package org.aiteam.code.generic;

import java.util.ArrayList;
import java.util.List;

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

    // next fields are for visualization purposes
    private final String name;

    private static int objectsCreated = 0;

    private List<Node> children;

    public Node(SearchState state, Node parent, Operator operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
        this.name = "Node " + objectsCreated;
        objectsCreated++;
        children = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Name: Node " + objectsCreated + "\n";
        result += "State:  " + state.toString().replace(";", "     ") + "\n ";
        result += "Parent: " + (parent == null ? "null" : parent.getName()) + "\n";
        result += "Operator: " + (operator == null ? "null" : operator.toString()) + "\n";
        result += "Depth: " + depth + "\n";
        result += "Path cost: " + pathCost + "\n\n";
        return result;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

}
