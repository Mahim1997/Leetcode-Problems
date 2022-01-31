class Solution {
    
    static class DisjointSet{
        private int[] roots;
        private int[] ranks;
        
        public DisjointSet(int numVertices){
            this.roots = new int[numVertices];
            this.ranks = new int[numVertices];
            
            for(int i=0; i<numVertices; i++){
                this.roots[i] = i; // initially, it is the root
                this.ranks[i] = 1; // initially, only 1 element
            }
        }
        
        private void print(){
            for(int x: roots){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
        
        // Optimized find -> recursion + set
        private int find(int x){
            if(this.roots[x] == x){
                return x;
            }
            // recursion + set
            int ans = find(this.roots[x]);
            this.roots[x] = ans;
            return ans;
        }
        
        private int getRank(int x){
            return this.ranks[x];
        }
        
        // Union by ranks
        private void union(int x, int y){
            int rootX = find(x); int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            
            if(this.ranks[rootY] > this.ranks[rootX]){
                // Make sure bigger one gets new set
                this.roots[rootX] = rootY;
            }
            else if(this.ranks[rootX] > this.ranks[rootY]){
                this.roots[rootY] = rootX;
            }
            else{ // both are equal, place into anyone
                this.roots[rootY] = rootX;
                this.ranks[rootX]++;
            }
        }
        
        private boolean isConnected(int x, int y){
            return (find(x) == find(y));
        }
        
        private int getNumberOfUniqueRoots(){
            int count = 0;
            // index same as root, that is unique
            for(int i=0; i<this.roots.length; i++){
                if(i == this.roots[i])
                    count++;
            }
            return count;
        }
    }
    
    public int findCircleNum(int[][] isConnected) {
        if(isConnected.length == 1){
            return 1; // only one.
        }
        
        DisjointSet ds = new DisjointSet(isConnected.length);
        
        for(int i=0; i<isConnected.length; i++){
            for(int j=i+1; j<isConnected[i].length; j++){
                // 'i' ----- 'j' has an edge
                if(isConnected[i][j] == 1){
                    ds.union(i, j); // place as union
                }
            }
        }
        
        return ds.getNumberOfUniqueRoots();
    }
}