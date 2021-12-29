class Solution {    
    public List<List<String>> groupAnagrams(String[] strs) {
        // return sortedApproach(strs);
        return countApproach(strs);
    }
    
    private String sort(String s){    
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
    
    private List<List<String>> sortedApproach(String[] strs){
        if(strs.length == 0){
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        
        int n = strs.length;
        
        for(String s: strs){
            String sorted = sort(s);
            if(map.containsKey(sorted) == false){
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }
        
        List<List<String>> ans = new ArrayList<>();
        for(String key: map.keySet()){
            ans.add(map.get(key));
        }
        
        return ans;
    }
    
    private int[] getCountAlphabets(String s){
        int[] counts = new int[26];
        
        for(int i=0; i<26; i++){
            counts[i] = 0;
        }
        
        int len = s.length();
        for(int i=0; i<len; i++){
            char c = s.charAt(i);
            counts[c - 'a']++;
        }
        
        return counts;
    }
    
    private String getKey(int[] count){
        StringBuilder bld = new StringBuilder();
        bld.append("$"); // $1$2$...
        for(int cnt: count){
            bld.append(cnt);
            bld.append("$");
        }
        return bld.toString();
    }
    
    private List<List<String>> countApproach(String[] strs){
        // key: count of alphabets, val: List<List<String>>
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            int[] count = getCountAlphabets(s);
            String key = getKey(count);
            
            if(map.containsKey(key) == false){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        
        // System.out.println(map);
        
        List<List<String>> list = new ArrayList<>();
        for(String key: map.keySet()){
            list.add(map.get(key));
        }
        
        return list;
    }
}