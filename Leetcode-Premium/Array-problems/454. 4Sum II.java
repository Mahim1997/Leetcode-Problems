class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        
        // place in hashmap
        for(int n1: nums1){
            for(int n2: nums2){
                int sum = n1 + n2;
                map.put(sum, 
                        (map.getOrDefault(sum, 0) + 1)
                );
            }
        }
        
        
        // recover
        for(int n3: nums3){
            for(int n4: nums4){
                int sum = n3 + n4;
                int target = 0 - sum;
                cnt += map.getOrDefault(target, 0);
            }
        }
        
        return cnt;
    }
}