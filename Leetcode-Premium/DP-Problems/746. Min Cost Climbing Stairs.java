class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // int[] dp = new int[cost.length + 1];
        // dp[0] = 0;
        // dp[1] = 0;
        int dp_i_minus_1 = 0;
        int dp_i_minus_2 = 0;
        int ans = 0;
        
        // last + 1 -> final stair
        for(int i=2; i<cost.length + 1; i++){
            ans = Math.min(dp_i_minus_1 + cost[i - 1], dp_i_minus_2 + cost[i - 2]);
            dp_i_minus_2 = dp_i_minus_1;
            dp_i_minus_1 = ans;
        }
        return ans;
        
        
        // for(int i=2; i<dp.length; i++){
            // dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        // }
        
        // return dp[dp.length - 1];
    }
}