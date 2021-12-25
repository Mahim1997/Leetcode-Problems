class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        if(nRows == 1){
            int min = Integer.MAX_VALUE;
            for(int x: matrix[0]){
                min = Math.min(min, x);
            }
            return min;
        }

        
        int[][] dp = new int[nRows][nCols];
    
        // bottom to top fillup
        for(int col=0; col<nCols; ++col){
            dp[nRows - 1][col] = matrix[nRows - 1][col];
        }
        
        
        // recursion.
        int ans = Integer.MAX_VALUE;
        for(int row=nRows - 2; row>=0; row--){
            for(int col=0; col<nCols; ++col){
                int v1 = Integer.MAX_VALUE; 
                int v2 = Integer.MAX_VALUE;
                int v3 = Integer.MAX_VALUE;
                if(col > 0){
                    v1 = dp[row + 1][col - 1];
                }
                if(col < (nCols - 1)){
                    v2 = dp[row + 1][col + 1];
                }
                v3 = dp[row + 1][col];
                dp[row][col] = Math.min(Math.min(v1, v2), v3) + matrix[row][col];
                if(row == 0){
                    ans = Math.min(ans, dp[row][col]);
                }
            }
            // printDP(dp); // DEBUGGING
        }
    
        return ans;
    }
    
    void printDP(int[][] dp){
        for(int[] arr: dp){
            for(int x: arr){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }
}