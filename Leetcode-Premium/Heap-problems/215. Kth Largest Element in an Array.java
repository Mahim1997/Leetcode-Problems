class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        // largest ---> use min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int num: nums){
            if(pq.size() < k){
                // simply add the number, if size is smaller than 'k'
                pq.add(num);
            }
            else{
                // adjust accordingly
                int minElement = pq.peek();
                if(num > minElement){
                    // only add, if the num is bigger than the MINIMUM in pq & remove
                    pq.remove();
                    pq.add(num);
                }
            }
        }
        
        // TOP of min heap will be the answer
        return pq.peek();
    }
}