class Solution {
    public int rob(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>();
        return dp(nums, nums.length - 1, cache);
    }
    
    private void printMap(Map<Integer, Integer> map){
        for(int key: map.keySet()){
            System.out.println("Key: " + key + ", val = " + map.get(key));
        }
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
}