class Solution {
    
    private boolean doesConflict(int[] i1, int[] i2){
        // return (i2.startTime < i1.endTime);
        return i2[0] < i1[1];
    }
    
    private int sortingTechnique(int[][] intervals){
        if(intervals.length == 1)
            return 1;
        
        // sort
        Arrays.sort(intervals, (i1, i2) -> (i1[1] - i2[1]));
        
        // available rooms
        List<int[]> list = new ArrayList<>();
        for(int idx=0; idx<intervals.length; idx++){
            if(idx == 0){
                list.add(intervals[idx]);
                continue;
            }
            
            int[] interval = intervals[idx];
            // int[] lastInterval = list.get(list.size() - 1);
            int len = list.size();
            boolean found = false;
            
            for(int i=len-1; i>=0; i--){
                int[] intervalFromList = list.get(i);
                if(doesConflict(intervalFromList, interval) == true){
                    continue;
                }else{
                    found = true;
                    list.remove(i);
                    int[] newInterval = new int[2];
                    newInterval[0] = Math.min(interval[0], 
                                              intervalFromList[0]);
                    newInterval[1] = Math.max(interval[1],
                                             intervalFromList[1]);
                    list.add(newInterval);
                    break;
                }
            }
            if(found == false){
                list.add(interval);
            }
        }
        
        
        return list.size();
    }
    
    
    private int usingHeap(int[][] intervals){
        // edge case
        if(intervals.length == 0)
            return 0;
        
        // sort according to start times
        Arrays.sort(intervals,
            (arr1, arr2) -> (arr1[0] - arr2[0]));
        
        // min heap: according to finishing times
        PriorityQueue<Integer> pq=new PriorityQueue();
        
        for(int[] interval: intervals){
            if(pq.isEmpty()){
                // simply add the finishing time
                pq.add(interval[1]);
                continue;
            }
            
            int minFinish = pq.peek();
            if(interval[0] >= minFinish){
                // remove that meeting, add new meeting
                pq.remove();
            }
            
            // always add new meeting
            pq.add(interval[1]);
        }
        
        
        return pq.size();
    }
    
    public int minMeetingRooms(int[][] intervals) {
        return usingHeap(intervals);
        // return sortingTechnique(intervals);
    }
}