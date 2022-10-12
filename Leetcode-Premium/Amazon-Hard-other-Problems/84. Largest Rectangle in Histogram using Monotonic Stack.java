class Solution {
    private static int DEFAULT = -100000;
    
    private int getNext(int idx, boolean isLeft) {
        return isLeft ? idx + 1 : idx - 1;
    }
    
    private boolean hasNext(int idx, int[] arr, boolean isLeft) {
        return isLeft ? (idx < arr.length) : (idx >= 0);
    }
    
    // Monotonically INCREASING stack 
    private int[] getNextSmaller(int[] arr, boolean isLeft) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, DEFAULT);
        
        // Use ArrayDeque instead of Stack
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        
        int first = isLeft ? 0 : n - 1;
        ans[first] = DEFAULT;
        
        for(int i=first; hasNext(i, arr, isLeft); i=getNext(i, isLeft)) {
            int currentEl = arr[i];
            
            // Keep removing until stack's top is smaller than current
            while(!deq.isEmpty() && (arr[deq.peekLast()] > currentEl)) {
                int lastIdx = deq.peekLast();
                ans[lastIdx] = i;
                deq.removeLast();
            }
            
            // Add current to the stack top
            deq.addLast(i);
        }
        
        return ans;
    }
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // Edge case
        if(n == 1)
            return heights[0];
        
        // Stores indices only: -1 by default
        int[] nsLeft = getNextSmaller(heights, false);
        int[] nsRight = getNextSmaller(heights, true);
        
//         System.out.println("nsLeft: " + Arrays.toString(nsLeft));
//         System.out.println("nsRight: " + Arrays.toString(nsRight));
        
        int globalMax = 0;
        
        for(int i=0; i<n; i++) {
            int leftSmallestIdx = nsLeft[i];
            int rightSmallestIdx = nsRight[i];
            
            if(leftSmallestIdx == DEFAULT)
                leftSmallestIdx = -1; // so +1 can be added easily
            if(rightSmallestIdx == DEFAULT)
                rightSmallestIdx = n; // so -1 can be added easily
       
            int width = ((rightSmallestIdx - 1) - (leftSmallestIdx + 1) + 1);
            int height = heights[i];
            int areaWithThisAsMinElement = height*width;
            
            globalMax = Math.max(globalMax, areaWithThisAsMinElement);
        }
        
        return globalMax;
    }
}