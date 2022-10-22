class Solution {
    // private int[] getCounts(String s) {
    //     int[] counts = new int[26];
    //     Arrays.fill(counts, 0);
    //     for(char c: s.toCharArray()) {
    //         counts[c - 'a']++;
    //     }
    //     return counts;
    // }
    
    // [left, right] all inclusive
    private int getWindow(int left, int right) {
        return (right - left + 1);
    }
    
    // private boolean isInitialized(int left, int right) {
    //     return !((left == -1) && (right == -1));
    // }
    
//     private boolean areEqual(int[] arr1, int[] arr2) {
//         if(arr1.length != arr2.length) {
//             return false;
//         }
        
//         for(int i=0; i<arr1.length; i++) {
//             if(arr1[i] != arr2[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }
    
    private boolean contains(
        Map<Character, Integer> smallMap,
        Map<Character, Integer> bigMap
    ) {
        for(char key: smallMap.keySet()) {
            int smallValue = smallMap.get(key);
            int bigValue = bigMap.getOrDefault(key, 0);
            if(smallValue > bigValue) {       
                return false;
            }
        }
        return true;
    }
    
    private Map<Character, Integer> getCounts(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for(char c: s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        return counts;
    }
    
    public String minWindow(String s, String t) {
        String big = s, small = t;

        Map<Character, Integer> countsSmall = getCounts(small);
        Map<Character, Integer> countsBig = new HashMap<>();
        int bigLen = big.length();
        
        // Arrays.fill(countsBig, 0);
        
        int leftBest = -1, rightBest = -1;
        int minWindow = Integer.MAX_VALUE;
        int left = 0, right = 0;
        
        while(right < bigLen) {
            boolean containsFlag = contains(countsSmall, countsBig);
            // System.out.println("1. left = " + left + ", right = " + right + ", countsBig: " + countsBig + ", containsFlag: " + containsFlag);
            
            // as long as contains, keep moving left
            if(containsFlag) { 
                int currentWindow = getWindow(left, right);
                if(currentWindow < minWindow) {
                    leftBest = left;
                    rightBest = right;
                    minWindow = currentWindow;
                }

                char leftChar = big.charAt(left);
                countsBig.put(leftChar, countsBig.get(leftChar) - 1);
                left++;
            }
            else {                
                // just keep updating
                char rightChar = big.charAt(right);
                countsBig.put(rightChar, countsBig.getOrDefault(rightChar, 0) + 1);
                right++;
            }
        }

        
        // For right most window check
        // right = bigLen; // to set for outer limit
        while(left < bigLen) {
            // System.out.println("2. left = " + left + ", right = " + right + ", countsBig: " + countsBig);
            if(contains(countsSmall, countsBig)) {
                int currentWindow = getWindow(left, right);
                if(currentWindow < minWindow) {
                    leftBest = left;
                    rightBest = right;
                    minWindow = currentWindow;
                }

                char leftChar = big.charAt(left);
                countsBig.put(leftChar, countsBig.get(leftChar) - 1);
                left++;
            }
            else {
                break;
            }
        }
        
        // Manually last check using while loop, then code cleaner
        
        return (minWindow == Integer.MAX_VALUE) 
            ? "" 
            : big.substring(leftBest, rightBest);
    }
}

