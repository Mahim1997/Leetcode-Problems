class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Map<Character, Integer> last_occ_idx = new HashMap<>();
        // int[] table = new int[s.length()];
        // table[0] = 1;
        
        int prev = 0;
        
        int[] arr = new int[128];
        for(int i=0; i<arr.length; i++) arr[i] = -999;
        
        if(s.length() == 0){
            return 0;
        }
        
        arr[s.charAt(0)] = 0;
        
        prev = 1;
        int max = 1;
        int curr = 0;
        
        for(int i=1; i<s.length(); i++){
            
            char c = s.charAt(i);
            
            // System.out.println(c);
            // int with_take = table[i-1] + 1;
            int with_take = prev + 1;
            if(arr[c] != -999){ // aage occur korse.
                int diff = i - arr[c]; // diff = current_idx - prev_occured_idx
                int actual_take = Math.min(with_take, diff);
                // table[i] = actual_take;
                curr = actual_take;
                // System.out.println("Actually taking for c = " + c + " , actual_take = " + actual_take);
            }else{
                // table[i] = with_take;
                curr = with_take;
            }
            arr[c] = i;
            
            if(curr > max){
                max = curr;
            }
            
            prev = curr;
        }
        // System.out.println(table);
        
        // int max = table[0];
        // for(int i=0; i<table.length; i++){
        //     max = Math.max(max, table[i]);
        //     // System.out.println("i = " + i + " , table[i] = " + table[i]);
        // }
        return max;
    }
}