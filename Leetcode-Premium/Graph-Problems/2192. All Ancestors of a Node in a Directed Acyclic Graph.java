class Graph{
    public int numVertices;
    
    // USE REVERSE GRAPH
    public List<Set<Integer>> adjList = new ArrayList<>();
    public int[] indegrees;
    
    public Graph(int n){
        this.numVertices = n;
        this.adjList = new ArrayList<>();
        this.indegrees = new int[n];
        
        for(int i=0; i<n; i++){
            this.adjList.add(new HashSet<>());
        }
        Arrays.fill(this.indegrees, 0);
    }

    public void createEdges(int[][] edges){
        for(int[] edge: edges){
            int u = edge[0], v = edge[1];
            this.adjList.get(u).add(v); // DIRECTED
            // indegrees array update
            this.indegrees[v]++; 
        }
    }
}

class Solution {    
    private void bfs(Graph g, List<Set<Integer>> ancestors){
        // indegree == 0
        Queue<Integer> queue = new LinkedList<>();
        
        int n = g.numVertices;
        // sweep
        for(int v=0; v<n; v++){
            if(g.indegrees[v] == 0)
                queue.add(v);
        }
        
        while(!queue.isEmpty()){
            int qSize = queue.size();
            
            for(int i=0; i<qSize; i++){
                int u = queue.remove();
                
                for(int child: g.adjList.get(u)){
                    // add ancestors of u as well
                    ancestors.get(child).addAll(ancestors.get(u));
                    // add THIS as ancestor
                    ancestors.get(child).add(u);
                    
                    // decrement indegrees
                    g.indegrees[child]--;
                    
                    // change here only
                    if(g.indegrees[child] == 0){
                        queue.add(child);
                    }
                }
            }
        }
    }
    
    private List<Set<Integer>> getAncestorsSet(Graph g){
        List<Set<Integer>> ancestors = new ArrayList<>();
        int n = g.numVertices;
        
        for(int i=0; i<n; i++){
            ancestors.add(new HashSet<>());
        }
        
		// topological sort: bfs style
        bfs(g, ancestors);
        
        return ancestors;
    }
    
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Graph g = new Graph(n);
        g.createEdges(edges);
        
        List<Set<Integer>> ancestors = getAncestorsSet(g);
        
        // convert to list and sort
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> list = new ArrayList<>();
            list.addAll(ancestors.get(i));
            list.sort((x1, x2) -> (x1 - x2)); // ascending
            // ans.get(i).add(list);
            ans.add(list);
        }
        
        return ans;
    }
}