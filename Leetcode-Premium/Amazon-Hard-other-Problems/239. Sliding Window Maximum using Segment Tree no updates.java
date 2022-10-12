class SegmentTree {
    private int[] nums;
    private int[] tree;
    
    // No updates !! Only querying
    private static int DEFAULT = Integer.MIN_VALUE;
    
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[this.nums.length * 4];
        Arrays.fill(this.tree, DEFAULT);
        buildTree(0, 0, this.nums.length - 1);
    }
    
    private int getMid(int left, int right) {
        return (left + (right - left)/2);
    }
    
    // For MAXIMUM
    private void buildTree(int treeIdx, int left, int right) {
        // Edge case
        if(left == right) {
            this.tree[treeIdx] = this.nums[left];
            return;
        }
        
        // Recursive
        int mid = getMid(left, right);
        int leftChild = 2*treeIdx + 1;
        int rightChild = 2*treeIdx + 2;
        
        buildTree(leftChild, left, mid);
        buildTree(rightChild, mid + 1, right);
        
        // Merge
        this.tree[treeIdx] = Math.max(
            this.tree[leftChild],
            this.tree[rightChild]
        );
    }
    
    private int query(
        int treeIdx, 
        int left, 
        int right, 
        int qLeft, 
        int qRight
    ) {
        // No overlap: default
        if((qRight < left) || (qLeft > right))
            return DEFAULT;
        
        // Full overlap: return
        if((qLeft <= left) && (qRight >= right))
            return this.tree[treeIdx];
        
        // Partial: Recurse
        int mid = getMid(left, right);
        int leftQ = query(2*treeIdx + 1, left, mid, qLeft, qRight);
        int rightQ = query(2*treeIdx + 2, mid + 1, right, qLeft, qRight);
        
        // Merge
        return Math.max(leftQ, rightQ);
    }
    
    // APIs
    public int getMax(int qLeft, int qRight) {
        return query(0, 0, this.nums.length - 1, qLeft, qRight);
    }
}


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        SegmentTree segTree = new SegmentTree(nums);
        
        int n = nums.length;
        int len = n - k + 1;
        
        int[] ans = new int[len];
        for(int i=0; i<len; i++) {
            // [left, right]: Inclusive
            int query = segTree.getMax(i, i + k - 1);
            ans[i] = query;
        }
        
        return ans;
    }
}





