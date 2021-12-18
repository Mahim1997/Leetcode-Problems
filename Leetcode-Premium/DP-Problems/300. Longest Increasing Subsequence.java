class Solution {
    public int lengthOfLIS(int[] nums) {
        // directly bottom-up
        
        // return bottomUp(nums);
        Map<Integer, Integer> cache = new HashMap<>();
        int ret = dp(nums.length-1, nums, cache);
        // retrieve the max from cache.
        int max = 1;
        for(int key: cache.keySet()){
            int val = cache.get(key);
            max = Math.max(max, val);
        }
        return max;
    }
    
    private void printArray(int[] dp){
        for(int x: dp){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private int bottomUp(int[] nums){
        int[] dp = new int[nums.length];
        
        // Base case
        for(int i=0; i<dp.length; i++){
            dp[i] = 1; // initially all are 1.
        }

        // Recursion case
        for(int i=1; i<nums.length; i++){
            // start with dp[i] = dp[i - 1]
            for(int k=0; k<i; k++){
                if(nums[i] > nums[k]){
                    dp[i] = Math.max(dp[i], (dp[k] + 1)); 
                }
            }
            // System.out.println("After index i = " + i);
            // printArray(dp);
        }
        

        
        // get the max.
        int max = 1;
        for(int x: dp)
            max = Math.max(max, x);
        
        return max;
        
    }
    
    private int dp(int endIdx, int[] nums, Map<Integer, Integer> cache){
        // Base case.
        if(endIdx == 0) // initially, can take only that element.
            return 1;
        
        // contains that key.
        if(cache.containsKey(endIdx) == true)
            return cache.get(endIdx);
        
        /*
            dp(i) = 1 // assume NOT take, so ONLY THIS
            for j = i-1 to 0: // backward iteration
                if(nums[i] > nums[j])
                    dp(i) = max(dp(i), dp(j) + 1) // increment by 1
        */
        
        int max = 1; 
        for(int j=endIdx-1; j>=0; j--){
            // take with 'i' index as the last element, and THEN choose endIdx.
            // compare with immediately previous one.
            int j_value = dp(j, nums, cache);
            if(nums[endIdx] > nums[j])
                max = Math.max(max, j_value + 1); // increment by 1
            // else
            //     max = Math.max(max, j_value); // no increment by 1
        }
        
        // put into cache and return.
        cache.put(endIdx, max);
        
        return max;
    }
    
}