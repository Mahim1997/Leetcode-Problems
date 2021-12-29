class Solution {
    public int rob(int[] nums) {
        // Map<Integer, Integer> cache = new HashMap<>();
        // return dp(nums, nums.length - 1, cache);
    
        return bottomUp(nums);
    }

    private int bottomUpConstantSpace(int[] nums){
        int n = nums.length;
        if(n == 1)
            return nums[0];
        
        int twoPrev = nums[0];
        int onePrev = Math.max(nums[0], nums[1]);
        if(n == 2)
            return onePrev;
        
        int ans = 0;
        for(int i=2; i<n; i++){
            ans = Math.max(onePrev, twoPrev + nums[i]);
            twoPrev = onePrev;
            onePrev = ans;
        }
        
        return ans;
    }
    
    private int bottomUp(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];        
        // base cases
        if(nums.length == 1){
            return nums[0];
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        // iteration
        for(int i=2; i<n; i++){
            // int numsIdx = i - 1; // nums is from 0 indexing
            int robIdxHouse = dp[i - 2] + nums[i];
            int dontRobIdxHouse = dp[i - 1] + 0;
            dp[i] = Math.max(robIdxHouse, dontRobIdxHouse);
        }
        
        return dp[n - 1];
    }
    
    
    private int dp(int[] nums, int idx, Map<Integer, Integer> cache){
        // base cases.
        if(idx == 0){ // first house -> rob it.
            return nums[idx];
        }
        if(idx == 1){ // two houses  -> rob the max one.
            return Math.max(nums[idx], nums[idx - 1]);
        }
        
        // cache checking -> update if false
        if(cache.containsKey(idx) == false){           
            // rob the house -> dp(idx - 2) + nums[idx]
            int robIdxHouse = dp(nums, idx-2, cache) + nums[idx];
            // don't rob the house -> dp(idx - 1)
            int dontRobIdxHouse = dp(nums, idx-1, cache) + 0;
            int answer = Math.max(robIdxHouse, dontRobIdxHouse);
            
            cache.put(idx, answer);
        }
        
        // System.out.println("Calling for idx = " + idx + ", printing map");
        // printMap(cache);
        
        return cache.get(idx);
    }

    private void printMap(Map<Integer, Integer> map){
        for(int key: map.keySet()){
            System.out.println("Key: " + key + ", val = " + map.get(key));
        }
    }
}