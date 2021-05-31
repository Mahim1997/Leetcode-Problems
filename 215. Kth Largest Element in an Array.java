class Solution {
    public int findKthLargest(int[] nums, int k) {
        /* // kth largest element -> maintain Min Heap of k size i.e. remove k smallest elements every time a new element comes in.
        // no need to check distinct.
        // no need to maintain a hashset
        */
        
        // root will always be the min. element.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // auto min heap ?
        
        for(int x: nums){
            
            
            if(minHeap.size() > k){
                // check if number is smaller than minimum, then dont do anything
                // optimization
                if((!minHeap.isEmpty()) && (x < minHeap.peek())){continue;}
                minHeap.poll();
                minHeap.add(x);
            }else{            
                minHeap.add(x);
            }
        }        
        

        
        int nElementsRemove = minHeap.size() - k;
        
        for(int i=0; i<nElementsRemove; i++){
            minHeap.poll();
        }
        
        // now, top should be the answer ?

        
        
        return minHeap.peek();
        
    }
}