import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import permutation.generator.InputList;
import permutation.generator.OutputList;
import permutation.generator.PermutationGenerator;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermutationGeneratorTest {

    // List Tests
    static Stream<Arguments> inputProviderOne() {
        Integer[] input = new Integer[]{4, 1, 3, 2, 5};
        InputList<Integer> list1 = new InputList<>(input);

        OutputList<Integer> expected = new OutputList<>();
        expected.add(5);
        expected.add(2);
        expected.add(5);
        expected.add(5);
        expected.add(null);

        return Stream.of(Arguments.of(list1, expected));
    }

    static Stream<Arguments> inputProviderTwo() {
        Integer[] input = new Integer[]{11, 12, 13, 12, 16, 14};
        InputList<Integer> list1 = new InputList<>(input);

        OutputList<Integer> expected = new OutputList<>();
        expected.add(12);
        expected.add(13);
        expected.add(14);
        expected.add(14);
        expected.add(null);
        expected.add(null);

        return Stream.of(Arguments.of(list1, expected));
    }

    @ParameterizedTest
    @MethodSource("inputProviderOne")
        //@Test

    void permutationTestOne(InputList<Integer> input, OutputList<Integer> expected) {

        // expected: [17, 23, 9, 23, 23, 23, null]
        PermutationGenerator<Integer> p = new PermutationGenerator<>(input);

        assertEquals(input.size, p.size);

        OutputList<Integer> smallest = p.smallestLargerNumbers();

        assertEquals(smallest.size, expected.size);
        assertEquals(expected.get(0), smallest.get(0));
        assertEquals(expected.get(1), smallest.get(1));
        assertEquals(expected.get(2), smallest.get(2));
        assertEquals(expected.get(3), smallest.get(3));
        assertEquals(expected.get(4), smallest.get(4));
    }

    @ParameterizedTest
    @MethodSource("inputProviderTwo")
        //@Test

    void permutationTestTwo(InputList<Integer> input, OutputList<Integer> expected) {
        PermutationGenerator<Integer> p = new PermutationGenerator<>(input);

        assertEquals(input.size, p.size);

        OutputList<Integer> smallest = p.smallestLargerNumbers();

        assertEquals(smallest.size, expected.size);
        assertEquals(expected.get(0), smallest.get(0));
        assertEquals(expected.get(1), smallest.get(1));
        assertEquals(expected.get(2), smallest.get(2));
        assertEquals(expected.get(3), smallest.get(3));
        assertEquals(expected.get(4), smallest.get(4));
    }
}
