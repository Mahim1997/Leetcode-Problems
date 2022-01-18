class Solution {
    private boolean isOpenBracket(char c){
        return ((c == '(') || (c == '{') || (c == '['));
    }
    private boolean isCloseBracket(char c){
        return ((c == ')') || (c == '}') || (c == ']'));
    }
    
    private boolean areSameTypes(char c1, char c2){
        return (
            ((c1 == '(') && (c2 == ')')) || 
            ((c1 == '{') && (c2 == '}')) || 
            ((c1 == '[') && (c2 == ']'))
       );
    }
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i=0; i<len; i++){
            char c = s.charAt(i);
            if(isOpenBracket(c) == true){
                stack.push(c);
            }else{
                // check if stack is empty ... initially close bracket
                if(stack.isEmpty() == true){
                    return false;
                }
                
                char stkTop = stack.peek();
                if(areSameTypes(stkTop, c) == false){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        
        return (stack.isEmpty() == true);
    }
}