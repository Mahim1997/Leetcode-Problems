// Brute Force
class Solution {
    public int maxArea(int[] height) {
        if(height.length <= 1) return height[0];
        
        int max_area = -10000;
        for(int i=0; i<height.length; i++){
            for(int j=1; j<height.length; j++){
                int area = (j - i) * Math.min(height[j], height[i]);
                if(area > max_area){
                    max_area = area;
                }
            }
        }   
        return max_area;
    }
}

// O(n) two pointers approach.

class Solution {
    public int maxArea(int[] height) {
        if(height.length <= 1) return height[0];
        
        int max_area = -10000;
        
        int left_ptr = 0;
        int right_ptr = height.length - 1;
        
        while(left_ptr < right_ptr){
            int area = (right_ptr - left_ptr) * 
                        Math.min(height[left_ptr], height[right_ptr]);
            // System.out.println("area = " + area);
            max_area = Math.max(max_area, area);
            if (height[left_ptr] > height[right_ptr]){
                right_ptr--; // keep the max height one constant, move the other one
            }else{
                left_ptr++;
            }
        
        }
        
        
        return max_area;
    }
}