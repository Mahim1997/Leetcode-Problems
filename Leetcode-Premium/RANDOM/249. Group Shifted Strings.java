class Solution {
    // ALL LOWERCASE LETTERS
    
    private String getHashedStringOfCounts(String s){
        // one length -> all are same
        int len = s.length();
        StringBuilder bld = new StringBuilder();
        bld.append("$");
        if(len == 1){
            bld.append("SL"); // SL -> SINGLE_LETTER -> all are same
        }else{
            for(int i=1; i<len; i++){
                char charCurrent = s.charAt(i);
                char charPrevious = s.charAt(i - 1);
                // int diff1 = charCurrent - charPrevious;
                // int diff2 = charCurrent - charPrevious + 'a';
                // int diff = Math.min(diff1, diff2);
                int diff = charCurrent - charPrevious;
                
                if(diff < 0){
                    diff = 26 + diff;
                }
                
                bld.append(diff).append("$");
            }
        }
        
        // bld.append("$");
        return bld.toString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        // group by lengths
        // group by difference between successive chars
        List<List<String>> list = new ArrayList<>();
        
        // mapHashCountVsListStrings
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strings){
            // map.put(s, getHashedStringOfCounts(s));
            String hash = this.getHashedStringOfCounts(s);
            if(map.containsKey(hash) == false){
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(s);
            
            // System.out.println("s = " + s + ", hash = " + hash);
        }
        
        // Now recollect from the map.
        for(String key: map.keySet()){
            list.add(map.get(key));
        }
        
        return list;
    }
}