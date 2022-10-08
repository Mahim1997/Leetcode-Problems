class Interval {
    int start;
    int end;
    
    public Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }
    
    // Guaranteed for any interval, start < end
    // Strictly `this` interval should END before other starts
    private boolean isDisjointLeft(Interval other) {
        return (other.start >= this.end);
    }
    
    public boolean isConflicting(Interval other) {
        return !(
            this.isDisjointLeft(other) || 
            other.isDisjointLeft(this)
        );
    }
}

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        // Sort ascending order with respect to start times
        if(o1.start == o2.start)
            return (o1.end - o2.end);
        return (o1.start - o2.start);
    }
}

class MyCalendar {

    // TreeSet/SortedSet O(log n) insert & query
    private TreeSet<Interval> treeSet;
    private Comparator comparator;
    
    public MyCalendar() {
        this.comparator = new IntervalComparator();
        this.treeSet = new TreeSet<>(this.comparator);
    }
    
    // Maintain a Binary Search Tree (TreeSet => RedBlack Tree)
    public boolean book(int start, int end) {
        Interval interval = new Interval(start, end);
        Interval ceil = this.treeSet.ceiling(interval);
        Interval floor = this.treeSet.floor(interval);
        
        if((ceil != null) && interval.isConflicting(ceil))
            return false;
        
        if((floor != null) && interval.isConflicting(floor))
            return false;
        
        // Add i.e. book it
        this.treeSet.add(interval);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */