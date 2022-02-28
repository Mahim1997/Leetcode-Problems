class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        return usingPriorityQueue(matrix, k);
        // return usingConstantMemory(matrix, k);
    }

    
    private int usingPriorityQueue(int[][] matrix, int k){
        // kth smallest ==> reverse logic, max heap
        PriorityQueue<Integer> pq;
        pq = new PriorityQueue<>((v1, v2) -> (v2 - v1));
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                int num = matrix[i][j];
                if(pq.size() < k){
                    pq.add(num);
                }
                else{
                    if(num < pq.peek()){
                        pq.remove();
                        pq.add(num);                        
                    }
                }
            }
        }
        
        return pq.peek();
    }
}