class Solution {
    public int removeDuplicates(int[] nums) {
        // already sorted
        
        if(nums.length == 1){
            return 1;
        }
        
        // two pointers ?
        
        int numCurrentCounts = 1;
        int idxFill = 1;
        
        // start with i = 1
        for(int i=1; i<nums.length; i++){
            numCurrentCounts++;
            // System.out.println("Before, i = " + i + ", idxFill = " 
            //                   + idxFill + ", numCurrentCounts = "
            //                   + numCurrentCounts);
            if(nums[i - 1] != nums[i]){
                nums[idxFill] = nums[i]; // DON'T SWAP
                numCurrentCounts = 1;
                idxFill++;
            }else{
                // equal as previous element
                if(numCurrentCounts <= 2){
                    nums[idxFill] = nums[i];
                    idxFill++;
                }
            }
            
        }
        
        return idxFill;
    }
}