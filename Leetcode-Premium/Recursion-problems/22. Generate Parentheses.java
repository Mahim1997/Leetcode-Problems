class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    // helper
    private void backtrack(List<String> list, String temp,
                           int open, int close, int n){
        // open: # open brackets, close: # close brackets
        
        // edge case: close can't be bigger than open brackets
        if(close > open){
            return;
        }
        // edge case: if both open and close equal to n
        if((open == close) && (open == n)){
            list.add(temp);
            return;
        }
        // edge case: any one exceeds n
        if((open > n) || (close > n)){
            return;
        }
        
        
        // if both are equal
        if(open == close){
            // need to start with open
            backtrack(list, temp + "(", open + 1, close, n);
        }
        else if(open > close){
            // can choose either open bracket OR close bracket
            backtrack(list, temp + "(", open + 1, close, n);
            backtrack(list, temp + ")", open, close + 1, n);
        }
    }
}