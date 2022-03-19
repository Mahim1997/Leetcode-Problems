class Solution {
    // private Map<Pair<Integer, Integer>, Integer> cache;
    private int[][] cache;
    private int carpetLen;
    private String floor;
    
    private int[] numWhitesFromIndex;
    
    private void initializeCache(String floor, int numCarpets){
        int strlen = floor.length();
        this.cache = new int[strlen][numCarpets+1];
        
        for(int i=0; i<strlen; i++){
            for(int j=0; j<=numCarpets; j++){
                this.cache[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=0; i<strlen; i++){
            this.cache[i][0] = this.numWhitesFromIndex[i];
        }
    }
    
    private void precomputeWhites(){
        int strlen = floor.length();
        this.numWhitesFromIndex = new int[strlen];
        Arrays.fill(this.numWhitesFromIndex, 0);
        for(int i=strlen-1; i>=0; i--){
            int current = (this.floor.charAt(i) == '1') ? 1 : 0;
            if((i + 1) < strlen){
                numWhitesFromIndex[i] = current + numWhitesFromIndex[i + 1];
            }else{
                numWhitesFromIndex[i] = current;
            }
        }
        
    }
    
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        // Paint current Index OR not paint current Index
        // this.cache = new HashMap<>();
        
        this.carpetLen = carpetLen;
        this.floor = floor;
        
        precomputeWhites(); // precompute numWhites
        
        initializeCache(floor, numCarpets);
        
        return dfs(0, numCarpets);
    }
    
    private int dfs(int index, int numCarpets){
        int strlen = this.floor.length();
        // edge cases
        if(index >= strlen){
            return 0;
        }
        
        if(numCarpets == 0){
            // return dfs(index + 1, 0); // no more carpets, simply incerment
            return this.numWhitesFromIndex[index];
        }
        
        // cache check
        // Pair<Integer, Integer> key = new Pair<>(index, numCarpets);
        // if(this.cache.containsKey(key)){
            // return this.cache.get(key);
        // }
        
        if(this.cache[index][numCarpets] != Integer.MAX_VALUE){
            return this.cache[index][numCarpets];
        }
        
        // answer compute
        // 1. Put a black tile, skip to index + carpetLen
        int putBlackTile = dfs(index+this.carpetLen, numCarpets-1);
        
        // 2. Do not put a black tile, if its a white tile, 1 + dfs(i + 1), otherwise simply dfs(i + 1)
        int add = (this.floor.charAt(index) == '1') ? 1 : 0;
        int dontPutBlackTile = add + dfs(index+1, numCarpets);
        
        int answer = Math.min(putBlackTile, dontPutBlackTile);
        
        // cache update
        // this.cache.put(key, answer);
        this.cache[index][numCarpets] = answer;
        
        // reuturn
        return answer;
    }
}