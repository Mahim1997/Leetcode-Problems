class Solution {
    // key: time%60, val: list of indices
    
    Map<Integer, List<Integer>> map;
    public int numPairsDivisibleBy60(int[] times) {
        this.map = new HashMap<>();
        
        int cnt = 0;
        for(int i=0; i<times.length; i++){
            int time = times[i]%60;
            
            // check in map.
            int remainingTime = 0;
            if(time != 0)
                remainingTime = 60 - time;
            
            if(map.containsKey(remainingTime) == true){
                cnt += map.get(remainingTime).size();
            }
            
            // add to map.
            if(map.containsKey(time) == false){
                map.put(time, new ArrayList<>());
            }
                
            map.get(time).add(i); // add the index
            
            
        }
        
        return cnt;
        
    }
}

/*
class Solution {
    // key: time%60, val: count of items so far

    public int numPairsDivisibleBy60(int[] times) { 
        int[] freqArr = new int[60];
        Arrays.fill(freqArr, 0);
        
        int cnt = 0;
        for(int i=0; i<times.length; i++){
            int time = times[i]%60;
            
            // check in map.
            int remainingTime = 0;
            if(time != 0)
                remainingTime = 60 - time;
            
            // by default is zero
            cnt += freqArr[remainingTime];
            freqArr[time]++; // simply increment
        }
        
        return cnt;
        
    }
}
*/