class Solution {
    public int maxDistToClosest(int[] seats) {
        int maxDist = Integer.MIN_VALUE;
        int n = seats.length;
        
        
        int firstOneIdx = 0;
        for(int i=0; i<seats.length; i++){
            if(seats[i] == 1)
            {
                firstOneIdx = i;
                break;
            }
        }
        
        // only one '1' exists
        if(firstOneIdx == (n - 1)){
            return (firstOneIdx - 0);
        }
        
        maxDist = firstOneIdx - 0;
        
        int i = firstOneIdx + 1;
        int lastOneIdx = firstOneIdx;
        
        for(; i<seats.length; i++){
            if(seats[i] == 1){
                lastOneIdx = i;
                int currDist = i - firstOneIdx;
                currDist/=2;
                maxDist = Math.max(maxDist, currDist);
                
                firstOneIdx = i;
            }
        }
    
        if(lastOneIdx != (n - 1)){
            maxDist = Math.max(maxDist, (n - 1 - lastOneIdx));
        }
        
        return maxDist;
    }
    
    
}