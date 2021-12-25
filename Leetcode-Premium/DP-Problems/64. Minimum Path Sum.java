class Solution {
    public int minPathSum(int[][] grid) {
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        int[][] dp = new int[nRows][nCols];
        
        // base cases.
        dp[0][0] = grid[0][0];
        for(int row=1; row<nRows; ++row)
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        for(int col=1; col<nCols; ++col)
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        
        
        // recursion
        for(int row=1; row<nRows; row++){
            for(int col=1; col<nCols; col++){
                dp[row][col] = Math.min(
                    dp[row - 1][col], dp[row][col - 1]
                ) + grid[row][col];
            }
        }
            
        return dp[nRows - 1][nCols - 1];
    }
}