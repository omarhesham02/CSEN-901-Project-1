package code.generic;

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

    /** Used in other parts of the code, care! */
    public boolean equals(Object obj) {
        if (!(obj instanceof FixedSizeStack<?> other))
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
    public int hashCode() {
        int result = 1;
        for (int i = 0; i <= top; i++) {
            result = 31 * result + (stackArray[i] == null ? 0 : stackArray[i].hashCode());
        }
        return result;
    }

    @Override
    public FixedSizeStack<T> clone() throws CloneNotSupportedException {
        FixedSizeStack<T> clone = (FixedSizeStack<T>) super.clone();
        clone.stackArray = stackArray.clone();
        return clone;
    }

    public int getMaxCapacity() {
        return capacity;
    }

}
