class KthLargest {
    
    // kth largest ==> reverse logic, min heap
    private PriorityQueue<Integer> pq;
    private int K;
    
    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        this.K = k;
        
        for(int num: nums){
            this.add(num);
        }
    }
    
    public int add(int val) {
        if(this.pq.size() < this.K){
            // normally add
            this.pq.add(val);
        }
        else{
            // adjust, only when larger than TOP is found
            if(val > this.pq.peek()){
                // remove & then add
                this.pq.remove();
                this.pq.add(val);
            }
        }
        
        return this.pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */