class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        
        // EDGE CASES
        if(n <= 1)
            return true;
        if(nums[0] == 0)
            return false;
                
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        for(int i=0; i<(n - 1); i++){ // don't compute last element
            int num = nums[i];
            // all elements to the right upto & including MAX JUMP
            for(int j=0; j<=num; j++){ 
                if((i + j) < n){ // within bounds
                    dp[i + j] = dp[i];        
                }
            }
        }
        
        return dp[n - 1];
    }
}