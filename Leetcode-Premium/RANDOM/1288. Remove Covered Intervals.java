class Solution {
    private int[] getMergedInterval(int[] i1, int[] i2){
        int[] newInterval = new int[2];
        newInterval[0] = Math.min(i1[0], i2[0]);
        newInterval[1] = Math.max(i1[1], i2[1]);
        return newInterval;
    }
    
    // either/or case
    private boolean isCoveredBy(int[] left,
                                int[] right){
        return (
                ((left[0] <= right[0]) && 
                (left[1] >= right[1]))
            ||
               ((right[0] <= left[0]) && 
                (right[1] >= left[1]))
            );
    }
    
    private void printArr(int[][] arr){
        for(int[] x: arr){
            System.out.print("(" + x[0] + "," + x[1] + ") ");
        }
        System.out.println("");
    }
    
    private String getStr(int[] arr){
        return ("(" + arr[0] + "," + arr[1] + ") ");
    }
    
    // SIGNATURE METHOD
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by finishing times [ascending order]
        if(intervals.length == 1)
            return 1;
        
        Arrays.sort(intervals, (i1, i2) -> (i1[1] - i2[1]));
        
        // DEBUGGING
        // printArr(intervals);
        
        // Use stack ?
        Stack<int[]> stack = new Stack<>();
        for(int i=0; i<intervals.length; i++){
            // DEBUGGING
            
            int[] currentInterval = intervals[i];
            stack.push(currentInterval);
            
            if(stack.size() <= 1)
                continue;
            
            // check until either only one element exists, or merging happens
            while(stack.size() > 1){
                int[] stackTop = stack.pop();
                int[] stackPrev = stack.peek();
                
                if(isCoveredBy(stackTop, stackPrev) == false){
                    stack.push(stackTop); // push again
                    break;
                }else{
                    // covered ==> get the merged interval and push to stack
                    stack.pop(); // remove the '2nd last element'
                    int[] merged = getMergedInterval(stackTop, stackPrev);
                    stack.push(merged);
                }
                
            }
        }
        
        
        
        return stack.size();
    }
    
    
    // private void printStack(Stack<int[]> stack){
    //     Stack<int[]> copy = stack.clone();
    //     while(!copy.isEmpty()){
    //         int[] arr = copy.pop();
    //         System.out.print("(" + arr[0] + "," + arr[1] + ") ");
    //     }
    //     System.out.println("");
    // }
}