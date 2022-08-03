class Interval {
    int start;
    int end;
    
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString() {
        return "[" + this.start + "," + this.end + "]";
    }
    
    public static boolean areDisjoint(Interval o1, Interval o2) {
        if(o1.start < o2.start) {
            // should check if o2 starts AFTER o1 ends
            return (o2.start >= o1.end);
        }
        else if(o2.start < o1.start) {
            return (o1.start >= o2.end);
        }
        else { // can't start at the same time, conflict directly
            return false;
        }

    }
}

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        if(o1.start != o2.start) 
            return (o1.start - o2.start); // ascending order
        return (o1.end - o2.end);
    }
}

class MyCalendar {
    private IntervalComparator comparator;
    private TreeSet<Interval> treeSet;
    
    public MyCalendar() {
        this.comparator = new IntervalComparator();
        this.treeSet = new TreeSet<>(this.comparator);
    }
    
    // Total time: O(n * log n) OR O(log n) * 1000
    // Total space: O(n) [Worst case, store all]
    public boolean book(int start, int end) {
        Interval interval = new Interval(start, end);
        
        // O(log n) twice
        Interval closestUpper = this.treeSet.ceiling(interval);
        Interval closestLower = this.treeSet.floor(interval);
        
        // Early exits
        if((closestUpper != null) && 
           !Interval.areDisjoint(interval, closestUpper))
            return false;
        
        if((closestLower != null) && 
           !Interval.areDisjoint(interval, closestLower))
            return false;
        
        // O(log n) once
        // no conflicts with closest intervals
        this.treeSet.add(interval);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */