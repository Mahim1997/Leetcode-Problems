class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1){
            return strs[0]; // one string, all are common
        }
        
        int charIdx = 0;
        StringBuilder bld = new StringBuilder();
        
        boolean willRun = true;
        while(willRun == true){
            if(charIdx >= strs[0].length()){
                willRun = false;
                break;
            }
            char c = strs[0].charAt(charIdx);
            for(int i=1; i<strs.length; i++){
                if(charIdx >= (strs[i].length())){
                    willRun = false;
                    break;
                }
                if(strs[i].charAt(charIdx) != c){
                    willRun = false;
                    break;
                }
            }
            if(willRun){
                bld.append(c);
                charIdx++;
            }
        }
        
        
        return bld.toString();
    }
}