class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        // certain edge cases
        if(target <= nums[low])
            return low;
        if(target == nums[high])
            return high;
        if(target > nums[high])
            return high+1;
        
        while(low <= high){
            
            int mid = low + (high - low)/2;
            if(nums[mid] == target){
                return mid;
            }
            
            // other check ... not needed
            // if((nums[mid - 1] < target) && nums[mid] > target){
            //     return mid;
            // }
            
            if(nums[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        
        return low;
    }
}