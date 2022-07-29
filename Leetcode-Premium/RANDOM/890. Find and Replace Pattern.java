class Solution {
    private boolean addToMap(
        Map<Character, Character> map, 
        char key, 
        char value
    ) {
        if(map.containsKey(key))
            return (map.get(key) == value);

        // Normal case
        map.put(key, value);
        return true;
    }
    
    private boolean isValidPermutation(String word, String pattern) {
        // Fill up forward
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        
        int len = word.length();
        for(int i=0; i<len; i++) {
            char c1 = word.charAt(i), c2 = pattern.charAt(i);
            
            // map 1 check
            if(addToMap(map1, c1, c2) == false)
                return false;
            
            // map 2 check
            if(addToMap(map2, c2, c1) == false)
                return false;
        }
        
        return true;
    }
    
    public List<String> findAndReplacePattern(
        String[] words, 
        String pattern
    ) {
        // Edge case of 'abcdefghijklmnopqrstuvwxyz'
        // Given pattern.len <= 20, so this will not be the case
        
        List<String> list = new ArrayList<>();
        
        for(String word: words) {
            if(isValidPermutation(word, pattern))
                list.add(word);
        }
        
        return list;
    }
}
