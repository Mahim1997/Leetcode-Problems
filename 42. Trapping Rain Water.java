class Solution {
    private int getMaxLeft(int idx, int []arr){
        int max = arr[idx];
        for(int i=idx; i>=0; i--){
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    private int getMaxRight(int idx, int []arr){
        int max = arr[idx];
        for(int i=idx; i<arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    public int trap(int[] height) {
        if(height.length <= 1) return 0;
        
        // Brute Force. -> O(n^2)
        int heightCumulative = 0;
        for(int i=0; i<height.length; i++){
            int leftMax = getMaxLeft(i, height);
            int rightMax = getMaxRight(i, height);
            int heightBox = Math.min(leftMax, rightMax) - height[i]; // subtract current height
            heightCumulative += heightBox;
        }
        
        
        return heightCumulative;
    }
}