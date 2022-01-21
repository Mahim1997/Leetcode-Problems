class Solution {    
    public int canCompleteCircuit(int[] gases, int[] costs) {
        int numSteps = gases.length;
        
        int sumGases = 0, sumCosts = 0;
        for(int i=0; i<gases.length; i++){
            sumGases += gases[i];
            sumCosts += costs[i];
        }
        
        // not possible
        if(sumGases < sumCosts)
            return -1;
        
        
        // guaranteed possible, hence any startIdx that reaches till the end without going negative cumulative sum, that starting Idx will be the answer.
        int startIdx = 0;
        int total = 0;
        for(int i=0; i<numSteps; i++){
            int diff = gases[i] - costs[i];
            total += diff;
            
            if(total < 0){
                startIdx = i + 1;
                total = 0;
            }
        }
        return startIdx;
        
//         // this.initializeDP();
//         int[] diff = new int[numSteps];
//         // naive approach ... will extend
//         for(int i=0; i<numSteps; i++){
//             diff[i] = gases[i] - costs[i];
//         }
        
//         for(int idx=0; idx<numSteps; idx++){
//             if(diff[idx] < 0){
//                 continue;
//             }
//             if(canReachEnd(diff, idx) == true){
//                 return idx;
//             }
//         }
//         return -1;
    }
    
    private boolean canReachEnd(int[] diff, int idx){
        int nextIdx = idx;
        int cuml = 0;
        for(int cnt=0; cnt<diff.length; cnt++){
            if(cnt == 0){
                nextIdx = idx;
            }else{
                nextIdx = getNextIdx(idx, diff.length);
            }
            cuml += diff[nextIdx];
            if((cnt < (diff.length - 1)) && (cuml <= 0))
                return false;
            if((cnt == (diff.length - 1)) && (cuml < 0))
                return false;
            
            idx = nextIdx;
        }
        return true;
    }
    
    int getNextIdx(int i, int n){
        // if(i == (this.gases.length - 1)){
        //     return 0;
        // }
        return (i + 1)%n;
    }

    
}