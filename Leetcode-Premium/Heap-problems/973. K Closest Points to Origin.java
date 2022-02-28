class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Maintain a max priority queue, with 'k' elements.
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return getSquareDistance(o2) - getSquareDistance(o1);
            }
        }
        );
        
        for(int[] point: points){
            if(pq.size() == k){
                int[] currentMax = pq.peek();
                if(getSquareDistance(point) < getSquareDistance(currentMax)){
                    pq.remove(); // remove the 'max' element
                    pq.add(point);
                } // else DO NOT add the current point.
            }else{
                pq.add(point);
            }
        }
        
        int[][] ans = new int[k][2];
        int idx = 0;
        
        // while((idx < k) && (pq.isEmpty() == false)){
        while(pq.isEmpty() == false){
            int[] point = pq.remove();
            ans[idx][0] = point[0];
            ans[idx][1] = point[1];
            idx++;
        }
        
        return ans;
    }
    
    private int getSquareDistance(int[] p){
        int[] origin = new int[2];
        origin[0] = 0; origin[1] = 0;
        return getSquareDistance(p, origin);
    }
    
    private int getSquareDistance(int[] p1, int[] p2){
        return  ((p1[0]-p2[0])*(p1[0]-p2[0])) 
            +   ((p1[1]-p2[1])*(p1[1]-p2[1]));
    }
    
}