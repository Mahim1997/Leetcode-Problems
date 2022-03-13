class Solution {
    private Map<Character, Character> map;
    
    private char[] firstOptions = {'1', '6', '8', '9'};
    private char[] exploreOptions = {'0', '1', '6', '8', '9'};
    private char[] middleOptions = {'0', '1', '8'};
    
    private void initializeMap(){
        this.map = new HashMap<>();
        
        this.map.put('0', '0');
        this.map.put('1', '1');
        this.map.put('6', '9');
        this.map.put('8', '8');
        this.map.put('9', '6');
    }
    
    public List<String> findStrobogrammatic(int n) {
        List<String> list = new ArrayList<>();
        
        // edge case of n == 1, need to include '0' separately
        if(n == 1){
            for(char c: this.middleOptions){
                list.add("" + c);
            }
            return list;
        }
        
        this.initializeMap();
        
        backtrack(n, 0, "", list);
        return list;
    }

    // helper function
    private void backtrack(int n, int idx,
                          String temp, List<String> list){
        // edge cases of recursion
        if(idx == n){
            list.add(temp);
            return;
        }
        
        // 0th index ==> don't include zero
        if(idx == 0){
            for(char c: this.firstOptions){
                backtrack(n, idx+1, temp+c, list);
            }
            return;
        }
        
        // idx less than half n ==> explore choices
        int halfN = n/2;
        if(idx < halfN){
            for(char c: this.exploreOptions){
                backtrack(n, idx+1, temp+c, list);
            }
            return;
        }
        
        // odd n => middle index => special case
        if((n%2 == 1) && (idx == halfN)){
            for(char c: this.middleOptions){
                backtrack(n, idx+1, temp+c, list);
            }
            return;
        }
        
        // greater than half index ==> use reverse of prev
        int idxBeforeHalf = (n - 1) - idx;
        char c = temp.charAt(idxBeforeHalf);
        char reverse = this.map.get(c);
        
        backtrack(n, idx+1, temp+reverse, list);
        return;
    }

}











