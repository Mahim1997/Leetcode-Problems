class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
		// out of bounds check to improve time complexity for some cases
		if((target < nums[left]) || (target > nums[right]))
			return -1;
		
        while(left <= right){
            mid = left + (right - left)/2; // avoid overflow
            if(nums[mid] == target){
                return mid;
            }
            if(target < nums[mid]){ // need to check the left side
                right = mid - 1;
            }else{ // need to check right side
                left = mid + 1;
            }
        }
        
        return -1;
    }
}

/*
[-1,0,5,9,12]
5
[-1,0,5,9,12]
-1
[-1,0,5,9,12]
12
[-1,0,5,9,12]
0
[-1,0,5,9,12,13]
0
[-1,0,5,9,12,13]
13
[-1,0,5,9,12,13]
-1
*/