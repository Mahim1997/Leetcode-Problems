class Solution {
    private int[] piles;
    private int numPiles;
    private int UNASSIGNED = -10000;
    
    private int[][] dp;
    
    private void initializeDP(){
        this.dp = new int[this.numPiles][this.numPiles*2];
        for(int[] _1D: dp){
            Arrays.fill(_1D, UNASSIGNED);
        }
    }
    
    public int stoneGameII(int[] piles) {
        this.piles = piles;
        this.numPiles = piles.length;
        
        // initialize DP table
        this.initializeDP();
        
        // 
        int diffAliceBob = dfs(0, 1);   // A - B = dfs(0, 1)
                                        // A + B = sum(piles)
        int sum = 0;
        for(int pile: piles){sum += pile;}
        // A - B + A + B = 2A = dfs(0, 1) + sum(piles)
        // A = 1/2 * (dfs(0, 1) + sum[piles])
        return (diffAliceBob + sum)/2;
    }
    
    private int dfs(int firstIdx, int M){
        // 1<=X<=2M, next time M = max(M, X)
        // System.out.println("Calling for idx = " + firstIdx + ", M = "+M);
        
        
        if(firstIdx >= this.numPiles){
            return 0;
        }
        
        if(this.dp[firstIdx][M] != UNASSIGNED){
            return this.dp[firstIdx][M];
        }
        
        
        int ans = Integer.MIN_VALUE; // need to maximize
        
        int sum = 0;
        for(int x=0; x<(2*M); x++){
            int newIdx = firstIdx + x;
            if(newIdx < this.numPiles){
                sum += this.piles[newIdx];
                int newM = Math.max(x + 1, M); // max(M, X), 1-index
                int currVal = sum - dfs(newIdx + 1, newM);
                ans = Math.max(ans, currVal);
            }
        }
        
        
        // put into map
        this.dp[firstIdx][M] = ans;
    
        
        return ans;
    }
    
    
    
    
    
}