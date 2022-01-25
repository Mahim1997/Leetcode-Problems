class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] diff = new int[26]; // difference betn ransom and magazine
        Arrays.fill(diff, 0); // initially 0
        
        int len = ransomNote.length();
        for(int i=0; i<len; i++){
            char c = ransomNote.charAt(i);
            diff[c - 'a']++;
        }
        
        len = magazine.length();
        for(int i=0; i<len; i++){
            char c = magazine.charAt(i);
            diff[c - 'a']--;
        }

        // check if +ve diff exists...
        for(int i=0; i<26; i++){
            if(diff[i] > 0)
                return false;
        }
        
        return true;
    }
}