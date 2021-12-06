import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if((matrix == null) || (matrix.length == 0)) return null;

        int m = matrix.length;
        int n = matrix[0].length;

        int row_start = 0, col_start = 0;
        int row_end = m - 1, col_end = n - 1;

        List<Integer> list = new ArrayList<>();

        // Base cases.
        if(row_start == row_end){
            for(int col=col_start; col<=col_end; col++){
                list.add(matrix[row_start][col]);
            }
            return list;
        }
        if(col_start == col_end){
            for(int row=row_start; row<=row_end; row++){
                list.add(matrix[row][col_start]);
            }
            return list;
        }

        while (true){
            // First row movement
            for(int col=col_start; col<=col_end; col++){
                list.add(matrix[row_start][col]);
            }
            if(row_start == row_end)
                break;

            // First column movement
            for(int row=row_start+1; row<=row_end; row++){
                list.add(matrix[row][col_end]);
            }
            if(col_start == col_end) {
                break;
            }

            // Last row movement
            for(int col=col_end-1; col>=col_start; col--){
                list.add(matrix[row_end][col]);
            }

            // Last col. movement
            for(int row=row_end-1; row>row_start; row--){
                list.add(matrix[row][col_start]);
            }

            // Check for neighbor row conditions.
            if((row_start == (row_end - 1)))
                break;

            // Check for neighbor col conditions.
            if((col_start) == (col_end - 1))
                break;

            // Increment.
            row_start ++;
            row_end --;
            col_start ++;
            col_end --;

        }

        return list;
    }
}
