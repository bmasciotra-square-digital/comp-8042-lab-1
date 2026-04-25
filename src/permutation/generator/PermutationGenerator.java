package permutation.generator;

import java.util.ArrayList;
import java.util.Random;


public class PermutationGenerator<T extends Comparable<T>> {
    public final int size;
    private InputList<T> inputList;
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

    /// Example: L = [1,0,6,4,2,3,5] L new = [0,null,5,3,null,null,null]


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

    public void shuffle() {
        InputList<T> shuffled = new InputList<>();
        ArrayList<Integer> visited = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < this.inputList.size; i++) {
            int index = random.nextInt(this.inputList.size);

            while (visited.contains(index)) {
                index = random.nextInt(this.inputList.size);
            }

            visited.add(index);

            shuffled.append(this.inputList.getValueAtIndex(index));
        }

        this.inputList = shuffled;
    }

    private T getChosenSmallestValue(T value, T front, T back) {
        int frontCompared = front.compareTo(value);
        int backCompared = back.compareTo(value);

        T chosenValue = null;

        // If they are both larger than the value in scope
        if (frontCompared == backCompared) {
            // we want the smallest compared to both
            int smallest = front.compareTo(back);
            chosenValue = (smallest < 0) ? front : back;
        }

        // If the front is
        if (frontCompared > backCompared) {
            chosenValue = front;
        }

        if (backCompared > frontCompared) {
            chosenValue = back;
        }

        return chosenValue;

    }

    private T getChosenLargestValue(T value, T front, T back) {
        int frontCompared = front.compareTo(value);
        int backCompared = back.compareTo(value);

        T chosenValue = null;

        // If they are both larger than the value in scope
        if (frontCompared == backCompared) {
            int largest = front.compareTo(back);
            chosenValue = (largest > 0) ? front : back;
        }

        if (frontCompared > backCompared) {
            chosenValue = back;
        }

        if (backCompared > frontCompared) {
            chosenValue = front;
        }

        return chosenValue;

    }

    private void iterateAndAssess(int i, T value, OutputList<T> output, boolean small) {
        int n = this.inputList.size;
        T focused = null;

        for (int j = i + 1; j < n; j++) {
            T front = inputList.getValueAtIndex(j);
            T back = inputList.getValueAtIndex(n - 1);
            T chosenValue = (small) ? getChosenSmallestValue(value, front, back) : getChosenLargestValue(value, front, back);

            boolean evaluation = (small) ? value.compareTo(chosenValue) < 0 : value.compareTo(chosenValue) > 0;

            if (evaluation) {
                if (focused == null) {
                    focused = chosenValue;
                } else {
                    boolean finalEvaluation = (small) ? chosenValue.compareTo(focused) <= 0 : chosenValue.compareTo(focused) >= 0;
                    if (finalEvaluation) {
                        focused = chosenValue;
                    }
                }
            }

            n--;
        }

        output.add(focused);
    }
}
