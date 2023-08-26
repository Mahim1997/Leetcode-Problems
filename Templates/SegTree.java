class SegmentTree {
    int[] tree;
    int[] lazy;
    int[] arr;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        int treeSize = 2 * (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))) - 1;
        tree = new int[treeSize];
        lazy = new int[treeSize];
        buildSegmentTree(0, n - 1, 0);
    }

    // Build the segment tree recursively
    private void buildSegmentTree(int start, int end, int treeNode) {
        if (start == end) {
            tree[treeNode] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        buildSegmentTree(start, mid, 2 * treeNode + 1);
        buildSegmentTree(mid + 1, end, 2 * treeNode + 2);
        tree[treeNode] = tree[2 * treeNode + 1] + tree[2 * treeNode + 2];
    }

    // Update the values in the given range
    public void updateRange(int start, int end, int delta) {
        updateRangeUtil(0, arr.length - 1, start, end, delta, 0);
    }

    // Update the values in the given range (helper function)
    private void updateRangeUtil(int start, int end, int rangeStart, int rangeEnd, int delta, int treeNode) {
        if (lazy[treeNode] != 0) {
            tree[treeNode] += (end - start + 1) * lazy[treeNode];
            if (start != end) {
                lazy[2 * treeNode + 1] += lazy[treeNode];
                lazy[2 * treeNode + 2] += lazy[treeNode];
            }
            lazy[treeNode] = 0;
        }

        // No overlap
        if (rangeStart > end || rangeEnd < start) {
            return;
        }

        // Complete overlap
        if (rangeStart <= start && rangeEnd >= end) {
            tree[treeNode] += (end - start + 1) * delta;
            if (start != end) {
                lazy[2 * treeNode + 1] += delta;
                lazy[2 * treeNode + 2] += delta;
            }
            return;
        }

        // Partial overlap
        int mid = (start + end) / 2;
        updateRangeUtil(start, mid, rangeStart, rangeEnd, delta, 2 * treeNode + 1);
        updateRangeUtil(mid + 1, end, rangeStart, rangeEnd, delta, 2 * treeNode + 2);
        tree[treeNode] = tree[2 * treeNode + 1] + tree[2 * treeNode + 2];
    }

    // Query the sum in the given range
    public int querySum(int start, int end) {
        return querySumUtil(0, arr.length - 1, start, end, 0);
    }

    // Query the sum in the given range (helper function)
    private int querySumUtil(int start, int end, int rangeStart, int rangeEnd, int treeNode) {
        if (lazy[treeNode] != 0) {
            tree[treeNode] += (end - start + 1) * lazy[treeNode];
            if (start != end) {
                lazy[2 * treeNode + 1] += lazy[treeNode];
                lazy[2 * treeNode + 2] += lazy[treeNode];
            }
            lazy[treeNode] = 0;
        }

        // No overlap
        if (rangeStart > end || rangeEnd < start) {
            return 0;
        }

        // Complete overlap
        if (rangeStart <= start && rangeEnd >= end) {
            return tree[treeNode];
        }

        // Partial overlap
        int mid = (start + end) / 2;
        int leftSum = querySumUtil(start, mid, rangeStart, rangeEnd, 2 * treeNode + 1);
        int rightSum = querySumUtil(mid + 1, end, rangeStart, rangeEnd, 2 * treeNode + 2);
        return leftSum + rightSum;
    }
}
