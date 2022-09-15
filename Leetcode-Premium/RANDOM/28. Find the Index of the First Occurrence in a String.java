class Solution {
    // Rolling hash
    private boolean areEqual(String s, int left, int right, String p) {
        int i = 0, j = left, n = p.length();
        
        if(n != (right - left + 1))
            return false;
        
        while((j < left) && (i < n)) {
            if(s.charAt(j) != p.charAt(i))
                return false;
        }
        
        return true;
    }
    
    private int[] getIntArr(String s) {
        int[] arr = new int[s.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = (int) (s.charAt(i) - 'a');
        }
        return arr;
    }
    
    // number of chars
    private static int BASE = 26;
    
    private long getHash(int[] arr, long MOD) {
        long hash = 0;
        for(int i=0; i<arr.length; i++)
            hash = (hash*BASE + arr[i]) % MOD;
        return hash;
    }
    
    // API
    public int strStr(String haystack, String needle) {
        long MOD = (long) Integer.MAX_VALUE; // Math.pow(2, 32);   
        
        int needleLen = needle.length();
        int hayLen = haystack.length();
        
        // Edge cases
        if(needleLen == 0)
            return 0;
        if(needleLen > hayLen)
            return -1;
        
        int[] needleArr = getIntArr(needle);
        int[] hayArr = getIntArr(haystack);
        
        long needleHash = getHash(needleArr, MOD);
        
        // now, use the rolling hash
        long rollingHash = 0;
        for(int i=0; i<needleLen; i++) {
            rollingHash = (rollingHash*BASE + hayArr[i]) % MOD;
        }
        
        long adjustedWeight = 1;
        for (int i=0; i<needleLen; i++)
            adjustedWeight = (adjustedWeight * BASE) % MOD;

        // System.out.println("Needle hash: " + needleHash);
        
        int left = 0, right = needleLen - 1;
        while(true) {
            if(
                (rollingHash == needleHash) && 
                areEqual(haystack, left, right, needle)
            ) {
                return left;
            }
            
            right++;
            
            if(right >= hayLen)
                break;
            
            rollingHash = (rollingHash*BASE - hayArr[left]*adjustedWeight + hayArr[right]) % MOD;
            
            // System.out.println("left = " + left + ", right = " + right + ", rollingHash: " + rollingHash);
            
            // adjust rolling hash, until it is positive
            while(rollingHash < 0)
                rollingHash += MOD;
            
            left++;
        }
        
        return -1;
    }
}

