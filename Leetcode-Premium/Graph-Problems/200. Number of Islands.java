class Solution {
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    
    private boolean isWithinBounds(int row, int col, char[][] grid){
        return (
            (row >= 0) &&
            (row < grid.length) &&
            (col >= 0) &&
            (col < grid[0].length)
        );
    }
    
    private void printArr(char[][] grid){
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public int numIslands(char[][] grid) {
        // Not using extra space, using grid[][] as extra space
        // grid[i][j] == '$', then it is visited.
        // BFS style.
        
        int counter = 0;
        for(int row=0; row<grid.length; row++){
            for(int col=0; col<grid[row].length; col++){
                if(grid[row][col] == '1'){
                    // RUN BFS and fill-up the island
                    runBFS(row, col, grid);
                    counter++;
                    
                    // System.out.println("Calling for row = " + row 
                    //                    + ", col = " + col);
                    // printArr(grid);
                }
            }
        }
        
        // replace char[][] grid back to normal
        for(char[] arr: grid){
            for(char c: arr){
                if(c == '$'){
                    c = '1';
                }
            }
        }
        
        return counter;
    }
    
    private void runBFS(int row, int col, char[][] grid){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        
        while(!queue.isEmpty()){
            int[] arr = queue.remove();
            int r = arr[0]; int c = arr[1];
            
            // System.out.println("QUEUE: r = " + r + ", c = " + c);
            // already visited.
            if(grid[r][c] == '$'){
                continue;
            }
 
            // mark as visited
            grid[r][c] = '$';
            
            // place children
            for(int move=0; move<dx.length; move++){
                int delX = dx[move];
                int delY = dy[move];
                int newR = r + delY;
                int newC = c + delX;
                if(isWithinBounds(newR, newC, grid) && 
                   (grid[newR][newC] == '1')){
                    queue.add(new int[]{newR, newC});
                }
            }            
        }
    }
    
}
