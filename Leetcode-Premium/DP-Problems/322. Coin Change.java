class Solution {
    public int coinChange(int[] coins, int amount) {
        // sorting ? NOT NEEDED
        // Map<Integer, Integer> map = new HashMap<>();
        // int ans = dp(coins, amount, map);
        // return (ans == Integer.MAX_VALUE) ? -1 : ans;
        
        return bottomUp(coins, amount);
    }
    
    private int bottomUp(int[] coins, int amount){
        if(amount == 0){
            return 0;
        }
        Arrays.sort(coins);
        int[] dp = new int[amount + 1]; // exactly equal to amount can be used.
        
        // base cases.
        for(int currentAmount=1; currentAmount<=amount; currentAmount++){
            dp[currentAmount] = Integer.MAX_VALUE;
        }
        for(int coin: coins){
            if(coin <= amount){
                dp[coin] = 1; // only 1 coin is enough
            }
        }
        
        // recursive cases.
        for(int i=1; i<=amount; i++){
            int min = Integer.MAX_VALUE;
            for(int coin: coins){
                if(i >= coin){
                    min = Math.min(min, dp[i - coin]);
                    if(min != Integer.MAX_VALUE){
                        dp[i] = min + 1;
                    }
                }
            }
        }
        
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
    
    private int dp(int[] coins, int currentAmount, Map<Integer, Integer> map){
        // base cases.
        if(currentAmount == 0){
            return 0;
        }
        if(currentAmount < 0){
            return Integer.MAX_VALUE; // minimize -> use reverse case.
        }
        
        if(map.containsKey(currentAmount) == false){
            int min = Integer.MAX_VALUE;
            for(int coin: coins){
                // if((currentAmount - coin) >= 0)
                min = Math.min(min, dp(coins, currentAmount - coin, map));
            }
            if(min != Integer.MAX_VALUE)
                map.put(currentAmount, min + 1);
            else
                map.put(currentAmount, Integer.MAX_VALUE); // invalid ans
        }
        return map.get(currentAmount);
    }
    
}