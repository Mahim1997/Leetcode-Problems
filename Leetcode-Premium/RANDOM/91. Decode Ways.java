class Solution {
    private boolean isWithin(char c, char left, char right) {
        return ((c >= left) && (c <= right));
    }
    
    private boolean isFirstValid(char first) {
        return (first != '0');
    }
    
    private boolean isFirstSecondValid(char first, char second) {
        if(first == '0')
            return false;
        
        if(first == '1')
            return true;
        
        if(first == '2')
            return isWithin(second, '0', '6');
        
        return false;
    }
    
    public int numDecodings(String s) {
        int n = s.length();
        // For extra space, no need for bound checking
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        
        // Base cases [last char]
        dp[n] = 1; // redundant 1 for generalized coding
        dp[n - 1] = (s.charAt(n - 1) == '0') ? 0 : 1;
        if(n == 1) {
            return dp[0];
        }
        
        // Recursive cases
        for(int i=n-2; i>=0; i--) {
            int toPlace = 0;
            
            char first = s.charAt(i);
            char second = s.charAt(i + 1);

            if(isFirstSecondValid(first, second)) {
                toPlace = dp[i + 1] + dp[i + 2];
            }
            else if(isFirstValid(first)) {
                toPlace = dp[i + 1];
            }
            else {
                toPlace = 0;
            }
            
            dp[i] = toPlace;
        }
        
        // System.out.println("dp: " + Arrays.toString(dp));
        
        return dp[0];
    }
}