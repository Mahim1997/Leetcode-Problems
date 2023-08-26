class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + this.start + "," + this.end + ")";
    }
}

class Solution {
    private boolean isMergePossible(
        Interval interval1, 
        Interval interval2
    ) {
        return (interval1.end < interval2.start);
    }
 
    public int findLongestChain(int[][] pairs) {
        // Create intervals data structure
        int numIntervals = pairs.length;
        Interval[] intervals = new Interval[numIntervals];
        for(int i=0; i<pairs.length; i++) {
            int[] pair = pairs[i];
            intervals[i] = new Interval(pair[0], pair[1]);
        }

        // Sort wrt start time ascending order
        // Arrays.sort(intervals, (a, b) -> 
        //     ((a.start != b.start) ? (a.start - b.start) : (a.end - b.end))
        // );

        // Sort wrt end times ascending 
        Arrays.sort(intervals, (a, b) -> 
            ((a.end != b.end) ? (a.end - b.end) : (a.start - b.start))
        );

        // System.out.println("Intervals sorted: " + Arrays.toString(intervals));

        // cache
        int[] dp = new int[numIntervals];
        Arrays.fill(dp, 0);

        // First one is always `1`
        dp[0] = 1;

        // traversal
        int maxIntervalChain = dp[0];
        for(int i=1; i<numIntervals; i++) {
            for(int j=i-1; j>=0; j--) { // backward first ?
                if(isMergePossible(intervals[j], intervals[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxIntervalChain = Math.max(maxIntervalChain, dp[i]);
                }
            }
        }

        return maxIntervalChain;
    }
}