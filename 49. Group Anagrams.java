/*
1. Sort each word
2. Use dictionary/HashMap
*/
class Solution {
    private String sortString(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
    public List<List<String>> groupAnagrams(String[] arr) {
        // Sort each string.
        String[] sorted_arr = new String[arr.length];
        for(int i=0; i<arr.length; i++){
            sorted_arr[i] = sortString(arr[i]);
        }
        
        // Create a map of "KEY" with "indices" -> each clusters/anagrams.
        Map<String, List<Integer>> map = new HashMap<>();
        
        for(int i=0; i<sorted_arr.length; i++){
            String s = sorted_arr[i];
            if(map.containsKey(s) == false){
                map.put(s, new ArrayList<>()); // insert new array list.
            }
            // append this index.
            List list = map.get(s);
            list.add(i);
        }
        
        // take the values of above map, and put into double list.
        List<List<String>> ans = new ArrayList<>();
        for(String key: map.keySet()){
            List<String> temp_list = new ArrayList<>();
            for(int idx: map.get(key)){
                temp_list.add(arr[idx]);    
            }
            ans.add(temp_list);
        }
        
        return ans;
    }
}
