class Solution {
    int[] houses;
    int[][] costs;  // costs[houseIdx][colorIdx]
    int numHouses;  // num of houses
    int numColors;  // num of colors
    int initialTarget;
    private int[][][] cache;
    
    private int UNASSIGNED = -100000;
    private int MAX_VALUE = Integer.MAX_VALUE/2;
    
    private void initialize(){
        this.cache = new int[this.houses.length][this.initialTarget+1][this.numColors+1]; // colors will be from 1...numColors
        
        for(int[][] _2D: this.cache){
            for(int[] arr: _2D){
                Arrays.fill(arr, UNASSIGNED);
            }            
        }
    }
    
    public int minCost(int[] houses, int[][] costs, 
                       int m, int n, int target) {
        this.houses = houses;
        this.costs = costs;
        this.numHouses = m;
        this.numColors = n;
        this.initialTarget = target;
        
        // initialize data structures
        this.initialize();
        
        // houses == 1 check.
        if(this.houses.length == 1){
            if(this.houses[0] != 0){
                return 0;
            }else{
                int min = Integer.MAX_VALUE;
                for(int i=0; i<this.costs.length; i++){
                    for(int j=0; j<this.costs[i].length; j++){
                        min = Math.min(min, this.costs[i][j]);
                    }
                }
                return min;
            }
        }
        
        int ans = dfs(0, 0, 0);
        // initially start with 1 neighbor
        return (ans == this.MAX_VALUE) ? -1 : ans; 
    }
    
    // , int colorPrevious
    private int dfs(int idx, int numNeighbors, int colorPrevious){
        // System.out.println("Calling for idx = " + idx + ", numNeighbors = " + numNeighbors + ", colorPrevious = " + colorPrevious);
        
        if(numNeighbors > this.initialTarget){
            return this.MAX_VALUE;
        }
        if(idx == this.numHouses){
            return (numNeighbors == this.initialTarget) ? 0 : this.MAX_VALUE;
        }
        
        
        if(this.cache[idx][numNeighbors][colorPrevious] != UNASSIGNED){
            // if(this.cache[idx][numNeighbors] != this.MAX_VALUE)
            return this.cache[idx][numNeighbors][colorPrevious];
        }
        
        int ans = this.MAX_VALUE;
        
        // go with the i_th house
        if(this.houses[idx] != 0){ // house is already colored.
            int currentColor = this.houses[idx];
            int newNeighbors = getNewNeighbors(numNeighbors, 
                                               colorPrevious, 
                                               currentColor);
            // pass with current color
            ans = Math.min(ans, 0 + dfs(idx+1, newNeighbors, currentColor));            
        }
        else{   // house is not colored ... need to color
            for(int currentColor=1; currentColor<=this.numColors; currentColor++){
                int colorIdx = currentColor-1;
                // calculate new neighbors
                int newNeighbors = getNewNeighbors(numNeighbors, 
                                                   colorPrevious, 
                                                   currentColor);
                
                int currentCost = this.costs[idx][colorIdx];
                ans = Math.min(
                    ans,
                    currentCost + dfs(idx+1, newNeighbors, currentColor)
                );
            }
        }
        
        // System.out.println("For idx = " + idx + ", numNeighbors = " + numNeighbors + ", colorPrevious = " + colorPrevious + ", ans = " + ans);
        
        this.cache[idx][numNeighbors][colorPrevious] = ans;
        return ans;
    }
    
    // private Map<Integer, Integer> memo;

    private int getNewNeighbors(int numNeighbors, 
                                int colorPrevious, 
                                int currentColor){
        return (currentColor == colorPrevious) ? numNeighbors : (numNeighbors + 1);
    }
    
    private void printHouses(){
        for(int x: this.houses){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
 }