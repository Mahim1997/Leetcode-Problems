class Solution {
    // save as variables.
    private int[] prices;
    private int[][] cache;
    
    private int[][] getTopDownCache(int[] prices){
        // first dimension -> i_th day
        // second dimension -> buy/sell/holdout 
        // [NO NEED for HOLDOUT state, as it is simply next day's profit]
        int[][] cache = new int[prices.length+1][2];
        // fill each with -INF value
        Arrays.stream(cache).forEach(a -> Arrays.fill(a, Integer.MIN_VALUE));
        return cache;
    }
    
    private void printCache(){
        for(int[] arr: this.cache){
            for(int x: arr){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }
    
    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.cache = getTopDownCache(prices);
        int ans = dp(0, 0);
        // printCache();
        return ans;
    }
    private int getNextMode(int mode){
        return (mode + 1)%3; // 2 -> (2 + 1)%3 = 3%3 = 0
    }
    
    private int dp(int startDay, int mode){
        if(startDay == this.prices.length)
            return 0; // END IS REACHED, no more stocks available.
        if((mode != 2) && (this.cache[startDay][mode] != Integer.MIN_VALUE))
            return this.cache[startDay][mode];
        
        int maxProfit = Integer.MIN_VALUE;
        if(mode == 0){ // buy mode
            maxProfit = Math.max(                
                dp(startDay+1, mode), // do nothing
                -prices[startDay] + dp(startDay+1, getNextMode(mode)) // buy stock
            );
        }
        else if(mode == 1){ // sell mode
            maxProfit = Math.max(
                dp(startDay+1, mode), // do nothing
                prices[startDay] + dp(startDay+1, getNextMode(mode)) // sell stock
            );
        }
        else if(mode == 2){ // holdout mode
            // do nothing by force
            maxProfit = dp(startDay+1, getNextMode(mode));
        }
        
        if(mode != 2)
            this.cache[startDay][mode] = maxProfit;
        
        return maxProfit;
    }
    
}