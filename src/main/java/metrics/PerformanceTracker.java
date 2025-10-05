package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long timeNs = 0;

    public void addComparison(){ comparisons++; }
    public void addComparisons(long k){ comparisons += k; }
    public void addSwap(){ swaps++; }
    public void addSwaps(long k){ swaps += k; }
    public void addArrayAccess(){ arrayAccesses++; }
    public void addArrayAccesses(long k){ arrayAccesses += k; }

    public void setTimeNs(long ns){ timeNs = ns; }
    public long getComparisons(){ return comparisons; }
    public long getSwaps(){ return swaps; }
    public long getArrayAccesses(){ return arrayAccesses; }
    public long getTimeNs(){ return timeNs; }

    public void reset(){
        comparisons = 0; swaps = 0; arrayAccesses = 0; timeNs = 0;
    }

    public String csvHeader(){
        return "sequence,distribution,n,time_ns,time_ms,comparisons,swaps,array_accesses";
    }

    public String toCsvLine(String sequence, String distribution, int n){
        double timeMs = getTimeNs() / 1_000_000.0;
        return String.format("%s,%s,%d,%d,%.6f,%d,%d,%d",
                sequence, distribution, n, getTimeNs(), timeMs,
                getComparisons(), getSwaps(), getArrayAccesses());
    }
}
