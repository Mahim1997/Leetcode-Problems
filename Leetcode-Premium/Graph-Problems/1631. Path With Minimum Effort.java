class Solution {
    private void printArray(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private boolean isDest(int r, int c, int nRows, int nCols){
        return  ((r == (nRows - 1)) && 
                 (c == (nCols - 1)));
    }
    
    private boolean isWithin(int r, int c, int nRows, int nCols){
        return  (r >= 0) &&
                (c >= 0) &&
                (r < nRows) &&
                (c < nCols);
    }
    
    private int getIndex(int r, int c, int nCols){
        return (r*nCols + c);
    }
    private int[] getRowCol(int idx, int nCols){
        return new int[]{idx/nCols, idx%nCols};
    }
    
    // Uses Dijkstra's Algorithm
    private int usingDijkstrats(int[][] heights){
        int nRows = heights.length;
        int nCols = heights[0].length;
        
        int srcRow = 0, srcCol = 0;
        
        int[] moveRows = {-1,  0, 1, 0};
        int[] moveCols = { 0, -1, 0, 1};
        
        // Set<Integer> visited = new HashSet<>();
        
        // {row, col, effortSoFar}
        PriorityQueue<int[]> pq;
        pq = new PriorityQueue<>((arr1, arr2) -> (arr1[2] - arr2[2]));
        
        
        int[][] distances = new int[nRows][nCols];
        
        for(int[] arr: distances){
            Arrays.fill(arr, Integer.MAX_VALUE);    
        }
        
        // source
        distances[srcRow][srcCol] = 0;
        
        pq.add(new int[]{srcRow, srcCol, 0});
        
        while(!pq.isEmpty()){
            int[] top = pq.remove();
            int r = top[0], c = top[1], effortSoFar = top[2];
            
            // System.out.println("pq remove, r = " + r + ", c = " + c + ", effortSoFar = " + effortSoFar);
            
            // already visited
            // // if(visited.contains(getIndex(r, c, nCols)))
            // //     continue;
            
            // add to visited set
            // visited.add(getIndex(r, c, nCols));
            
            // add children by movements
            for(int j=0; j<moveRows.length; j++){
                int dRow = moveRows[j];
                int dCol = moveCols[j];
                
                int newR = r + dRow;
                int newC = c + dCol;
                
                // only within movement, then update and push to pq
                if(isWithin(newR, newC, nRows, nCols)){
                    int edgeDistance = Math.abs(
                        heights[newR][newC] - heights[r][c]
                    );
                    
                    // path distance will be this
                    int altDistance = Math.max(edgeDistance, 
                                               effortSoFar);
                    
                    // System.out.println("newR = " + newR + ", newC = " + newC + ", effortSoFar = " + effortSoFar + ", altDistance = " + altDistance);
                    
                    if(altDistance < distances[newR][newC]){
                        // push to pq.
                        distances[newR][newC] = altDistance;
                        pq.add(new int[]{newR, newC, altDistance});
                    }
                    
                }
            }
            
        }
        
        return distances[nRows-1][nCols-1];    
    }
    
    public int minimumEffortPath(int[][] heights) {
        // Edge cases, only one element
        if((heights.length == 1) && (heights[0].length == 1))
            return 0; // no effort should be required 
            // heights[0][0];
        
        return usingDijkstrats(heights);
    }
}

