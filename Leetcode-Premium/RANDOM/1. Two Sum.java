class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        int n = nums.length;
        
        // Nested Loop.
        // for(int i=0; i<(n - 1); i++){
        //     for(int j=i+1; j<n; j++){
        //         if((nums[i] + nums[j]) == target){
        //             ans[0] = i;
        //             ans[1] = j;
        //             return ans;
        //         }
        //     }    
        // }
        
        // Hash Map; key = num, val = idx
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            int otherVal = target - nums[i];
            if(map.containsKey(otherVal)){
                int idx = map.get(otherVal);
                ans[0] = idx;
                ans[1] = i;
                return ans;
            }
            map.put(nums[i], i);
        }
        
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }
}