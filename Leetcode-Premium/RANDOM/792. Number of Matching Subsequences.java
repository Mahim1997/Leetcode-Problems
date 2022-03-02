class Solution {
    private boolean isSubsequence(String big, String small){
        int bigLen = big.length(), smallLen = small.length();
        int bPtr = 0, sPtr = 0;
        
        // edge cases
        if(smallLen == 0){return true;}
        if(bigLen == 0){return (smallLen == 0);}
        if(smallLen > bigLen){return false;}
        
        while((sPtr < smallLen) && (bPtr < bigLen)){
            if(big.charAt(bPtr) == small.charAt(sPtr)){
                sPtr++;
            }
            bPtr++;
            
            if(sPtr == smallLen){return true;}
        }
        
        return false;
    }
    
    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        
        Map<String, Boolean> map = new HashMap<>();
        Arrays.sort(words, 
                    ((x1, x2) -> (x2.length() - x1.length())));
        
        for(String word: words){
            boolean result;
            
            if(map.containsKey(word) == true){
                result = map.get(word);
            }else{
                result = isSubsequence(s, word);
                map.put(word, result);                
            }
            
            if(result == true)
                count++;
        }
        
        return count;
    }
}