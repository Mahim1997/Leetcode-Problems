class Solution {
    private int MOD = 1000000000 + 7;
    private Map<Integer, Integer> cache;
    
    public int countOrders(int n) {
        // base case
        
        // this.cache = new HashMap<>();
        // return dp(n - 1); // 0-indexed
        return bottomUpSpaceOptimized(n - 1); // 0-indexed
    }
    
    private int bottomUpSpaceOptimized(int n){
        if(n==0){return 1;}
        long ans = 1;
        
        for(int i=1; i<=n; i++){
            ans = ans * getCurrentWays(i);
            ans = ans % MOD;
            
            // System.out.println("i = " + i + ", ans = " + ans);
        }
        
        return (int) ans;
    }
    
    private long getCurrentWays(int n){
        // gaps = 2(n - 1) + 1 ==> n = 2, gaps = 2*1 + 1 = 3
        
        /*      gaps + (gaps - 1) + (gaps - 2) + ... [gaps times]
            =   gaps*gaps - (1 <= k <= [gaps - 1])[SUM(k)]
            =   gaps*gaps - gaps*(gaps + 1)/2
            =   (gaps*(gaps - 1))/2
        */
        long gaps = (long)(2*n) + 1;
        long ways = (gaps * (gaps + 1))/2;
        return ways;
    }
    
    private int dp(int n){
        // base case
        if(n == 0){return 1;}
        
        // cache check
        if(this.cache.containsKey(n)){
            return this.cache.get(n);
        }
        
        
        // recursive case
        long currentWays = getCurrentWays(n);
        
        // System.out.println("currentWays = " + currentWays 
        //                    + ", n = " + n);
        
        long ans = 0;
        ans = dp(n - 1) * currentWays;
        ans = ans % MOD;
        
        this.cache.put(n, (int) ans);
        return (int) ans;
    }
}