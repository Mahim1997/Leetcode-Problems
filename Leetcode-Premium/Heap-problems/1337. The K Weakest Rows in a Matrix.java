class MyRow {
    public int index;
    public int numSoldiers;
    
    // Get the rightmost index of '1'
    private int binarySearch(int[] nums, int target){
        int low = 0, high = nums.length - 1, ans = -1;
        
        // edge case
        if(nums[high] == target)
            return high;
        
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid] == 1){
                // move to the right
                ans = mid;
                low = mid + 1;
            }
            else if(nums[mid] == 0){
                high = mid - 1;
            }
        }
        return ans;
    }
    
    public MyRow(int idx, int[] row){
        int idxLastOne = binarySearch(row, 1);
        this.numSoldiers = 1 + idxLastOne;
        
        this.index = idx;
    }
    
    @Override
    public String toString(){
        // return "index = " + index + ", numSoldiers = " + numSoldiers;
        return "(" + index + "," + numSoldiers + ")";
    }
}

class MyRowComparator implements Comparator<MyRow> {
    
    @Override
    public int compare(MyRow o1, MyRow o2){
        // Reverse comparison for reverse priority queue
        return -minimumComparison(o1, o2);
    }
    
    private int minimumComparison(MyRow o1, MyRow o2){
        // 1 means swap, -1/0 means correct position
        
        // correct positions
        if(o1.numSoldiers < o2.numSoldiers) // has smaller num of soldiers
            return -1;
        if((o1.numSoldiers == o2.numSoldiers) && (o1.index < o2.index))
            return -1;
        
        // o1 is STRONGER than o2, hence swap
        return 1;
    }
}

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        MyRowComparator comparator = new MyRowComparator();
        PriorityQueue<MyRow> pq = new PriorityQueue<>(comparator);
        
        int n = mat.length;
        for(int i=0; i<n; i++){
            MyRow newRow = new MyRow(i, mat[i]);
            // pq.add(newRow);
            
            // check for 'k' length
            if(pq.size() < k){
                pq.add(newRow);
            }
            else{
                MyRow topRow = pq.peek();
                
                // if need to swap i.e. BIGGER value is found, then ignore
                if(comparator.compare(topRow, newRow) == 1){
                    continue;
                }
                else{ // else, remove & add
                    pq.remove();
                    pq.add(newRow);
                }
            }
            
            // System.out.println("After i = " + i + ", pq => " + pq);
        }
        
        // fill up answer
        int[] ans = new int[k];
        int idx = k-1;
        while(idx >= 0){
            ans[idx--] = pq.remove().index;
        }
        
        return ans;
    }
}
                                                      
                                                      
