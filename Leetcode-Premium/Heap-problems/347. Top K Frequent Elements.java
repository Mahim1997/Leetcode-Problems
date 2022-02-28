class Solution {
    private Map<Integer, Integer> getCountMap(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        // maintain counts
        Map<Integer, Integer> counts = getCountMap(nums);
        
        // top 'k' elements, with MOST counts
        
        // construct min heap, reverse logic
        // int[] ==> [0]: value, [1]: count
        PriorityQueue<int[]> pq = new PriorityQueue<>((v1, v2) -> (v1[1] - v2[1]));
        
        for(int key: counts.keySet()){
            int val = counts.get(key);
            
            int[] arr = {key, val};
            
            // within size, simply add
            if(pq.size() < k){
                pq.add(arr);
            }else{
                // adjust accordingly
                int[] top = pq.peek();
                if(val > top[1]){
                    // if current number's count > existing MIN count, then remove & add
                    pq.remove();
                    pq.add(arr);
                }
            }
        }
        
        // Time: O(n) counting + O(k log n) for THIS style of heaping
        // TOP elements are the top 'k' frequent
        
        int[] ans = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()){
            ans[idx++] = pq.remove()[0]; // [0] is the value/number &&& [1] is the count
        }
        
        return ans;
    }
}