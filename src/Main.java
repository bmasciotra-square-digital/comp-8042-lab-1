import permutation.generator.InputList;
import permutation.generator.OutputList;
import permutation.generator.PermutationGenerator;
import stack.JavaVerifier;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        // Section 1: List
        //     Integer[] input = new Integer[]{12, 64, 13, 4, 35, 10, 38};
//        Integer[] input = new Integer[]{1, 0, 6, 4, 2, 3, 5};
//
//        // should be [64, null, 38, 38, 38, 38]
//        InputList<Integer> integerInputList = new InputList<>(input);
//
//        System.out.println(integerInputList);
//        PermutationGenerator<Integer> generator = new PermutationGenerator<>(integerInputList);
//        OutputList<Integer> output = generator.smallestLargerNumbers();
//
//        System.out.println(output);
//
//        OutputList<Integer> largestSmallerNumbers = generator.largestSmallerNumbers();
//
//        System.out.println(largestSmallerNumbers);
//
//        // [0,null,5,3,null,null,null]
//        generator.shuffle();

        // Section 2: Stack

        String path = "external/StackParenthesesTask3.java";
        JavaVerifier<Character> verifier = new JavaVerifier<>();

        File file = new File(path);

        try {
            verifier.run(file);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}