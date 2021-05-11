class Solution {
    private int getIdxPivot(int[] nums, int left, int right){
        while(left < right){
            int mid = (left + right)/2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
    
    public int findMin(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        // pivot is the smallest element.
        return nums[getIdxPivot(nums, 0, nums.length - 1)];
    }
}