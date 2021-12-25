class Solution {
    public int uniquePaths(int nRows, int nCols) {
        // return linearTimeSpace(nRows, nCols);
        return linearTimeOptimizedSpace(nRows, nCols);
    }
    
    private int linearTimeOptimizedSpace(int nRows, int nCols){
        // only keep previous row
        if((nRows == 1) || (nCols == 1)){
            return 1; // always one path.
        }
        
        int[] currentRow = new int[nCols];
        int[] prevRow = new int[nCols];
        
        for(int col=0; col<nCols; col++){
            // initially row_0 all are 1s
            prevRow[col] = 1;
            currentRow[col] = 1;
        }
        
        // recursion.
        for(int row=1; row<nRows; row++){       // start with idx = 1
            for(int col=1; col<nCols; col++){   // start with idx = 1
                currentRow[col] = prevRow[col] + currentRow[col - 1];
            }
            // replace with prevRow <---> currentRow
            prevRow = currentRow;
            
            // printArray(currentRow); // DEBUGGING
        }
        
        return currentRow[nCols - 1];
    }
    
    private void printArray(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private int linearTimeSpace(int nRows, int nCols){
        int[][] dp = new int[nRows][nCols];
        
        // initial columns are 1s
        for(int row=0; row<nRows; row++){
            dp[row][0] = 1;
        }
        // initial rows are 1s
        for(int col=0; col<nCols; col++){
            dp[0][col] = 1;
        }
        
        // recursion
        for(int i=1; i<nRows; i++){
            for(int j=1; j<nCols; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // printDP(dp);
        
        return dp[nRows - 1][nCols - 1];
    }
    
    private void printDP(int[][] dp){
        for(int[] arr: dp){
            for(int x: arr){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }
    
}