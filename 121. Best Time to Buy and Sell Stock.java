class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 1){
            return 0;
        }
        int low = prices[0];
        int maxProfit = 0;
        int highestSoFar = 0;
        int priceCurrent, profitCurrent;
        
        for(int i=1; i<prices.length; i++){
            priceCurrent = prices[i];
            
            if(priceCurrent <= low){ // same OR smaller, low will update
                low = priceCurrent;
                highestSoFar = 0;
            }else{ // bigger
                profitCurrent = priceCurrent - low;
                if(priceCurrent >= highestSoFar){ // update max profit and so far max/HIGH
                    maxProfit = Math.max(profitCurrent, maxProfit);
                    highestSoFar = priceCurrent;
                } // else do nothing.
            }
        }
        
        
        return maxProfit;
    }
}