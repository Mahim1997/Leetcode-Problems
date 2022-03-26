class Solution {
    Pair<List<Integer>, List<Integer>> getCountsNumbers(int[] fruits){
        List<Integer> listNumbers = new ArrayList<>();
        List<Integer> listCounts = new ArrayList<>();
        
        int n = fruits.length;
        
        int numberSoFar = fruits[0];
        int countSoFar = 1;
        
        // start at idx = 1
        for(int i=1; i<n; i++){
            if(fruits[i] == fruits[i - 1]){
                // same as previous
                countSoFar++;
            }else{
                // not same as previous
                listNumbers.add(numberSoFar);
                listCounts.add(countSoFar);
                
                numberSoFar = fruits[i];
                countSoFar = 1;
            }
        }
        
        // add the last fruit's count
        // if(listNumbers.get(listNumbers.size() - 1) != fruits[n - 1]){
        listNumbers.add(numberSoFar);
        listCounts.add(countSoFar);
        // }
        
        Pair<List<Integer>, List<Integer>> ans = new Pair<>(listNumbers, listCounts);
        return ans;
    }
    
    // Signature
    public int totalFruit(int[] fruits) {
        // list of numbers, list of counts
        int n = fruits.length;
        
        // n == 1 edge case
        if(n <= 2) // can only pick THIS fruit [n == 2, 2 fruits]
            return n;
        
        Pair<List<Integer>, List<Integer>> countsAndNumbers = getCountsNumbers(fruits);
        
        // double sized
        
        // O(n) time
        List<Integer> numbers = countsAndNumbers.getKey();
        List<Integer> counts = countsAndNumbers.getValue();
        
        
        // Traversal: O(n) time
        Set<Integer> setSoFar = new HashSet<>(); // to ensure only two elements contained
        int size = numbers.size();
        
        int count = 0;
        // edge case: only one/two types of fruits
        if(size <= 2){
            for(int i=0; i<size; i++)
                count += counts.get(i);
            return count;
        }
        
        
        int bestCount = 0;
        
        
        for(int i=0; i<size; i++){
            setSoFar.add(numbers.get(i));    
            
            // edge case
            if(i <= 1){ // [0, 1] indices will get both
                count += counts.get(i);
                bestCount = Math.max(bestCount, count);
                continue;
            }
            
            // normal case
            if(setSoFar.size() > 2){
                count = 0; // reset counter
                
                // clear the set, add previous number, add current number
                setSoFar.clear();
                setSoFar.add(numbers.get(i - 1));
                setSoFar.add(numbers.get(i));

                count += counts.get(i - 1); // start at the previous index
            }
            
            // always CURRENT will be taken [if/else case]
            count += counts.get(i);
            bestCount = Math.max(bestCount, count);
        }
        
        
        return bestCount;
    }
}