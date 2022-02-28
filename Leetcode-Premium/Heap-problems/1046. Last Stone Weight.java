class Solution {
    public int lastStoneWeight(int[] stones) {
        // max heap
        PriorityQueue<Integer> pq;
        pq = new PriorityQueue<>((v1, v2) -> (v2 - v1));
        
        // add all initially
        for(int stone: stones)
            pq.add(stone);
        
        // System.out.println("Printing pq: " + pq + " size = " + pq.size());
        
        // edge case; only one element
        if(pq.size() == 1)
            return pq.peek();
        
        while(pq.size() > 1){
            // System.out.println("HERE");
            int top = pq.remove();
            int secondTop = pq.remove();
            
            // System.out.println("top = " + top 
            //                    + ", sTop = " + secondTop);
            // equal -> ignore, else top-secondTop add
            if(top != secondTop){
                int newWeight = top - secondTop;
                pq.add(newWeight);
                // System.out.println("Adding newWeight = " + newWeight);
            }
        }
        
        return (pq.isEmpty() == false) ? pq.peek() : 0;
    }
}