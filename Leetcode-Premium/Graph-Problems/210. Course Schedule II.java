class Graph{
    private int numVertices;
    public List<List<Integer>> adjListOutgoing;
    public List<List<Integer>> adjListIncoming;
    
    public Graph(int n){
        this.numVertices = n;
        
        this.adjListOutgoing = new ArrayList<>();
        this.adjListIncoming = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjListOutgoing.add(new ArrayList<>());
            this.adjListIncoming.add(new ArrayList<>());
        }
    }
    
    public void formEdges(int[][] prerequisites){
        // Can simply calculate the indegree array here
        for(int[] edge: prerequisites){
            int left = edge[0];
            int right = edge[1];
            
            this.adjListOutgoing.get(right).add(left);
            this.adjListIncoming.get(left).add(right);
        }
    }
    
    public List<Integer> topologicalSort(){
        List<Integer> list = new ArrayList<>();
        
        // Calculate indegrees initially
        int[] indegrees = new int[this.numVertices];
        
        for(int v=0; v<this.numVertices; v++){
            indegrees[v] = this.adjListIncoming.get(v).size();
        }
        
        Set<Integer> visited = new HashSet<>();
        
        while(list.size() < this.numVertices){
            int vertexWithZero = -1;
            
            // get the vertex that has no pre-requisites currently
            for(int v=0; v<this.numVertices; v++){
                if((indegrees[v] == 0) && (visited.contains(v) == false))
                    vertexWithZero = v;
            }
            
            if(vertexWithZero == -1){
                // return empty list AS it is not possible
                return new ArrayList<>();
            }
            
            // add the vertex with zero to the result list
            list.add(vertexWithZero);
            visited.add(vertexWithZero);
            
            // System.out.println("Adding vertexWithZero = " 
            //                    + vertexWithZero);
            
            // decrement all outgoing edges indegrees
            for(int vertex: this.adjListOutgoing.get(vertexWithZero)){
                indegrees[vertex]--;
            }
        }
        
        
        return list;
    }
}


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // adjList to store outgoing edges, to iterate them
        Graph g = new Graph(numCourses);
        
        // form edges [left, right] ==> right->left 
        // ==> adjList.get(right).add(left) // OUTGOING EDGES
        g.formEdges(prerequisites);
        
        // DEBUGGING
        
        // Topological Sort
        List<Integer> ordering = g.topologicalSort();
        
        // Convert to array
        int[] arr = new int[ordering.size()];
        for(int i=0; i<arr.length; i++){
            arr[i] = ordering.get(i);
        }
        return arr;
    }
}