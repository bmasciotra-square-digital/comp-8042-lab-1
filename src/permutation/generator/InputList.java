package permutation.generator;

import java.util.ArrayList;
import java.util.Arrays;


public class InputList<T> {
    public int size;
    private ArrayList<T> data;

    public InputList() {
        this.data = new ArrayList<>();
    }

    public InputList(T[] input) {
        this.size = input.length;
        this.data = new ArrayList<>(Arrays.stream(input).toList());
    }


    public void append(T value) {
        data.add(value);
        size++;
    }

    public void addToInputList(T value, int index) {
        data.add(index, value);
        size++;
    }

    public T getValueAtIndex(int index) {
        if (index == -1) {
            return data.getLast();
        }

        return data.get(index);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        int i = 0;
        for (T next : data) {
            String s = next.toString();
            builder.append(s).append((i == data.size() - 1) ? "" : ", ");
            i++;
        }

        builder.append("]");
        return builder.toString();
    }


}
