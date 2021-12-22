class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        
        // add all elements check
        for(int x: arr){
            int _double = x<<1; // x*2
            if(set.contains(_double))
                return true;
            if(x%2 == 0) // even number
            {
                int half = x>>1; // x / 2
                if(set.contains(half))
                    return true;
            }
            set.add(x);
        }
        
        
        return false;
    }
}