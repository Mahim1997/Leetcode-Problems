// Using Prim's Algorithm


class MyPair{
    public int destination;
    public int weight;
    
    public MyPair(int d, int w){
        this.destination = d;
        this.weight = w;
    }
}

class Graph{
    private int numVertices;
    public List<List<MyPair>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int u, int v, int w){
        this.adjList.get(u).add(new MyPair(v, w));
        this.adjList.get(v).add(new MyPair(u, w));
    }
    
    public int getMSTPrims(){
        
        // ascending order wrt weights
        PriorityQueue<MyPair> pq;
        pq = new PriorityQueue<>((p1, p2) -> (p1.weight - p2.weight));
        
        Set<Integer> visited = new HashSet<>();
        
        
        int cost = 0;
        
        // start with '0' vertex
        pq.add(new MyPair(0, 0));
        
        while(visited.size() < this.numVertices){
            MyPair top = pq.remove();
            int v = top.destination;
            int w = top.weight;
            
            if(visited.contains(v)){
                continue;
            }
            visited.add(v);
            
            cost += w;
            
            for(MyPair childPair: this.adjList.get(v)){
                pq.add(childPair);
            }
        }
        
        return cost;
    }
}

class Solution {
    private int getMHDist(int[] p1, int[] p2){
        return (Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]));
    }
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        Graph g = new Graph(n);
        
        // 0 to connect only one point
        if(points.length == 1){
            return 0;
        }
        
        // form edges
        for(int i=0; i<(n - 1); i++){
            for(int j=i+1; j<n; j++){
                int w = getMHDist(points[i], points[j]);
                g.addEdge(i, j, w);
            }
        }
        
        // run PRIM's algorithm
        return g.getMSTPrims();
    }
}


