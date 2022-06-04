class PrefixSum {
    private int[][] sum;
    
    public PrefixSum(int[][] mat) {
        int ROWS = mat.length, COLS = mat[0].length;
        
        this.sum = new int[ROWS][COLS];
        
        for(int r=0; r<ROWS; r++) {
            for(int c=0; c<COLS; c++) {
                int left     = (c > 0) ? sum[r][c - 1] : 0;
                int top      = (r > 0) ? sum[r - 1][c] : 0;
                int diagonal = ((c > 0) && (r > 0)) ? sum[r - 1][c - 1] : 0;
                
                int current = left + top - diagonal;
                sum[r][c] = current + mat[r][c];
            }
        }
    }
    
    private boolean isWithin(int r, int c) {
        return (
            (r >= 0) && (c >= 0) &&
            (r < this.sum.length) && (c < this.sum[0].length)
        );
    }
    
    public int getSum(int r, int c) {
        return (isWithin(r, c) ? this.sum[r][c] : 0);
    }
}

class NumMatrix {
    private PrefixSum prefixSum;
    
    public NumMatrix(int[][] matrix) {
        this.prefixSum = new PrefixSum(matrix);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return (
            prefixSum.getSum(row2, col2)        -
            prefixSum.getSum(row1 - 1, col2)    -
            prefixSum.getSum(row2, col1 - 1)    +
            prefixSum.getSum(row1 - 1, col1 - 1)
        );
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */