class Solution {
    private Map<Integer, Integer> getCounts(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int num: arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }
    
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counts = getCounts(arr);
    
        // Counting sort, counts cannot be higher than arr.length
        // 1 <= arr.length <= 10^5
        // int MAX_LEN = 100000 + 1;
        int[] countIndices = new int[arr.length + 1];
        Arrays.fill(countIndices, 0);
        
        for(int count: counts.values()) {
            countIndices[count]++;
        }
        
        // System.out.println("counts: " + counts + ", counts.values: " + counts.values() + ", countIndices: " + Arrays.toString(countIndices));
        
        int numUnique = counts.keySet().size();
        int numRemoval = k;
        
        int countIdx = 0;
        while(true) {
            while(
                (countIdx < countIndices.length) &&             
                (countIndices[countIdx] == 0)
            ) {
                countIdx++;
            }
            // Found the smallest count
            if(countIdx >= countIndices.length)
                break;
            
            countIndices[countIdx]--;
            numRemoval -= countIdx;
            
            if(numRemoval >= 0) {
                numUnique--;
            }
            else {
                break;
            }
        }
        
        return numUnique;
    }
}