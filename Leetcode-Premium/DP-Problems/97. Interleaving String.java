class Solution {
    private String s1;
    private int len1;
    
    private String s2;
    private int len2;
    
    private String dest;
    private int lenDest;
    
    private int[][] cache;
    
    private int UNASSIGNED = -10000;
    private int TRUE = 1;
    private int FALSE = 2;
    
    private void initialize(String s1, String s2, String s3){
        this.s1 = s1;
        this.s2 = s2;
        this.dest = s3;
        
        this.len1 = this.s1.length();
        this.len2 = this.s2.length();
        this.lenDest = this.dest.length();
        
        this.cache = new int[this.len1][this.len2];
        // for(int[][] _2D: this.cache){
        for(int[] _1D: this.cache){
            Arrays.fill(_1D, UNASSIGNED);
        }
        // }
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        // initialize data structures
        this.initialize(s1, s2, s3);
        
        // Edge cases
        if(s1.isEmpty()){
            return s2.equals(s3);
        }
        if(s2.isEmpty()){
            return s1.equals(s3);
        }
        if((this.len1 + this.len2) != this.lenDest){
            return false;
        }
        
        
        
        return (dfs(0, 0) == TRUE) ? true : false; // prefix-wise OR suffix-wise ??
    }

    private int dfs(int idx1, int idx2){
        // System.out.println("CALL FOR idx1 = " + idx1 + ", idx2 = " + idx2);
        
        // Base cases
        int idxDest = idx1 + idx2;
        if(idx1 == this.len1){
            return (this.s2.substring(idx2, this.len2)
                .equals(
                    this.dest.substring(idxDest, this.lenDest)
                )) ? TRUE : FALSE;
        }
        if(idx2 == this.len2){
            return (this.s1.substring(idx1, this.len1)
                .equals(
                    this.dest.substring(idxDest, this.lenDest)
                )) ? TRUE : FALSE;
        }
        
        // check cache
        if(this.cache[idx1][idx2] != UNASSIGNED){
            return this.cache[idx1][idx2];
        }
        
        // recursion
        int ans = FALSE;
        
        int ans1 = FALSE, ans2 = FALSE;
        if(this.s1.charAt(idx1) == this.dest.charAt(idxDest)){
            ans1 = dfs(idx1+1, idx2);
        }
        if(this.s2.charAt(idx2) == this.dest.charAt(idxDest)){
            ans2 = dfs(idx1, idx2+1);
        }
        if((ans1 == TRUE) || (ans2 == TRUE)){
            ans = TRUE;
        }
        
        this.cache[idx1][idx2] = ans;
        return ans;
    }

}








