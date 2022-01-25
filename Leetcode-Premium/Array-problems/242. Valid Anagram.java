class Solution {
    public boolean isAnagram(String s, String t) {
        int len = s.length();
        if(len != t.length())
            return false;
        
        int[] counts = new int[26];
        // For unicode -> Use HashMap ... no need to fix for 26 size
        Arrays.fill(counts, 0);
        char c;
        
        for(int i=0; i<len; i++){
            c = s.charAt(i);
            counts[c - 'a']++;
            c = t.charAt(i);
            counts[c - 'a']--;
        }
        
        for(int count: counts){
            if(count != 0)
                return false;
        }
        
        return true;
    }
}