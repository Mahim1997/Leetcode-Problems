class Solution {    
    private boolean doesConflict(int[] i1, int[] i2){
        // return (i2.startTime < i1.endTime);
        return i2[0] < i1[1];
    }
    
    public boolean canAttendMeetings(int[][] intervalsArr) {
        if(intervalsArr.length <= 1)
            return true;
        
        // sort
        // Arrays.sort(intervals, (i1, i2) -> (i1.endTime - i2.endTime));
        // Arrays.sort(intervalsArr, (i1, i2) -> (i1[1] - i2[1]));
        Arrays.sort(intervalsArr, (a, b) -> Integer.compare(a[0], b[0]));
        
        
        // check if conflict
        for(int i=1; i<intervalsArr.length; i++){
            if(doesConflict(intervalsArr[i - 1], intervalsArr[i]))
                return false;
        }
        return true;
    }
}