class Solution {
    public int coinChange(int[] coins, int amount) {
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