class Solution {
    private Map<Pair<Integer, Integer>, Double> cache;
    public double champagneTower(int poured, 
                                 int query_row, int query_glass) {
        
        
        // recursive solution ... up until 0,0 [dp, memoization]
        this.cache = new HashMap<>();
        double totalAmount = getTotalAmount(query_row,
                                            query_glass, poured);
        // -ve
        if(totalAmount < 0)
            return 0;
            
        // [0, 1] -> as is
        if((totalAmount >= 0) && (totalAmount <= 1))
            return totalAmount;
        
        // overflow [only 1 will remain]
        if(totalAmount > 1)
            return 1;
        
        return -1;
    }
    
    // DP -> Recursive Memoization
    
    // This function calculates TOTAL amount in a glass (with surplus)
    // SUBTRACT '1' to get actual amount in glass
    private double getTotalAmount(int row, int col, int poured){
        // base case
        if((row == 0) && (col == 0))
            return poured;
        
        // cache checking
        Pair<Integer, Integer> key = new Pair<>(row, col);
        if(this.cache.containsKey(key)){
            return this.cache.get(key);
        }
        
        // recursive logic
        double ans = 0;
        
        // dp(i, j) relates with dp(i-1, j) and dp(i-1, j-1)
        int[] moveRow = {-1, -1}; // both are i-1
        int[] moveCol = { 0, -1}; // 1st: j, 2nd: j-1
    
        for(int mIdx=0; mIdx<moveRow.length; mIdx++){
            int newRow = row + moveRow[mIdx];
            int newCol = col + moveCol[mIdx];
            
//             System.out.println("row=" + row + ", col=" + col
//               + ", newRow=" + newRow + ", newCol=" + newCol);
            
            // check within bounds
            if((newRow < 0) || (newCol < 0) || (newCol > newRow))
                continue;
            
            // use '-1' to get extra
            double extra = getTotalAmount(newRow, newCol, poured) - 1;
            // System.out.println(">> extra = " + extra);
            
            // not possible to add
            extra = Math.max(extra, 0.0); // -ve numbers become 0
            
            // System.out.println("--- extra = " + extra);
            
            ans = ans + 0.5*extra; // 0.5 X comes from the logic
            // i.e. dp(i, j) =  getExtra of dp(i-1, j) X 0.5 +
                            //  getExtra of dp(i-1, j-1) X 0.5
            // System.out.println("row=" + row + ", col=" 
                               // + col + ", ans=" + ans);
        }
        
        // update cache & return answer
        this.cache.put(key, ans);
        return ans;
    }
}