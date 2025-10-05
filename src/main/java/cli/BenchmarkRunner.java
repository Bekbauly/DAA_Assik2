package cli;

import algorithms.ShellSort;
import algorithms.ShellSort.GapSequence;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Arrays;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        // defaults
        String sequence = "SHELL";
        int n = 10000;
        String distribution = "random";
        int runs = 3;
        String out = "results.csv";

        // simple args parsing: --sequence SHELL --n 10000 --distribution random --runs 3 --out results.csv
        for (int i=0; i<args.length; i++){
            switch(args[i]){
                case "--sequence": sequence = args[++i]; break;
                case "--n": n = Integer.parseInt(args[++i]); break;
                case "--distribution": distribution = args[++i]; break;
                case "--runs": runs = Integer.parseInt(args[++i]); break;
                case "--out": out = args[++i]; break;
                default:
                    System.err.println("Unknown arg: " + args[i]);
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter(out, true));
        PerformanceTracker pt = new PerformanceTracker();
        // write header if file empty - for simplicity always write header
        pw.println(pt.csvHeader());

        for (int run = 0; run < runs; run++){
            int[] arr = generateArray(n, distribution, run);
            // copy array for safety (we do only one sort)
            int[] copy = Arrays.copyOf(arr, arr.length);
            pt.reset();
            long start = System.nanoTime();
            ShellSort.sort(copy, GapSequence.valueOf(sequence), pt);
            long end = System.nanoTime();
            pt.setTimeNs(end - start);
            pw.println(pt.toCsvLine(sequence, distribution, n));
            pw.flush();
            System.out.println("Run " + run + " done: n=" + n + " seq=" + sequence + " dist=" + distribution);
        }

        pw.close();
    }

    private static int[] generateArray(int n, String distribution, int seed){
        Random rnd = new Random(1234 + seed);
        int[] a = new int[n];
        switch (distribution) {
            case "random":
                for (int i=0;i<n;i++) a[i] = rnd.nextInt();
                break;
            case "sorted":
                for (int i=0;i<n;i++) a[i] = i;
                break;
            case "reversed":
                for (int i=0;i<n;i++) a[i] = n - i;
                break;
            case "nearly_sorted":
                for (int i=0;i<n;i++) a[i] = i;
                // small number of swaps
                int swaps = Math.max(1, n/100);
                for (int s=0;s<swaps;s++){
                    int i = Math.abs(rnd.nextInt()) % n;
                    int j = Math.abs(rnd.nextInt()) % n;
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown distribution: " + distribution);
        }
        return a;
    }
}
