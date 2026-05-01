package permutation.generator;

import java.util.Collections;
import java.util.Random;


public class PermutationGenerator<T extends Comparable<T>> {
    public final int size;
    private final InputList<T> inputList;
    private final IntermediaryList<T> interList;

    public PermutationGenerator(int n) {
        this.size = n;
        this.inputList = new InputList<>();
        this.interList = new IntermediaryList<>();
    }

    public PermutationGenerator(InputList<T> inputList) {
        this.inputList = inputList;
        this.interList = new IntermediaryList<T>();
        this.size = inputList.size;

        // shuffle(); // Note: This is commented out for testing to ensure expected values, uncomment to test the shuffle functionality
    }


    /**
     * Finds the smallest value LARGER than the current index of the input and outputs a new output list
     *
     * @return a new OutputList
     */
    public OutputList<T> smallestLargerNumbers() {
        OutputList<T> output = new OutputList<>();

        for (int i = 0; i < inputList.size; i++) {
            T value = inputList.getValueAtIndex(i);
            iterateAndAssess(i, value, output, true);
        }

        return output;
    }

    /**
     * Given a list L of n integers which are some permutation of the numbers 0, 1, 2, ... , n-1. Use the
     * same data structures you chose in question 1 to write a method that generates a new list of
     * size n that contains the largest number in L located after position i that is smaller than L[i].
     *
     * @return and Output list with
     */
    public OutputList<T> largestSmallerNumbers() {
        OutputList<T> output = new OutputList<>();

        for (int i = 0; i < inputList.size; i++) {
            T value = inputList.getValueAtIndex(i);
            iterateAndAssess(i, value, output, false);
        }

        return output;
    }

    /**
     * Shuffles the input list a random amount of times less than the size of the input list.
     * the shuffle utilizes collections swap to swap the elements of 2 random indices n amount of times
     */
    public void shuffle() {
        Random random = new Random();

        int bound = this.inputList.size;
        int shuffles = random.nextInt(bound);

        for (int i = 0; i < shuffles; i++) {
            // swap 2 random items per shuffle with the collections API
            // (there is a case where we swap the same index, but it won't affect the shuffle to much

            Collections.swap(this.inputList.getData(), random.nextInt(bound), random.nextInt(bound));
        }
    }

    /**
     * Performs a second iteration to find the optimal value based on the condition of the algorithm
     *
     * @param i      the index
     * @param value  the value we are assessing
     * @param output the output list
     * @param small  a boolean determining if we are solving for smallest or largest after index
     */
    private void iterateAndAssess(int i, T value, OutputList<T> output, boolean small) {
        T best = null;

        for (int j = i + 1; j < inputList.size; j++) {
            T x = inputList.getValueAtIndex(j);

            boolean ok = small ? x.compareTo(value) > 0 : x.compareTo(value) < 0;

            if (!ok) {
                continue;
            }

            if (best == null) {
                // Set the value if null
                best = x;
                continue;
            }

            // compare the new value to the optimal value
            int cmp = x.compareTo(best);

            // cover the larger and smaller scenarios
            if ((small && cmp < 0) || (!small && cmp > 0)) {
                best = x;
            }
        }

        output.add(best);
    }
}
