class Solution {
    private int[] values;

    private Map<Integer, Integer> cache;
    // private int[][] cache;
    
    // private void initializeCache(){
    //     this.cache = new int[]
    // }
    
    public String stoneGameIII(int[] values) {
        this.values = values;
//         this.cache = new HashMap<>();
        
//         // int sum = 0;
//         // for(int v: values){sum += v;}
//         int diffAliceBob = dfs(0);
//         System.out.println("diffAliceBob = " + diffAliceBob);
        // return (diffAliceBob == 0) ? "Tie": 
        //         ((diffAliceBob >= 0) ? "Alice": "Bob");
        return bottomUP();
    }
    
    private String bottomUP(){
        int n = this.values.length;
        int[] dp = new int[n + 1];
        dp[n] = 0; // base case
        for(int i=n-1; i>=0; i--){
            int ans = Integer.MIN_VALUE;
            int sumSoFar = 0;
            for(int stones=0; stones<3; stones++){
                if((i + stones) < n){
                    sumSoFar += this.values[i + stones];
                    int current = sumSoFar - dp[i + stones + 1];
                    ans = Math.max(ans, current);   
                }
                dp[i] = ans;
            }            
        }
        int val = dp[0];
        if(val == 0){
            return "Tie";
        }else if(val > 0){
            return "Alice";
        }else{
            return "Bob";
        }
    }
    
    private int dfs(int idx){
        // System.out.println("Calling for idx = " + idx);
        
        if(idx >= values.length)
            return 0;
    
        if(this.cache.containsKey(idx) == true){
            return this.cache.get(idx);
        }
        
        int ans = Integer.MIN_VALUE;
        
        // int take1Stone  = this.values[idx] + dfs(idx + 1);
        // int take2Stones = this.values[idx] 
        //                     + this.values[idx + 1] 
        //                     + dfs(idx + 2);
        // int take3Stones = this.values[idx]
        //                     + this.values[idx + 1]
        //                     + this.values[idx + 2]
        //                     + dfs(idx + 3);
        
        int sumSoFar = 0;
        for(int stones=0; stones<3; stones++){
            if((idx + stones) < this.values.length){
                sumSoFar += this.values[idx + stones];
                int current = sumSoFar - dfs(idx + stones + 1);
                ans = Math.max(ans, current);                
            }
        }
        
        
        this.cache.put(idx, ans);
        return ans;
    }
    
}