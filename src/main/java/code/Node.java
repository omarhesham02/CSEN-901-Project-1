package code;

import java.util.ArrayList;
import java.util.List;

import code.generic.Operator;
import code.generic.SearchState;

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

    // Next fields are for visualization purposes
    private int orderOfVisiting;
    private List<Node> children;     // TODO: Storing this is not efficient, but is used currently for visualization

    public Node(SearchState state, Node parent, Operator operator, int depth, int pathCost, int orderOfVisiting) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
        this.orderOfVisiting = orderOfVisiting;
        children = new ArrayList<>();
    }

    public Node(SearchState state, Node parent, Operator operator, int depth, int pathCost) {
        this(state, parent, operator, depth, pathCost, -1);
    }

    public Node(SearchState state) {
        this(state, null, null, 0, 0, 0);
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

    public void setOrderOfVisiting(int orderOfVisiting) {
        this.orderOfVisiting = orderOfVisiting;
    }

    public String getName() {
        return "Node " + orderOfVisiting;
    }

    public int getOrderOfVisiting() {
        return orderOfVisiting;
    }

    // TODO: Convert this method to use StringBuilder
    @Override
    public String toString() {
        return "Name:  " + getName() + "\n" +
                "State:  " + state.toString().replace(";", "     ") + "\n" +
                "Parent: " + (parent == null ? "null" : parent.getName()) + "\n" +
                "Operator: " + (operator == null ? "null" : operator.toString()) + "\n" +
                "Depth: " + depth + "\n" +
                "Path cost: " + pathCost + "\n\n";
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

}
