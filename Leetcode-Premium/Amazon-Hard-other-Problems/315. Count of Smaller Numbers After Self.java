// For sum
class SegmentTree {
    private int[] data;
    private int[] tree;
    
    private static int DEFAULT = 0;
    
    public SegmentTree(int size) {
        // Initially all will be zeros
        this.data = new int[size];
        Arrays.fill(this.data, DEFAULT);
        this.tree = new int[data.length * 4]; // safe
        Arrays.fill(this.tree, DEFAULT);
    }
    
    /// IMPORTANT: No need to build tree, as initially zeros
    
    private int query(int treeIdx, int left, int right, int qLeft, int qRight) {
        // No overlap
        if((qRight < left) || (qLeft > right))
            return DEFAULT;
        
        // Whole overlap
        if((qLeft <= left) && (qRight >= right))
            return this.tree[treeIdx];
        
        // Partial overlap
        int mid = left + (right - left)/2;
        int queryLeft = query(2*treeIdx + 1, left, mid, qLeft, qRight);
        int queryRight = query(2*treeIdx + 2, mid + 1, right, qLeft, qRight);
        
        // Merge & return
        return (queryLeft + queryRight);
    }
    
    private void update(int treeIdx, int left, int right, int pos, int diff) {
        // If out of range, ignore
        if((pos < left) || (pos > right))
            return;
        
        this.tree[treeIdx] += diff;
        
        if(left == right) {
            this.data[pos] += diff;
            return;
        }
        
        int mid = left + (right - left)/2;
        update(2*treeIdx + 1, left, mid, pos, diff);
        update(2*treeIdx + 2, mid + 1, right, pos, diff);
    }
    
    // APIs
    
    public int rangeQuery(int left, int right) {
        return query(0, 0, this.data.length - 1, left, right);    
    }
    
    public void updateDifference(int pos, int diff) {
        update(0, 0, this.data.length - 1, pos, diff);
    }
}

/*
Convert to all positive numbers: Use + BASE
Store counts
Update count by 1 when moved from right to left
Get range query of counts of [0, num - 1]
Range Query: Segment Tree
*/

class Solution {
    private static int MIN = -10000; // -10^4
    private static int MAX = 10000;  //  10^4
    private static int SIZE = MAX - MIN + 1;
    
    private static int BASE = 10000;
    
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        
        int n = nums.length;
        
        SegmentTree segTree = new SegmentTree(SIZE);
        
        for(int i=n-1; i>=0; i--) {
            int num = nums[i] + BASE;
            
            // Query
            int countSmallerThanNum = segTree.rangeQuery(0, num - 1);
            list.addFirst(countSmallerThanNum);
            
            // Update
            int diff = 1;
            segTree.updateDifference(num, diff);
        }
        
        return list;
    }
}

