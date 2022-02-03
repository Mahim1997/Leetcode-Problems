class Graph{
    private int numVertices;
    private List<List<Integer>> adjList;

    public Graph(int numVertices){
        this.numVertices = numVertices;
        this.adjList = new ArrayList<>();
        for(int i=0; i<numVertices; i++){
            this.adjList.add(new ArrayList<>());
        }
    }

    public void printGraph(){
        for(int i=0; i<this.adjList.size(); i++){
            System.out.print(i + ": ");
            for(int v: this.adjList.get(i)){
                System.out.print(v + " ");
            }
            System.out.println("");
        }
    }
    
    public void addEdge(int src, int dest){
        this.adjList.get(src).add(dest);
    }
    
    public boolean doesHaveCycle(){
        Set<Integer> whiteSet = new HashSet<>();
        Set<Integer> graySet = new HashSet<>();
        Set<Integer> blackSet = new HashSet<>();
        
        // initially, all vertices are unvisited
        for(int vertex=0; vertex<this.numVertices; vertex++){
            whiteSet.add(vertex);
        }
        
        while(!whiteSet.isEmpty()){
            int v = whiteSet.iterator().next();
            if(dfs(v, whiteSet, graySet, blackSet)){
                return true;
            }
        }
        
        return false;
    }
    
    private void changeSets(int v, Set<Integer> set1, Set<Integer> set2){
        set1.remove(v);
        set2.add(v);
    }
    
    // returns true only if cycle exists
    private boolean dfs(int vertex,
                       Set<Integer> whiteSet,
                       Set<Integer> graySet,
                       Set<Integer> blackSet){
        
        // System.out.println("Calling for vertex = " + vertex);
        // move from white to gray
        changeSets(vertex, whiteSet, graySet);
        
        // for each children
        for(int child: this.adjList.get(vertex)){
            
            // cycle exists if inside graySet
            if(graySet.contains(child) == true){
                return true;
            }
            
            // ignore if already visited completely before
            if(blackSet.contains(child) == true){
                continue;
            }
            
            
            // recursive call
            if(dfs(child, whiteSet, graySet, blackSet) == true){
                return true;
            }
        }
        
        // move from gray to black
        changeSets(vertex, graySet, blackSet);
        return false;
    }
}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        
        for(int[] pre: prerequisites){
            g.addEdge(pre[1], pre[0]);
        }
        
        // DEBUGGING
        // g.printGraph();
        
        return (g.doesHaveCycle() == false);
    }
}




