package stack;

import java.util.ArrayList;

public class ArrayListStack<T> implements Stack<T> {

    private final ArrayList<T> stack;

    public ArrayListStack() {
        this.stack = new ArrayList<>();
    }

    @Override
    public boolean empty() {
        return this.stack.isEmpty();
    }

    @Override
    public T peek() {
        return this.stack.getLast();
    }

    @Override
    public T pop() {
        return this.stack.removeLast();
    }

    @Override
    public T push(T item) {
        this.stack.add(item);
        return this.stack.getLast();
    }
}
