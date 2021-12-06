class Solution {
    private int binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    // Naive
//    private int getRowIndexToSearch(int[][] matrix, int target){
//        int m = matrix.length, n = matrix[0].length;
//        for(int row=0; row<m; row++){
//            if((matrix[row][0] <= target)
//                && (matrix[row][n-1] >= target))
//            {
//                return row;
//            }
//        }
//        return -1;
//    }

    // Optimized
    private int getRowIndexToSearch(int[][] matrix, int target){
        int m = matrix.length;
        int n = matrix[0].length;

        if(target <= matrix[0][n-1])
            return 0; // search in the first row.

        int row_first = 0, row_last = m - 1;


        while(row_first <= row_last){
            int row_mid = (row_first + row_last) / 2;

//            System.out.printf("%d, %d, %d\n", row_first, row_last, row_mid);

            // checking criterion.
            if((target <= matrix[row_mid][n-1])
               && (target > matrix[row_mid-1][n-1])){
                return row_mid;
            }

            // binary search conditions.
            if(target > matrix[row_mid][n-1]){
                row_first = row_mid + 1;
            }
            else{
                row_last = row_mid - 1;
            }
        }
        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // Base cases.
        int m = matrix.length;
        int n = matrix[0].length;

        if(target < matrix[0][0]){return false;}
        if(target > matrix[m-1][n-1]){return false;}

        // 1. Find which row to search in
        int rowIdx = getRowIndexToSearch(matrix, target);
        if(rowIdx == -1){return false;} // not going to happen.

//        System.out.println("rowIdx = " + rowIdx);

        // 2. In that row, run the binary search
        int arr[] = matrix[rowIdx];
        return (binarySearch(arr, target) != -1);
    }
}


