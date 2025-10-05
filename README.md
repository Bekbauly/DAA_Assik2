Assignment 2 – Advanced Sorting Algorithms

Student A: Galymzhan Bekbauly
Algorithm: Shell Sort (Shell, Knuth, and Sedgewick gap sequences)

1 Algorithm Overview

Shell Sort is an improved version of Insertion Sort that allows exchanging elements that are far apart.
It works by sorting elements at a certain gap distance apart, then gradually reducing the gap until it becomes 1.
This improves efficiency for medium and large arrays.

Implemented gap sequences:

Shell’s sequence – n/2, n/4, …, 1

Knuth’s sequence – (3^k - 1) / 2

Sedgewick’s sequence – hybrid sequence with theoretical O(n^(4/3)) performance

2 Complexity Analysis
Case	Time Complexity	Space Complexity
Best	Ω(n log n)	O(1)
Average	Θ(n^1.25)	O(1)
Worst	O(n²)	O(1)

Shell Sort is in-place, requiring no additional memory beyond a few variables.

3 Experimental Results
Sequence	n	Avg Time (ms)	Comparisons	Swaps	Array Accesses
SHELL	10,000	4	263,000	150,000	650,000
KNUTH	10,000	3	239,000	168,000	560,000
SEDGEWICK	10,000	2	197,000	109,000	490,000

Note: The values above are the average of three runs on random data.
Detailed raw results are available in docs/results.csv.

4 Analysis

Based on the benchmark results:

Sedgewick’s gap sequence achieved the best performance — fastest execution and fewest operations.

Knuth’s sequence performed slightly slower but still much better than the original Shell’s.

Shell’s sequence had the highest time and operation count.

These findings match the theoretical predictions: better-designed gap sequences reduce both time and cache misses.

5 Repository Structure
assignment2-shellsort/
├── src/
│   ├── main/java/
│   │   ├── algorithms/ShellSort.java
│   │   ├── metrics/PerformanceTracker.java
│   │   └── cli/BenchmarkRunner.java
│   └── test/java/
│       └── algorithms/ShellSortTest.java
├── docs/
│   └── results.csv
├── pom.xml
└── README.md

6 Conclusion

Among the three tested gap sequences, Sedgewick’s sequence provided the best balance of speed and efficiency.
The empirical measurements confirm the theoretical complexity analysis.
This implementation demonstrates how gap optimization can significantly improve sorting performance.

📎 7. References

Shell, D.L. (1959). A High-Speed Sorting Procedure. Communications of the ACM.

Knuth, D.E. (1998). The Art of Computer Programming, Vol. 3: Sorting and Searching.

Sedgewick, R. (1986). Analysis of Shellsort and Related Algorithms.

8 How to Run

To run benchmarks from the command line:

mvn compile
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="--sequence SEDGEWICK --n 10000"
