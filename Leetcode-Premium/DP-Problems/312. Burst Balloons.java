class Solution {
    public int maxCoins(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        int n = nums.length + 2;
        int[] newNums = new int[n];
        int[][] dp = new int[n][n];

        newNums[0] = 1;
        newNums[n - 1] = 1;
        for(int i=1; i<=(n - 2); i++){
            newNums[i] = nums[i - 1];
        }
        

        // for(int left=1; left<=(n - 2); left++){
        for(int left=(n-2); left>=1; left--){
            for(int right=left; right<=(n-2); right++){
                dp[left][right] = 0;
                for(int i=left; i<=right; i++){
                    int ans = (newNums[left - 1] * 
                               newNums[i] * 
                               newNums[right + 1]);
                    ans = ans + dp[left][i - 1] + dp[i + 1][right];
                    
                    dp[left][right] = Math.max(
                        dp[left][right],
                        ans
                    );
                }
            }
        }
        
        return dp[1][n-2];
    }
    
}