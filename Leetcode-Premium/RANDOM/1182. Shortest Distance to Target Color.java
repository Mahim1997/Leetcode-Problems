class Solution {
    private int[][] getArray(int n) {
        int[][] mat = new int[n][4];
        for(int[] arr: mat) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        return mat;
    }
    
    private void fillRightToLeft(int[][] arr, int[] colors) {
        int n = colors.length;
        for(int i=n-1; i>=0; i--) {
            int color = colors[i];
            
            for(int col=1; col<=3; col++) {
                if(col == color) {
                    arr[i][col] = 0;
                }
                else {
                    // corner case
                    int rightVal = (i < (n - 1)) ? 
                        arr[i + 1][col] : 
                        Integer.MAX_VALUE;
                    
                    arr[i][col] = (rightVal == Integer.MAX_VALUE) ?
                        Integer.MAX_VALUE : 
                        (rightVal + 1);
                }
            }
        }
    }
    
    private void fillLeftToRight(int[][] arr, int[] colors) {
        int n = colors.length;
        for(int i=0; i<n; i++) {
            int color = colors[i];
            
            for(int col=1; col<=3; col++) {
                if(col == color) {
                    arr[i][col] = 0;
                }
                else {
                    // corner case
                    int leftVal = (i > 0) ? 
                        arr[i - 1][col] : 
                        Integer.MAX_VALUE;
                    
                    arr[i][col] = (leftVal == Integer.MAX_VALUE) ?
                        Integer.MAX_VALUE : 
                        (leftVal + 1);
                }
            }
        }
    }
    
    private int[][] getAggregate(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int cols = arr1[0].length;
        int[][] ans = new int[n][cols];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<cols; j++) {
                ans[i][j] = Math.min(arr1[i][j], arr2[i][j]);
            }
        }
        
        return ans;
    }
    
    public List<Integer> shortestDistanceColor(
        int[] colors, 
        int[][] queries
    ) {
        int n = colors.length;
        
        int[][] rightToLeft = getArray(n);
        int[][] leftToRight = getArray(n);
        
        fillRightToLeft(rightToLeft, colors);
        fillLeftToRight(leftToRight, colors);
        
        int[][] dp = getAggregate(rightToLeft, leftToRight);
        
        List<Integer> list = new ArrayList<>();
        for(int[] query: queries) {
            int idx = query[0];
            int color = query[1];
            
            int ans = (dp[idx][color] == Integer.MAX_VALUE) 
                ? -1 
                : dp[idx][color];
            
            list.add(ans);
        }
        return list;
    }
}