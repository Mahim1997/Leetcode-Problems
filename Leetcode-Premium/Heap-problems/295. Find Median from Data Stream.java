class MedianFinder {
    private PriorityQueue<Integer> leftPQ;
    private PriorityQueue<Integer> rightPQ;
    
    public MedianFinder() {
        // NEED TO make sure left side all elements <= right side all
        this.leftPQ = new PriorityQueue<>((x1, x2) -> (x2 - x1));
        
        // min heap
        this.rightPQ = new PriorityQueue<>(); // max heap
    }
    
    public void addNum(int num) {
        int leftSize = this.leftPQ.size();
        int rightSize = this.rightPQ.size();
        
        int diff = Math.abs(leftSize - rightSize);
        
        // need to maintain property of 
        // left side all elements <= right side all elements
        if(diff == 0){
            // empty checking
            if(rightSize == 0){
                this.leftPQ.add(num);
                return;
            }
            if(leftSize == 0){
                this.rightPQ.add(num);
                return;
            }
            
            // check where to add
            if(num <= this.leftPQ.peek()){
                this.leftPQ.add(num);
            }else{
                this.rightPQ.add(num);
            }
        }else{
            // size issue
            if(this.leftPQ.size() > this.rightPQ.size()){
                // left heap has higher size, need to push to right
                if(num > this.leftPQ.peek()){
                    this.rightPQ.add(num);
                }
                else{
                    int leftItem = this.leftPQ.remove();
                    this.leftPQ.add(num);
                    this.rightPQ.add(leftItem);
                }               
            }
            else{
                // need to push to left
                if(num < this.rightPQ.peek()){
                    this.leftPQ.add(num);
                }
                else{
                    int rightItem = this.rightPQ.remove();
                    this.rightPQ.add(num);
                    this.leftPQ.add(rightItem);
                }
                
            }
                
        }
        
    }
    
    public double findMedian() {
        int totalSize = this.leftPQ.size() + this.rightPQ.size();
        // System.out.println("totalSize = " + totalSize);
        // System.out.println("leftPQ: " + leftPQ 
        //                    + ", rightPQ: " + rightPQ);
        
        if(totalSize%2 == 0){
            // even
            return (double)(this.leftPQ.peek() + this.rightPQ.peek())/(2.0);
        }else{
            // odd
            if(this.leftPQ.size() > this.rightPQ.size()){
                return this.leftPQ.peek();
            }else{
                return this.rightPQ.peek();
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */