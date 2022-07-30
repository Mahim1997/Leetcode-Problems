class Solution {
    public List<String> wordSubsets(
        String[] words1, 
        String[] words2
    ) {
        // Create a int[] arr of all words in words2
        int[] countsWords2 = getCount(words2); 
        
        // For each word in words1, check with the int[] arr
        List<String> list = new ArrayList<>();
        
        for(String word: words1) {
            int[] countWord = getCount(word);
            if(isSubset(countsWords2, countWord)) {
                list.add(word);
            }
        }
        
        return list;
    }
    
    private boolean isSubset(int[] smaller, int[] larger) {
        int len = smaller.length; // lengths should be same == 26
        for(int i=0; i<len; i++) {
            if(smaller[i] > larger[i])
                return false;
        }
        return true;
    }
    
    private int[] getCount(String word) {
        int[] arr = new int[26];
        Arrays.fill(arr, 0);        
        for(char c: word.toCharArray())
            arr[c - 'a']++;
        return arr;
    }
    
    private int[] getCount(String[] words) {
        int[] totalCounts = new int[26];
        Arrays.fill(totalCounts, 0);
        
        for(String word: words) {
            int[] countsOfWord = getCount(word);
            for(int i=0; i<26; i++) {
                // "Combines" each word
                totalCounts[i] = Math.max(
                    totalCounts[i], 
                    countsOfWord[i]
                );
            }
        }
        
        return totalCounts;
    }
}