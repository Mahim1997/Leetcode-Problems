class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int globalMax = Integer.MIN_VALUE;
        int currentMax = 0;
        
        for(int num: nums){
            switch(num){
                case 1:
                    currentMax++;
                    break;
                case 0:
                    currentMax = 0;
                    break;
                    
            }
            globalMax = Math.max(globalMax, currentMax);
        }
        
        return globalMax;
    }
}