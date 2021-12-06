class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        
        for(int i=0; i<s.length(); i++){
            char c_s = s.charAt(i);
            char c_t = t.charAt(i);
            
            if(map1.containsKey(c_s)){
                if(c_t != map1.get(c_s)){
                    return false;
                }
            }else{
                map1.put(c_s, c_t);
            }
            
            if(map2.containsKey(c_t)){
                if(c_s != map2.get(c_t)){
                    return false;
                }
            }else{
                map2.put(c_t, c_s);
            }
            
        }
        
        return true;
    }
}