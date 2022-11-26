class Job {
    int start;
    int end;
    int profit;

    public Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "[" + this.start + "," + this.end + ";" + this.profit + "]";
    }
}

class Solution {
    private Job[] jobs;
    private static int INVALID = -1;
    private Map<Integer, Integer> cache;

    private boolean isConflicting(Job job1, Job job2) {
        return ((job1.end > job2.start) || (job2.end > job1.start));
    }

    // Use binary search for now
    private int getNextNonConflictingJobIdx(int index) {
        int left = index + 1, right = jobs.length - 1;
        if(index == right) { 
            return INVALID; 
        }

        Job jobCurrent = jobs[index];
        int ans = INVALID;
        while(left <= right) {
            int mid = left + (right - left)/2;
            Job jobMid = jobs[mid];

            if(jobMid.start >= jobCurrent.end) {
                // Can move left
                ans = mid;
                right = mid - 1;
            }
            else {
                // Need to move right
                left = mid + 1;
            }
        }

        return ans;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // Data preparation
        int n = startTime.length;
        this.cache = new HashMap<>();
        this.jobs = new Job[n]; 
        for(int i=0; i<n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // Sort by start times Ascending order ?
        Arrays.sort(
            jobs, 
            (j1, j2) -> (
                (j1.start == j2.start) 
                    ? (j1.end - j2.end) 
                    : (j1.start - j2.start)
            )
        );

        // System.out.println("DEBUG. printing nextJobIndices");
        // for(int i=0; i<jobs.length; i++) {
        //     System.out.println("For idx: " + i + ", next job idx: " + getNextNonConflictingJobIdx(i));
        // }

        return dfs(0);       
    }


    private int dfs(int startIdx) {
        // System.out.println("Running for idx: " + startIdx);

        // Base case
        if((startIdx == INVALID) || (startIdx >= jobs.length)) {
            return 0;
        }

        // Cache check
        if(this.cache.containsKey(startIdx)) {
            return this.cache.get(startIdx);
        }

        // Recursive cases
        int maxProfit = 0;

        int nextJobIdx = getNextNonConflictingJobIdx(startIdx);
        int profitWithCurrent = jobs[startIdx].profit + dfs(nextJobIdx);
        int profitWithoutCurrent = 0 + dfs(startIdx + 1);
        maxProfit = Math.max(profitWithCurrent, profitWithoutCurrent);

        // Cache update
        this.cache.put(startIdx, maxProfit);

        // Return
        return maxProfit;
    }
}
