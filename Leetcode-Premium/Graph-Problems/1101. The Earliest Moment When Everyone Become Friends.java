class DisjointSet{
    private int[] roots;
    private int[] ranks;
    
    // when numRoots == 1, all are connected
    private int numRoots;

    
    public boolean areAllConnected(){
        return (this.numRoots == 1);
    }
    
    public DisjointSet(int n){
        this.numRoots = n;
        
        this.roots = new int[n];
        this.ranks = new int[n];
        
        for(int i=0; i<this.roots.length; i++){
            this.roots[i] = i;
            this.ranks[i] = 1;
        }
    }
    
    // Path compressed
    public int find(int x){
        if(x == this.roots[x]){return x;}
        return this.roots[x] = find(this.roots[x]);
    }
    
    // By rank
    public void union(int x, int y){
        // FIND should be used to 'correct' the roots
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY){return;}
        
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
        
        this.numRoots--;
    }
    
    public boolean isConnected(int x, int y){
        return (find(x) == find(y));
    }
}

class Solution {

    public int earliestAcq(int[][] logs, int n) {
        // Sort by timestamp
        Arrays.sort(logs, (log1, log2) -> (log1[0] - log2[0]));
        
        DisjointSet ds = new DisjointSet(n);
        
        for(int[] log: logs){
            int ts = log[0]; int src = log[1]; int dest = log[2];
            
            // Make sure 'src' is smaller always
            // if(src > dest){
            //     int temp = src;
            //     src = dest;
            //     dest = temp;
            // }
        
            ds.union(src, dest);
            if(ds.areAllConnected() == true){
                return ts;
            }
        }
        
        return -1;
    }
}



