class Solution {
    private List<Set<Integer>> adjList;
    private int numVertices;
    
    private int maxDistance;
    
    private void formEdges(int[][] edges){
        // this.degrees = new int[this.numVertices];
        // Arrays.fill(this.degrees, 0);
        
        // Initialize Adjacent List
        this.adjList = new ArrayList<>();
        for(int i=0; i<this.numVertices; i++){
            this.adjList.add(new HashSet<>());
        }
        
        this.maxDistance = Integer.MIN_VALUE;
        // Form edges
        for(int[] edge: edges){
            int u = edge[0], v = edge[1];
            this.adjList.get(u).add(v);
            this.adjList.get(v).add(u);
            
            // Recompute the maxDistance
            this.maxDistance = Math.max(maxDistance, this.adjList.get(u).size());
            this.maxDistance = Math.max(maxDistance, this.adjList.get(v).size());
        }
    }
    
    private List<Integer> runTopologicalElimination(){
        
        Queue<Integer> queue = new LinkedList<>();
        int n = this.numVertices;
        
        // push those with adjList.size() == 1
        for(int i=0; i<n; i++){
            if(this.adjList.get(i).size() == 1)
                queue.add(i);
        }
        
        Set<Integer> visited = new HashSet<>();
        while(n > 2){
            int qSize = queue.size();
            n -= qSize;
            
            for(int i=0; i<qSize; i++){
                int vertex = queue.remove();
                
                if(visited.contains(vertex)){
                    continue;
                }
                visited.add(vertex);
                
                for(int child: this.adjList.get(vertex)){
                    // remove vertex from child's adjList
                    this.adjList.get(child).remove(vertex);
                    
                    // add back to queue, if degree == 1
                    if(this.adjList.get(child).size() == 1){
                        queue.add(child);
                    }
                }
            }
            
        }
        
        
        List<Integer> list = new ArrayList<>();
        
        while(!queue.isEmpty()){
            list.add(queue.remove());
        }
        
        return list;
    }
    
    private void printGraph(){
        for(int i=0; i<this.numVertices; i++){
            System.out.println(i + ": " + this.adjList.get(i));
        }
    }
    
    // SIGNATURE METHOD
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Run BFS from every vertex ?? ==> TLE
        
        this.numVertices = n;
        
        // edge cases
        if(n == 1){
            return new ArrayList<>(List.of(0));
        }
        if(n == 2){
            return new ArrayList<>(List.of(0, 1));
        }
        
        // form edges
        this.formEdges(edges);
        
        // DEBUGGING
        // this.printGraph();
        
        // run topological elimination
        return this.runTopologicalElimination();
    }
}