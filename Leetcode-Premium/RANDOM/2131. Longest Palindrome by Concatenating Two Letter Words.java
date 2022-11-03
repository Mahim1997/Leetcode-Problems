class Solution {
    private Map<String, Integer> getCounts(String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        for(String word: words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        return counts;
    }
    
    private String getReverse(String word) {
        // Can do more efficiently ?
        return new StringBuilder(word).reverse().toString();
    }
    
    public int longestPalindrome(String[] words) {
        Map<String, Integer> counts = getCounts(words);
        Set<String> seen = new HashSet<>();
        
        boolean isMiddleTaken = false;
        int lengthRunning = 0;
        
        for(String word: words) {
            String reverse = getReverse(word);
            
            // It was processed previously (or its reverse)
            if(seen.contains(word) || seen.contains(reverse)) {
                continue;
            }
            
            if(word.equals(reverse)) {
                int count = counts.getOrDefault(word, 0);
                if(count%2 != 0) {
                    // odd => if middle is not taken, only then take it
                    if(!isMiddleTaken) {
                        isMiddleTaken = true;
                    }
                    else {
                        count--; // only take the even number of items
                    }
                }
                lengthRunning += (count * word.length());
            }
            else {
                int countWord = counts.getOrDefault(word, 0);
                int countReverse = counts.getOrDefault(reverse, 0);
                int countMin = Math.min(countWord, countReverse);
                
                // 2* ==> take both word & its reverse
                lengthRunning += (countMin * 2 * word.length());
            }
            
            // If both are equal, set will ensure only one is added
            seen.add(word);
            seen.add(reverse);
        }
        
        return lengthRunning;
    }
}


