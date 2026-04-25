package permutation.generator;

import java.util.ArrayList;
import java.util.Comparator;


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
            T focused = null;

            int n = inputList.size;

            for (int j = i + 1; j < n; j++) {
                T front = inputList.getValueAtIndex(j);
                T back = inputList.getValueAtIndex(n - 1);

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

                // compare value against comparison, if comparison is greater...
                if (value.compareTo(chosenValue) < 0) {
                    if (focused == null) {
                        focused = chosenValue;
                    } else {
                        // If the new value is less than the focused value
                        if (chosenValue.compareTo(focused) <= 0) {
                            focused = chosenValue;
                        }
                    }
                }
                n--;
            }

            output.add(focused);

        }
        return output;
    }


    public OutputList<T> largestSmallerNumbers() {
        // we know the length, if its the legnth then there will be none bigger than it
        OutputList<T> output = new OutputList<>();

        for (int i = 0; i < inputList.size; i++) {
            T value = inputList.getValueAtIndex(i);

            T focused = null;
            for (int j = i + 1; j < inputList.size; j++) {
                T front = inputList.getValueAtIndex(j);
                T back = inputList.getValueAtIndex(inputList.size - i);

                int frontCompared = value.compareTo(front);
                int backCompared = value.compareTo(back);

                // is the first value larger than the second
                int chosenValue = front.compareTo(back);
                if (value.compareTo(front) < 0) {
                    if (focused == null) {
                        focused = front;
                    } else {
                        if (front.compareTo(focused) >= 0) {
                            focused = front;
                        }
                    }
                }

            }
            output.add(focused);

        }
        return output;
    }

    //30
//        31
//
//    public void shuffle() {
//        32 // your code here
//        33
//    }


}
