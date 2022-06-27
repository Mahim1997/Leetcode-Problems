class Solution {
    public int minPartitions(String n) {
        int count = 0;
        char minSoFar = '0';
        
        for(char c: n.toCharArray()) {
            if(c <= minSoFar) continue; // ignore        
            count += (c - minSoFar);
            minSoFar = c;
        }
        
        return count;
    }
}