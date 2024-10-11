package org.aiteam.code.generic;

public interface Operator<T extends SearchState<?>> {
    T apply(T state);

    <T> boolean isApplicable(SearchState<T> state);
}