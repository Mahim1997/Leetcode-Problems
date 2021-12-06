class Solution {
    private boolean isOperation(String token){
        switch(token){
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
        }
        return false;
    }
    
    private int compute(int elLeft, int elRight, String token){
        switch(token){
            case "+":
                return (elLeft + elRight);
            case "-":
                return (elLeft - elRight);
            case "*":
                return (elLeft * elRight);
            case "/":
                return (elLeft / elRight);
        }
        return -1;
    }
    
    public int evalRPN(String[] tokens) {
        if(tokens.length == 1){
            return Integer.parseInt(tokens[0]);
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<tokens.length; i++){
            if(isOperation(tokens[i])){
                
                int elRight = stack.pop();
                int elLeft = stack.pop();
                
                int elResult = compute(elLeft, elRight, tokens[i]);
            
                stack.push(elResult);
            }
            else{
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        
        return stack.pop();
    }
}