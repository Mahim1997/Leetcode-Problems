class MinStack {
    int[] arr = new int[30000 + 1]; // 3*10^4 at max
    int[] minElements = new int[30000 + 1]; // for O(1) lookup of min elements as well
    int idx;
    /** initialize your data structure here. */
    public MinStack() {
        this.idx = 0;
        Arrays.fill(minElements, Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        arr[idx] = val;
        if(idx == 0)
            minElements[idx] = val;
        else if (idx > 0){
            minElements[idx] = Math.min(minElements[idx-1], val);
        }
        
        idx++;
    }
    
    public void pop() {
        if(idx >= 0){
            idx--;
        }
    }
    
    public int top() {
        if(idx > 0){
            return arr[idx - 1];        
        }else{
            return -1;
        }
    }
    
    public int getMin() {
        if(idx > 0){
            return minElements[idx - 1];        
        }else{
            return -1;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */