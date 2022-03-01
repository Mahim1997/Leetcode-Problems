class Solution {
    public int connectSticks(int[] sticks) {
        int ans = 0;
        
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // O(n log n) for insertion
        for(int stick: sticks){
            pq.add(stick);
        }
        
        int top, secondTop, newCost;
        while(pq.size() > 1){
            top = pq.remove();
            secondTop = pq.remove();
            newCost = top + secondTop;
            
            pq.add(newCost);
            ans += newCost;
        }
        
        
        return ans;
    }
}