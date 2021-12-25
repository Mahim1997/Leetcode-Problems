class Solution {
    private boolean isValid(String s){
        if(s.equals("0"))
            return false;
        if(s.startsWith("0"))
            return false;
        int x = Integer.parseInt(s);
        return ((x >= 1) && (x <= 26));
    }
    
    private Map<Integer, Integer> cache;
    private String str;
    
    public int numDecodings(String s) {
        if(s.startsWith("0")) // contains any leading zeros.
            return 0;
        
        // dp(0) = 0
        
        
        // no need to store the maps.
        
        /*
            dp(i) = 
                      dp(i - 1) // if s.substr(i-1, i+1) IS NOT zero
                    + dp(i - 2) // if s.substr(i-2, i+1)
            & doesn't have trailing zeros, and [1, 26]
        */
        // this.cache = new HashMap<>();
        // this.str = s;
        // return dp(s.length() - 1);
        
        return bottomUp(s);
    }
    
    private int bottomUp(String s){
        int strlen = s.length();
        if(strlen == 1){
            if(isValid(s))
                return 1;
        }
        
        int[] dp = new int[strlen + 1];
        dp[0] = 1; // -1 is 1
        
        for(int i=1; i<dp.length; i++){
            int oneVal = 0, twoVal = 0;
            if(isValid(s.substring(i-1, i))){
                oneVal = dp[i - 1];
            }
            if(i > 1){
                if(isValid(s.substring(i-2, i))){
                    twoVal = dp[i - 2];
                }
            }
            dp[i] = oneVal + twoVal;
        }
        
        return dp[strlen];
    }
    
    private int dp(int index){
        if(index == -1)
            return 1;  // actually all are matched !!
        if(index < -1)
            return 0;
        
        // int strlen = this.str.length();
        if(this.cache.containsKey(index)){
            return this.cache.get(index);
        }
        
        int oneCharVal = 0, twoCharVal = 0;

        String strOneChar = this.str.substring(index, index+1);
        if(this.isValid(strOneChar)){
            oneCharVal = dp(index - 1);
        }
   
        if(index > 0){
            String strTwoChar = this.str.substring(index-1, index+1);
            if(this.isValid(strTwoChar)){
                twoCharVal = dp(index - 2);
            }
        }
        
        int ans = oneCharVal + twoCharVal;
        this.cache.put(index, ans);
        
        // put into cache, return answer
        return ans;
    }
    
}