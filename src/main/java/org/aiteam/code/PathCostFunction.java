package org.aiteam.code;

@FunctionalInterface
public interface PathCostFunction {
    int apply(Node node);
}
