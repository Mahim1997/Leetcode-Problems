class Solution {
    private int fee;
    private int[] prices;
    private int[][] cache;
    
    private void initializeCache(){
        // Dim1: number of days
        // Dim2: mode -> 0/buy, 1/sell
        this.cache = new int[prices.length + 1][2];
        for(int[] arr: this.cache){
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
    }
    
    public int maxProfit(int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;
        initializeCache();
        int startDay = 0, startMode = 0; // 0 -> buy.
        return dp(startDay, startMode);
    }
    private int getNextMode(int mode){
        return (mode + 1)%2;
    }
    
    private int dp(int startDay, int mode){
        // base cases.
        if(startDay == this.prices.length)
            return 0;
        // inside cache checking.
        int val = this.cache[startDay][mode];
        if(val != Integer.MIN_VALUE)
            return val;
        
        int maxProfit = 0;
        if(mode == 0){ // mode -> buy
            maxProfit = Math.max(
                dp(startDay + 1, mode), // hold
                // buy the stock.
                -this.prices[startDay] + dp(startDay + 1, getNextMode(mode)) 
            );
        }else{ // mode -> sell
            maxProfit = Math.max(
                dp(startDay + 1, mode), // hold
                // sell the stock -> pay the fell
                this.prices[startDay] - this.fee + dp(startDay + 1, getNextMode(mode))
            );
        }
        
        this.cache[startDay][mode] = maxProfit;
        return maxProfit;
    }
}