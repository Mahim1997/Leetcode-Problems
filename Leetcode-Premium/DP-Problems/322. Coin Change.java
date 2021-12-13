class Solution {
    public int coinChange(int[] coins, int amount) {
        // sorting ? NOT NEEDED
        if(amount == 0)
            return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int ans = dp(coins, amount, map);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
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