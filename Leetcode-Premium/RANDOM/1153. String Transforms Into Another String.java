class Solution {
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2))
            return true;
        
        Map<Character, Character> map = new HashMap<>();
        
        // to keep count of unique chars -> edge case of 26
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        
        int strlen = str1.length();
        
        // One-to-many is NOT allowed, many-to-one is allowed.
        for(int i=0; i<strlen; i++){
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            
            if(map.containsKey(c1) == false){
                map.put(c1, c2);
            }else{
                char c2Reversed = map.get(c1);
                if(c2Reversed != c2){ // other map is obtained.
                    return false;
                }
            }
            
            set1.add(c1);
            set2.add(c2);
        }
        
        if(set1.size() == 26){ // all 26 unique chars are present.
            if(set2.size() == 26){
                return (str1.equals(str2));
            }else{
                return true;
            }
        }
        
        return true;
    }
    
    
    
}