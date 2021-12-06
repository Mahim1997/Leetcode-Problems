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
        // Create a map of "KEY" with "indices" -> each clusters/anagrams.
        Map<String, List<String>> map = new HashMap<>();
        
        for(int i=0; i<arr.length; i++){
            // String s = sortString(arr[i]); // On the fly, sort each string
            
			map.putIfAbsent(s, new ArrayList<>()); // one liner
            map.get(s).add(arr[i]); // append this string (initial string).
        }
        
        return new ArrayList(map.values()); // works
    }
}
