class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // sliding window with character frequency ?
        
        // return usingBruteForce(s1, s2);
        return usingCharFrequency(s1, s2);
    }
    
    private boolean usingCharFrequency(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        
        // Brute Force
        if(len1 > len2){ // can't have pattern with bigger length
            return false;
        }
        
        int[] countsStr2 = new int[26];
        Arrays.fill(countsStr2, 0);
        
        
        int[] countsStr1 = new int[26];
        Arrays.fill(countsStr1, 0);
        
        for(char c: s1.toCharArray()){
            countsStr1[c - 'a']++;
        }
        
        for(int i=0; i<len1; i++){
            char c = s2.charAt(i);
            countsStr2[c - 'a']++;
        }
        
        // <= for last char checking.
        for(int i=len1; i<=len2; i++){
            // char c = s2.charAt(i);
            
            //// [left, right] inclusive
            if(areArraysEqual(countsStr1, countsStr2) == true){
                return true;
            }
            
            // not have found so far, hence can't check further
            if(i == len2){
                return false;
            }
            countsStr2[s2.charAt(i) - 'a']++;
            countsStr2[s2.charAt(i - len1) - 'a']--;
            
            // printArr(countsStr2); // DEBUGGING
            // printArr(countsStr1);
        }
        
        return false;
    }
    
    private void printArr(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private boolean areArraysEqual(int[] arr1, int[] arr2){
        for(int i=0; i<arr1.length; i++){
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    
    private boolean usingBruteForce(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        
        // Brute Force
        if(len1 > len2){ // can't have pattern with bigger length
            return false;
        }
        
        int left = 0;
        int right = left + len1;
        
        while(right <= len2){
            String substr = s2.substring(left, right);
            
            if(areAnagrams(substr, s1) == true){
                return true;
            }
            
            left++;
            right++;
        }
        
        return false;
    }
    
    private boolean areAnagrams(String s1, String s2){
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }
}