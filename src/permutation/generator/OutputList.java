package permutation.generator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OutputList<T> {
    public int size;
    private final ArrayList<T> data;

    public OutputList() {
        this.size = 0;
        this.data = new ArrayList<>();
    }

    public void add(T value) {
        this.data.add(value);
        size++;
    }

    public T get(int index) {
        return this.data.get(index);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        int i = 0;
        for (T next : data) {
            String s;
            if (next == null) {
                s = "null";
            } else {
                s = next.toString();
            }
            builder.append(s).append((i == data.size() - 1) ? "" : ", ");
            i++;
        }

        builder.append("]");
        return builder.toString();
    }

}
