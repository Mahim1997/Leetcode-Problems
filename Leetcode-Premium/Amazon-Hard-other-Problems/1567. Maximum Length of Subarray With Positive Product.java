class Solution {
    // at max, 2 passes => O(2n)
    // [leftBound, rightBound] inclusive
    private int getLengthWindow(int[] nums, int leftBound, int rightBound) {
        int numNegatives = 0, firstNegativeIdx = -1, lastNegativeIdx = -1;
        
        for(int i=leftBound; i<=rightBound; i++) {
            int num = nums[i];
            if(num < 0) {
                if(firstNegativeIdx == -1) {
                    firstNegativeIdx = i;
                }
                else {
                    lastNegativeIdx = i;
                }
                numNegatives++;
            }
        }
        
        int window = rightBound - leftBound + 1; // whole window 
        if((numNegatives%2) == 0) { // even
            return window; // inclusive
        }
        
        lastNegativeIdx = (lastNegativeIdx == -1) ? firstNegativeIdx : lastNegativeIdx;
        int leftDistance = firstNegativeIdx - leftBound + 1;
        int rightDistance = rightBound - lastNegativeIdx + 1;
        
        int minDistance = Math.min(leftDistance, rightDistance);
        return (window - minDistance);
    }
    
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int bestWindow = 0;
        
        while((left < n) && (right < n)) {
            if((right == (n - 1)) || (nums[right] == 0)) {
                int rightBoundary = (nums[right] == 0) ? right - 1 : right;
                bestWindow = Math.max(
                    bestWindow, 
                    getLengthWindow(nums, left, rightBoundary) // skip once due to 0
                );
                
                // move to new window without zeros
                left = right + 1;
                while((left < n) && (nums[left] == 0)) {
                    left++;
                }
                right = left;
            }
            else {            
                right++;
            }
        }
        // [1,-2,-3,4,0,1,2,3,4,-2,-1,0,0,0,0,0,0,1,-1,2,30]
        return bestWindow;
    }
}