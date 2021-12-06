class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        
        // compute sum
        for(int i=1; i<nums.length; i++){
            if(nums[i] != 0){
                nums[i] = nums[i-1] + nums[i];
            }
        }
        
        // get max
        int max = 0;
        for(int i=0; i<nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        
        
        return max;
    }
}