import permutationGenerator.InputList;
import permutationGenerator.OutputList;
import permutationGenerator.PermutationGenerator;
import stack.JavaVerifier;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        // Section 1: List — handout example L = [1,0,6,4,2,3,5]
        Integer[] input = new Integer[]{1, 0, 6, 4, 2, 3, 5};
        InputList<Integer> integerInputList = new InputList<>(input);

        PermutationGenerator<Integer> generator = new PermutationGenerator<>(integerInputList);
        System.out.println("Section 1: List\n");
        System.out.println("L: " + integerInputList);

        OutputList<Integer> smallest = generator.smallestLargerNumbers();
        System.out.println("smallest (actual):   " + smallest);
        System.out.println("smallest (expected): [2, 2, null, 5, 3, 5, null]");

        OutputList<Integer> largest = generator.largestSmallerNumbers();

        System.out.println("largest (actual):    " + largest);
        System.out.println("largest (expected):  [0, null, 5, 3, null, null, null]");

        // Section 2: Stack
        System.out.println("\n\nSection 2: Stacks\n");
        String[] paths = {"external/StackParenthesesTask1.java", "external/StackParenthesesTask2.java", "external/StackParenthesesTask3.java", "external/StackParenthesesTask4.java"};

        JavaVerifier<Character> verifier = new JavaVerifier<>();

        for (String path : paths) {
            File file = new File(path);

            try {
                verifier.run(file);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}