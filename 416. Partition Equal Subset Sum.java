class Solution {
    private int getSum(int[] nums){
        int sum = 0;
        for(int x: nums){
            sum += x;
        }
        return sum;
    }
    
    public boolean canPartition(int[] nums) {
        // return bottomUpDP(nums);
        return topDownMemoization(nums);
    }
    
    private boolean topDownMemoization(int[] nums){
        int target = getSum(nums);
        if((target % 2) != 0){
            return false;
        }
        target /= 2; // divide by 2 for partition.
        int[] cache = new int[target + 1]; // 0_th index -> contains
        // 1 -> TRUE, 0 -> FALSE, -1 -> unassigned
        Arrays.fill(cache, -1);
        cache[0] = 1; // target == 0 is always possible.
        
        return recursion(nums, 0, target, cache);
        // return cache[target];
    }
    
    private boolean recursion(int[] nums, int startIdx, int currentTarget, int[] cache){
        // Base cases.
        if(currentTarget < 0){
            return false; // exceeded the requirement.
        }
        if(currentTarget == 0){
            return true; // reached a solution.
        }
        
        // Check cache
        if(cache[currentTarget] != -1){ // does exist in cache.
            return (cache[currentTarget] == 1) ? true : false;
        }
        
        
        for(int i=startIdx; i<(nums.length); i++){
            int x_i = nums[i];
            // boolean takeWithout_x_i = recursion(nums, i+1, currentTarget, cache); 
            // gonna be the same as with previous index.
        
            boolean takeWith_x_i = recursion(nums, i+1, currentTarget - x_i, cache);
            cache[currentTarget] = (takeWith_x_i == true) ? 1 : 0; // update cache.
            if(takeWith_x_i == true){
                return true; // one solution is reached
            }
        }
        
        // None of the sets are found.
        return false;
        
    }
    
    private boolean bottomUpDP(int[] nums){
        // partition -> one subset should have SUM//2
        int target = getSum(nums);
        
        if((target % 2) != 0){
            return false; // can't be odd
        }
        
        target = target / 2; // should be half
        
        Set<Integer> setOfSums = new HashSet<>();
            
        setOfSums.add(0); // 0 -> don't take anything.
        
        int x_i, newSum;
        
        for(int i=0; i<nums.length; i++){
            x_i = nums[i];
            // DON'T take x_i -> same set.
            // Take x_i -> ADD x_i to everything in the set.
            Set<Integer> newSetofSums = new HashSet<>();
            for(int element: setOfSums){newSetofSums.add(element); } // copy previous set.
            for(int element: setOfSums){ // can't iterate while modifying.
                newSum = x_i + element;
                if(newSum == target){
                    return true;
                }
                newSetofSums.add(newSum);
            }
            
            setOfSums = newSetofSums; // assign for the next iteration.
        }
        
        // Check if newSetofSums contains.
        return setOfSums.contains(target);
    }
}