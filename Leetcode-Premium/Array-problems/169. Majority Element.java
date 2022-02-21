class Solution {

// Boyer - Moore Algorithm
// Should have majority element, otherwise will fail

    public int majorityElement(int[] nums) {
        // Linear Time
        // O(1) extra space
        int cnt = 0;
        int res = nums[0];
        if(nums.length == 1)
            return res;
        
        for(int num: nums){
            if(cnt == 0)
                res = num;
            
            if(num == res)
                cnt++;
            else
                cnt--;
        }
        return res;
        
        // HashMap -> O(n) worst case space
        // return usingHashMap(nums);
    }
    
    private int usingHashMap(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = nums.length;
        for(int key: map.keySet()){
            if(map.get(key) > (n/2))
                return key;
        }
        return -1;
    }
}