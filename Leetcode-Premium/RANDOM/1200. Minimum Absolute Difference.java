class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        
        int minDiff = Integer.MAX_VALUE;
        
        for(int i=1; i<arr.length; i++){
            int prevIdx = i - 1;
            int diff = arr[i] - arr[prevIdx];
            
            if(diff == minDiff){
                list.add(Arrays.asList(arr[i-1], arr[i]));
            }
            else if(diff < minDiff){ // discard existing solutions
                list.clear();
                minDiff = Math.min(minDiff, diff);
                list.add(Arrays.asList(arr[i-1], arr[i]));
            }
            // else -> IGNORE
        }
        
        // int minDiff = getMinAbsDifference(arr);
        // for(int i=1; i<arr.length; i++){
        //     int diff = Math.abs(arr[i] - arr[i-1]);
        //     if(diff == minDiff){
        //         list.add(Arrays.asList(new Integer[]{arr[i-1], arr[i]}));
        //     }
        // }
        
        return list;
    }
    
    private int getMinAbsDifference(int[] arr){
        int minDiff = Integer.MAX_VALUE;
        for(int i=1; i<arr.length; i++){
            int prevIdx = i-1;
            int diff = Math.abs(arr[i] - arr[prevIdx]);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }
}