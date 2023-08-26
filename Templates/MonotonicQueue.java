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

class Pair {
    int index;
    int val;
    
    public Pair(int i, int v) {
        this.index = i;
        this.val = v;
    }
    
    @Override
    public String toString() {
        return "[" + this.index + "," + this.val + "]";
    }
}

class Solution {
    private int[] usingSegmentTree(int[] nums, int k) {
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
    
    // Decreasing order
    private int[] usingMonotonicQueue(int[] nums, int k) {
        ArrayDeque<Pair> deq = new ArrayDeque<>();
        int n = nums.length;
        int len = n - k + 1;
        int[] ans = new int[len];
        
        // pointers
        int left = 0, right = 0;
        
        // initially: add elements
        for(right=0; right<k; right++) {
            // maintain order: if any smaller found, keep removing from deq
            int currentEl = nums[right];
            while(!deq.isEmpty() && (deq.peekLast().val < currentEl)) {
                deq.removeLast();
            }
            deq.add(new Pair(right, currentEl));
        }
        
        // System.out.println("After first k elements: deque is => " + deq);
        
        ans[left] = deq.peekFirst().val; // left side of dequeue
        
        while(right < n) {            
            // maintain decreasing order
            int currentEl = nums[right];
            // System.out.println("BEFORE: At r = " + right + ": deque is => " + deq);
            
            while(!deq.isEmpty() && (deq.peekLast().val < currentEl)) {
                deq.removeLast();
            }
            deq.add(new Pair(right, currentEl));
            
            // System.out.println("At r = " + right + ": deque is => " + deq);
            
            // increment by one
            left++;
            right++;
            
            // Check if we have to remove from deque's first or not
            int currentDeqWindow = deq.peekLast().index - deq.peekFirst().index;
            if(currentDeqWindow >= k)
                deq.removeFirst(); // remove from deque, if window size >= k
            
            ans[left] = deq.peekFirst().val;
        }
        
        return ans;
    }
    
    // API
    public int[] maxSlidingWindow(int[] nums, int k) {
        // return usingSegmentTree(nums, k);
        return usingMonotonicQueue(nums, k);
    }
}




