package org.aiteam.code.generic;

public interface Operator<T> {
    T apply(SearchState<T> state);

    <V> boolean isApplicable(SearchState<V> state);
}