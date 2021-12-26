class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        // normal maxSumSubarray
        // circular wrap around -> Arrays.sum - minSumSubArray
        // all negative -> get the smallest negative number
        
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        int maxEndingHere = 0;
        int minEndingHere = 0;
        
        int arrSum = 0;
        
        for(int i=0; i<nums.length; i++){
            int num = nums[i];
            
            // take num OR don't take num
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere); // update global max
            
            // take num OR don't take num
            minEndingHere = Math.min(num, minEndingHere + num);
            minSoFar = Math.min(minSoFar, minEndingHere); // update global min
        
            arrSum += num;
        }
        
        if(maxSoFar < 0){
            // this will be all -ve number, as it will be max sub array
            return maxSoFar; 
        }
        
        return Math.max(maxSoFar, arrSum - minSoFar);
    }
}