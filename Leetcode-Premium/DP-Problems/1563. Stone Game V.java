class Solution {
    private int[] stoneValues;
    private int[][] cache;
    
    private int[][] sumAll;
    
    private int UNASSIGNED = -100000;
    
    private void printArr(int[][] arr){
        for(int[] _1D: arr){
            for(int x: _1D){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }
    
    private void initializeCache(){
        int n = stoneValues.length;
        this.cache = new int[n+1][n+1];
        for(int[] arr: this.cache){
            Arrays.fill(arr, UNASSIGNED);
        }
        
        this.sumAll = new int[n][n];
        for(int[] arr: this.sumAll){
            Arrays.fill(arr, 0);
        }

        for(int i=0; i<n; i++){
            // diagonal elements single value
            this.sumAll[i][i] = this.stoneValues[i];
            for(int j=i+1; j<n; j++){
                this.sumAll[i][j] = 
                    this.sumAll[i][j - 1] + this.stoneValues[j];
            }
        }
        
        
        // DEBUGGING
        // printArr(this.sumAll);
    }
    
    public int stoneGameV(int[] stoneValues) {
        if(stoneValues.length == 1)
            return 0;
        
        this.stoneValues = stoneValues;
        
        this.initializeCache();
    
        return dfs(0, stoneValues.length - 1);
    }
    
    // [first, last]: both are inclusive
    private int dfs(int first, int last){
        // System.out.println("first = " + first + ", last " + last);
        
        // base case: one element.
        if(first == last){
            // no need to return stoneValues[first], as sumAll[i][i] handles this
            return 0;
            // return this.stoneValues[first];
        }
        
        // check cache
        if(this.cache[first][last] != UNASSIGNED){
            return this.cache[first][last];
        }
        
        // maximize so, initially is MIN_VALUE
        int ans = Integer.MIN_VALUE;
        
        for(int i=first; i<last; i++){
            int left1 = first;  int right1 = i;
            int left2 = i+1;    int right2 = last;
            
            int sumLeft = this.sumAll[left1][right1];
            int sumRight = this.sumAll[left2][right2];
            
            int current = 0;
            
            // System.out.println("FOR LOOP, 1: (" + left1 + "," + right1 + "), "
            //           + "2: (" + left2 + "," + right2 + ") "
            //           + ", sumLeft = " + sumLeft + ", sumRight = " + sumRight);
            
            int takeLeft, takeRight;
            if(sumLeft != sumRight){
                if(sumLeft > sumRight){
                    // Bob will eliminate bigger one i.e. left eliminated
                    current = sumRight;
                    takeLeft = left2;
                    takeRight = right2;
                }else{
                    // Bob will eliminate bigger one i.e. right eliminated
                    current = sumLeft;
                    takeLeft = left1;
                    takeRight = right1;
                }
                
                // System.out.println("Calling takeLeft = " + takeLeft + 
                //                   ", takeRight = " + takeRight + 
                //                   ", current = " + current);
                
                ans = Math.max(ans,
                          current + dfs(takeLeft, takeRight)
                      );
            }else{
                // Alice will decide
                // System.out.println("ELSE COND. left1 = " + left1 + 
                //           ", right1 = " + right1 + ", left2 = " + left2 + 
                //           ", right2 = " + right2);
                
                ans = Math.max(ans,
                           Math.max(sumLeft + dfs(left1, right1), 
                                    sumRight + dfs(left2, right2)
                           )
                    );
            }
        }
        
        // System.out.println(">> first=" + first +", last=" + last+ ", ans=" + ans);
        
        this.cache[first][last] = ans;
        return ans;
    }
}
