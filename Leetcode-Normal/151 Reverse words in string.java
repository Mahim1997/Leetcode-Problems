class Solution {
    public String reverseWords(String s) {
        s = s.trim(); // remove trailing & leading spaces.
        // char[] chars = s.toCharArray();
        
        System.out.println(s);
        StringBuilder bld = new StringBuilder();
        
        
        boolean is_first_space = false;
        boolean is_chars = false;
        int right_ptr = s.length() - 1; // to get the right-most pointer
        
        
        for(int i=(s.length() - 1); i>=0; i--){
            char c = s.charAt(i);
            // System.out.println("For i = " + i + " , c = " + c);
            if(c == ' '){ // white space is encountered.
                if(is_first_space){ // only called once i.e. for the first space.
                    // System.out.println("FIRST, (i+1) = " + (i+1) + " , rightPtr + 1 = " + (right_ptr + 1));
                    bld.append(s.substring(i+1, right_ptr+1)); // right side is exclusive
                    bld.append(" "); // append white space
                    is_first_space = false;
                }
                right_ptr = i - 1; // one to the left.
            }
            else{ // normal character is encountered.
                is_first_space = true; // set this flag so that above condition handles it.
                if(i == 0){ // need to append here as well.
                    // System.out.println("INIT, i = " + i + " , rightPtr + 1 = " + (right_ptr + 1));
                    bld.append(s.substring(i, right_ptr+1));
                }
            }
            
            
        }
        
        
        // return String.valueOf(chars);
        return bld.toString();
    }
}