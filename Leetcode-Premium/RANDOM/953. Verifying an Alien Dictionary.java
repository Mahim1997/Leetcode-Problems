class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> mapOrder = getOrderingMap(order);
        
        // back to back sorted, then continue
        // anyone not, then return false
        
        // one word is always lexicographically sorted
        if(words.length == 1)
            return true;
        
        for(int i=1; i<words.length; i++){
            String wordCurrent = words[i];
            String wordPrevious = words[i - 1];
            
            // previous must be lexicographically smaller than current
            if(areTwoWordsSorted(wordPrevious, wordCurrent, mapOrder) == false)
                return false;
        }
        
        return true;
    }
    
    private boolean areTwoWordsSorted(String word1, String word2, 
                                      Map<Character, Integer> mapOrder){
        int minLength = Math.min(word1.length(), word2.length());
        for(int i=0; i<minLength; i++){
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            
            
            if(mapOrder.get(c1) > mapOrder.get(c2)){
                // this idx is NOT sorted
                return false;
            }
            else if(mapOrder.get(c1) < mapOrder.get(c2)){
                // => lex sorted
                return true;
            }
            
            // otherwise, this idx is EQUAL, so continue implicitly
        }
        
        // remaining lengths, eg. word1 = "aa", word2 = "aab"
        // just checking if word1.length() is smaller
        // exactly equal ?? eg. "aaa", "aaa"
        return word1.length() <= word2.length();
    }
    
    private Map<Character, Integer> getOrderingMap(String order){
        Map<Character, Integer> mapOrder = new HashMap<>();
        int strlen = order.length();
        for(int i=0; i<strlen; i++){
            char c = order.charAt(i);
            mapOrder.put(c, i);
        }
        return mapOrder;
    }
}