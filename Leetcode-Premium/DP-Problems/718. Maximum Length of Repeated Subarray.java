class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        // return bottomUp(nums1, nums2);
        return bottomUpSpaceOptimized(nums1, nums2);
    }
    
    private int bottomUpSpaceOptimized(int[] nums1, int[] nums2){
        // if(nums1.length < nums2.length){ // make sure nums2 is smaller
        //     // change references
        //     int[] temp = nums1;
        //     nums1 = nums2;
        //     nums2 = temp;
        // }
        
        int m = nums1.length;
        int n = nums2.length;
        
        int[] prevRow = new int[n + 1];
        int[] currRow = new int[n + 1];
        
        Arrays.fill(prevRow, 0);
        Arrays.fill(currRow, 0);
        
        int max = 0;
        for(int i=1; i<=m; i++){
            
            // compute
            for(int j=1; j<=n; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    currRow[j] = prevRow[j - 1] + 1;
                }else{
                    currRow[j] = 0;
                }
                max = Math.max(max, currRow[j]);
            }
            
            // swap rows
            for(int j=1; j<=n; j++){
                prevRow[j] = currRow[j];
            }
        }
        
        return max;
    }
    
    private int bottomUp(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        
        int[][] dp = new int[m + 1][n + 1];
        
        for(int[] x: dp){Arrays.fill(x, 0);}
        
        int max = 0;
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else{
                    dp[i][j] = 0;
                    // dp[i][j] = Math.max(
                    //     dp[i - 1][j],
                    //     dp[i][j - 1]
                    // );
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        
        // printArray(dp); // DEBUG
        
        // return dp[m][n];
        return max;
    }
    
    private void printArray(int[][] dp){
        for(int[] x: dp){
            for(int y: x){
                System.out.print(y + " ");
            }
            System.out.println("");
        }
    }
}