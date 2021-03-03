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
    
    private int trapUsingSuffixAndPrefix(int[] height){
        int heightCumulative = 0;
        
        // Using Prefixes & Suffixes array
        int num_elements = height.length;
        
        int[] prefix_sum = new int[num_elements];
        int[] suffix_sum = new int[num_elements];
        
        // Compute prefixes and suffixes
        prefix_sum[0] = height[0];
        for(int i=1; i<num_elements; i++){
            prefix_sum[i] = Math.max(prefix_sum[i-1], height[i]);
        }
        
        suffix_sum[num_elements - 1] = height[num_elements - 1];
        for(int i=num_elements-2; i>=0; i--){
            suffix_sum[i] = Math.max(suffix_sum[i+1], height[i]);
        }
        
        // Compute heights.
        for(int i=0; i<height.length; i++){
            int leftMax = prefix_sum[i];
            int rightMax = suffix_sum[i];
            int heightBox = Math.min(leftMax, rightMax) - height[i];
            heightCumulative += heightBox;
        }
        
        return heightCumulative;
    }
    
    public int trap(int[] height) {
        if(height.length <= 1) return 0;
        
        // Brute Force. -> O(n^2)
        int heightCumulative = 0;
        // for(int i=0; i<height.length; i++){
        //     int leftMax = getMaxLeft(i, height);
        //     int rightMax = getMaxRight(i, height);
        //     int heightBox = Math.min(leftMax, rightMax) - height[i]; // subtract current height
        //     heightCumulative += heightBox;
        // }
        
        // Prefixes & Suffixes -> O(n) Space, O(n) Time Complexity
        heightCumulative = trapUsingSuffixAndPrefix(height);
        return heightCumulative;
    }
}