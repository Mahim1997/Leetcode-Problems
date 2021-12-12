class Solution {
    
    Map<Position, Integer> map = new HashMap<>();
    
    static class Position{
        int idx; int first;
        
        Position(int i, int f){
            idx = i;
            first = f;
        }
        
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + this.idx;
            hash = 53 * hash + this.first;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Position other = (Position) obj;
            if (this.idx != other.idx) {
                return false;
            }
            if (this.first != other.first) {
                return false;
            }
            return true;
        }
        
    }
    
    public int maximumScore(int[] nums, int[] multipliers) {
        // return dp(0, 0, nums, multipliers);
        return bottomUp(nums, multipliers);
    }
    
    private int bottomUp(int[] nums, int[] multipliers){
        int[][] dp = new int[multipliers.length + 1][multipliers.length + 1];
        
        // base cases ->
        // eg. dp[0][0] is the ans.
        // nums.length = n = 6, mult.length = m = 5
        /*  dp[0][0] = max(dp[1][0] + n[0]*m[5], dp[1][1] + n[0]*m[0])
            ......
            dp[m][i1....i2] = 0
            
        */
        
        // traverse from right to left.
        for(int idx=multipliers.length-1; idx>=0; idx--){ // idx goes from end to start.
            for(int left=idx; left>=0; left--){  // left goes from 
                int right = nums.length - 1 + left - idx;
                int mult = multipliers[idx];
                
                dp[idx][left] = Math.max(
                    mult*nums[left]  + dp[idx+1][left+1],   // pick left
                    mult*nums[right] + dp[idx+1][left]      // pick right
                );
            }
        }
        
        return dp[0][0];
    }
    
    // idx -> multipliers index
    private int dp(int idx, int first, int[] nums, int[] multipliers){
       // base cases.
        // System.out.println("Calling for idx = " + idx + ", f = " + first + ", l = " + last);
        int last = nums.length - 1 + first - idx;
        if(idx == multipliers.length-1){
            // check signs.
            if(multipliers[idx] < 0){
                return (multipliers[idx] * Math.min(nums[first], nums[last]));
            }else{
                return (multipliers[idx] * Math.max(nums[first], nums[last]));
            }
            
        }
        // check in dictionary
        Position p = new Position(idx, first);
        if(map.containsKey(p)){
            return map.get(p);
        }
        
        // recurrence relation.
        if((first <= last) && (idx < multipliers.length)){
            int ans;
            ans = Math.max(
                dp(idx+1, first+1, nums, multipliers) 
                    + multipliers[idx]*nums[first],
                
                dp(idx+1, first, nums, multipliers)
                    + multipliers[idx]*nums[last]
            );
            
            
            map.put(p, ans);
            return map.get(p);
        }
        
        // System.out.println("RETURINNG -10000 for idx = " + idx + ", f = " + first + ", l = " + last);
        
        return -1000000;
    }
}