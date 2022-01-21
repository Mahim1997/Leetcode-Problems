class Solution {
    // private int[] costs;
    private int[] days;
    private int numDays;
    private int[] changeDays;
    private int[] costs;
    
    private Map<Integer, Integer> cache;
    
    public int mincostTickets(int[] days, int[] costs) {
        // this.costs = costs;
        this.days = days;
        this.numDays = days.length;
        
        this.costs = costs;
        
        this.changeDays = new int[3];
        this.changeDays[0] = 1;
        this.changeDays[1] = 7;
        this.changeDays[2] = 30;
        
        this.cache = new HashMap<>();
        return dp(0);
    }

    private boolean isWithin(int nextIdx, int prevIdx, int numDays){
        return (
            (this.days[nextIdx] - this.days[prevIdx] + 1) <= numDays
        );
    }
    
    private boolean areValuesWithin(int v1, int v2, int n){
        return(
            (v1 - v2 + 1) <= n
        );
    }
    
    private int dp(int idxCurrent){
        // Base case
        if(idxCurrent == this.numDays){
            return 0;
        }
        
        // Check in cache
        if(this.cache.containsKey(idxCurrent) == true){
            return this.cache.get(idxCurrent);
        }
        

        int min = Integer.MAX_VALUE;
        
        // for costs[0] i.e. 1 day
        int costForOneDay = dp(idxCurrent + 1) + costs[0];
        
        // for costs[1] i.e. 7 days
        // find the next index to call.
        int idxToCall = idxCurrent;
        for(idxToCall=idxCurrent; idxToCall<this.numDays; idxToCall++)
        {
            if(isWithin(idxToCall, idxCurrent, this.changeDays[1])){
                continue;
            }else{
                break;
            }
        }
        int costFor7Days = dp(idxToCall) + costs[1];
        
        // for costs[2] i.e. 30 days
        // find the next index to call.
        for(idxToCall=idxCurrent; idxToCall<this.numDays; idxToCall++)
        {
            if(isWithin(idxToCall, idxCurrent, this.changeDays[2])){
                continue;
            }else{
                break;
            }
        }
        
        int costFor30Days = dp(idxToCall) + costs[2];
        
        min = getMin(
            costForOneDay,
            costFor7Days,
            costFor30Days
        );
        
        // insert into cache
        this.cache.put(idxCurrent, min);
        
        // return it
        return min;
    }
    
    private int getMin(int v1, int v2, int v3){
        return Math.min(Math.min(v1, v2), v3);
    }
    
}