class EdgePair{
    public int dest;
    public int weight;
    
    public EdgePair(int d, int w){
        this.dest = d;
        this.weight = w;
    }
    
    @Override
    public String toString(){
        return (dest + ":" + weight);
    }
}

class Graph{
    public int numVertices;
    public List<List<EdgePair>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjList.add(new ArrayList<>());
        }
    }
    
    // directed edge
    public void addEdge(int src, int dest, int w){
        this.adjList.get(src).add(new EdgePair(dest, w));
        //////// this.adjList.get(dest).add(new EdgePair(dest, w));
    }
    
    public void printGraph(){
        for(int i=0; i<this.numVertices; i++){
            System.out.println(i + ": " + this.adjList.get(i));
        }
    }
}


class Solution {
    private int[] runDijkstras(Graph g, int source){
        int n = g.numVertices;
        int[] distances = new int[n];
        int[] parents = new int[n];
        
        for(int i=0; i<n; i++){
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }
        
        // Ascending order ==> second element of array => [vertex, d(vertex)]
        PriorityQueue<int[]> pq;
        pq = new PriorityQueue<>((v1, v2) -> (v1[1] - v2[1]));
        
        // push source initially
        parents[source] = source;
        distances[source] = 0;
        pq.add(new int[]{source, distances[source]});
        
        Set<Integer> visited = new HashSet<>();
        
        while(!pq.isEmpty()){
            int[] top = pq.remove();
            
            int u = top[0];
            int distance_u = top[1];
            
            if(visited.contains(u))
                continue;
            
            visited.add(u);
            
            for(EdgePair childPair: g.adjList.get(u)){
                int v = childPair.dest;
                int w = childPair.weight; // weight(u, v)
                
                // alternative distance from source--v i.e. d[v]
                int altDist = distances[u] + w; // alt = d[u] + w(u, v)
                if(altDist < distances[v]){
                    distances[v] = altDist;
                    parents[v] = u; // travel from visiting 'u' first
                    
                    // push back new distance to priority queue
                    pq.add(new int[]{v, distances[v]});
                }
            }
        }
        
        return distances;
    }
    
    private void printArr(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        Graph g = new Graph(n);
        for(int[] time: times){
            // Convert 1-indexed to 0-indexed
            int src = time[0] - 1;
            int dest = time[1] - 1;
            int weight = time[2];
            
            g.addEdge(src, dest, weight);
        }
        
        // Run Dijkstra's algorithm for shortest path computation
        int source = k - 1;
        int[] distances = runDijkstras(g, source);
        
        // DEBUGGING
        // printArr(distances);
        
        int maxDist = Integer.MIN_VALUE;
        for(int dist: distances){
            maxDist = Math.max(maxDist, dist);
            
            // any vertex is unvisited
            if(dist == Integer.MAX_VALUE){
                return -1;
            }
        }
        return maxDist;
    }
}