class KthLargest {

    PriorityQueue<Integer> pq;
    private int k;
    
    public int add(int x){
        if(this.pq.size() >= k){
            if(x > this.pq.peek()){ // optimization version
                this.pq.poll();
                this.pq.add(x);
            }
        }else{            
            this.pq.add(x);
        }
        
        return this.pq.peek();
    }
    
    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>(k);
        this.k = k;
        
        Arrays.stream(nums)
            .forEach(x -> add(x));
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */