class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;
    private Set<Integer> restrictedNodes;

    public Graph(int n) {
        this.numVertices = n;
        this.restrictedNodes = new HashSet<>();
        
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdges(int[][] edges) {
        for(int[] edge: edges) {
            int u = edge[0], v = edge[1];
            this.adjList.get(u).add(v);
            this.adjList.get(v).add(u);
        }
    }
    
    public void addRestrictedNodes(int[] restricted) {
        for(int v: restricted) {
            this.restrictedNodes.add(v);
        }
    }
    
    public Set<Integer> findReachableNodes(int source) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Edge case: source node is restricted
        if(this.restrictedNodes.contains(source))
            return visited; // return empty hashset
        
        visited.add(source);
        queue.add(source);
        
        while(!queue.isEmpty()) {
            int top = queue.remove();
            
            for(int child: this.adjList.get(top)) {
                // ignore if in restricted set
                if(this.restrictedNodes.contains(child))
                    continue;
                
                // ignore if already visited
                if(visited.contains(child))
                    continue;
                
                // add to queue and 'visit' them
                visited.add(child);
                queue.add(child);
            }
        }
        
        return visited;
    }
}

class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Graph g = new Graph(n);
        g.addEdges(edges);
        g.addRestrictedNodes(restricted);
        
        Set<Integer> visitedNodes = g.findReachableNodes(0);
        return visitedNodes.size();
    }
}







