// src/main/java/algorithms/ShellSort.java
package algorithms;

import metrics.PerformanceTracker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShellSort {

    public enum GapSequence { SHELL, KNUTH, SEDGEWICK }

    public static void sort(int[] a, GapSequence seq, PerformanceTracker tracker){
        if (a == null) return;
        int n = a.length;
        List<Integer> gaps = generateGaps(n, seq);
        for (int gap : gaps) {
            // gapped insertion sort
            for (int i = gap; i < n; i++) {
                int temp = a[i];
                tracker.addArrayAccess(); // read a[i]
                int j = i;
                while (j >= gap) {
                    tracker.addComparison();
                    tracker.addArrayAccess(); // read a[j-gap] for comparison
                    if (a[j - gap] > temp) {
                        a[j] = a[j - gap];
                        tracker.addArrayAccess(); // write a[j]
                        tracker.addSwap(); // count shift as swap-like operation
                        j -= gap;
                    } else {
                        break;
                    }
                }
                a[j] = temp;
                tracker.addArrayAccess(); // write
            }
        }
    }

    private static List<Integer> generateGaps(int n, GapSequence seq){
        List<Integer> gaps = new ArrayList<>();
        if (n <= 1) { gaps.add(1); return gaps; }
        switch (seq) {
            case SHELL:
                int gap = n / 2;
                while (gap > 0) {
                    gaps.add(gap);
                    gap = gap / 2;
                }
                break;
            case KNUTH:
                int h = 1;
                while (h < n/3) h = 3*h + 1;
                while (h > 0) {
                    gaps.add(h);
                    h = (h - 1) / 3;
                }
                break;
            case SEDGEWICK:
                // Sedgewick 1986 sequence (common form)
                int k = 0;
                while (true) {
                    long g;
                    if (k % 2 == 0) {
                        g = 9L * (1L << k) - 9L * (1L << (k/2)) + 1;
                    } else {
                        g = 8L * (1L << k) - 6L * (1L << ((k + 1)/2)) + 1;
                    }
                    if (g >= n) break;
                    gaps.add((int) g);
                    k++;
                    if (k > 60) break; // safety
                }
                if (gaps.isEmpty()) gaps.add(1);
                Collections.sort(gaps, Collections.reverseOrder());
                break;
        }
        return gaps;
    }
}
