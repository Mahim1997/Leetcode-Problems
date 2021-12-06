class Solution {

	// Using existing array
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // All Rows, 1st col, base case
        for(int i=1; i<m; i++){
            grid[i][0] += grid[i-1][0];
        }
        
        // All cols, 1st row, base case
        for(int j=1; j<n; j++){
            grid[0][j] += grid[0][j-1];
        }
        
        if(m == 1){ // One row only
            return grid[0][n-1]; // first row, last col
        }
        if(n == 1){ // One col. only
            return grid[m-1][0]; // last row, first col
        }
        
        // Normal cases.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                // come_from_left = grid[i][j-1];
                // come_from_up = grid[i-1][j];
                // decision = Math.min(come_from_left, come_from_up);
                
                grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
            }
        }
        
        return grid[m-1][n-1];
    
    }

	// Using new 2D array
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        // All Rows, 1st col, base case
        for(int i=1; i<m; i++){
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        
        // All cols, 1st row, base case
        for(int j=1; j<n; j++){
            dp[0][j] = grid[0][j] + dp[0][j-1];
        }
        
        if(m == 1){ // One row only
            return dp[0][n-1]; // first row, last col
        }
        if(n == 1){ // One col. only
            return dp[m-1][0]; // last row, first col
        }
        int come_from_left, come_from_up, decision;
        // Normal cases.
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                // come from left i.e. move to right
                come_from_left = dp[i][j-1]; // + grid[i][j];
                // come from above i.e. move down
                come_from_up = dp[i-1][j]; // + grid[i][j];
                decision = Math.min(come_from_left, come_from_up);
                dp[i][j] = decision + grid[i][j];
            }
        }
        // printArray(dp);
        
        return dp[m-1][n-1];
    
    }
}