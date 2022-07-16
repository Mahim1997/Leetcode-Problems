class Solution {
    private static int MOD = 1000000000 + 7;    
    private static int INVALID = -777;
    private static int[][] directions = {
        {0, -1}, {0, 1}, {-1, 0}, {1, 0}
    };

    private int ROWS;
    private int COLS;
    
    private int[][][] cache;
    
    private void initializeParameters(int m, int n) {
        this.ROWS = m;
        this.COLS = n;
    }
    
    private void initializeCache(int m, int n, int moves) {
        this.cache = new int[m][n][moves + 1];
        for(int i=0; i<this.cache.length; i++) {
            for(int j=0; j<this.cache[i].length; j++) {
                for(int k=0; k<this.cache[i][j].length; k++) {
                    this.cache[i][j][k] = INVALID;
                }
            }
        }
    }
    
    
    public int findPaths(
        int m, 
        int n, 
        int maxMove, 
        int startRow, 
        int startColumn
    ) {
        initializeCache(m, n, maxMove);
        initializeParameters(m, n);
        return dfs(startRow, startColumn, maxMove);
    }
    
    private boolean isOutOfBounds(int row, int col) {
        return (
            (row < 0) || (col < 0) ||
            (row >= ROWS) || (col >= COLS)
        );  
    }
    
    private int dfs(
        int row, 
        int col, 
        int movesRemain
    ) {
        // Base cases

        // 1. Failure
        if(movesRemain < 0)
            return 0;   // summing will have no effect
        // 2. Success
        if(isOutOfBounds(row, col))
            return 1;   // increment
        
        // Cache check
        if(this.cache[row][col][movesRemain] != INVALID)
            return this.cache[row][col][movesRemain];

        // Recursive
        int sum = 0;
        for(int[] dir: directions) {
            int rowNew = row + dir[0];
            int colNew = col + dir[1];
            
            sum = sum + dfs(rowNew, colNew, movesRemain - 1);
            sum = sum % MOD;
        }
        sum = sum % MOD;
        
        // Cache update
        this.cache[row][col][movesRemain] = sum;
        
        // Return
        return sum;
    }
}


