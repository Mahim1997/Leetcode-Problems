class Index {
    int row;
    int col;
    
    public Index(int r, int c) {
        setValues(r, c);
    }
    
    public void setValues(int r, int c) {
        this.row = r;
        this.col = c;
    }
    
    @Override
    public boolean equals(Object o) {
        Index other = (Index) o;
        return ((this.row == other.row) && 
                (this.col == other.col));
    }
    
    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash*7 + this.row;
        hash = hash*7 + this.col;
        return hash;
    }
    
    @Override
    public String toString() {
        return "(" + this.row + "," + this.col + ")";
    }
}

class Island {
    List<Index> indices;
    int minRow; 
    int minCol;
    
    public Island() {
        this.indices = new ArrayList<>();
        this.minRow = Integer.MAX_VALUE;
        this.minCol = Integer.MAX_VALUE;
    }

    public void transform() {
        for(Index index: indices) {
            index.setValues(
                index.row - this.minRow, 
                index.col - this.minCol
            );
        }
    }
    
    private void updateMinimumValues(int row, int col) {
        this.minRow = Math.min(this.minRow, row);
        this.minCol = Math.min(this.minCol, col);
    }
    
    public void addIndex(int row, int col) {
        updateMinimumValues(row, col);
        this.indices.add(new Index(row, col));
    }
    
    @Override
    public boolean equals(Object o) {
        Island other = (Island) o;
        // if(other.minRow != this.minRow){return false;}
        // if(other.minCol != this.minCol){return false;}
        
        return this.indices.equals(other.indices);
    }
    
    @Override
    public int hashCode() {
        int hash = 13;
        // hash = hash*7 + this.minRow;
        // hash = hash*7 + this.minCol;
        hash = hash*7 + this.indices.hashCode();
        // for(Index index: this.indices) {
            // hash = hash + index.hashCode();
        // }
        return hash;
    }
    
    @Override
    public String toString() {
        return "minRow = " + this.minRow + ", minCol = " + this.minCol + ", indices = " + this.indices;
    }
}


class Solution {
    public int numDistinctIslands(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        boolean[][] visited = new boolean[ROWS][COLS]; // false
    
        Set<Island> setIslands = new HashSet<>();
        
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                
                if((visited[i][j] == false) && 
                   (grid[i][j] == 1)){
                   
                    // visit it
                    visited[i][j] = true;
                    Island island = new Island();
                    
                    // fill island
                    dfs(grid, visited, island, i, j);
                    
                    // System.out.println("island is: " + island);
                    
                    // transform
                    island.transform();
                    
                    // add to set
                    setIslands.add(island);
                }
                
            }
        }
        
        // System.out.println("setIslands: " + setIslands);
        
        return setIslands.size();
    }
    
    
    private int[][] movements = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    private boolean isInside(int row, int col, int[][] grid){
        return (
            (row >= 0) && (col >= 0) &&
            (row < grid.length) && (col < grid[0].length)
        );
    }
    
    private void dfs(int[][] grid,
                     boolean[][] visited,
                     Island island,
                     int row, 
                     int col) {
        
        // System.out.println("Calling dfs for row = " + row + ", col = " + col);
        
        visited[row][col] = true; // visit it
        island.addIndex(row, col);
        
        // for each child
        for(int[] move: movements){
            int newRow = row + move[0];
            int newCol = col + move[1];
            
            if((isInside(newRow, newCol, grid) == true) &&
                (grid[newRow][newCol] == 1) &&
                (visited[newRow][newCol] == false)) {
                
                // visited[newRow][newCol] = true;
                // island.addIndex(newRow, newCol);
                
                // call dfs
                dfs(grid, visited, island, newRow, newCol);
            }
        }
    }
}






