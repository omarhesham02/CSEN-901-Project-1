package org.aiteam.code.generic;

public interface Operator<T> {
    T apply(Object... args);

    <V> boolean isApplicable(State<V> state);
}