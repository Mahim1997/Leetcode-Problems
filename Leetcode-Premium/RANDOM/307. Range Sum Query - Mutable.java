class NumArray {
    private int[] arr;
    private int[] st;
    private int n;
    
    private int NULL_VALUE = 0;
    
    private int ceilOfLog2(int n) {
        double logBase2 = Math.log(n) / Math.log(2);
        return (int)Math.ceil(logBase2);
    }
    
    public NumArray(int[] nums) {
        this.arr = nums;
        this.n = nums.length;

        // int smallestPowerOf2 = (int)Math.ceil(Math.log(2*n - 1));
        int smallestPowerOf2 = ceilOfLog2(2 * n - 1);
        int size = (int)Math.pow(2, smallestPowerOf2);
        
        this.st = new int[size];
        Arrays.fill(this.st, NULL_VALUE);
        constructST(0, 0, this.n - 1);
    }
    
    // construct
    private int constructST(int si, int l, int r) {
        if(l == r) {
            // leaf node, simply need to return
            this.st[si] = this.arr[l];
            return this.st[si];
        }
        
        // recursion
        int mid = l + (r - l)/2;
        int left    = constructST(2*si + 1, l, mid);
        int right   = constructST(2*si + 2, mid + 1, r);
        int sum = left + right;
        this.st[si] = sum;
        return sum;
    }
    
    private boolean isNoOverlap(int sl, int sr, int l, int r) {
        return ((sr < l) || (sl > r));
    }
    
    private boolean isFullOverlap(int sl, int sr, int l, int r) {
        return (l <= sl) && (r >= sr);
    }
    
    private int getSum(int si, int sl, int sr, int l, int r) {
        // Case of full overlap; return st node
        if(isFullOverlap(sl, sr, l, r)) {
            return this.st[si];
        }
        
        // Case of no overlap; return null value
        if(isNoOverlap(sl, sr, l, r)) {
            return NULL_VALUE;
        }   
        
        // Else: Case of partial overlap, recursion
        int mid = sl + (sr - sl)/2;
        int left    = getSum(2*si + 1, sl, mid, l, r);
        int right   = getSum(2*si + 2, mid + 1, sr, l, r);
        return (left + right);
    }
    
    private void updateST(
        int si, int sl, int sr, 
        int position, int diff
    ) {
        // No overlap, ignore
        if(isNoOverlap(sl, sr, position, position)) return;
        // if((sr < position) || (sl > position))  return;

        this.st[si] += diff;

        // One single position, ignore, as it is already filled
        if(sl != sr) {        
            int mid = sl + (sr - sl)/2;
            updateST(2*si + 1, sl, mid, position, diff);
            updateST(2*si + 2, mid + 1, sr, position, diff);
        }
    }
    
    // update
    public void update(int index, int val) {
        // update array
        int diff = val - this.arr[index];
        this.arr[index] = val;
        
        // update st
        updateST(0, 0, this.n - 1, index, diff);
    }
    
    // query
    public int sumRange(int left, int right) {
        return getSum(0, 0, this.n - 1, left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */