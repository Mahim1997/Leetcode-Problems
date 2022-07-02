class Solution {
    private static int MOD = 1000000000 + 7;
    
    private int getMaxGap(int[] arr, int rightMostLimit) {
        int maxGap = 0, n = arr.length;
        
        // Sort
        Arrays.sort(arr);
        
        maxGap = Math.max(maxGap, arr[0] - 0); // initial
        maxGap = Math.max(maxGap, rightMostLimit - arr[n - 1]); // final
        
        for(int i=1; i<n; i++) {
            maxGap = Math.max(maxGap, arr[i] - arr[i - 1]);
        }
        
        return maxGap;
    }
    
    public int maxArea(
        int h, 
        int w, 
        int[] horizontalCuts,
        int[] verticalCuts
    ) {
        int maxHeight = getMaxGap(verticalCuts, w);
        int maxWidth = getMaxGap(horizontalCuts, h);
        
        long product = (long) maxWidth * (long) maxHeight;
        product = product % (long) MOD;
        
        int prod = (int) product;
        return prod;
    }
}