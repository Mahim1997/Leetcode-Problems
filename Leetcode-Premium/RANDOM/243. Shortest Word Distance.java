class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) 
    {
        // List<Integer> indicesWord1 = new ArrayList<>();
        // List<Integer> indicesWord2 = new ArrayList<>();
        
        int minDist = Integer.MAX_VALUE; 
        int n = wordsDict.length;
        
        int idx1Last = -1;
        int idx2Last = -1;
        
        for(int i=0; i<n; i++){
            String word = wordsDict[i];
            int currentDist = Integer.MAX_VALUE;
            
            if(word.equals(word1)){
                idx1Last = i;
                if(idx2Last != -1){
                    currentDist = Math.abs(idx2Last - i);
                }
            }
            if(word.equals(word2)){
                idx2Last = i;
                if(idx1Last != -1){
                    currentDist = Math.abs(idx1Last - i);
                }
                
            }
            minDist = Math.min(minDist, currentDist);
        }
        
        return minDist;
    }
}