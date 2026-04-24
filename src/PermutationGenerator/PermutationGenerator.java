package PermutationGenerator;

import java.util.LinkedList;

public class PermutationGenerator<T> {
    private int size;
    private InputList<T> inputList;
    private IntermediaryList<T> interList;


    public PermutationGenerator(int n) {
        this.size = n;
        this.inputList = new InputList<T>();
        this.interList = new IntermediaryList<T>();

    }


    public PermutationGenerator(InputList<T> inputList) {
        this.inputList = inputList;
        this.interList = new IntermediaryList<T>();
        this.size = inputList.size;
//
//
//    public OutputList<T> smallestLargerNumbers() {
//        24 // your code here
//        25
//    }
//26
//        27
//
//    public OutputList<T> largestSmallerNumbers() {
//        28 // your code here
//        29
//    }
//30
//        31
//
//    public void shuffle() {
//        32 // your code here
//        33
//    }
    }
}
