class Solution {
    public String addBinary(String a, String b) {
        String max = a, min = b;
        if(a.length() < b.length()){
            max = b;
            min = a;
        }
        // pad 'min'
        for(int i=min.length(); i<max.length(); i++){
            min = "0" + min;
        }
        // System.out.println("max = " + max + " , min = " + min);
        
        char to_put = 'N';
        boolean carry_exists = false;
        
        
        // Stack<Character> stack = new Stack<>();
        StringBuilder bld = new StringBuilder();
        // List<Character> list = new ArrayList<>();
        
        for(int i=0; i<min.length(); i++){
            
            char last_max = max.charAt(max.length() - i - 1);
            char last_min = min.charAt(min.length() - i - 1);
            
            if(last_max == '1' && last_min == '1'){
                if(carry_exists){
                    to_put = '1';
                }else{
                    to_put = '0';
                }
                carry_exists = true;
            }
            else if((last_max == '1' && last_min == '0')
               || (last_max == '0' && last_min == '1')){
               if(carry_exists){
                   to_put = '0';
                   // carry_exists = false;
               }else{
                   to_put = '1';
               }
            }
            else{
                if(carry_exists){
                    to_put = '1';
                    carry_exists = false;
                }else{
                    to_put = '0';
                }
                
            }
            
            bld.append(to_put);
        }
        
        if(carry_exists){
            bld.append('1');
        }
        
        bld.reverse(); // reverse
        return bld.toString();
    }
}