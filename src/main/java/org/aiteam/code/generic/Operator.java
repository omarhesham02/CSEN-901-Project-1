package org.aiteam.code.generic;

@FunctionalInterface
public interface Operator<T> {
    T apply(Object... args);
}