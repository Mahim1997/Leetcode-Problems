class Solution {
    private int[][] cache;
    private static int NULL_VALUE = -1000;
    
    private int[] jobs;
    
    private int[] suffixMax;
    
    // Gets max starting from idx, all the way to end
    private int getMax(int[] arr, int idx) {
        int max = arr[idx];
        for(int i=idx; i<arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        int numJobs = jobDifficulty.length;

        // Edge case: Not possible
        if(numJobs < d)
            return -1;
        
        this.cache = new int[numJobs][d + 1];
        for(int[] arr: this.cache) {
            Arrays.fill(arr, NULL_VALUE);
        }
        
        this.suffixMax = new int[numJobs];
        int max = Integer.MIN_VALUE;
        for(int i=numJobs-1; i>=0; i--) {
            max = Math.max(jobDifficulty[i], max);
            this.suffixMax[i] = max;
        }
        
        this.jobs = jobDifficulty;
        
        return dfs(0, d);
    }
    
    private int dfs(int startIdx, int numDaysRemain) {
        // Base case
        if(numDaysRemain == 1) {
            // Only one partition, so, get the max & return it
            // return getMax(this.jobs, startIdx);
            return this.suffixMax[startIdx];
        }
        
        // Cache check
        if(this.cache[startIdx][numDaysRemain] != NULL_VALUE) {
            return this.cache[startIdx][numDaysRemain];
        }
        
        int takeMin = Integer.MAX_VALUE;
        int max = this.jobs[startIdx];
        
        for(int i=startIdx; i<(this.jobs.length - numDaysRemain + 1); i++) {
            max = Math.max(max, this.jobs[i]); // Update max
            
            int local = dfs(i + 1, numDaysRemain - 1); // get new dfs call
            takeMin = Math.min(takeMin, max + local); // Update required takeMin
        }
        
        this.cache[startIdx][numDaysRemain] = takeMin;
        return takeMin;
    }
}

