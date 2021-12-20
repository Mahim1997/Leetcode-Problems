class Solution {
    public int lengthOfLastWord(String s) {
        // String[] arr = s.trim().split(" ");
        // return arr[arr.length - 1].length();
        int strlen = s.length();
        int len = 0;
        boolean firstTime = true;
        for(int i=strlen-1; i>=0; i--){
            char c = s.charAt(i);
            if(c == ' '){
                if(firstTime == true)
                    continue;
                else
                    return len;
            }else{
                firstTime = false;
                len++;
            }
        }
    
        return len;
    }
}