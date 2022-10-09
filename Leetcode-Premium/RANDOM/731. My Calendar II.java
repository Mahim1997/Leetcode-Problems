class MyCalendarTwo {

    private TreeMap<Integer, Integer> map; // Ascending order: Default
    private int limit;
    
    public MyCalendarTwo() {
        this.map = new TreeMap<>((x1, x2) -> (x1 - x2));
        this.limit = 2;
    }
    
    public boolean book(int start, int end) {
        // Book it
        this.map.put(start, this.map.getOrDefault(start, 0) + 1);
        this.map.put(end, this.map.getOrDefault(end, 0) - 1);
        
        // Now, check
        int sum = 0;
        boolean isBroken = false;
        
        // keys() -> .get(key) would require O(n log n) time ??
        for(int val: this.map.values()) {
            sum += val;
            
            if(sum > limit) {
                isBroken = true;
                break;
            }
        }
        
        if(isBroken) {
            // Unbook [reverse the operations]
            this.map.put(start, this.map.get(start) - 1);
            this.map.put(end, this.map.get(end) + 1);
            
            // Remove 0 blocks
            if(this.map.get(start) == 0) {
                this.map.remove(start);
            }
            
            return false;
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */