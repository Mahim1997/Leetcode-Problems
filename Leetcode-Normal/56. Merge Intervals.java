class Solution {
    private boolean doesOverlap(int[] arr1, int[] arr2){
        return (arr1[1] >= arr2[0]);
    }
    private void printList(List<int[]> list){
        for(int[] x: list){
            System.out.println("(" + x[0] + "," + x[1] + ")");
        }
    }
    public int[][] merge(int[][] intervals) {
        
        if(intervals.length == 1){
            return intervals;
        }
        
        
        // Sort wrt start times. [end times doesnt work]
        Arrays.sort(intervals, (int[] arr1, int[] arr2) -> (arr1[0] - arr2[0]));
        
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        
        // Traverse each interval and check with the latest of the list.
        for(int itr=1; itr<intervals.length; itr++){
            int[] intervalFromList = list.get(list.size() - 1);
            int[] intervalCurrent = intervals[itr];
            
            if(doesOverlap(intervalFromList, intervalCurrent)){
            // Update the int[] interval INSIDE the list -> since its reference exists
                intervalFromList[0] = Math.min(intervalFromList[0], intervalCurrent[0]);
                intervalFromList[1] = Math.max(intervalFromList[1], intervalCurrent[1]);
            }else{
            // Else, no overalap, simply append to list.
                list.add(intervalCurrent);
            }
            // System.out.println("After itr = " + itr + " list = ");
            // printList(list);
        }
        
        // https://stackoverflow.com/questions/47135950/how-can-i-convert-a-listint-to-a-2d-array
        return list.toArray(new int[list.size()][]);
        
    }
}