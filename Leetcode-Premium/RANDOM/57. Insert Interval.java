class Solution {
    // use binary search later
    private int findIndexSmaller(int[][] intervals, int startTime) {
        int n = intervals.length;
        int left = 0, right = n - 1;
        int index = -1;
        
        while(left <= right) {
            int mid = left + (right - left)/2;
            int time = intervals[mid][0];
            
            if(time <= startTime) {     // go right
                index = mid;
                left = mid + 1;
            }
            else {                      // go left
                right = mid - 1;
            }
        }
        
        return index + 1; // exactly, where to insert
    }
    
    private boolean doesConflict(int[] i1, int[] i2) {
        // First one should END before Second one STARTS 
        return (i1[1] >= i2[0]); 
    }
    
    private int[] mergeInterval(int[] i1, int[] i2) {
        return new int[]{
            Math.min(i1[0], i2[0]),
            Math.max(i1[1], i2[1])
        };
    }

    private void mergeAndPush(Stack<int[]> stack, int[] interval) {
        if(stack.isEmpty()) {
            stack.push(interval);
            return;
        }
        
        // check last two and merge
        if(doesConflict(stack.peek(), interval)) {
            int[] poppedInterval = stack.pop();
            stack.add(mergeInterval(poppedInterval, interval));
        }
        else {
            // simply add the new interval
            stack.add(interval);
        }
    }
    
    private int[][] convertToArray(Stack<int[]> stack) {
        // reconvert back to array
        int n = stack.size();
        int idx = n - 1;
        int[][] intervals = new int[n][2];
        
        while(!stack.isEmpty())
            intervals[idx--] = stack.pop();
        
        return intervals;
    }
    
    // API
    // already sorted by start times ascending order
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int indexToInsert = findIndexSmaller(
            intervals, 
            newInterval[0]
        );
        
        Stack<int[]> intervalsStack = new Stack<>();
        for(int i=0; i<indexToInsert; i++) {
            intervalsStack.push(intervals[i]);
        }
        
        // now, simply merge two, add to stack, keep merging others
        mergeAndPush(intervalsStack, newInterval);
        
        for(int i=indexToInsert; i<intervals.length; i++) {
            int[] intervalToAdd = intervals[i];
            mergeAndPush(intervalsStack, intervalToAdd);
        }
        
        return convertToArray(intervalsStack);
    }
}


