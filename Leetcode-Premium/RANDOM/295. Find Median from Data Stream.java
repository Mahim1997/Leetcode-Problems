class MedianFinder {
    private PriorityQueue<Integer> leftPQ;  // max heap
    private PriorityQueue<Integer> rightPQ; // min heap
    
    public MedianFinder() {
        this.leftPQ = new PriorityQueue<>((a1, a2) -> (a2 - a1));
        this.rightPQ = new PriorityQueue<>((a1, a2) -> (a1 - a2));
    }
    
    public void addNum(int num) {
        int leftSize = this.leftPQ.size();
        int rightSize = this.rightPQ.size();
        
        if(leftSize == 0) {
            // simply add to left pq
            this.leftPQ.add(num);
        }
        else {            
            int leftPeek = this.leftPQ.peek();
            if(num > leftPeek) { // need to be added in the right side
                this.rightPQ.add(num);
            }
            else { // add in left side
                this.leftPQ.add(num);
            }
        }
        adjust();
    }
    
    private void adjust() {
        int leftSize = this.leftPQ.size();
        int rightSize = this.rightPQ.size();
        int diff = leftSize - rightSize;
        
        if(diff > 1) {
            // left pq has more elements
            int leftPeek = this.leftPQ.poll();
            this.rightPQ.add(leftPeek);
        }
        else if(diff < -1) {
            // right pq has more elements
            int rightPeek = this.rightPQ.poll();
            this.leftPQ.add(rightPeek);
        }
    }
    
    // API
    public double findMedian() {
        int leftSize = this.leftPQ.size();
        int rightSize = this.rightPQ.size();
        int diff = leftSize - rightSize;
        
        if(diff == 0) {
            // even number of elements, need to pick both
            return 0.5 * (double)(this.leftPQ.peek() + this.rightPQ.peek());
        }
        else if(diff > 0) {
            // left has more elements, left peek is median
            return (double) this.leftPQ.peek();
        }
        else {
            // right has more elements, right peek is median
            return (double) this.rightPQ.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */