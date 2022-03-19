class FreqStack {
    private Map<Integer, List<Integer>> mapAllCounts;
    private Map<Integer, Integer> mapValueCount;
    private int maxCount;
    
    public FreqStack() {
        this.mapAllCounts = new HashMap<>();
        this.mapValueCount = new HashMap<>();
        this.maxCount = 0;
    }
    
    public void push(int val) {
        int currentCount = this.mapValueCount.getOrDefault(val, 0) + 1;
        this.mapValueCount.put(val, currentCount);
        this.maxCount = Math.max(this.maxCount, currentCount);
        
        // Now, adjust the 2D map
        List<Integer> list;
        
        // Linked List to easily remove tail
        list = this.mapAllCounts.getOrDefault(currentCount,
                                            new LinkedList<>());
        
        list.add(val); // add to the end
        
        // put back to map
        this.mapAllCounts.put(currentCount, list);
    }
    
    public int pop() {
        // System.out.println("Popping, printing this.mapValueCount => "
        //   + this.mapValueCount + ", maxCount = " + this.maxCount
        //   + ", mapAllCounts => " + this.mapAllCounts);
        
        List<Integer> list = this.mapAllCounts.get(this.maxCount);
        int lastElement = list.get(list.size() - 1);
        
        // System.out.println("lastElement = " + lastElement);
        
        // remove last element
        list.remove(list.size() - 1);

        // update map
        this.mapAllCounts.put(this.maxCount, list);
        
        // if list is empty, then decrement count
        // decrement maxCount
        if(list.size() == 0){
            this.maxCount--;
        }
        
        
        
        // decrement count of this current element 
        // [WILL ALWAYS be present] due to pop before push NOT available
        this.mapValueCount.put(lastElement, 
                  this.mapValueCount.get(lastElement) - 1);
        
        
        return lastElement;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */