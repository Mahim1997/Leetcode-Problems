class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n == 0){
            return new int[]{-1, -1};
        }
        // binary search once to get the first idx
        int idx = binarySearchNormal(nums, target, 0, n-1);
        if(idx == -1){ // not found even once
            return new int[]{-1, -1};
        }
        
        // binary search second time to get leftmost
        int idxLeft = binarySearchLeft(nums, target, 0, idx);
        
        // binary search third time to get rightmost
        int idxRight = binarySearchRight(nums, target, idx, n-1);
        
        return new int[]{idxLeft, idxRight};
    }
    
    private int binarySearchRight(int[] nums, int target, int low, int high){
        int left = low, right = high;
        
        while(left <= right){
            int mid = left + (right - left)/2;
            
            // edge cases
            if(nums[right] == target){
                return right;
            }
            if(left == (right - 1)){
                return left;
            }
            // edge case of 1 element
            if(left == right){
                return left;
            }
            
            if(nums[mid] == target){
                left = mid;
            }
            else{
                // only nums[mid] > target is possible
                right = mid - 1;
            }
        }
        
        // should not reach this case
        return -1;
    }
    
    private int binarySearchLeft(int[] nums, int target, int low, int high){
        int left = low, right = high;
        
        while(left <= right){
            if(left == right){
                return left;
            }
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                // search left side [inclusive]
                right = mid;
            }else{
                // nums[mid] < target ONLY possible
                // search right side [exclusive]
                left = mid + 1;
            }
        }
        
        // should not reach this case
        return -1;
    }
    
    private int binarySearchNormal(int[] nums, int target, int low, int high){
        int left = low, right = high;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                // search the left side
                right = mid - 1;
            }
            else{
                // search the right side
                left = mid + 1;
            }
        }
        
        return -1;
    }
}