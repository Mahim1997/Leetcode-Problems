class Solution {
    
    public int maximalSquare(char[][] matrix) {
        return bottomUp(matrix);
    }
    
    private int bottomUp(char[][] matrix){
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        
        int squareSize = Math.min(nRow, nCol);
        
        boolean[][][] dp = new boolean[nRow][nCol][squareSize];
        // initially all are false.
        
        // base case -> size of '1' i.e. sz = 0 with char array check.
        int maxSize = -1;
        
        for(int row=0; row<nRow; row++){
            for(int col=0; col<nCol; col++){
                boolean val = (matrix[row][col] == '1') ? true: false;
                if(val == true){maxSize = 0;}
                dp[row][col][0] = val;
            }
        }
        
        // recursion. [size=1 is actually SIZE=2]
        for(int size=1; size<squareSize; size++){
            for(int row=0; row<(nRow - size); row++){
                for(int col=0; col<(nCol - size); col++){
                    boolean val =   dp[row][col][size-1] && 
                                    dp[row][col+1][size-1] && 
                                    dp[row+1][col][size-1] &&
                                    dp[row+1][col+1][size-1];
                    // System.out.println("r, c = (" + row + "," + col + "), size = " + size + " val = " + val);
                
                    if(val == true){
                        maxSize = Math.max(size, maxSize);
                    }
                    dp[row][col][size] = val;
                }
            }
        }
        
        return (maxSize+1)*(maxSize+1); // maxSize stores ACTUAL SIZE - 1.
    }
    
}