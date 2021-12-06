class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 1){ // 1 element
            return 0;
        }        
        
        int profitCumulative = 0;
        int idxStart = 0;
        
        // loop until LOW found: starting descending order subarray is traversed.
        while((idxStart < prices.length - 1)
              && (prices[idxStart + 1] <= prices[idxStart])){
            idxStart++;           
        }

        if(idxStart >= prices.length){ // all descending order.
            return 0;
        }
        
        for(int i=idxStart+1; i<prices.length; i++){
            if(prices[i]>=prices[i-1]){ // keep taking the profit.
                profitCumulative += (prices[i] - prices[i - 1]);
            }
        }
        
        return profitCumulative;
    }
}