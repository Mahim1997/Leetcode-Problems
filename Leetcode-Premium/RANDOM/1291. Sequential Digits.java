class Solution {
//     private int low;
//     private int high;
    
//     private List<Integer> answer;
    
    private int[] digitsSequential = {
        1,2,3,4,5,6,7,8,9,
        12,23,34,45,56,67,78,89,
        123,234,345,456,567,678,789,
        1234,2345,3456,4567,5678,6789,
        12345,23456,34567,45678,56789,
        123456,234567,345678,456789,
        1234567,2345678,3456789,
        12345678,23456789,
        123456789
    };
    
    private int getNumberFromList(List<Integer> digits){
        int num = 0;
        int n = digits.size();
        for(int i=0; i<n; i++){
            num = num*10;
            num = num + digits.get(i);
        }
        return num;
    }
    
    public List<Integer> sequentialDigits(int low, int high) {
//         this.low = low;
//         this.high = high;
        
//         this.answer = new ArrayList<>();
        
//         // recursive backtracking function.
//         for(int start=1; start<=9; start++){
//             backtrack(start, 0);
//         }
        
//         Collections.sort(this.answer);
//         return this.answer;
        
        // NINJA technique
        List<Integer> list = new ArrayList<>();
        
        for(int digit: this.digitsSequential){
            if((digit >= low) && (digit <= high))
                list.add(digit);
        }
        
        return list;
    }
    
//     private boolean isWithin(int num){
//         return ((num >= this.low) && (num <= this.high));
//     }
    
//     private void backtrack(int startDigit, int numSoFar){
//         // int startDigit = start + 1;
//         // int newNum = num*10 + digit;
//         int currentNum = numSoFar*10 + startDigit;
        
//         if(isWithin(currentNum) == true){
//             this.answer.add(currentNum);
//         }
        
//         // exceeds bound, don't go further
//         if(currentNum > this.high)
//             return;
        
//         // recursive calling.
//         if(startDigit < 9){
//             backtrack(startDigit+1, currentNum);
//         }else{
//             return;
//         }
//     }
    
}