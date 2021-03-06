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
    
    private int trapUsingBruteForce(int []height){
        int heightCumulative = 0;
        for(int i=0; i<height.length; i++){
            int leftMax = getMaxLeft(i, height);
            int rightMax = getMaxRight(i, height);
            int heightBox = Math.min(leftMax, rightMax) - height[i]; // subtract current height
            heightCumulative += heightBox;
        }
        return heightCumulative;
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
    
    private int trapUsing2Pointers(int[] height){
        int left_ptr = 0;
        int right_ptr = height.length - 1;
        int left_max = 0, right_max = 0;
        int cumulative_height = 0;
        while(left_ptr < right_ptr){
            // System.out.println("left_ptr = " + left_ptr + " , right_ptr = " + right_ptr + " , height[l] = " + height[left_ptr] + ", height[r] = " + height[right_ptr] + ", lmax = " + left_max + ", rmax = " + right_max);
            if(height[left_ptr] < height[right_ptr]){ // Smaller one is taken care of first .. since we want Min(leftMax, rightMax)
                // This case for assigning height at left_ptr.
                if(height[left_ptr] > left_max){ 
                    // Checking if maximum, then simply assign.
                    left_max = height[left_ptr];
                }else{ 
        // Else, add to result i.e. remove left_max (the highest of left and right side)
                    cumulative_height += (left_max - height[left_ptr]);
                }
                left_ptr++; // Incrememnt as left-side was better than right-side
            }
            else{ // condition: if(height[left_ptr] < height[right_ptr])
                // This case for assigning height at right_ptr.
                if(height[right_ptr] > right_max){ // Same thing as in left_ptr above.
                    right_max = height[right_ptr];
                }else{
                    cumulative_height += (right_max - height[right_ptr]);
                }
                right_ptr--;
            }
        }
        return cumulative_height;
    }
	
    public int trap(int[] height) {
        if(height.length <= 1) return 0;
		int heightCumulative;
        
        // Brute Force. -> O(n^2)
        // heightCumulative = trapUsingBruteForce(height);
        
        // Prefixes & Suffixes -> O(n) Space, O(n) Time Complexity
        // heightCumulative = trapUsingSuffixAndPrefix(height);
        
		// Using two pointers approach.
		// Min of two monotonically increasing functions -> can use 2 pointers approach.
		heightCumulative = trapUsing2Pointers(height);
		
		return heightCumulative;
    }
}