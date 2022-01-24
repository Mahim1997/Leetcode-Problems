class Solution {
    public void reverseString(char[] s) {
        int len = s.length;
        recursion(s, 0, len-1, (len%2 == 0));
    }
    
    void recursion(char[] arr, int f, int l, boolean isEven){
        // base case
        if(isEven){
            if(f >= (l + 1))
                return;
        }else{
            if(f >= l)
                return;
        }
        
        // recursion
        
        // change two values
        char temp = arr[f];
        arr[f] = arr[l];
        arr[l] = temp;
        
        // recursion
        recursion(arr, f+1, l-1, isEven);
    }
}