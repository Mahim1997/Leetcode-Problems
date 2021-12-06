class Solution {
    private void printArray(int[] nums){
        System.out.println(Arrays.stream(nums)
                   .mapToObj(String::valueOf)
                   .collect(Collectors.joining(",")));
    }
	
    public int firstMissingPositive(int[] nums) {
        
        // Check if '1' exists, otherwise return '1'
        // Preprocess to make all x<0 and x>=nums.length to '1' since '1' idx will be invalidated either way
        // Index mapping starts from [1..nums.length]
        boolean doesOneExist = false;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 1){
                doesOneExist = true;
            }
            else if((nums[i] <= 0) || (nums[i] > nums.length)){
                nums[i] = 1; // set to '1'
            }
        }
        // since '1' doesn't exist, this will be the first missing positive
        if(doesOneExist == false){return 1;}
        
        // printArray(nums);
        // Now, mark as -ve any number -> no need to worry about cases of exceeding index, since each value is 1
        int x, idxMap;
        for(int i=0; i<nums.length; i++){
            x = nums[i];
            idxMap = Math.abs(x) - 1; // [2,3,1,1,1] -> [2,-3,1,1,1,] -> so, need to take abs.
            if(nums[idxMap] > 0){
                nums[idxMap] = -nums[idxMap];
            } // else it is already negative, so, no need to do again. [won't be duplicates]
        }
        
        // printArray(nums);
        
        // Check which first idx is +ve
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                return (i+1); // idxMap starts with 1
            }
        }
        
    
        return nums.length+1; // all the array size is exhausted -> One extra len is the ans.
    }
}