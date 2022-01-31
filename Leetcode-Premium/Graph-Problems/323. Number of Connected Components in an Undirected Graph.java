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
    
    // Path Compressed via recursion
    public int find(int x){
        if(this.roots[x] == x)
            return x;
        return this.roots[x] = find(this.roots[x]);
    }
    
    // Rank optimized
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY){return;}
        
        // Make sure bigger one gets new set
        if(this.ranks[rootX] > this.ranks[rootY]){
            this.roots[rootY] = rootX;
        }
        else if(this.ranks[rootY] > this.ranks[rootX]){
            this.roots[rootX] = rootY;
        }
        else{
            this.roots[rootX] = rootY;
            this.ranks[rootY]++;
        }
    }
    
    public boolean isConnected(int x, int y){
        return (find(x) == find(y));
    }
    
    public int getNumberOfConnectedComponents(){
        int numRoots = 0;
        for(int i=0; i<this.roots.length; i++){
            if(this.roots[i] == i){
                numRoots++;
            }
        }
        return numRoots;
    }
}

class Solution {
    
    public int countComponents(int n, int[][] edges) {        
        DisjointSet ds = new DisjointSet(n);
            
        for(int[] e: edges){
            int src = e[0]; int dest = e[1];
            
            // Make sure 'src' is smaller
            if(src > dest){
                int temp = src;
                src = dest;
                dest = temp;
            }
            
            ds.union(src, dest);
        }
        
        return ds.getNumberOfConnectedComponents();
    }
}