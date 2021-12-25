class Solution {
    public int maxSubArray(int[] nums) {
        // DP -> O(n) time, O(n) space
        // return dpLinearTimeLinearSpace(nums);
        return kadanesAlgorithm(nums);
    }
    private int kadanesAlgorithm(int[] nums){
        if(nums.length == 1)
            return nums[0];
        
        int currentSubArrSum = nums[0];
        int maxSoFar = nums[0];
        
        for(int i=1; i<nums.length; i++){
            // currentSubArrSum -> updates for _i_th element
            int onlyNum = nums[i];
            int takeSubArray = currentSubArrSum + nums[i];
            int bestSubArr_i = Math.max(onlyNum, takeSubArray);
            currentSubArrSum = bestSubArr_i;
            
            maxSoFar = Math.max(maxSoFar, currentSubArrSum);
        }
        return maxSoFar;
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