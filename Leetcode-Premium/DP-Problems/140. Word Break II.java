class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        return getWords(s, new HashSet<String>(wordDict), 
                        ans, "", 0);
    }
    
    // TODO: Use StringBuffer instead of String as tempString
    private List<String> getWords(String s, HashSet<String> words, List<String> listAns, String tempString, int firstIdx){
        // base case for adding.
        int strlen = s.length();
        if(firstIdx == strlen){
            listAns.add(tempString);
            tempString = "";
        }
        
        for(String word: words){
            int wordLen = word.length();
            int newIdx = firstIdx + wordLen;
            if(newIdx <= strlen){
                // exclusive
                String prevTemp = tempString;
                
                // choose
                String leftSubstr = s.substring(firstIdx, newIdx);
                if(leftSubstr.equals(word)){ // now, recurse
                    if(tempString.equals(""))
                        tempString = leftSubstr;
                    else
                        tempString = tempString + " " + leftSubstr;
                    
                    // utilize choice
                    getWords(s, words, listAns, tempString, newIdx);
                    
                    // remove choice
                    tempString = prevTemp;
                }
            }
        }
        
        
        
        return listAns;
    }
    
}