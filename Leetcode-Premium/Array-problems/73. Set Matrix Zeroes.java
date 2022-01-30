class Solution {
    public void setZeroes(int[][] matrix) {
        // setZerosUsing_M_PLUS_N_Space(matrix);    
        
        setZerosUsing_ConstantSpace(matrix);
        // row[] and column[] will be inside matrix
        
    }
    
    private void setZerosUsing_ConstantSpace(int[][] matrix){
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        
        boolean markFirstRowZero = false;
        
        // mark zero-based row and columns
        for(int r=0; r<nRows; r++){
            for(int c=0; c<nCols; c++){
                int el = matrix[r][c];
                if(el == 0){
                    // mark the row and column
                    if(r == 0)
                        markFirstRowZero = true;
                    else
                        matrix[r][0] = 0; // mark that ROW as zero

                    matrix[0][c] = 0; // mark that COLUMN as zero
                }
            }
        }
        
        // set zeros from bottom-right-up-left
        
        // set first row & col later
        for(int r=nRows-1; r>=1; r--){
            if(matrix[r][0] == 0){
                setRowZero(matrix, r);
            }
        }
        for(int c=nCols-1; c>=1; c--){
            if(matrix[0][c] == 0){
                setColumnZero(matrix, c);
            }
        }
        
        // col. check
        if(matrix[0][0] == 0){
            setColumnZero(matrix, 0);            
        }
        if(markFirstRowZero == true){
            setRowZero(matrix, 0);
        }
    }
    
    private void setZerosUsing_M_PLUS_N_Space(int[][] matrix){
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        
        // O(m + n) space, keep row[] and col[] arrays 
        // to indicate whether to set whole row/col to zero or not
        
        // by default are false
        boolean[] rowCheck = new boolean[nRows];
        boolean[] columnCheck = new boolean[nCols];
        
        // Set which rows and columns need to update.
        for(int r=0; r<nRows; r++){
            for(int c=0; c<nCols; c++){
                if(matrix[r][c] == 0){
                    rowCheck[r] = true;
                    columnCheck[c] = true;
                }
            }
        }
        
        // Update row-wise
        for(int r=0; r<rowCheck.length; r++){
            if(rowCheck[r] == true){
                setRowZero(matrix, r);
            }
        }
        
        // Update col-wise
        for(int c=0; c<columnCheck.length; c++){
            if(columnCheck[c] == true){
                setColumnZero(matrix ,c);
            }
        }
    }
    
    
    private void setRowZero(int[][] matrix, int row){
        for(int col=0; col<matrix[0].length; col++){
            matrix[row][col] = 0;
        }
    }
    
    private void setColumnZero(int[][] matrix, int col){
        for(int row=0; row<matrix.length; row++){
            matrix[row][col] = 0;
        }
    }
    
}