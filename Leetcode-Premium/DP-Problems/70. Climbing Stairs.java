class Solution {
    public int climbStairs(int n) {
        return bottomUpConstantSpace(n);
        // return bottomUpApproach(n);
        
        // int[] cache = new int[n + 1];
        // Arrays.fill(cache, -1); // initially use as -1.
        
        // return topDownApproach(n, cache);
        // return climbStairs(n - 2) + climbStairs(n - 1);
    }
    
    private int topDownApproach(int n, int[] cache){
        if(n <= 2){ // Base Case
            cache[n] = n;
        }
        else if(cache[n] == -1){ // Already exists
            cache[n] = topDownApproach(n - 1, cache) + topDownApproach(n - 2, cache);
        }
        return cache[n];
    }
    
    private int bottomUpApproach(int n){
        if(n <= 2)
            return n;
        
        int[] arr = new int[n+1];
        for(int i=0; i<=2; i++){
            arr[i] = i;
        }
        
        for(int i=3; i<=n; i++){
            arr[i] = arr[i - 2] + arr[i - 1];
        }
        
        return arr[n];
    }
    
    private int bottomUpConstantSpace(int n){
        if(n <= 2)
            return n;
        
        int elementTwoPrevious = 1; // arr[1] = 1
        int elementPrevious = 2;    // arr[2] = 2
        int currentElement = 0;     // arr[3] onwards
        
        for(int i=3; i<=n; i++){
            currentElement = elementTwoPrevious + elementPrevious;
            
            elementTwoPrevious = elementPrevious;
            elementPrevious = currentElement;
        }
        
        return currentElement;
    }


}