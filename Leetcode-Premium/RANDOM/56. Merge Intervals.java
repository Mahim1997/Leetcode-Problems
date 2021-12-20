class Solution {
    
    private void printArrays(int[][] arr){
        for(int[] a: arr){
            System.out.print(a[0] + " " + a[1] + ", ");
        }
        System.out.println("");
    }
    
    private boolean doesConflict(int[] i1, int[] i2){
        return (i1[1] >= i2[0]); // left ends AFTER right starts 
    }
    
    private int[] createMergedInterval(int[] left, int[] right){
        int[] newInterval = new int[2];
        newInterval[0] = Math.min(left[0], right[0]);
        newInterval[1] = Math.max(left[1], right[1]);
        return newInterval;
    }
    
    public int[][] merge(int[][] intervals) {
        // Sort arrays by start times ?
        if(intervals.length == 1)
            return intervals;
        
        Arrays.sort(intervals, (x1, x2) -> (x1[0] - x2[0]));
        
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]); // push the first element.
        int idx = 1;
        while(idx < intervals.length){
            
            int[] stackInterval = stack.peek();
            int[] currentInterval = intervals[idx];
            if(doesConflict(stackInterval, currentInterval)){
                int[] newInterval = createMergedInterval(stackInterval, currentInterval);
                // remove from stack and add new merged interval
                stack.pop();
                stack.push(newInterval);
            }else{
                // just push the new interval to stack's top
                stack.push(intervals[idx]);
            }   
            idx++;
        }
        
        // Convert stack to array
        int[][] ans = new int[stack.size()][2];
        idx=0;
        while(stack.isEmpty() == false){
            int[] interval = stack.pop();
            ans[idx][0] = interval[0];
            ans[idx][1] = interval[1];
            idx++;
        }
        
        return ans;
    }
}