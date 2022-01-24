class Solution {
    public String reverseWords(String str) {
        String[] arr = str.trim().split(" ");
        StringBuilder bld = new StringBuilder();
        
        for(int i=arr.length-1; i>=0; i--){
            String s = arr[i];
            // System.out.println("<" + s + ">");
            if(s.length() == 0)
                continue;
            // bld.append(s.replace(" ", "")); // remove spaces
            bld.append(s);
            if(i > 0)
                bld.append(" ");
        }
        
        return bld.toString();
    }
}