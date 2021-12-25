class Solution {
    private boolean isObstacle(int val){
        return (val == 1);
    }
    
    private int[][] getInitialTable(int[][] grid){
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        int[][] dp = new int[nRows][nCols];
        
        // any obstacle found, subsequent all will be zeros, 
        // for base cases row & col
        boolean flag = false;
        for(int row=0; row<nRows; ++row){
            if(isObstacle(grid[row][0]))
                flag = true;
            if(flag)
                dp[row][0] = 0;
            else
                dp[row][0] = 1 - grid[row][0];
        }
        flag = false;
        for(int col=0; col<nCols; ++col){
            if(isObstacle(grid[0][col]))
                flag = true;
            if(flag)
                dp[0][col] = 0;
            else
                dp[0][col] = 1 - grid[0][col];
        }
        dp[0][0] = 1 - grid[0][0]; // because first one is by default 1 ?? NO !
        return dp;
    }
    
    public int uniquePathsWithObstacles(int[][] grid) {
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        if(isObstacle(grid[nRows - 1][nCols - 1]) // ending is an obstacle
          || isObstacle(grid[0][0])){ // starting is already an obstacle
            return 0; // can't reach obstacle
        }
        
        // initial conditions are met
        int[][] dp = getInitialTable(grid);
        
        // recursion
        for(int row=1; row<nRows; row++){
            for(int col=1; col<nCols; col++){
                if(isObstacle(grid[row][col]) == false){
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }else{ // THIS is a grid
                    dp[row][col] = 0;
                }
            }
        }
        
        return dp[nRows - 1][nCols - 1];
    }
}