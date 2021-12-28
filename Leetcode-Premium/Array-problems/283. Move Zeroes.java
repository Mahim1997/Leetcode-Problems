class Solution {
    public void moveZeroes(int[] nums) {
        int idx = 0, temp;
        for(int i=0; i<nums.length; i++){
            int currentNum = nums[i];
            if(currentNum != 0){
                nums[idx++] = nums[i];
            }
        }
        for(int i=idx; i<nums.length; i++){
            nums[i] = 0; // insert zeros at the end
        }
    }
}

// temp = nums[i];
// nums[i] = nums[idx];
// nums[idx] = temp;
// idx++;