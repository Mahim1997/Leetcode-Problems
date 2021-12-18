class Solution {
    
    public int maxProfit(int maxTransactions, int[] prices){
        int[][][] cache = new int[prices.length+1][maxTransactions+1][2];
        
        for(int i=0; i<cache.length; i++){
            for(int j=0; j<cache[i].length; j++){
                for(int k=0; k<cache[i][j].length; k++){
                    cache[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        
        return dp(0, maxTransactions, 0, cache, prices);
    }
    
    private int dp(int startDay, int numTransactionsRemain, 
                    int mode, int[][][] cache, int[] prices){
        // mode == 0, can buy, mode == 1, can sell
        if(startDay == prices.length){ // end is reached
            // AFTER last day.
            return 0;
        }
        if(numTransactionsRemain == 0) // no more transactions possible
            return 0;
        
        if(cache[startDay][numTransactionsRemain][mode] != Integer.MIN_VALUE){
            return cache[startDay][numTransactionsRemain][mode];
        }
        
        int hold=0, buy=0, sell=0, max=Integer.MIN_VALUE;
        if(mode == 0){ // can buy
            hold = dp(startDay+1, numTransactionsRemain, mode, cache, prices);
            // flip(0) = 1 -> 1 - 0 = 1
            buy = dp(startDay+1, numTransactionsRemain, 1-mode, cache, prices) 
                    - prices[startDay]; 
            max = Math.max(hold, buy);
        }else{ // can sell
            hold = dp(startDay+1, numTransactionsRemain, mode, cache, prices);
            // flip(1) = 1 -> 1 - 1 = 0, transactions--
            sell = dp(startDay+1, numTransactionsRemain-1, 1-mode, cache, prices)
                    + prices[startDay];
            max = Math.max(hold, sell);
        }
        cache[startDay][numTransactionsRemain][mode] = max;
        return max;
    }
}