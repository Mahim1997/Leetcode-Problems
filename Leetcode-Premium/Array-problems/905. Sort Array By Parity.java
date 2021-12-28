class Solution {
    public int[] sortArrayByParity(int[] nums) {
//         int[] ans = new int[nums.length];
        
//         int left = 0, right = nums.length - 1;
//         for(int i=0; i<ans.length; i++){
//             if((nums[i]%2) == 0){ // even
//                 ans[left++] = nums[i];
//             }else{ // odd
//                 ans[right--] = nums[i];
//             }
//         }
        
        // return ans;
        
        int left = 0, right = nums.length - 1;
        for(int i=0; i<nums.length; i++){
            if((nums[i]%2) == 0){ // even
                // swap and increment pointer
                int temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
        
        return nums;
    }
}