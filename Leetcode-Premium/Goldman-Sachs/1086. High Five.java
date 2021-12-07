class Solution {
    public int[][] highFive(int[][] items) {
        // int num = 0;
        int idx, sum, count, val;
        
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        
        for(int i=0; i<items.length; i++){
            idx = items[i][0];
            val = items[i][1];
            if(map.containsKey(idx) == false){
                PriorityQueue<Integer> pq = map.get(idx);
                pq = new PriorityQueue<Integer>(5, (Integer x1, Integer x2) -> (x2 - x1));
                map.put(idx, pq);
                keys.add(idx);
            }
            map.get(idx).add(val); // add to priority queue, it will automatically adjust.
        }
        
        int[][] ans = new int[map.size()][2];        
 
        Collections.sort(keys);
        idx = 0;
        for(int key: keys){
            ans[idx][0] = key;
            // ans[idx][1] = indexToSum.get(key)/indexToCount.get(key);
            sum = 0;
            PriorityQueue<Integer> pq = map.get(key);
            count = pq.size();
            int numItems = 0;
            while((numItems < 5) && (pq.isEmpty() == false)){
                // System.out.println("Idx = " + idx + ", pqItem = " + pq.peek());
                sum += pq.poll();
                
                numItems++;
            }
            ans[idx][1] = (numItems == 0) ? 0 :(sum/numItems);
            idx++;
        }
        
        return ans;
    }
}