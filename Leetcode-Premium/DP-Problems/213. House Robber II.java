class Solution {
    public int rob(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        // H_1, H_2, H_3, ..., H_N ==> H_1 and H_N are adjacent
        // So, H_1 ------- H_N-1  and H_2 ------ H_N should be considered 
        // and max should be taken
        
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        
        max = Math.max(max, robBUCS(nums, 0, n-2));
        max = Math.max(max, robBUCS(nums, 1, n-1));
        
        return max;
    }
    
    // Bottom Up Constant Space
    private int robBUCS(int[] nums, int firstIdx, int lastIdx) {
        int n = lastIdx - firstIdx + 1; // nums.length;
        if(n == 1)
            return nums[firstIdx];
        
        int twoPrev = nums[firstIdx];
        int onePrev = Math.max(nums[firstIdx], nums[firstIdx+1]);
        if(n == 2)
            return onePrev;
        
        int globalMax = 0;
        for(int i=firstIdx+2; i<=lastIdx; i++){
            globalMax = Math.max(onePrev, twoPrev + nums[i]);
            twoPrev = onePrev;
            onePrev = globalMax;
        }
        
        return globalMax;
    }
}