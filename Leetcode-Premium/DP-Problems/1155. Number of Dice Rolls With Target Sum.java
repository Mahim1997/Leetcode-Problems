class Solution {
    private static int MOD = 1000000000 + 7;
    private static int INVALID = -10000;
    
    private int[][] cache;
    
    private int numFaces;
    
    private void initializeCache(int n, int k, int target) {
        this.cache = new int[n + 1][target + 1];
        for(int[] _1D: this.cache) {
            Arrays.fill(_1D, INVALID);
        }
    }
    
    public int numRollsToTarget(int n, int k, int target) {
        // Branch & Bound, DP
        initializeCache(n, k, target);
        int ans = 0;
        this.numFaces = k;
        
        for(int f=1; f<=k; f++) {        
            ans = (ans + dfs(n - 1, target - f)) % MOD;
        }
        return ans;
    }
    
    private int dfs(int numDiesRemain, int target) {
        // Edge cases & Base cases
        if(target < 0)  return 0; // no counts [Branch-n-Bound]
        
        if((numDiesRemain == 0) && (target == 0))   return 1; // success 
        if(numDiesRemain == 0)  return 0;
        if(target == 0) return 0;
        
        // Check cache
        if(this.cache[numDiesRemain][target] != INVALID)
            return this.cache[numDiesRemain][target];
        
        int ans = 0;
        
        // Recurse
        for(int f=1; f<=this.numFaces; f++) {
            ans = (ans + dfs(numDiesRemain - 1, target - f)) % MOD;
        }
        
        // Update cache & Return
        ans = ans % MOD;
        this.cache[numDiesRemain][target] = ans;
        return ans;
    }
}




