
package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    public void testEmpty() {
        int[] a = new int[0];
        PerformanceTracker pt = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SHELL, pt);
        assertEquals(0, a.length);
    }

    @Test
    public void testSingle() {
        int[] a = new int[]{42};
        PerformanceTracker pt = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.KNUTH, pt);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    public void testSorted() {
        int[] a = new int[]{1,2,3,4,5,6};
        PerformanceTracker pt = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SEDGEWICK, pt);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
    }

    @Test
    public void testReverse() {
        int[] a = new int[]{6,5,4,3,2,1};
        PerformanceTracker pt = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.SHELL, pt);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
    }

    @Test
    public void testDuplicates() {
        int[] a = new int[]{5,1,3,1,5,1,2};
        PerformanceTracker pt = new PerformanceTracker();
        ShellSort.sort(a, ShellSort.GapSequence.KNUTH, pt);
        int[] expect = a.clone();
        java.util.Arrays.sort(expect);
        assertArrayEquals(expect, a);
    }
}
