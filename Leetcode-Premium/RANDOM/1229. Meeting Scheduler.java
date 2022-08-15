class Solution {
    private boolean isDisjoint(int[] a1, int[] a2) {
        // anyone starts AFTER other ends
        return ((a1[0] > a2[1]) || (a2[0] > a1[1]));
    }
    
    private boolean isOverlap(int[] a1, int[] a2) {
        return !isDisjoint(a1, a2);
    }
    
    private int getGap(int[] a1, int[] a2) {
        return (
            Math.min(a1[1], a2[1]) -
            Math.max(a1[0], a2[0])
            // 1   // DO NOT INCLUDE for inclusive
        );
    }
    
    private List<Integer> getInterval(
        int[] a1, 
        int[] a2, 
        int dur
    ) {
        int start = Math.max(a1[0], a2[0]);
        return new ArrayList<>(List.of(start, start + dur));
    }
    
    static class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return (o1[0] - o2[0]);
        }
    }
    
    public List<Integer> minAvailableDuration(
        int[][] slots1,
        int[][] slots2, 
        int duration
    ) {
        // Can't assume to be sorted
        // Sort wrt either start or end times
        // Using start times, because it is the same things
        Comparator<int[]> comp = new IntervalComparator();
        Arrays.sort(slots1, comp);
        Arrays.sort(slots2, comp);
        
        int p1 = 0, p2 = 0;
        int n1 = slots1.length, n2 = slots2.length;
        
        while((p1 < n1) && (p2 < n2)) {
            int[] a1 = slots1[p1];
            int[] a2 = slots2[p2];
            
            if(isOverlap(a1, a2)) {
                // check gap
                int gap = getGap(a1, a2);
                if(gap >= duration) {
                    // found, break
                    return getInterval(a1, a2, duration);
                }
            }
            
            // otherwise, advance the pointer which has a lower end time
            if(a1[1] < a2[1])
                p1++;
            else
                p2++;
        }
        
        return new ArrayList<>(); // return empty array
    }
}