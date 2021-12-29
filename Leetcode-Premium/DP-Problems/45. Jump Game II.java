class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        
        if(n == 1)
            return 0;
        if(nums[0] == 0)
            return 0;
        
        
        for(int i=0; i<n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        
        dp[0] = 0;
        
        for(int i=1; i<n; i++){
            // if(dp[i] != Integer.MAX_VALUE) // already filled
            //     continue;
            
            int counter = dp[i - 1] + 1;
            int num = nums[i - 1];
            
            // System.out.println("i = " + i + ", num = " + num +
                               // ", counter = " + counter);
            
            for(int j=0; j<num; j++){
                int idx = i + j;
                // System.out.println(">> idx = " + idx + ", cnt = " + counter);
                if(idx < n)
                    if(dp[idx] == Integer.MAX_VALUE)
                        dp[idx] = counter;
            }
            
            // printArr(dp); // debug
        }
        
        
        return dp[n - 1];
    }
    
    private void printArr(int[] dp){
        for(int x: dp){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
}