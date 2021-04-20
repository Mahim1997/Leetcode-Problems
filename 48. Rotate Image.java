class Solution {

    private void swap2Positions(int[][] matrix, int r1, int c1, int r2, int c2) {
        matrix[r1][c1] = matrix[r1][c1] + matrix[r2][c2];
        matrix[r2][c2] = matrix[r1][c1] - matrix[r2][c2];
        matrix[r1][c1] = matrix[r1][c1] - matrix[r2][c2];
    }

    private void rotate4Positions(int[][] matrix,
                                  int r1, int c1, int r2, int c2, int r3, int c3, int r4, int c4) {
        swap2Positions(matrix, r1, c1, r4, c4);
        swap2Positions(matrix, r4, c4, r3, c3);
        swap2Positions(matrix, r3, c3, r2, c2);
    }

    private void rotateLevelWise(int[][] matrix, int start, int end) {
        // Gather the positions. level == n in this case.
        if (start == end) {
            return;
        } // one element box
//        int r1, r2, r3, r4, c1, c2, c3, c4;
        for (int i = 0; i < (end - start); i++) {
//            r1 = start; c1 = i + start;
//            r2 = i + start; c2 = end;
//            r3 = end; c3 = end - i;
//            r4 = end - i; c4 = start;
//            System.out.printf("\n\n%d, %d, %d, %d, %d, %d, %d, %d", r1, c1, r2, c2, r3, c3, r4, c4);
//            rotate4Positions(matrix, r1, c1, r2, c2, r3, c3, r4, c4);

            rotate4Positions(matrix, start, i + start, i + start, end, end, end - i, end - i, start);
        }

    }

    public void rotate(int[][] matrix) {
        // corner case
        if (matrix == null) {
            return;
        }
        if (matrix.length == 1) {
            return;
        }
        int start = 0, end = matrix.length - 1;

        while (start <= end) {
//            System.out.println("start = " + start + " , end = " + end);
            rotateLevelWise(matrix, start, end);
            start++;
            end--;
        }

    }
}