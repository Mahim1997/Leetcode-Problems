class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        list.add(new ArrayList<>(temp));
        
        if(numRows == 1){
            return list;
        }
        temp.add(1);
        list.add(new ArrayList<>(temp));
        if(numRows == 2){
            return list;
        }
        
        for(int i=2; i<numRows; i++){
            List<Integer> prevList = list.get(i - 1);
            List<Integer> currList = new ArrayList<>();
            
            currList.add(1);    // leftmost 1
            int prevSize = prevList.size();
            
            for(int k=1; k<prevSize; k++){
                int topNum = prevList.get(k);
                int topLeftNum = prevList.get(k - 1);
                int sum = topNum + topLeftNum;
                currList.add(sum);
            }
            
            currList.add(1);    // rightmost 1
            list.add(new ArrayList<>(currList));
        }
        
        return list;
    }
}