class Solution {
    
    // Naive Solution
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        if(strs[0].length() == 0) return "";
        
        int max_len = -9999;
        
        for(int i=0; i<strs.length; i++){
            if(strs[i].length() >= max_len){max_len = strs[i].length();}
        }
        
        String maxCommonPrefix = "";
        
        for(int lenCommonPrefix=0; lenCommonPrefix<max_len; lenCommonPrefix++){
            char nowChar = 'a';
            // char nowChar = strs[0].charAt(lenCommonPrefix);
            for(int i=0;i<strs.length; i++){
                // if any string has a higher len. then break.
                String s = strs[i];
                if(s.length() == lenCommonPrefix){return maxCommonPrefix;}
                if(i==0){nowChar = strs[0].charAt(lenCommonPrefix);}
                else{
                    // check common chars.
                    if(strs[i].charAt(lenCommonPrefix) != nowChar){
                        return maxCommonPrefix;
                    }
                }
            }
            maxCommonPrefix += nowChar;
        }
        
        return maxCommonPrefix;
        
        
    }
}