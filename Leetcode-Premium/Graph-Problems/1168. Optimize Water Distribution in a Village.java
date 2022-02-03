// Kruskal's Algorithm can be used for getting MST

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
        if(x == this.roots[x]){return x;}
        return this.roots[x] = find(this.roots[x]);
    }
    
    public void union(int x, int y){
        int root1 = find(x);
        int root2 = find(y);
        
        if(root1 == root2){return;}
        
        if(this.ranks[root1] > this.ranks[root2]){
            this.roots[root2] = this.roots[root1];
        }
        else if(this.ranks[root2] > this.ranks[root1]){
            this.roots[root1] = this.roots[root2];
        }
        else{
            this.roots[root1] = this.roots[root2];
            this.ranks[root2]++;
        }
    }
}

class Value{
    int id;
    int weight;
    
    public Value(int i, int w){
        this.id = i; this.weight = w;
    }
    
    @Override
    public String toString(){
        return "{" + id + "," + weight + "}";
    }
}

class Graph{
    private int numVertices;
    private List<List<Value>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest, int weight){
        List<Value> list;
        
        list = this.adjList.get(src);
        list.add(new Value(dest, weight));
    
        list = this.adjList.get(dest);
        list.add(new Value(src, weight));        
    }
    
    public void printGraph(){
        for(int i=0; i<this.adjList.size(); i++){
            System.out.print(i + ": ");
            for(int j=0; j<this.adjList.get(i).size(); j++){
                System.out.print(this.adjList.get(i).get(j) + ", ");
            }
            System.out.println("");
        }
    }
}

class Edge{
    int src;
    int dest;
    int weight;
    
    public Edge(int s, int d, int w){
        // Always make sure 's' is smaller
        if(s > d){
            int temp = s;
            s = d;
            d = temp;
        }
        this.src = s; this.dest = d; this.weight = w;
    }
    
    @Override
    public String toString(){
        return "{" + src + "," + dest + "," + weight + "}"; 
    }
}

class Solution {

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        
        int numVertices = n + 1;
        
        // Graph g = new Graph(numVertices);
        
        List<Edge> edges = new ArrayList<>();
        // Add normal edges
        for(int[] pipe: pipes){
            int src = pipe[0] - 1;
            int dest = pipe[1] - 1;
            int weight = pipe[2];
            
            // g.addEdge(src, dest, weight);
            edges.add(new Edge(src, dest, weight));
        }
        
        // Add a new 'dummy' vertex, and add all the edges
        for(int i=0; i<wells.length; i++){
            int v = i;
            int w = wells[i];
            
            // g.addEdge(n, v, w);
            edges.add(new Edge(n, v, w));
        }
        
        // Sort the edges in ascending order 'wrt' weights
        edges.sort((e1, e2) -> (e1.weight - e2.weight));
        
        // for(Edge e: edges){
        //     System.out.println(e.toString());
        // }
        

        
        // Kruskal's algorithm
        DisjointSet ds = new DisjointSet(numVertices);
        
        List<Edge> edgesOfMST = new ArrayList<>();
        
        for(Edge e: edges){
            if(ds.find(e.src) != ds.find(e.dest)){
                ds.union(e.src, e.dest);
                edgesOfMST.add(new Edge(e.src, e.dest, e.weight));
            }
        }
        
        int cnt = 0;
        for(Edge e: edgesOfMST){
            cnt += e.weight;
        }
        
        return cnt;
    }
}


