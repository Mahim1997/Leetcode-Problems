class Solution {
    long sum = 0;
    
    private void getSum(int[] piles){
        for(int pile: piles)
            sum += pile;
    }
    
    public int minEatingSpeed(int[] piles, int h) {
        this.getSum(piles); // get the sum

        long hLong = (long) h;
        long firstValue = sum/hLong;
        firstValue = (sum%h == 0) ? firstValue : firstValue + 1;
        
        long lastValue = 0;
        for(int pile: piles){
            lastValue = Math.max(lastValue, (long) pile);
        }
        
        long currValue = firstValue;
        long midValue;
        
        
        // Binary Search
        while(firstValue < lastValue){
            midValue = firstValue + (lastValue - firstValue)/2;
            
            // cnt works in reverse order ...
            int cnt = getNumHours(piles, midValue);
            if(cnt > h){
                // move firstValue, so cnt will decrease
                firstValue = midValue + 1;
            }else{
                // move lastValue, so cnt will increase
                lastValue = midValue;
            }
        }
        return (int) lastValue;
        // return 0;
    }
    
    private int getNumHours(int[] piles, long k){
        int cnt = 0;
        for(int pile: piles){
            int div = pile/(int)k;
            if(pile%k == 0){
                // ignore
            }else{
                div++;
            }
            cnt += div;
        }
        return cnt;
    }
}