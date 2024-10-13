package org.aiteam.code.generic;

import org.aiteam.code.watersort.Color;

/**
 * A generic fixed-size stack implementation.
 *
 * @param <T> the type of elements in the stack
 */
public class FixedSizeStack<T> implements Cloneable {
    private T[] stackArray;
    private int top;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public FixedSizeStack(int size) {
        stackArray = (T[]) new Object[size];
        capacity = size;
        top = -1;
    }

    public void push(T element) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Stack is full, cannot push.");
        }
        stackArray[++top] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Index out of bounds, can't get element.");
        }
        return stackArray[index];
    }

    public T pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot pop.");
        }
        return stackArray[top--];
    }

    public T peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot peek.");
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    // used in other parts of the code, care !
    public boolean equals(Object obj) {
        if (!(obj instanceof FixedSizeStack other))
            return false;
        if (size() != other.size())
            return false;
        for (int i = 0; i < size(); i++) {
            if (!stackArray[i].equals(other.stackArray[i]))
                return false;
        }
        return true;
    }

    @Override
    public FixedSizeStack<T> clone() {
        try {
            FixedSizeStack<T> clone = (FixedSizeStack<T>) super.clone();
            clone.stackArray = stackArray.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // test equality of 2 FixedSizeStacks
    public static void main(String[] args) {
        // Create the first FixedSizeStack<Color>
        FixedSizeStack<Color> stack1 = new FixedSizeStack<>(3);
        stack1.push(Color.r);
        stack1.push(Color.g);
        stack1.push(Color.b);

        // Create the second FixedSizeStack<Color>
        FixedSizeStack<Color> stack2 = new FixedSizeStack<>(3);
        stack2.push(Color.r);
        stack2.push(Color.g);
        stack2.push(Color.b);

        // Test the equality of the two FixedSizeStack objects
        boolean areEqual = stack1.equals(stack2);
        System.out.println("The two FixedSizeStack objects are equal: " + areEqual);
    }

}
