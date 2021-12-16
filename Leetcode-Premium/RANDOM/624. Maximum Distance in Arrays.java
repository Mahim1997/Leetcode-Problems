class Solution {
    public int maxDistance(List<List<Integer>> list) {
        // return bruteForce(list);
        return sortedTwice(list);
    
        // largest, smallest, secondLargest, secondSmallest.
    }
    
    static class Position{
        int idx, val;
        public Position(int i, int v){this.idx = i; this.val = v;}

        @Override
        public String toString(){
            return "Position i = " + idx + " , val = " + val;
        }
    }
    
    private int sortedTwice(List<List<Integer>>list){
        Position largest = new Position(-1, Integer.MIN_VALUE);
        Position secondLargest = new Position(-1, Integer.MIN_VALUE);
        
        Position smallest = new Position(-1, Integer.MAX_VALUE);
        Position secondSmallest = new Position(-1, Integer.MAX_VALUE);
        
        int numLists = list.size();
        
        List<Integer> currentList;
        
        int first, last, currentLen;
        
        for(int i=0; i<numLists; i++){
            currentList = list.get(i);
            currentLen = currentList.size();
            first = currentList.get(0);
            last = currentList.get(currentLen - 1);
            
            
            // smallest checking.
            if(first < smallest.val){
                secondSmallest = smallest;
                smallest = new Position(i, first);
            } else{
                if((first < secondSmallest.val) && 
                   (i != smallest.idx)){
                    secondSmallest = new Position(i, first);
                }
            }
            
            // largest checking.
            if(last > largest.val){
                secondLargest = largest;
                largest = new Position(i, last);
            } else{
                if((last > secondLargest.val)
                  && (i != largest.idx)){
                    secondLargest = new Position(i, last);
                }
            }
            
        }
        
        
        if(largest.idx != smallest.idx){
            return Math.abs(largest.val - smallest.val);
        }
        
        return Math.max(
            Math.abs(largest.val - secondSmallest.val),
            Math.abs(secondLargest.val - smallest.val)
        );
        
    }
    
    private int bruteForce(List<List<Integer>>list){
        int maxDist = Integer.MIN_VALUE, n = list.size();
        int firstListLen, secondListLen, currentPairDist;
        List<Integer> firstList, secondList;
        int numLists = list.size();
        for(int i=0; i<numLists-1; i++){
            for(int j=i+1; j<numLists; j++){            
                firstList = list.get(i);
                firstListLen = firstList.size();
                secondList = list.get(j);
                secondListLen = secondList.size();
                
                currentPairDist = Math.max(
                    Math.abs(firstList.get(0) - 
                             secondList.get(secondListLen - 1)),
                    Math.abs(secondList.get(0) - 
                             firstList.get(firstListLen - 1))
                );
                maxDist = Math.max(maxDist, currentPairDist);
            }
        }
        
        
        return maxDist;
    }
}