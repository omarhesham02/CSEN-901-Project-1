package org.aiteam.code.generic;

public interface Operator<T> {
    T apply(State<T> state);

    <V> boolean isApplicable(State<V> state);
}