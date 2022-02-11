class Solution {
    public int subarraySum(int[] nums, int k) {
        // Sliding window ? Can't do it due to -ve numbers
        // return usingTwoPointers(nums, k);
        return usingHashMap(nums, k);
    }
    
    private int usingHashMap(int[] nums, int k){
        // key: prefix sum, val: count of that key
        Map<Integer, Integer> mapPrefixSums = new HashMap<>();
        mapPrefixSums.put(0, 1); // 0, appears once, 0:1
        int ans = 0;
        
        int sum = 0;
        for(int num: nums){
            sum += num;
            int diff = sum - k;
            
            // get the difference's count
            ans += mapPrefixSums.getOrDefault(diff, 0);
            
            // increment count of this prefix sum as key
            mapPrefixSums.put(sum, 
                mapPrefixSums.getOrDefault(sum, 0) + 1                 
            );
        }
    
        return ans;
    }
    
    private int usingTwoPointers(int[] nums, int k){
        if(nums.length == 1){
            return (nums[0] == k) ? 1 : 0;
        }
        
        int cnt = 0;
        int n = nums.length;
        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=i; j<n; j++){
                sum += nums[j];
                
                // anywhere is reached
                if(sum == k){
                    cnt++;
                }
                // cant break, as it can be negative numbers
                // [1,2,3,-2,2]
            }
        }
        
        return cnt;
    }
}