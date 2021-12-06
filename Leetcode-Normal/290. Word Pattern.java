class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<String, Character> map1 = new HashMap<>();
        Map<Character, String> map2 = new HashMap<>();
        
        String[] arr = s.split(" ");
        if(pattern.length() != arr.length){return false;}
        
        char c;
        String temp;
        for(int i=0; i<arr.length; i++){
            c = pattern.charAt(i);
            temp = arr[i];
            
            if(map1.containsKey(temp)){
                if(map1.get(temp) != c){
                    return false;
                }
            }else{
                map1.put(temp, c);
            }
            
            if(map2.containsKey(c)){
                if(map2.get(c).equals(temp) == false){
                    return false;
                }
            }else{
                map2.put(c, temp);
            }
            
        }
        
        return true;
    }
}