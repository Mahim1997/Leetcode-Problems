class Solution {
    public int closedIsland(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        
        
        int count = 0;
        for(int r=0; r<ROWS; r++){
            for(int c=0; c<COLS; c++){
                Pair<Integer, Integer> pair = new Pair<>(r, c);
                
                if( (grid[r][c] == 0) &&
                    (visited.contains(pair) == false)){
                    
                    if(dfs(grid, visited, r, c) == true)
                        count++;
                    
                }
            }
        }
        
        
        return count;
    }
    
    private boolean isWithinBounds(int row, int col, int[][] grid){
        return (
            (row >= 0) &&
            (col >= 0) &&
            (row < grid.length) &&
            (col < grid[0].length)
        );
    }

    private int[][] movements = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private boolean dfs(int[][] grid,
                        Set<Pair<Integer, Integer>> visited,
                        int row, int col){
        
        if(isWithinBounds(row, col, grid) == false)
            return false;
        
        Pair<Integer, Integer> pair = new Pair<>(row, col);
        
        // if already in visited, OR contains a '1', then return true [THIS path, stop here]
        if(visited.contains(pair) || (grid[row][col] == 1))
            return true;
        
        // add to visited
        visited.add(pair);
        
        boolean answer = true;
        for(int[] move: movements){
            int rowNew = row + move[0], colNew = col + move[1];
            answer = answer & dfs(grid, visited, rowNew, colNew);
         }
        
        return answer;
    }
}





