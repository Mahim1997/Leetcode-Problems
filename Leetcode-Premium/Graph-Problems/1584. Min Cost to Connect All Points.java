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
    
    // Finds 'root': Path compression
    public int find(int v){
        if(this.roots[v] == v)
            return v;
        return this.roots[v] = find(this.roots[v]);
    }
    
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY)
            return;
        
        if(this.ranks[rootX] > this.ranks[rootY]){
            // assign to rootX
            this.roots[rootY] = this.roots[rootX];
        }
        else if(this.ranks[rootX] < this.ranks[rootY]){
            this.roots[rootX] = this.roots[rootY];
        }
        else{
            this.roots[rootY] = this.roots[rootX];
            this.ranks[rootY]++;
        }
    }
    
    public boolean areConnected(int x, int y){
        return (find(x) == find(y));
    }
}

class Edge{
    public int source;
    public int destination;
    public int weight;
    
    public Edge(int s, int d, int w){
        this.source = s;
        this.destination = d;
        this.weight = w;
    }
    
    @Override
    public String toString(){
        return "(" + source + "," + destination + "): " + weight;
    }
}

class Solution {
    private int getManhattanDistance(int[] p1, int[] p2){
        // |x_i - x_j| + |y_i - y_j|
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
    
    private List<Edge> formEdges(int[][] points){
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<(points.length - 1); i++){
            for(int j=i+1; j<points.length; j++){
                int w = getManhattanDistance(points[i],
                                             points[j]);
                edges.add(new Edge(i, j, w));
                edges.add(new Edge(j, i, w));
            }            
        }
        return edges;
    }
    
    public int minCostConnectPoints(int[][] points) {
        // Graph -> vertex_i = points[i]
        // Edges -> Manhattan distance (Undirected)
        
        // already a tree
        if(points.length == 1)
            return 0;
        

        
        // Graph (Edges) formation
        List<Edge> edges = formEdges(points);
        
        // Sort the edges (according to weights, asc order)
        edges.sort((e1, e2) -> (e1.weight - e2.weight));
        
        // Kruskal's algorithm
        int minCost = 0;
        int edgesTaken = 0;
        int numEdges = edges.size();
        int numVertices = points.length;
            
        DisjointSet ds = new DisjointSet(numVertices);
        for(int i=0; i<numEdges; i++){
            Edge edge = edges.get(i);
            
            if(!ds.areConnected(edge.source, edge.destination)){
                ds.union(edge.source, edge.destination);
                edgesTaken++;
                minCost += edge.weight;
            }
            
            if(edgesTaken == numEdges)
                break;
        }
        
        return minCost;
    }
}