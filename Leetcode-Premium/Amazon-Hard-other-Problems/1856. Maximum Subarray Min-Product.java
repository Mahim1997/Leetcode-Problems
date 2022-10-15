class Solution {
    private static int NULL_VALUE = -1;
    private static int MOD = 1000000000 + 7;
    
    private int[] getNextMinIndices(int[] nums) {
        // Needs monotonically increasing stack ?
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int itr = 0;
        int[] nextMin = new int[n];
        Arrays.fill(nextMin, NULL_VALUE);
        
        for(int i=0; i<n; i++) {
            int num = nums[i];
            
            // Maintains monotonoic order
            while(!stack.isEmpty() && (num < nums[stack.peek()])) {
                int idxRemoved = stack.pop();
                nextMin[idxRemoved] = i;
            }
            
            // Add to stack [index]
            stack.add(i);
        }
        
        return nextMin;
    }
    
    
    private int[] getNextMinIndicesLeftSide(int[] nums) {
        // Needs monotonically increasing stack ?
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int itr = 0;
        int[] nextMin = new int[n];
        Arrays.fill(nextMin, NULL_VALUE);
        
        for(int i=n-1; i>=0; i--) {
            int num = nums[i];
            
            // Maintains monotonoic order
            while(!stack.isEmpty() && (num < nums[stack.peek()])) {
                int idxRemoved = stack.pop();
                nextMin[idxRemoved] = i;
            }
            
            // Add to stack [index]
            stack.add(i);
        }
        
        return nextMin;
    }
    
    private long[] getPrefixSums(int[] nums) {
        int n = nums.length;
        long[] pref = new long[n];
        Arrays.fill(pref, 0);
        
        pref[0] = (long) nums[0];
        for(int i=1; i<n; i++) {
            pref[i] = pref[i - 1] + (long) nums[i];
        }
        
        return pref;
    }
    
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] nextMinIndicesLeft = getNextMinIndicesLeftSide(nums);
        int[] nextMinIndicesRight = getNextMinIndices(nums);
        long[] prefixSums = getPrefixSums(nums);

        // System.out.println("nextMinIndicesLeft: " + Arrays.toString(nextMinIndicesLeft) + " nextMinIndicesRight: " + Arrays.toString(nextMinIndicesRight) + ", prefixSums: " + Arrays.toString(prefixSums));
        
        long globalMax = 0; // Integer.MIN_VALUE;
        
        for(int i=0; i<n; i++) {
            int nextMinIdxLeft = nextMinIndicesLeft[i];
            int nextMinIdxRight = nextMinIndicesRight[i];
            if(nextMinIdxRight == NULL_VALUE) {
                nextMinIdxRight = n; // for easier -1 computation
            }
            
            long prefixSumLeft;
            if(nextMinIdxLeft == NULL_VALUE) {
                prefixSumLeft = 0; // for easier computation
            }
            else {
                prefixSumLeft = prefixSums[nextMinIdxLeft];
            }
            
            // int sum = prefixSums[nextMinIdx - 1] - ((i > 0) ? prefixSums[i - 1]: 0);
            
            long sum = prefixSums[nextMinIdxRight - 1] - prefixSumLeft;
            
            int min = nums[i];
            long product = (long) sum * (long) min;
            
            // System.out.println("For i = " + i + ", nextMinIdxLeft = " + nextMinIdxLeft + ", nextMinIdxRight = " + nextMinIdxRight + ", sum = " + sum + ", min = " + min + ", product = " + product);
            
            globalMax = Math.max(globalMax, product);
        }
        
        return (int) (globalMax% (long) MOD);
    }
}

