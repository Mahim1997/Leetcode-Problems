class Solution {
    public int coinChange(int[] coins, int amount) {
        // return bottomUpApproach(coins, amount);
        return topDownApproach(coins, amount);
    }
    
    private int topDownApproach(int[] coins, int amount){
        if(amount < 1){
            return 0;
        }
        int[] cache = new int[amount];
        return recursiveFunction(coins, amount, cache);
    }
    
    private int recursiveFunction(int[] coins, int currentAmount, int[] cache){
        
        // Base cases.
        if(currentAmount < 0){
            return -1; // stop, exceeded amount.
        }
        if(currentAmount == 0){
            return 0; // stop, found correct amount.
        }
        
        // Check if already solution exists, and return.
        if(cache[currentAmount - 1] != 0){
            return cache[currentAmount - 1];
        }
        
        // Check for each coin.
        int min = Integer.MAX_VALUE;
        for(int coin: coins){
            int res = recursiveFunction(coins, currentAmount - coin, cache);
            if(res >= 0 && res < min){
                // if the returned result is a better result.
                min = 1 + res;
            }
        }
        
        // Update in cache.
        cache[currentAmount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return cache[currentAmount - 1];
    }
    
    private int bottomUpApproach(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        
        Arrays.sort(coins); // sort coin ordering, to maintain bottom-up DP.
        // Bottom-up
        
        // DP[0] = 0
        // DP[i] = min(for all coins c, 1 + DP[i - c.value])
        
        // Initialize everything with -1.
        Arrays.fill(dp, -1);
        
        dp[0] = 0; // for amount = 0, 0 coins are needed
        int minAmount = Integer.MAX_VALUE;
        int tempAmount = 0;
        int prevAmount = 0;
        
        for(int currentAmount=1; currentAmount<=amount; currentAmount++){
            minAmount = Integer.MAX_VALUE;
                
            for(int coin: coins){
                if(coin <= currentAmount){
                    // tempAmount = 1 + dp[currentAmount - coin];
                    prevAmount = dp[currentAmount - coin];
                    if(prevAmount == Integer.MAX_VALUE){
                        tempAmount = Integer.MAX_VALUE;
                    }else{
                        tempAmount = 1 + prevAmount;
                    }
                    minAmount = Math.min(minAmount, tempAmount);
                }else{
                    break; // no need to explore further coins.
                }
            }
            dp[currentAmount] = minAmount;
        }
        
        // for(int x: dp){
        //     System.out.print(x + ", ");
        // }
        // System.out.println("");
        
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}