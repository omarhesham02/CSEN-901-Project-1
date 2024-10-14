// src/main/java/org/aiteam/code/generic/QueueingFunctions/IDSQueueingFunction.java
package code.generic.QueueingFunctions;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import code.Node;

public class IDSQueueingFunction implements QueueingFunction {

    @Override
    public PriorityQueue<Node> apply() {
        return new DFSQueueingFunction().apply();
    }
}