class Pair {
    long number;
    int index;
    
    public Pair(long num, int idx) {
        this.number = num;
        this.index = idx;
    }
    
    @Override
    public String toString() {
        return "[" + this.number + "," + this.index + "]";
    }
}

class PairComparator implements Comparator<Pair> {
    private boolean isMax;
    
    public PairComparator(boolean isMax) {
        this.isMax = isMax;    
    }
    
    @Override
    public int compare(Pair p1, Pair p2) {
        if(p1.number == p2.number) {
            return (p1.index - p2.index);
        }
        
        return this.isMax ? Long.compare(p2.number, p1.number) : Long.compare(p1.number, p2.number);
        
        // return this.isMax ? (int) (p2.number - p1.number) : (int) (p1.number - p2.number);
    }
}


class Solution {
    private void addNumber(
        long num, 
        int index,
        TreeSet<Pair> leftSet, 
        TreeSet<Pair> rightSet
    ) {
        int leftSize = leftSet.size(); 
        int rightSize = rightSet.size();
        Pair pair = new Pair(num, index);
        if(leftSize == 0) {
            leftSet.add(pair);
        }
        else {
            long leftPeek = leftSet.first().number;
            
            if(num > leftPeek) { // add to right set
                rightSet.add(pair);
            }
            else {
                leftSet.add(pair);
            }
        }
        
        adjustSizes(leftSet, rightSet);
    }
    
    private void adjustSizes(TreeSet<Pair> leftSet, TreeSet<Pair> rightSet) {
        int diff = leftSet.size() - rightSet.size();
        
        if(diff > 1) { // left set has more items than needed
            Pair leftPeek = leftSet.pollFirst();
            rightSet.add(leftPeek);
        }
        else if(diff < -1) {
            Pair rightPeek = rightSet.pollFirst();
            leftSet.add(rightPeek);
        }
    }
    
    private double getMedian(TreeSet<Pair> leftSet, TreeSet<Pair> rightSet) {
        int leftSize = leftSet.size(), rightSize = rightSet.size();
        if(leftSize == rightSize) { // even lengths, take half
            return 0.5 * (double) (
                leftSet.first().number + rightSet.first().number
            );
        }   
        else if(leftSize > rightSize) { // just take from left set
            return (double) leftSet.first().number;
        }
        else { // just take from right set
            return (double) rightSet.first().number;
        }
    }
    
    private void removeNumber(long number, int index, TreeSet<Pair> leftSet, TreeSet<Pair> rightSet) {
        Pair toRemove = new Pair(number, index);

        // Will be removed from ANY ONE SET
        leftSet.remove(toRemove);
        rightSet.remove(toRemove);
        
        // Need to adjust by sizes
        adjustSizes(leftSet, rightSet);
    }
    
    // API
    public double[] medianSlidingWindow(int[] nums, int k) {
        PairComparator minComp = new PairComparator(false);
        PairComparator maxComp = new PairComparator(true);

        TreeSet<Pair> leftSet = new TreeSet<>(maxComp);
        TreeSet<Pair> rightSet = new TreeSet<>(minComp);
        
        List<Double> list = new ArrayList<>();
        int n = nums.length;
        
        for(int i=0; i<k; i++) {
            addNumber((long) nums[i], i, leftSet, rightSet);    
        }
        
        int left = 0, right = k;
        while(right < n) {
            double median = getMedian(leftSet, rightSet);
            int prevNumber = nums[left];
            int nextNumber = nums[right];

            // System.out.println("For the config => leftSet: " + leftSet + ", rightSet: " + rightSet + ", median: " + median);
            
            removeNumber((long) prevNumber, left, leftSet, rightSet);
            left++;
            
            addNumber((long) nextNumber, right, leftSet, rightSet);
            right++;
            
            list.add(median);
            // if(right == n) {
            //     break;
            // }
        }
        
        // Add for the last
        list.add(getMedian(leftSet, rightSet));
        
        double[] ans = new double[list.size()];
        for(int j=0; j<ans.length; j++) {
            ans[j] = list.get(j);
        }
        
        return ans;
    }
}
