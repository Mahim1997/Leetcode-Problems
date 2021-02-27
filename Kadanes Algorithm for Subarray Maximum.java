class Solution {
    public int maxSubArray(int[] nums) { // Using Kadane's Algorithm
        if(nums.length == 1) return nums[0];
    
        // Using Kadane's Algorithm
        int prev_sum = nums[0], max_sum = nums[0];
        for(int i=1; i<nums.length; i++){
            int curr_max_sum = Math.max(nums[i], /*Only this number*/
                                       prev_sum + nums[i]); /*this number + prevBestSum*/
            
            max_sum = Math.max(max_sum, curr_max_sum); // update the max sum
            prev_sum = curr_max_sum; // update the previous iteration's sum
        }
        
        return max_sum;
    }
}