package permutationGenerator;

import java.util.ArrayList;

public class IntermediaryList<T> {

    public int size;
    private final ArrayList<T> buffer;

    public IntermediaryList() {
        this.size = 0;
        this.buffer = new ArrayList<>();
    }

    public void AddToIntermediaryList(T value) {
        // Accept null values
        this.buffer.add(value); // Append to the end
    }
}
