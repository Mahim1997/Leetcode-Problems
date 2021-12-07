class Solution {
    public int firstUniqChar(String s) {
        // return firstUniqCharHashMap(s);
        return firstUniqCharUsingArray(s);
    }
    
    private int firstUniqCharUsingArray(String s){
        int[] arr = new int[26];
        Arrays.fill(arr, 0);
        char c;
        
        // Firstly, fill the map/array to include.
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            arr[c - 'a']++;
        }
        
        // Now search.
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            if(arr[c - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
    
    private int firstUniqCharHashMap(String s){
        Map<Character, Integer> map = new HashMap<>();
        
        char c;
        // Firstly, fill the map to include.
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Now, search.
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            if(map.get(c) == 1){
                return i;
            }
        }
        
        return -1;
    }
}