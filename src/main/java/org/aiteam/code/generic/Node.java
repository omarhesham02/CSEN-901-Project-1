package org.aiteam.code.generic;

// TODO: Review if we need operatorCost field

/**
 * Represents a node in the search tree.
 *
 * @param <V>          The actual value of the state in the specific problem (e.g. Array of bottles in the Water Sort problem)
 * @param state        The state this node corresponds to.
 * @param parent       The parent node of this node.
 * @param operator     The operator applied from the parent state to reach this current state.
 * @param depth        The depth of the node in the tree.
 * @param pathCost     The path cost from the root to this state.
 * @param operatorCost The cost of the operator applied to reach this state.
 */
public record Node<T, V>(State<V> state, Node<T, V> parent, Operator<T> operator, int depth,
                         int pathCost, int operatorCost) {
}
