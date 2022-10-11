// For sums
class SegmentTree {
    private int[] tree;
    private int[] nums;
    
    private static int DEFAULT = 0;
    
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[4 * nums.length + 1];
        Arrays.fill(this.tree, DEFAULT);
        
        buildTree(0, 0, nums.length - 1);
    }
    
    private int getMid(int left, int right) {
        return (left + (right - left)/2);
    }
    
    private int[] getChildrenIdx(int idx) {
        return new int[]{2*idx + 1, 2*idx + 2};
    }
    
    private void buildTree(int treeIdx, int left, int right) {
        // Base case
        if(left == right) {
            this.tree[treeIdx] = this.nums[left];
            return;
        }
        
        // Recursive cases
        int[] childrenIdx = getChildrenIdx(treeIdx);
        int mid = getMid(left, right);
        
        buildTree(childrenIdx[0], left, mid);
        buildTree(childrenIdx[1], mid + 1, right);
        
        // Merge results
        this.tree[treeIdx] = this.tree[childrenIdx[0]] +
                                this.tree[childrenIdx[1]];
    }
    
    private int query(
        int treeIdx, 
        int leftTree, 
        int rightTree, 
        int qLeft, 
        int qRight
    ) {
        // Completely in bounds
        // qLeft ------ leftTree ----- rightTree -------- qRight
        if((qLeft <= leftTree) && (rightTree <= qRight)) {
            return this.tree[treeIdx];
        }
        
        // Out of bounds
        // qL ----- qR ---- leftTree ---- rightTree ---- qL --- qR
        if((qRight < leftTree) || (qLeft > rightTree)) {
            return DEFAULT;
        }
        
        // Recurse
        int mid = getMid(leftTree, rightTree);
        int[] childrenIdx = getChildrenIdx(treeIdx);
        
        int leftQ = query(childrenIdx[0], leftTree, mid, qLeft, qRight);
        int rightQ = query(childrenIdx[1], mid + 1, rightTree, qLeft, qRight);
        
        return (leftQ + rightQ);
    }
    
    // Point update
    private void update(
        int treeIdx, 
        int leftTree, 
        int rightTree, 
        int pos, 
        int del
    ) {
        // Out of bounds, ignore
        // pos -- left ---- right -- pos
        if((pos < leftTree) || (pos > rightTree)) {
            return;
        }
        
        // Sum => Always add for internal node
        this.tree[treeIdx] += del;
        
        if(leftTree == rightTree) {
            this.nums[pos] += del;
            return;
        }
        
        // Recurse
        int[] childrenIdx = getChildrenIdx(treeIdx);
        int mid = getMid(leftTree, rightTree);
        
        update(childrenIdx[0], leftTree, mid, pos, del);
        update(childrenIdx[1], mid + 1, rightTree, pos, del);
    }
    
    // APIs
    public int sumRange(int qLeft, int qRight) {
        return query(0, 0, this.nums.length - 1, qLeft, qRight);
    }
    
    // Also, update the nums !!
    public void update(int pos, int val) {
        int diff = val - this.nums[pos];
        update(0, 0, this.nums.length - 1, pos, diff);
    }
}

class NumArray {
    private SegmentTree segTree;

    public NumArray(int[] nums) {
        this.segTree = new SegmentTree(nums);
    }
    
    public void update(int index, int val) {
        this.segTree.update(index, val);
    }
    
    public int sumRange(int left, int right) {
        return this.segTree.sumRange(left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */