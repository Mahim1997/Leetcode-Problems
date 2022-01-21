class Solution {    
    public int canCompleteCircuit(int[] gases, int[] costs) {
        int numSteps = gases.length;
        
        // this.initializeDP();
        int[] diff = new int[numSteps];
        // naive approach ... will extend
        for(int i=0; i<numSteps; i++){
            diff[i] = gases[i] - costs[i];
        }
        
        for(int idx=0; idx<numSteps; idx++){
            if(diff[idx] < 0){
                continue;
            }
            if(canReachEnd(diff, idx) == true){
                return idx;
            }
        }
        return -1;
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