import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import permutationGenerator.InputList;
import permutationGenerator.OutputList;
import permutationGenerator.PermutationGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermutationGeneratorTest {

    @Test
    void handoutExampleSmallestAndLargestSmaller() {
        Integer[] input = new Integer[]{1, 0, 6, 4, 2, 3, 5};
        InputList<Integer> list = new InputList<>(input);
        PermutationGenerator<Integer> p = new PermutationGenerator<>(list);

        OutputList<Integer> smallest = p.smallestLargerNumbers();

        assertEquals(7, smallest.size);
        assertEquals(Integer.valueOf(2), smallest.get(0));
        assertEquals(Integer.valueOf(2), smallest.get(1));

        assertNull(smallest.get(2));

        assertEquals(Integer.valueOf(5), smallest.get(3));
        assertEquals(Integer.valueOf(3), smallest.get(4));
        assertEquals(Integer.valueOf(5), smallest.get(5));

        assertNull(smallest.get(6));

        OutputList<Integer> largest = p.largestSmallerNumbers();

        assertEquals(7, largest.size);
        assertEquals(Integer.valueOf(0), largest.get(0));

        assertNull(largest.get(1));

        assertEquals(Integer.valueOf(5), largest.get(2));
        assertEquals(Integer.valueOf(3), largest.get(3));

        assertNull(largest.get(4));
        assertNull(largest.get(5));
        assertNull(largest.get(6));
    }

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
