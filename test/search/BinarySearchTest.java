package search;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BinarySearchTest {
    private static int solution(final int[] array, final int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= element) {
                return i;
            }
        }
        return array.length;
    }

    private void test(final int[] array, final int element) {
        System.err.println("Searching " + element + " in " + Arrays.toString(array) + ".");
        final int iterActual = BinarySearch.iterativeSearch(array, element);
        final int recActual = BinarySearch.recursiveSearch(array, element);
        final int expected = solution(array, element);
        Assert.assertEquals("Iterative search error", expected, iterActual);
        Assert.assertEquals("Recursive search error", expected, recActual);
    }

    private final int[] SIZES = new int[]{1, 2, 3, 4, 5, 6, 10, 20, 50, 100, 300};

    @Test
    public void testManual() {
        test(new int[]{}, 0);
        test(new int[]{0}, 0);
        test(new int[]{0, 0}, 0);
        test(new int[]{0, 0, 0, 0}, 0);
        test(new int[]{0, 0}, -1);
        test(new int[]{0, 0}, 1);
        test(new int[]{0, 0, 2, 2}, -1);
        test(new int[]{0, 0, 2, 2}, 0);
        test(new int[]{0, 0, 2, 2}, 1);
        test(new int[]{0, 0, 2, 2}, 2);
        test(new int[]{0, 0, 2, 2}, 3);
    }
    private final int[] BOUNDS = new int[]{1, 2, 3, 4, 5, 10, 15, 20, 100, 10000, Integer.MAX_VALUE / 2};
    private final Random random = new Random(2304985623087764549L);

    @Test
    public void testRandom() {
        for (final int size : SIZES) {
            for (final int bound : BOUNDS) {
                for (int i = 0; i < 10; i++) {
                    final int origin = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE - bound);
                    final int value = random.nextInt(origin, origin + bound);
                    final int[] array = random.ints(size, origin, origin + bound).sorted().toArray();
                    test(array, value);
                }
            }
        }
    }
}