class Solution {
    public int lengthOfLongestSubstring(String s) {        
        int strlen = s.length();
        if(strlen <= 1)
            return strlen;
        
        
        int ptrOuter = 0;
        int ptrInner = 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        
        while(ptrOuter < (strlen)){
            char charOuter = s.charAt(ptrOuter);
            // map.put(charOuter, ptrOuter);
            
            ptrInner = ptrOuter;
            while(ptrInner < strlen){            
                char charInner = s.charAt(ptrInner);
                if(map.containsKey(charInner)){
                    int lastIdx = map.get(charInner);
                    ptrOuter = lastIdx + 1;
                    // reset
                    maxLen = Math.max(maxLen, map.size());
                    map.clear();
                    break;
                    // put into map the prev char.
                    // map.put(s.charAt(lastIdx), lastIdx);
                }else{        
                    map.put(charInner, ptrInner);
                    ptrInner++;
                }
                // System.out.println("ptrOuter = " + ptrOuter + ", ptrInner = " + ptrInner + ", map = " + map + ", maxLen = " + maxLen);
            }
        }
        // System.out.println(map + ", maxLen = " + maxLen);
        // System.out.println("ptrOuter = " + ptrOuter);
        
        // check last char
        // ptrOuter++;
        // if(map.containsKey(s.charAt(ptrOuter))){
        //     return maxLen;
        // }else{
        //     map.put(s.charAt(ptrOuter), ptrOuter);
        //     return map.size();
        // }
        
        return Math.max(maxLen, map.size());
    }
}