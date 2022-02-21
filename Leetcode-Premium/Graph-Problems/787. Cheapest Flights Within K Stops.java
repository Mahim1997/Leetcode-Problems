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
    private List<Edge> createEdges(int[][] flights){
        List<Edge> edges = new ArrayList<>();
        
        for(int[] flight: flights){
            edges.add(new Edge(flight[0], flight[1], flight[2]));
        }
        
        return edges;
    }
    
    private void runBellManFord(int[][] dp, List<Edge> edges, 
                                int src, int K){
        // 1st dimension is #stops, 2nd dimension is vertex
        int n = dp[0].length;
        
        // Base case
        for(int v=0; v<n; v++){
            dp[0][v] = Integer.MAX_VALUE;
        }
        dp[0][src] = 0;
        
        // Iterate upto K times
        for(int k=1; k<=K; k++){
            // Firstly copy previous row
            for(int v=0; v<n; v++){
                dp[k][v] = dp[k - 1][v];
            }
            
            // Relax each edge
            for(Edge e: edges){
                int u = e.source;
                int v = e.destination;
                int w = e.weight;
                
                // can't releax this edge AS path to 'u' not present
                if(dp[k-1][u] == Integer.MAX_VALUE)
                    continue;
                
                dp[k][v] = Math.min(
                    dp[k][v],
                    dp[k - 1][u] + w // w = weight(u, v)
                );
            }
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, 
                                 int src, int dst, int k) {
        // at most 'k' stops === at most 'k + 1' edges
        int K = k + 1;
        
        // Using Bellman Ford Naive algorithm
        int[][] dp = new int[K + 1][n];
        
        // Create edges
        List<Edge> edges = createEdges(flights);
        // System.out.println(edges);
        
        // Run BellmanFord algorithm
        runBellManFord(dp, edges, src, K);
        
        // Check for dp[last][dst]
        return (dp[K][dst] == Integer.MAX_VALUE) ? -1 : dp[K][dst];
    }
}


