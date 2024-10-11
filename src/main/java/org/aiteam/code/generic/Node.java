package org.aiteam.code.generic;

// TODO: Review if we need operatorCost field

import org.aiteam.code.watersort.Bottle;
import org.aiteam.code.watersort.Pour;
import org.aiteam.code.watersort.WaterSortState;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the search tree.
 *
 * @param <V>          The actual value of the state in the specific problem
 *                     (e.g. Array of bottles in the Water Sort problem)
 * @param state        The state this node corresponds to.
 * @param parent       The parent node of this node.
 * @param operator     The operator applied from the parent state to reach this
 *                     current state.
 * @param depth        The depth of the node in the tree.
 * @param pathCost     The path cost from the root to this state.
 * @param operatorCost The cost of the operator applied to reach this state.
 */

public record Node<T, V>(SearchState<V> state, Node<T, V> parent, Operator<T> operator, int depth,
        int pathCost, int operatorCost) {

    public Node(SearchState<V> state, Node<T, V> parent, Operator<T> operator, int depth, int pathCost) {
        this(state, parent, operator, depth, pathCost, 0);
    }

    public Node(SearchState<V> initialState) {
        this(initialState, null, null, 0, 0, 0);

    }

    public SearchState<V> getState() {
        return state;
    }

    public Node<T, V> getParent() {
        return parent;
    }

    public Operator<T> getOperator() {
        return operator;
    }

    public Node[] expand(Node node) {
        // Apply every possible combination of pour operations to the current state and
        // create a new node for each successful pour operation
        WaterSortState state = (WaterSortState) node.getState();
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < state.getBottles().length; i++) {
            for (int j = 0; j < state.getBottles().length; j++) {
                if (i != j) {
                    Pour pour = new Pour(i, j);
                    Bottle[] bottles = state.getBottles();
                    int layersPoured = Pour.pour(bottles, i, j);
                    if (layersPoured > 0) {
                        WaterSortState newState = new WaterSortState(bottles);
                        Node<T, V> child = new Node<>(newState, node, pour, node.depth() + 1,
                                node.pathCost() + layersPoured);
                        children.add(child);
                    }
                }
            }
        }
        return children.toArray(new Node[0]);
    }
}
