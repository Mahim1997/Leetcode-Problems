class Pair {
    int row;
    int col;
    
    public Pair(int r, int c) {
        this.row = r;
        this.col = c;
    }
    
    @Override
    public String toString() {
        return "[" + this.row + "," + this.col + "]";
    }
}

class Solution {
    private List<Pair> getStartIndices(int[][] mat) {
        List<Pair> list = new ArrayList<>();
        int ROWS = mat.length, COLS = mat[0].length;
        
        list.add(new Pair(0, 0)); // guaranteed 1 <= m,n <= 
        
        for(int r=1; r<ROWS; r++)
            list.add(new Pair(r, 0));

        for(int c=1; c<COLS; c++) 
            list.add(new Pair(0, c));
        
        return list;
    }
    
    // 1 <= mat[i][j] <= 100 (MAX is 100)
    private static int MAX = 100;
    private int[] getCounts(List<Integer> list) {
        // Use generic min and max, using Collections.min(), .max()
        int[] counts = new int[MAX + 1];
        Arrays.fill(counts, 0);
        
        for(int val: list)
            counts[val]++;
        
        return counts;
    }
    
    private List<Integer> countingSort(List<Integer> list) {
        int[] counts = getCounts(list);
        List<Integer> sorted = new ArrayList<>();
        
        for(int i=0; i<counts.length; i++) {
            int times = counts[i];
            while(times > 0) {
                sorted.add(i); // i + min, if min != 0
                times--;
            }
        }
        
        return sorted;
    }
    
    // API
    public int[][] diagonalSort(int[][] mat) {
        int ROWS = mat.length;
        int COLS = mat[0].length;
        
        int[][] ans = new int[ROWS][COLS];
        
        // capture each diagonal in an array list
        // sort them
        // put back into new array
        
        List<Pair> startIndices = getStartIndices(mat);
        
        for(Pair index: startIndices) {
            int r = index.row, c = index.col;
            
            List<Integer> list = new ArrayList<>();
            
            // Obtain in list
            while((r < ROWS) && (c < COLS)) {
                list.add(mat[r][c]);
                r++; c++;
            }
            
            // Sort the list, use counting sort
            List<Integer> sorted = countingSort(list);
            
            // Put back into the answer in same order of indices
            r = index.row; c = index.col;
            for(int val: sorted) {
                ans[r++][c++] = val;
            }
        }
        
        return ans;
    }
}

