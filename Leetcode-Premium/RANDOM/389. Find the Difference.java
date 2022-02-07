class Solution {
    public char findTheDifference(String s, String t) {
        // return usingSorting(s, t);
        // return usingCounts(s, t);
        return usingXOR(s, t);
    }
    
    private char usingXOR(String s, String t){
        int strlen = s.length();
        int ans = 0;
        char c;
        int val;
        for(int i=0; i<strlen; i++){
            c = s.charAt(i);
            val = c - 'a' + 1; // 1 -> a, otherwise "0" will be ans.
            ans = ans ^ val;
            
            c = t.charAt(i);
            val = c - 'a' + 1;
            ans = ans ^ val;
        }
        
        c = t.charAt(t.length()-1);
        val = c - 'a' + 1;
        ans = ans ^ val;
        
        c = (char)(ans + 'a' - 1);
        return c;
    }
    
    private char usingCounts(String s, String t){
        int[] counts = new int[26];
        Arrays.fill(counts, 0); // fill with 0
        
        char c;
        int strlen = s.length();
        for(int i=0; i<strlen; i++){
            c = s.charAt(i);
            counts[c - 'a']++;
        }
        
        strlen = t.length();
        for(int i=0; i<strlen; i++){
            c = t.charAt(i);
            counts[c - 'a']--;
            
            if(counts[c - 'a'] < 0){
                return c;
            }
        }
        return '$';
    }
    
    private char usingSorting(String s, String t){
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        
        Arrays.sort(s1);
        Arrays.sort(t1);
        
        int strlen = s.length();
        for(int i=0; i<strlen; i++){
            if(s1[i] != t1[i]){
                return t1[i];
            }
        }
        
        return t1[t1.length - 1];
    }
}