class Solution {
    int[][] memo;
    int[] jobDifficulties;
    int numJobs;
    
    public int minDifficulty(int[] jobDifficulties, int d) {
        int numJobs = jobDifficulties.length;
        if(d > numJobs)
            return -1;
        
        this.jobDifficulties = jobDifficulties;
        this.numJobs = this.jobDifficulties.length;
        this.memo = new int[numJobs][d + 1]; // days => 0,1,2,..,d
        
        
        for(int[] m: this.memo){Arrays.fill(m, -1);}
        
        return dfs(0, d);
    }
    
    private int dfs(int idx, int day){
        // base case
        if(day == 1){
            return getMax(this.jobDifficulties, idx);
        }
        // check inside memo
        if(this.memo[idx][day] != -1)
            return this.memo[idx][day];
        
        // recursion
        int max = 0;
        int ans = Integer.MAX_VALUE;
        for(int cut=idx; cut<(this.numJobs - day + 1); cut++){
            // max so far
            max = Math.max(max, this.jobDifficulties[cut]);
            ans = Math.min(ans, 
                    max + dfs(cut+1, day-1) // recursion call
                );
        }
        
        // put back into memo
        this.memo[idx][day] = ans;
        return ans;
    }
    
    private int getMax(int[] arr, int idx){
        int max = 0;
        for(int i=idx; i<arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}