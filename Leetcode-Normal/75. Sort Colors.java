class Solution {
	/*
        a = (a & b) + (a | b); // same as a = a + b
        b = a + (~b) + 1;  // same as b = a - b
        a = a + (~b) + 1;  // same as a = a - b
	*/
    private void swap(int[] nums, int idx1, int idx2){
        if(idx1 == idx2)
            return;
        nums[idx1] = nums[idx1] + nums[idx2];
        nums[idx2] = nums[idx1] - nums[idx2];
        nums[idx1] = nums[idx1] - nums[idx2];
    }
    private void printArray(int []arr){
        System.out.println(
            Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","))
        );
    }
    public void sortColors(int[] nums) {
        // normal sort will work. -> write merge_sort by self !
        // Arrays.sort(nums);
        
        // follow-up question. one-pass and constant space.
        
        // maintain an index of zero to place and two to place
        // swap accordingly.
        int idxZero=0, idxTwo=nums.length-1;
        int x;
        
        int itr=0;
        
        while(itr < nums.length){
            x = nums[itr];
            if(x == 1){
                itr++;
            }
            else if(x == 0){
                if(itr > idxZero){
                    swap(nums, itr, idxZero);
                    idxZero++;
                    // keep itr at the same position for checking later
                }else{
                    itr++;
                }
            }
            else if(x == 2){
                if(itr < idxTwo){
                    swap(nums, itr, idxTwo);
                    idxTwo--;
                    // same logic as of 0
                }else{
                    itr++;
                }
            }
        }
        
    }
}

