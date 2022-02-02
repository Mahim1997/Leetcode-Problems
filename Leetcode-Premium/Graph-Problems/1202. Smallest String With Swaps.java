class DisjointSet{
    private int[] roots;
    private int[] ranks;
    
    public DisjointSet(int n){
        this.roots = new int[n];
        this.ranks = new int[n];
        
        for(int i=0; i<n; i++){
            this.roots[i] = i;
            this.ranks[i] = 1;
        }
    }
    
    public int find(int x){
        if(this.roots[x] == x)
            return x;
        
        return this.roots[x] = find(this.roots[x]);
    }
    
    public void union(int x, int y){
        int rootX = find(x); int rootY = find(y);
        if(rootX == rootY)
            return;
        
        // Assign to the bigger ranked root
        if(this.ranks[rootX] > this.ranks[rootY]){
            this.roots[rootY] = rootX;
        }
        else if(this.ranks[rootY] > this.ranks[rootX]){
            this.roots[rootX] = rootY;
        }
        else{ // default is rootX
            this.roots[rootY] = rootX;
            this.ranks[rootX]++;
        }
    }
}

class Solution {
    public String smallestStringWithSwaps(String s, 
                                          List<List<Integer>> pairs) {
        
        int strlen = s.length();
        DisjointSet ds = new DisjointSet(strlen);
        for(List<Integer> pair: pairs){
            ds.union(pair.get(0), pair.get(1));
        }
        
        // Now, find the list of chars, with common roots i.e. common indices
        
        // key: root, val: List of characters [s.charAt(idx)] with common root.
        Map<Integer, List<Character>> mapCommonChars = new HashMap<>();
        Map<Integer, List<Integer>> mapCommonIndices = new HashMap<>();
        
        // Append to common characters list.
        for(int i=0; i<strlen; i++){
            char c = s.charAt(i);
            int root = ds.find(i);
            // System.out.println("i = " + i + ", root = " + root + ", c = " + c);
            
            List<Character> commonChars = mapCommonChars.getOrDefault(root, 
                                              new ArrayList<>());
            commonChars.add(c);
            mapCommonChars.put(root, commonChars);
            
            List<Integer> commonIndices = mapCommonIndices.getOrDefault(root,
                                               new ArrayList<>());
            commonIndices.add(i);
            mapCommonIndices.put(root, commonIndices);
        }        
        
        // Sort the common characters.
        for(int key: mapCommonChars.keySet()){
            List<Character> val = mapCommonChars.get(key);
            Collections.sort(val);
        }
        
        // System.out.println("PRINTING ... ");
        // System.out.println(mapCommonChars);
        // System.out.println(mapCommonIndices);
        
        // now place into a char array, and return string
        char[] newSortedArray = new char[strlen];
        for(int key: mapCommonChars.keySet()){
            
            List<Character> commonChars = mapCommonChars.get(key);
            List<Integer> commonIndices = mapCommonIndices.get(key);
            
            for(int i=0; i<commonChars.size(); i++){            
                char c = commonChars.get(i);
                int idx = commonIndices.get(i);
                newSortedArray[idx] = c;
            }
        }
        
        return new String(newSortedArray);
    }
}