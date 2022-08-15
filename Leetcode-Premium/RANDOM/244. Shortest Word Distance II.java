class WordDistance {

    private Map<String, List<Integer>> wordIndices;
    
    public WordDistance(String[] wordsDict) {
        this.wordIndices = new HashMap<>();
        for(int i=0; i<wordsDict.length; i++) {
            String word = wordsDict[i];
            List<Integer> list = this.wordIndices.getOrDefault(
                word,
                new ArrayList<>()
            );
            list.add(i);
            this.wordIndices.put(word, list);
        }
    }
    
    public int shortest(String word1, String word2) {
        // Assuming word1 and word2 are in wordIndices
        List<Integer> indices1 = this.wordIndices.get(word1);
        List<Integer> indices2 = this.wordIndices.get(word2);
        
        if(indices1.size() > indices2.size()) {
            // make sure indices1 is the smaller one
            List<Integer> temp = indices1;
            indices1 = indices2;
            indices2 = temp;
        }
        
        int minDistance = Integer.MAX_VALUE;
        // For each index in indices1, get the 'two'/'one' closest index in indices2 [use bin serach], update min distance
        
        for(int index1: indices1) {
            int ret = Collections.binarySearch(indices2, index1);
            // ret should be negative as it will not be present
            // as word1 != word2 [given]
            int idx = -(ret + 1);
            int index2;
            
            if(idx == indices2.size()) {
                // then index1 has higher value compared to the largest value in indices1
                // can only take the smaller value i.e. the left side
                index2 = indices2.get(idx - 1);
                minDistance = Math.min(
                    minDistance, 
                    Math.abs(index1 - index2)
                );
            }
            else if(idx == 0) {
                // then index1 has lower value compared to the smallest value in indices1
                // can only take the larger value i.e. the right side
                index2 = indices2.get(idx);
                minDistance = Math.min(
                    minDistance, 
                    Math.abs(index1 - index2)
                );
            }
            else {
                // in the middle, can take both            
                index2 = indices2.get(idx);
                minDistance = Math.min(
                    minDistance, 
                    Math.abs(index1 - index2)
                );

                index2 = indices2.get(idx - 1);
                minDistance = Math.min(
                    minDistance, 
                    Math.abs(index1 - index2)
                );
            }
        }
        
        return minDistance;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */