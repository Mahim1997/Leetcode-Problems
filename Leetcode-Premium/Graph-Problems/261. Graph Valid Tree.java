class Solution {
    static class DisjointSet{
        private int []roots;
        private int []ranks;
        private int numVertices;
        
        private int counter;
        
        public DisjointSet(int n){
            this.numVertices = n;
            this.roots = new int[n];
            this.ranks = new int[n];
            
            for(int i=0; i<n; i++){
                this.roots[i] = i;
                this.ranks[i] = 1;
            }
            this.counter = n;
        }
        
        // Path Compression
        public int find(int x){
            if(this.roots[x] == x)
                return x;
            return this.roots[x] = find(this.roots[x]);
        }
        
        public boolean isConnected(int x, int y){
            return (find(x) == find(y));
        }
        
        // Optimized by rank
        public void union(int x, int y){
            int rootX = find(x); int rootY = find(y);
            if(rootX == rootY)
                return;
            
            // Make sure bigger one gets newer set
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
            
            this.counter--;
        }
        
        public int getCounter(){return this.counter;}
    }
    
    
    public boolean validTree(int n, int[][] edges) {
        // Should be connected
        // Should have only one root
        
        DisjointSet ds = new DisjointSet(n);
        for(int[] e: edges){
            int src = e[0]; int dest = e[1];

            // Make sure 'src' is always the smaller vertex
            if(src > dest){
                int temp = src;
                src = dest;
                dest = temp;
            }
            
            if(ds.isConnected(src, dest)){
                return false;
            }
            ds.union(src, dest);
        }
        
        return (ds.getCounter() == 1);
    }
}