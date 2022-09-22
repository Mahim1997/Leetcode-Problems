class Solution {
    private boolean contains(int[] interval, int value) {
        return ((value >= interval[0]) && (value < interval[1]));
    }
    
    private int binarySearchStart(int[][] intervals, int start) {
        int n = intervals.length;
        int left = 0, right = n - 1;
        int lowestHigher = -1;
        
        while(left <= right) {
            int mid = left + (right - left)/2;
            int[] interval = intervals[mid];
            
            // found
            if(contains(interval, start)) {
                return mid;
            }
            
            if(start > interval[1]) {
                // go to right
                left = mid + 1;
            }
            else if(start < interval[0]) {
                // go to left
                lowestHigher = mid;
                right = mid - 1;
            }
            else {
                // default: go to left
                right = mid - 1;
            }
        }
        
        return lowestHigher;
    }
    
    private int binarySearchEnd(int[][] intervals, int end) {
        int n = intervals.length;
        int left = 0, right = n - 1;
        int highestLower = -1;
        
        while(left <= right) {
            int mid = left + (right - left)/2;
            int[] interval = intervals[mid];
            
            // found
            if(contains(interval, end)) {
                return mid;
            }
            
            if(end < interval[0]) {
                // go to left
                right = mid - 1;
            }
            else if(end > interval[1]) {
                // go to right
                highestLower = mid;
                left = mid + 1;
            }
            else {
                // default: go to right
                left = mid + 1;
            }
        }
        
        return highestLower;
    }
    
    public List<List<Integer>> removeInterval(
        int[][] intervals, 
        int[] toBeRemoved
    ) {
        int n = intervals.length;
        int start = toBeRemoved[0];
        int end = toBeRemoved[1];
        
        int startIdx = binarySearchStart(intervals, start);
        int endIdx = binarySearchEnd(intervals, end);
        
        List<List<Integer>> list = new ArrayList<>();
        
        // System.out.println("startIdx: " + startIdx + ", endIdx: " + endIdx);
        
        if(startIdx != -1) {
            // take initial ones
            for(int i=0; i<startIdx; i++) {
                list.add(List.of(intervals[i][0], intervals[i][1]));
            }
        
            // adjust middle [start]
            if(contains(intervals[startIdx], start)) {
                int newEnd = Math.min(intervals[startIdx][1], start);
                int startValue = intervals[startIdx][0];

                if(startValue != newEnd)
                    list.add(List.of(startValue, newEnd));
            }
        }
        
        if(endIdx != -1) {            
            // adjust middle [end]
            if(contains(intervals[endIdx], end)) {
                int endValue = intervals[endIdx][1];
                int newStart = Math.max(intervals[endIdx][0], end);
                
                if(newStart != endValue)
                    list.add(List.of(newStart, endValue));
            }
            
            // take last ones
            for(int i=endIdx+1; i<n; i++) {
                list.add(List.of(intervals[i][0], intervals[i][1]));
            }
        }
        
        return list;
    }
}