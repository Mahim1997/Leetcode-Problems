class Solution {
    int getRow(int idx, int row, int col){
        // return ((idx-col)/row);
        return (idx/col);
    }
    int getCol(int idx, int row, int col){
        // return ((idx - col)%row);
        return (idx%col);
    }
    int getIdx(int i, int j, int row){
        return (i*row + j);
    }
    
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int row = mat.length;
        int col = mat[0].length;
        int prod1 = row*col;
        int prod2 = r*c;
        
        // illegal products
        if(prod1 != prod2){
            return mat;
        }
        
        // same matrix shape
        if((row == r) && (col == c)){
            return mat;
        }
        
        int[][] newMat = new int[r][c];
        int idxNew = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                // int idxOld = getIdx(i, j, row);
                int val = mat[i][j];
                int rowNew = getRow(idxNew, r, c);
                int colNew = getCol(idxNew, r, c);
                // System.out.println("idxNew = " + idxNew + 
                // ", rowNew = " + rowNew + ", colNew = " + colNew + 
                // ", r = " + r + ", c = " + c);
                
                newMat[rowNew][colNew] = val;
                idxNew++;
            }
        }
        
        return newMat;
    }
}