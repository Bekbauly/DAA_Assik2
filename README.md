Shell Sort Algorithm - Report

Student B, Pair 2: Advanced Sorting Algorithms
Algorithm: Shell Sort with Multiple Gap Sequences (Shell, Knuth, Sedgewick)

📋 Algorithm Overview

Shell Sort is an in-place comparison-based sorting algorithm that generalizes insertion sort to allow the exchange of items that are far apart. The algorithm improves on insertion sort by allowing the comparison and exchange of elements that are distant from each other, reducing the amount of shifting needed when compared to the standard insertion sort.

Key Features:

✅ In-place sorting: O(1) auxiliary space.

✅ Multiple gap sequences: Uses Shell, Knuth, and Sedgewick gap sequences to optimize sorting performance.

✅ Improved performance over Insertion Sort: Reduces the number of comparisons and swaps in practice.

✅ Adaptable: You can experiment with different gap sequences for varying performance results.

🧮 Complexity Analysis
Time Complexity:
Case	Complexity	Explanation
Best Case	Θ(n log n)	When the input is nearly sorted, Shell Sort is efficient with small gaps.
Average Case	Θ(n^3/2)	The performance improves with better gap sequences like Knuth or Sedgewick.
Worst Case	Θ(n^2)	For Shell's original gap sequence, the worst case performance is quadratic.
Why Θ(n^2) in the worst case?

Build heap: O(n) using the bottom-up approach.

Gap sequence: Performance depends on the sequence chosen. For Shell, the worst case is O(n^2), but for Knuth and Sedgewick, it can be much better.

Space Complexity:

Auxiliary Space: Θ(1) - sorts in-place.

Total Space: Θ(n) - input array only.

🚀 Quick Start
Prerequisites:

Java 17 or higher

Maven 3.6+

Git

Build and Run:
# Clone repository
git clone <repository-url>
cd assignment2-shellsort

# Build project
mvn clean compile

# Run tests
mvn test

# Run benchmarks
mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner"

# Create JAR
mvn package
java -jar target/assignment2-shellsort-1.0.0.jar

🚀 Programmatic Usage
Example Code:
import algorithms.ShellSort;
import metrics.PerformanceTracker;

public class Example {
    public static void main(String[] args) {
        // Basic usage
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        ShellSort.sort(arr, ShellSort.GapSequence.SHELL);
        // arr is now sorted: [11, 12, 22, 25, 34, 64, 90]

        // With performance tracking
        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, ShellSort.GapSequence.KNUTH, tracker);

        tracker.startTiming();
        ShellSort.sort(arr, ShellSort.GapSequence.SEDGEWICK, tracker);
        tracker.stopTiming();

        tracker.printMetrics();
        // Outputs: comparisons, swaps, execution time, memory usage
    }
}

📊 CLI and Benchmarking
Quick Benchmark with Default Sizes:
java -cp target/assignment2-shellsort-1.0.0.jar cli.BenchmarkRunner

Create JAR:
mvn package
java -jar target/assignment2-shellsort-1.0.0.jar

🧪 Performance Tracking and Benchmark Results
Performance Metrics:

The algorithm tracks the following metrics:

Comparisons

Swaps

Array Accesses

Execution Time (in ns and ms)

CSV Data Example:
sequence,distribution,n,time_ns,time_ms,comparisons,swaps,array_accesses
SHELL,random,10000,10703500,10,703500,266695,151724,658429
SHELL,random,10000,1919500,1,919500,264830,149879,654719
SHELL,random,10000,1861000,1,861000,263092,148136,651238
sequence,distribution,n,time_ns,time_ms,comparisons,swaps,array_accesses
SHELL,random,10000,10595500,10,595500,266695,151724,658429
SHELL,random,10000,2287800,2,287800,264830,149879,654719
SHELL,random,10000,1762900,1,762900,263092,148136,651238
sequence,distribution,n,time_ns,time_ms,comparisons,swaps,array_accesses
KNUTH,random,10000,10175200,10,175200,229065,158208,537759
KNUTH,random,10000,1560700,1,560700,244184,173201,567871
KNUTH,random,10000,1403800,1,403800,239350,168444,558280
sequence,distribution,n,time_ns,time_ms,comparisons,swaps,array_accesses
SEDGEWICK,random,10000,9853100,9,853100,195625,107364,489363
SEDGEWICK,random,10000,1847400,1,847400,196538,108285,491197
SEDGEWICK,random,10000,1267900,1,267900,198103,109876,494353

📊 Benchmark Graphs

From the CSV data, we can generate the following performance graphs:

Time vs Input Size

Comparisons vs Input Size

Swaps vs Input Size

These graphs help visualize how the algorithm performs as the input size grows.

🧑‍💻 Unit Tests

The implementation is tested through various cases, including:

Empty array

Single element

Sorted array

Reversed array

Array with duplicates

Example Test Cases:
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
    int[] a = new int[]{1, 2, 3, 4, 5, 6};
    PerformanceTracker pt = new PerformanceTracker();
    ShellSort.sort(a, ShellSort.GapSequence.SEDGEWICK, pt);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, a);
}

📢 Conclusion

Shell Sort provides efficient O(n log n) sorting performance with in-place memory usage.

Performance Tracking helps identify key metrics, such as comparisons, swaps, and execution time.

The algorithm performs well across different distributions, including sorted, reversed, and nearly sorted arrays.
