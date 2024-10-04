package org.aiteam.code;

@FunctionalInterface
public interface Operator<T> {
    T apply(Object... args);
}