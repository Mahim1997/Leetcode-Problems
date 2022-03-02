class Solution {
    public int furthestBuilding(int[] heights, 
                                int bricks, int ladders) {
        // edge case
        if(heights.length == 1)
            return 0;
        
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // use ladders until pq is filled
        // then remove 'min' elements, and use bricks
        
        for(int i=1; i<heights.length; i++){
            int diff = heights[i] - heights[i - 1];
            
            // no need to use bricks/ladders
            if(diff <= 0)
                continue;
            
            // use ladders until not possible [use ladder initially]
            pq.add(diff);
            
            // check IF THIS WAS INVALID: Update pq
            if(pq.size() > ladders){
                int minEl = pq.remove();                
                bricks -= minEl;
                
                // NO MORE BRICKS ARE POSSIBLE
                if(bricks < 0){
                    return (i - 1);
                }
                
            }
        }
        
        return (heights.length - 1);
    }
}