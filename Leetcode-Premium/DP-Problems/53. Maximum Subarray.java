class Solution {
    public int maxSubArray(int[] nums) {
        // DP -> O(n) time, O(n) space
        // return dpLinearTimeLinearSpace(nums);
        return kadanesAlgorithm(nums);
    }
    private int kadanesAlgorithm(int[] nums){
        int globalMax = Integer.MIN_VALUE;
        int maxCurrent = 0;
        
        for(int num: nums){
            // take OR don't take num
            maxCurrent = Math.max(num, num+maxCurrent);
            globalMax = Math.max(globalMax, maxCurrent);
        }
        
        return globalMax;
    }
    
    private int dpLinearTimeLinearSpace(int[] nums){
        if(nums.length == 1)
            return nums[0];
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSoFar = dp[0];
        
        for(int i=1; i<nums.length; i++){
            // to take i_th element OR not to take i_th element
            int sumWith_i = dp[i - 1] + nums[i];
            int only_i = nums[i];
            dp[i] = Math.max(sumWith_i, only_i);
            maxSoFar = Math.max(dp[i], maxSoFar);
        }
        
        return maxSoFar;
    }
}