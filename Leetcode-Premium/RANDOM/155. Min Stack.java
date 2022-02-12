class MinStack {
    int ptr;
    int[] stack;
    int[] minSoFarArr;
    int LIMIT = 30000 + 1; // 3*10^4
    
    public MinStack() {
        this.ptr = -1;
        
        this.stack = new int[LIMIT];
        this.minSoFarArr = new int[LIMIT];
    }
    
    public void push(int val) {
        this.ptr++;
        
        int prevMin = val;
        if(this.ptr > 0){
            prevMin = Math.min(prevMin,
                this.minSoFarArr[this.ptr - 1]
            );            
        }

        this.stack[this.ptr] = val;
        this.minSoFarArr[this.ptr] = prevMin;
        
    }
    
    public void pop() {
        this.ptr--;
    }
    
    public int top() {
        return this.stack[this.ptr];
    }
    
    public int getMin() {
        return this.minSoFarArr[this.ptr];
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