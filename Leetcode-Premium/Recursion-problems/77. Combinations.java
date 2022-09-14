class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ansList = new ArrayList<>();
        backtrack(ansList, new ArrayList<>(), 1, k, n);
        return ansList;
    }
    
    private void backtrack(
        List<List<Integer>> ansList,
        List<Integer> listSoFar,
        int startingNum,
        int remaining,
        int n
    ) {
        // base case
        if(remaining == 0) {
            ansList.add(new ArrayList<>(listSoFar));
            return;
        }
        
        // recursive cases
        for(int num=startingNum; num<=n; num++) {
            // add choice
            listSoFar.add(num);
            
            // recurse/backtrack
            backtrack(ansList, listSoFar, num+1, remaining-1, n);
            
            // remove choice
            listSoFar.remove(listSoFar.size()-1);
        }
    }
}

