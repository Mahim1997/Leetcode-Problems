class Solution {
    private int[] prices;
    private int[][][] cache;
    
    private void initializeTopDownDPCache(int maxTransactions){
        // first dimension      -> day
        // second dimension     -> mode == 0, buy / == 1, sell
        // third dimension      -> num_transactions_remaining
        this.cache = new int[this.prices.length+1][2][maxTransactions+1];
        for(int[][] _2D: this.cache){
            for(int[] _1D: _2D){
                Arrays.fill(_1D, Integer.MIN_VALUE); // fill each val by -INF
            }
        }
    }
    
    public int maxProfit(int[] prices) {
        this.prices = prices;
        int maxTransactions = 2;
        int startDay = 0, startMode = 0;
        initializeTopDownDPCache(maxTransactions);
        return dp(startDay, startMode, maxTransactions);
    }
    
    private int getNextMode(int mode){
        return (mode + 1)%2;
    }
    
    private int dp(int startDay, int mode, int numTransactionsRemaining){
        // base cases.
        if(startDay == prices.length)
            return 0;
        if(numTransactionsRemaining == 0)
            return 0;
        
        // check if already exists.
        if(this.cache[startDay][mode][numTransactionsRemaining] != Integer.MIN_VALUE){
            return this.cache[startDay][mode][numTransactionsRemaining];
        }
        
        int maxProfit = 0;
        if(mode == 0){ // buy mode
            maxProfit = Math.max(
                dp(startDay + 1, mode, numTransactionsRemaining), // hold
                // buy -> one transaction is ONY BUY AND ONY SELL, hence
                // num transactions remain DOES NOT decrement
                dp(startDay + 1, getNextMode(mode), numTransactionsRemaining) - prices[startDay]
            );
        }else{ // sell mode
            maxProfit = Math.max(
                dp(startDay + 1, mode, numTransactionsRemaining), // hold
                // sell -> transaction gets decremented
                dp(startDay + 1, getNextMode(mode), numTransactionsRemaining-1) + prices[startDay]
            );
        }
        
        this.cache[startDay][mode][numTransactionsRemaining] = maxProfit;
        return maxProfit;
    }
    
}