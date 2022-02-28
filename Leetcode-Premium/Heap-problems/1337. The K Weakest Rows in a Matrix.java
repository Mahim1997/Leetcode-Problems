class MyComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] arr1, int[] arr2){
        // 0: idx, 1: count
        if(arr1[1] < arr2[1]){
            return -1;
        }
        else if(arr1[1] > arr2[1]){
            return 1;
        }
        
        // counts same, only compare indices
        return (arr1[0] < arr2[0]) ? -1: 1;
    }
}

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int nRows = mat.length, nCols = mat[0].length;
        int[] rowSoldierCount = new int[nRows];
        
        // arr[0] -> index, arr[1] -> count
        PriorityQueue<int[]> pq;
        pq = new PriorityQueue<>(new MyComparator());
        
        for(int r=0; r<nRows; r++){
            int countForThisRow = getCountRow(mat, r);
            int[] arr = {r, countForThisRow};
            pq.add(arr);
            // rowSoldierCount[r] = getCountRow(mat, r);
        }
        
        // System.out.println("Printing pq: " + pq);
        
        int idx = 0;
        int[] ans = new int[k];
        while(!pq.isEmpty()){
            if(idx < k)
                ans[idx++] = pq.remove()[0];
            else
                break;
        }
        
        return ans;
    }
    
    private int getCountRow(int[][] mat, int row){
        int nCols = mat[0].length;
        int sum = 0;
        for(int c=0; c<nCols; c++){
            sum += mat[row][c];
        }
        return sum;
    }
}