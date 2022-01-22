class Solution {
    private int numPiles;
    
    private int ALICE = 0;
    private int BOB = 1;
    private int UNASSIGNED = -10000000;
    private int NULL_VALUE = -20000000;
    
    private int[] piles;
    
    private int[][][] dp;
    
    private int getNewPlayer(int player){
        return (player == ALICE) ? BOB: ALICE;
    }
    
    private void initializeDP(){
        this.dp = new int[this.numPiles][this.numPiles][2];
        for(int[][] _2D: dp){
            for(int[] _1D: _2D){
                Arrays.fill(_1D, UNASSIGNED);
            }
        }
    }
    
    public boolean stoneGame(int[] piles) {
        this.numPiles = piles.length;
        int first = 0;
        int last = this.numPiles - 1;
        int startPlayer = ALICE;
        this.piles = piles;
        
        this.initializeDP();
    
        int aliceMaxPoints = Math.max(
                dfs(1, this.numPiles-1, ALICE), // choose 'f'
                dfs(0, this.numPiles-2, ALICE)  // choose 'l'
        );
        
        int sum = 0;
        for(int x: piles){sum += x;}
        // System.out.println(aliceMaxPoints);
        
        return (aliceMaxPoints > sum/2);
    }
    
    
    private int dfs(int first, int last, int playerType){
        // System.out.println("cALLING first = " + first + ", last = " + last + ", playerType = " + playerType);
        
        // base cases
        if(first == last){
            // System.out.println("RETURN ... first=last, first="+first);
            return this.piles[first];
        }
        if(first > last){ // should not reache
            return 0;
        }
        
        // check map
        if(this.dp[first][last][playerType] != UNASSIGNED)
            return this.dp[first][last][playerType];
        
        
        // recursion
        int ans = -1;
        int newPlayer = getNewPlayer(playerType);
        int takeFirst = dfs(first+1, last, newPlayer) 
                            + this.piles[first];
        int takeLast = dfs(first, last-1, newPlayer)
                            + this.piles[last];
        
        if(playerType == ALICE){
            // minimize
            ans = Math.min(takeFirst, takeLast);
        }
        else if(playerType == BOB){
            // maximize
            ans = Math.max(takeFirst, takeLast);
        }

        
        // put into map
        this.dp[first][last][playerType] = ans;
        
        // System.out.println("for first = " + first + ", last = " + last + ", ans = " + ans);
        
        // return the ans.
        return ans;
    }
    
    
    
    
    
    
    
    
}