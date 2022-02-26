class Graph{
    private int numVertices;
    // Set because, parallel edges do not matter
    public List<Set<Integer>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjList.add(new HashSet<>());
        }
    }
    
    public void createAdjList(int[][] edges){
        // KEEP SELF LOOPS ? YES, for detection of cycle
        for(int[] edge: edges){
            int u = edge[0], v = edge[1];
            this.adjList.get(u).add(v);
        }
    }
    
    public void printGraph(){
        for(int v=0; v<this.numVertices; v++){
            System.out.println(v + ": " + this.adjList.get(v));
        }
    }
}

class Solution {
    // dfs style checking [false -> false for leading to destination]
    private boolean dfs(int source, int destination, 
                        Graph g, Set<Integer> visited){
        // visited set is a local reference
        
        // cycle checking
        if(visited.contains(source) == true)
            return false;
        
        // base cases
        int numChildren = g.adjList.get(source).size();
        if(numChildren == 0){
            // no neighbors, THIS should be destination
            return (source == destination);
        }
        
        // add to source locally
        visited.add(source);
        
        for(int child: g.adjList.get(source)){
            boolean childAns = dfs(child, destination, g, visited);
            if(childAns == false)
                return false;
        }
        
        
        // remove from source
        visited.remove(source);
        
        // otherwise, return true as it is possible
        return true;
    }
    
    public boolean leadsToDestination(int n, int[][] edges, 
                                      int source, int destination) {
        Graph g = new Graph(n);
        g.createAdjList(edges);
        
        // g.printGraph();
        
        boolean ans = dfs(source, destination, g, new HashSet<>());
        return ans;
    }
}