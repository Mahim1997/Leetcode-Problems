class Solution {
    private void printArr(int[] arr){
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println("");
    }
    
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        
        // return bottomUp(s, words);
        
        // int len = s.length();
        // return topDownApproach1(s, 0, words, new HashMap<>());
        return topDownApproach2(s, 0, words, new HashMap<>());
        // int[][] memo = new int[len][len]; // initially 0
        // return dp(s, 0, len - 1, words, len, memo);
        // left|right
    }
    
    // Iterate over every word
    private boolean topDownApproach2(String s, int firstIdx, Set<String> words, HashMap<Integer, Boolean> cache){
        int strlen = s.length();
        if(firstIdx == strlen){
            return true;
        }
        if(cache.containsKey(firstIdx)){
            return cache.get(firstIdx);
        }
        
        // iterate over every word
        for(String word: words){
            int wordLen = word.length();
            int newIdx = firstIdx + wordLen;
            if(newIdx <= strlen){ // can include.
                String leftWord = s.substring(firstIdx, newIdx);
                if(leftWord.equals(word)){ // left side matched !
                    boolean rightAns = topDownApproach2(s, newIdx, words, cache);
                    if(rightAns == true){
                        cache.put(firstIdx, true);
                        return true;
                    }
                }
            }
        }
        
        
        
        cache.put(firstIdx, false); // no substring possible.
        return false;
    }
    
    // Iterate over every index
    private boolean topDownApproach1(String s, int firstIdx, Set<String> words, HashMap<Integer, Boolean> cache){
        // System.out.println("s = " + s + ", firstIdx = " + firstIdx);
        int strlen = s.length();
        if(firstIdx == strlen){
            return true;
        }
        if(cache.containsKey(firstIdx)){
            return cache.get(firstIdx);
        }
        
        // iterate over every index
        for(int i=firstIdx; i<=strlen; i++){ // <= DUE to substr exclusive
            String leftStr = s.substring(firstIdx, i);
            if(words.contains(leftStr)){
                // i is EXCLUSIVE for SUBSTR RIGHT SIDE
                boolean ans = topDownApproach1(s, i, words, cache);
                if(ans == true){
                    cache.put(firstIdx, true);
                    return true;
                }
            }
        }
    
        // System.out.println("RETURN False");
        cache.put(firstIdx, false); // no substring possible.
        return false;
    }
    
    private boolean bottomUp(String s, Set<String> words){
        int strlen = s.length();
        boolean[] dp = new boolean[strlen + 1];
        // last idx is TRUE -> base case, all match
        dp[strlen] = true;
        
        for(int i=strlen; i>=0; i--){
            // check each word.
            for(String word: words){
                int newLen = word.length();
                if((i + newLen) <= strlen){
                    String substr = s.substring(i, i + newLen);
                    if(substr.equals(word) == true){
                        dp[i] = dp[i + newLen];
                    }
                    
                    if(dp[i] == true){
                        break;
                    }
                }
            }
        }
        
        return dp[0];
    }

    
}