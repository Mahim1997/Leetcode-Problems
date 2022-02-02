class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        
        List<Integer> listIndices = new ArrayList<>();
        
        int sLen = s.length();
        int pLen = p.length();
        
        // null checking
        if((s.length() == 0) || (s == null))
            return listIndices;
        if(pLen > sLen)
            return listIndices;
            
        int[] sCounts = new int[26];
        int[] pCounts = new int[26];

        
        Arrays.fill(sCounts, 0);
        Arrays.fill(pCounts, 0);
        
        // [left, right] -> [left+1, right+1] -> remove left char, add right char
        int left = 0;
        int right = 0;
        
        // initial window
        for(right=0; right<pLen; ++right){
            sCounts[s.charAt(right) - 'a']++;
            pCounts[p.charAt(right) - 'a']++;
        }
        right = pLen - 1;
        
        while((left < sLen) && (right < sLen)){
            
            // initially, increment the counter of right's
            if(right >= pLen){
                sCounts[s.charAt(right - pLen) - 'a']--;
                sCounts[s.charAt(right) - 'a']++;
            }
            
            // System.out.println("left = " + left + ", right = " + right);
            // printArray(pCounts);
            // printArray(sCounts);
            
            // compare two arrays.
            if((right - left + 1) == pLen){
                if(Arrays.equals(sCounts, pCounts) == true){
                    listIndices.add(left);
                }
                // remove sCounts's positions
                // sCounts[s.charAt(left) - 'a']--;
                // left = right+1;
                left++;
            }
            right++;
        }
        
        return listIndices;
    }
    
    private void printArray(int[] arr){
        for(int x: arr){System.out.print(x + " ");}
        System.out.println("");
    }
    
    
}