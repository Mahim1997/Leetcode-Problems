class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0, right = nums.length - 1;
        
        // start filling up from the last index of result array.
        for(int i=ans.length-1; i>=0; i--){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                // pick the left element.
                ans[i] = nums[left]*nums[left];
                left++;
            }else{
                // pick the right element.
                ans[i] = nums[right]*nums[right];
                right--;
            }
        }
        
        return ans;
    }
}