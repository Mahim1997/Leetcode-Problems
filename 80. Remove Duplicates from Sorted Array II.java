class Solution {

    public int removeDuplicates(int[] nums) {
        if(nums.length <= 1)
            return nums.length;

        int indexUnique = 0; // unique num index
        int cnt = 0; // start with 1
        
        
        for(int i=0; i<nums.length; i++){
            
            if((i==0) || (nums[i] == nums[i-1])){ // previous === current
                cnt++;
            }else{
                cnt = 1; // start with 1
            }
            
            if(cnt <= 2){
                // dont swap as it will lead to consecutive different numbers
                nums[indexUnique] = nums[i]; 
                indexUnique++;
            }
        }
        
        return indexUnique;
    }
}