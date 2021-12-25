class Solution {
    private int[] coins;
    // private Map<Integer, Integer> map;
    private int[][] cache;
    
    private void initializeDataStructures(int amount){
        // Initialize cache.
        this.cache = new int[this.coins.length+1][amount + 1];
        for(int i=0; i<this.cache.length; i++){
            for(int k=0; k<this.cache[i].length; k++){
                this.cache[i][k] = 0;
            }
        }
    }
    
    public int change(int amount, int[] coins) {
        // Sort the coins
        Arrays.sort(coins);
        
        // Edge Cases
        if(amount == 0)
            return 1;
        if(amount < coins[0])
            return 0;
        
        this.coins = coins;
        initializeDataStructures(amount);

        // Top Down gives TLE
        // Need bottom-up approach
        return bottomUp(amount);
        
        
//         // int ans = dp(0, amount);
//         // return (ans < 0) ? 0 : ans;
        // return dp(0, amount);
    }
    
    private int bottomUp(int maxAmount){
        // use cache as dp.
        for(int i=0; i<this.cache[0].length; i++){
            this.cache[0][i] = 0; // first row == 0
        }
        for(int i=0; i<this.cache.length; i++){
            this.cache[i][0] = 1; // amount == 0, is 1, first col == 1
        }
        
        // DP.
        for(int coinIdx=1; coinIdx<=this.coins.length; coinIdx++){
            for(int amount=1; amount<=maxAmount; amount++){
                // coins indexed from 0,...,
                int toNotTakeValue = this.cache[coinIdx - 1][amount];

                int toTakeValue = 0;
                int newAmount = amount - this.coins[coinIdx - 1];
                if(newAmount >= 0)
                    toTakeValue = this.cache[coinIdx][newAmount];
                
                this.cache[coinIdx][amount] = toTakeValue + toNotTakeValue;
                
                // this.cache[coinIdx][amount] = 
                    // this.cache[coinIdx][newAmount]      // take
                    // + this.cache[coinIdx - 1][amount];  // don't take
            }
        }
        
        return this.cache[this.cache.length - 1][this.cache[0].length - 1];
    }
    
    private void printCache(){
        for(int i=0; i<this.cache.length; i++){
            for(int j=0; j<this.cache[i].length; j++){
                System.out.print(this.cache[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    // coinIndex -> only can choose coins with idx >= coinIndex
    // can't choose to the left side
    private int dp(int coinIndex, int amount){
        if(amount < 0)
            return 0;
        if(amount == 0)
            return 1;
        if(coinIndex >= this.coins.length) // out of bounds
            return 0;
        
        if(this.cache[coinIndex][amount] != 0){
            return this.cache[coinIndex][amount];
        }

        int current = 0;
        current = dp(coinIndex, 
                    amount-this.coins[coinIndex]) // take the coin
                + dp(coinIndex + 1,  // don't take the coin, increment idx
                            amount);        // amount stays the same
            
        

        this.cache[coinIndex][amount] = current;        
        
        return current;
    }
}