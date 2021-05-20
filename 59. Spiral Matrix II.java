class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int cnt = 1;
        int offset = 0;
        
        int i, j;
        
        while(offset <= n/2){

            // left to right fillup [1st row (row_itr = offset), all columns]
            for(int col_itr=offset; col_itr<(n - offset); col_itr++){
                i = offset; j = col_itr;
                matrix[i][j] = cnt;
                // System.out.println("1. Setting mat(" + i + "," + j + ") = " + cnt);
                cnt++;
            }
            
            
            // top to down fillup [all rows, last column = (n - offset - 1)]
            for(int row_itr=offset+1; row_itr<(n - offset); row_itr++){
                i = row_itr; j = n - offset - 1;
                matrix[i][j] = cnt;
                // System.out.println("2. Setting mat(" + i + "," + j + ") = " + cnt);               
                cnt++;
            }
            
            
            // right to left fillup [last row (n - offset - 1), all columns]
            for(int col_itr=(n - offset - 2); col_itr>=offset; col_itr--){
                i = n - offset - 1; j = col_itr;
                matrix[i][j] = cnt;
                // System.out.println("3. Setting mat(" + i + "," + j + ") = " + cnt);
                cnt++;
            }
            
            
            // down to top fillup [all rows, first column (offset)]
            for(int row_itr=(n - offset - 2); row_itr>offset; row_itr--){
                i = row_itr; j = offset;
                matrix[i][j] = cnt;
                // System.out.println("4. Setting mat(" + i + "," + j + ") = " + cnt);      
                cnt++;
            }
            
            
            offset++;
            // System.out.println("INCREMENTING OFFSET, offset = " + offset);
        }
        
        
        return matrix;
    }
}