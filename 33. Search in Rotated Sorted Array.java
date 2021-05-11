class Solution {
    private int getIdxPivot(int[] nums, int left, int right){
        // Check left & right corner cases separately ?
        if(nums[0] > nums[1]) return 0; // left-most is the pivot
        if(nums[nums.length-1] < nums[nums.length-2]) return(nums.length-1);
        
        // initially, left -> 0, right -> end
        while(true){
            // Check if NOT ascending order.[DISTINCT values, no equal checking]
            if(nums[left] > nums[right]){ // move the left to middle.
                left = (left + right)/2;
            }
            if(nums[left] < nums[right]){
                right = (left + right)/2;
            }
            System.out.println("While -> left = " + left + " , right = " + right);
            
            // Breaking condition. [in the middle checking]
            if((left > 0) && (left < nums.length-1)){
                if((nums[left-1]>nums[left]) && (nums[left+1]>nums[left]))
                    break; // found the pivot index ?
            }
            if(left == right) break;
        }
        return left;
    }
    
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1; // invalid case.
        if(nums.length == 1){
            if(nums[0] == target) return 0;
            return -1;
        } // one length case.
        
        // Find the pivot index using binary search.
        int pivot = getIdxPivot(nums, 0, nums.length-1);
        System.out.println("Pivot idx: " + pivot);
        
        // Logically, partition the two sides, and search each side using binary search.
        
        // int find1 = binary_search(nums, 0, pivot);
        // int find2 = binary_search(nums, pivot+1, nums.length-1);
        // return Math.max(find1, find2); // if both are -1, then gives -1
        
        return -1;
    }
}