class Solution {
    private void reverse(int[] nums, int first, int last){
        // in-place, inclusive
        for(int i=first, j=last; i<=(first+last)/2; i++, j--){
            if(i != j){
                nums[i] = nums[i] + nums[j]; // swap in-place
                nums[j] = nums[i] - nums[j];
                nums[i] = nums[i] - nums[j];                
            }
        }
    }
    private void printArray(int []nums){
        for(int x: nums){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    public void rotate(int[] nums, int k) {
        if(nums.length <= 1)
            return;
        
        // k will be mod.
        k = k % nums.length;
        
        if(k == 0)
            return;
        
        // reverse whole array
        reverse(nums, 0, nums.length-1); // inclusive
        // printArray(nums);
        
        // reverse until k-1 index
        reverse(nums, 0, k-1);
        // printArray(nums);
        
        // reverse from k to end.
        reverse(nums, k, nums.length-1); // inclusive
        // printArray(nums);
        
    }
}