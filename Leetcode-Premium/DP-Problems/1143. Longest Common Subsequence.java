class Solution {
    
    static class Position{
        int idx1; int idx2;
        Position(int i1, int i2){idx1 = i1; idx2 = i2;}
        
        @Override
        public int hashCode(){
            int hashcode = 7;
            hashcode = hashcode*53 + idx1;
            hashcode = hashcode*53 + idx2;
            return hashcode;
        }
        
        @Override
        public boolean equals(Object o){
            Position p = (Position)o;
            return ((this.idx1 == p.idx1) && (this.idx2 == p.idx2));
        }
    }
    
    public int longestCommonSubsequence(String text1, String text2) {
        // Map<Position, Integer> cache = new HashMap<>();
        // return dpHashMap(text1.length() - 1, text2.length() - 1, text1, text2, cache);
        // int[][] arr = new int[text1.length() + 1][text2.length() + 1];
        // return dpArray(text1.length() - 1, text2.length() - 1, text1, text2, arr);
        return bottomUp(text1, text2);
    }
    
    private int bottomUp(String text1, String text2){
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        // initially all are zeros.
        for(int i=1; i<=text1.length(); i++){
            for(int j=1; j<=text2.length(); j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[text1.length()][text2.length()];
    }
    
    private int dpHashMap(int idx1, int idx2, String text1, String text2, Map<Position, Integer> cache){
        // base cases.
        if((idx1 <= -1) || (idx2 <= -1))
            return 0;
        
        Position p = new Position(idx1, idx2);
        if(cache.containsKey(p) == false){
            int ans;
            if(text1.charAt(idx1) == text2.charAt(idx2)){
                ans = 1 + dpHashMap(idx1 - 1, idx2 - 1, text1, text2, cache);
            }else{
                ans = Math.max(
                    dpHashMap(idx1 - 1, idx2, text1, text2, cache),
                    dpHashMap(idx1, idx2 - 1, text1, text2, cache)
                );
            }
            cache.put(p, ans);
        }
        return cache.get(p);
    }
}